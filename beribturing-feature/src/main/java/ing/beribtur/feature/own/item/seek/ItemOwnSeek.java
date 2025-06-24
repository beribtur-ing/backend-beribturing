package ing.beribtur.feature.own.item.seek;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.feature.shared.action.AuthHelper;
import ing.beribtur.feature.shared.customstore.ProductCategoryCustomStore;
import ing.beribtur.feature.shared.customstore.ProductCustomStore;
import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import ing.beribtur.feature.shared.sdo.ProductRdo;
import ing.beribtur.feature.shared.sdo.ProductSearchQdo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemOwnSeek {
    //
    private final AuthHelper authHelper;
    private final ProductCategoryCustomStore productCategoryCustomStore;
    private final ProductCustomStore productCustomStore;

    // RDO methods for owner-specific queries
    public Page<ProductCategoryRdo> findProductCategoryRdos(String searchKeyword, Offset offset) {
        //
        return productCategoryCustomStore.findProductCategoryRdos(searchKeyword, offset, true); // Owners see only active categories
    }

    public ProductCategoryRdo findProductCategoryRdo(String categoryId) {
        //
        return productCategoryCustomStore.findProductCategoryRdo(categoryId, true); // Owners see only active categories
    }

    public Page<ProductRdo> findProductRdos(ProductSearchQdo qdo, Offset offset) {
        //
        String ownerId = authHelper.currentUserId(Role.ROLE_OWNER);
        qdo.setOwnerIds(List.of(ownerId));
        qdo.setActive(true); // Owners can only see active products
        return productCustomStore.findProductRdos(qdo, offset);
    }

    public ProductRdo findProductRdo(String productId) {
        //
        String ownerId = authHelper.currentUserId(Role.ROLE_OWNER);

        ProductRdo productRdo = productCustomStore.findProductRdo(productId);

        // Security check: ensure the product belongs to the owner
        if (productRdo != null && !ownerId.equals(productRdo.getProduct().getOwnerId())) {
            throw new AccessDeniedException("Forbidden: Access denied to product owned by another user");
        }

        return productRdo;
    }
}

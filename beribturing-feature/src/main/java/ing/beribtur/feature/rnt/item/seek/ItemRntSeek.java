package ing.beribtur.feature.rnt.item.seek;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.feature.shared.customstore.ProductCategoryCustomStore;
import ing.beribtur.feature.shared.customstore.ProductCustomStore;
import ing.beribtur.feature.shared.sdo.PopularProductRdo;
import ing.beribtur.feature.shared.sdo.ProductCategoryRdo;
import ing.beribtur.feature.shared.sdo.ProductRdo;
import ing.beribtur.feature.shared.sdo.ProductSearchQdo;
import ing.beribtur.proxy.minio.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemRntSeek {
    //
    private final ProductCategoryCustomStore productCategoryCustomStore;
    private final ProductCustomStore productCustomStore;
    private final MinioService minioService;

    // RDO methods for renter queries (can see all available products)
    public Page<ProductCategoryRdo> findProductCategoryRdos(String searchKeyword, Offset offset) {
        //
        return productCategoryCustomStore.findProductCategoryRdos(searchKeyword, offset, true); // Renters see only active categories
    }

    public ProductCategoryRdo findProductCategoryRdo(String categoryId) {
        //
        return productCategoryCustomStore.findProductCategoryRdo(categoryId, true); // Renters see only active categories
    }

    public Page<ProductRdo> findProductRdos(ProductSearchQdo qdo, Offset offset) {
        //
        qdo.setActive(true); // Renters can only see active products
        return productCustomStore.findProductRdos(qdo, offset);
    }

    public ProductRdo findProductRdo(String productId) {
        //
        ProductRdo productRdo = productCustomStore.findProductRdo(productId);

        // Renters can see any product (no ownership restrictions)
        // But we might want to filter for active/available products only
        if (productRdo != null && productRdo.getProduct() != null) {
            // Optional: Check if product has active variants
            boolean hasActiveVariants = productRdo.getVariantRdos() != null &&
                    productRdo.getVariantRdos().stream()
                            .anyMatch(variantRdo -> variantRdo.getVariant() != null && variantRdo.getVariant().isActive());

            if (!hasActiveVariants) {
                return null; // Don't show products without active variants to renters
            }
        }

        return productRdo;
    }

    public Page<PopularProductRdo> findPopularProductRdos(Integer maxCount, Offset offset) {
        //
        Page<PopularProductRdo> rawPage = productCustomStore.findPopularProductRdos(maxCount, offset);

        List<PopularProductRdo> enrichedProducts = rawPage.getContent().stream()
                .peek(this::enrichWithSignedUrl)
                .toList();

        return new PageImpl<>(enrichedProducts, rawPage.getPageable(), rawPage.getTotalElements());
    }

    private void enrichWithSignedUrl(PopularProductRdo product) {
        //
        String url = product.getUrl();
        if (url != null && !url.isBlank()) {
            try {
                String fileName = minioService.extractFileNameFromUrl(url);
                String signedUrl = minioService.generatePresignedUrl(fileName);
                product.setUrl(signedUrl);
            } catch (Exception ignored) {
                product.setUrl(null);
            }
        }
    }
}

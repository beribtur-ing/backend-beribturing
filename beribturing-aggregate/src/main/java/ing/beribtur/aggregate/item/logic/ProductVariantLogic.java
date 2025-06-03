package ing.beribtur.aggregate.item.logic;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.item.entity.sdo.ProductVariantCdo;
import ing.beribtur.aggregate.item.store.ProductVariantStore;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductVariantLogic {
    //
    private final ProductVariantStore productVariantStore;

    public String registerProductVariant(ProductVariantCdo productVariantCdo) {
        //
        ProductVariant productVariant = new ProductVariant(productVariantCdo);
        productVariantStore.create(productVariant);
        return productVariant.getId();
    }

    public List<String> registerProductVariants(List<ProductVariantCdo> productVariantCdos) {
        //
        return productVariantCdos.stream().map(this::registerProductVariant).collect(Collectors.toList());
    }

    public ProductVariant findProductVariant(String productVariantId) {
        //
        ProductVariant productVariant = productVariantStore.retrieve(productVariantId);
        if (productVariant == null) {
            throw new NoSuchElementException("ProductVariant id: " + productVariantId);
        }
        return productVariant;
    }

    public List<ProductVariant> findProductVariants(List<String> productVariantIds) {
        //
        return productVariantStore.retrieveAll(productVariantIds);
    }

    public List<ProductVariant> findProductVariants(Offset offset) {
        //
        return productVariantStore.retrieveList(offset);
    }

    public void modifyProductVariant(String productVariantId, NameValueList nameValues) {
        //
        ProductVariant productVariant = findProductVariant(productVariantId);
        productVariant.modify(nameValues);
        productVariantStore.update(productVariant);
    }

    public void modifyProductVariant(ProductVariant productVariant) {
        //
        ProductVariant oldProductVariant = findProductVariant(productVariant.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldProductVariant, productVariant);
        if (!nameValues.list().isEmpty()) {
            modifyProductVariant(productVariant.getId(), nameValues);
        }
    }

    public void removeProductVariant(String productVariantId) {
        //
        productVariantStore.delete(productVariantId);
    }

    public boolean existsProductVariant(String productVariantId) {
        //
        return productVariantStore.exists(productVariantId);
    }
}

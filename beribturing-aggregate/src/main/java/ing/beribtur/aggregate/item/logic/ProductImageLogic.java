package ing.beribtur.aggregate.item.logic;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.sdo.ProductImageCdo;
import ing.beribtur.aggregate.item.store.ProductImageStore;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductImageLogic {
    //
    private final ProductImageStore productImageStore;

    public String registerProductImage(ProductImageCdo productImageCdo) {
        //
        ProductImage productImage = new ProductImage(productImageCdo);
        productImageStore.create(productImage);
        return productImage.getId();
    }

    public List<String> registerProductImages(List<ProductImageCdo> productImageCdos) {
        //
        return productImageCdos.stream().map(this::registerProductImage).collect(Collectors.toList());
    }

    public ProductImage findProductImage(String productImageId) {
        //
        ProductImage productImage = productImageStore.retrieve(productImageId);
        if (productImage == null) {
            throw new NoSuchElementException("ProductImage id: " + productImageId);
        }
        return productImage;
    }

    public List<ProductImage> findProductImages(List<String> productImageIds) {
        //
        return productImageStore.retrieveAll(productImageIds);
    }

    public List<ProductImage> findProductImages(Offset offset) {
        //
        return productImageStore.retrieveList(offset);
    }

    public void modifyProductImage(String productImageId, NameValueList nameValues) {
        //
        ProductImage productImage = findProductImage(productImageId);
        productImage.modify(nameValues);
        productImageStore.update(productImage);
    }

    public void modifyProductImage(ProductImage productImage) {
        //
        ProductImage oldProductImage = findProductImage(productImage.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldProductImage, productImage);
        if (!nameValues.list().isEmpty()) {
            modifyProductImage(productImage.getId(), nameValues);
        }
    }

    public void removeProductImage(String productImageId) {
        //
        productImageStore.delete(productImageId);
    }

    public boolean existsProductImage(String productImageId) {
        //
        return productImageStore.exists(productImageId);
    }
}

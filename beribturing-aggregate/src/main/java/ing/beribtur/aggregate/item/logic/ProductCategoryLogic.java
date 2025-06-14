package ing.beribtur.aggregate.item.logic;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.accent.util.Entities;
import ing.beribtur.aggregate.item.entity.ProductCategory;
import ing.beribtur.aggregate.item.entity.sdo.ProductCategoryCdo;
import ing.beribtur.aggregate.item.store.ProductCategoryStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCategoryLogic {
    //
    private final ProductCategoryStore productCategoryStore;

    public String registerProductCategory(ProductCategoryCdo productCategoryCdo) {
        //
        ProductCategory productCategory = new ProductCategory(productCategoryCdo);
        productCategoryStore.create(productCategory);
        return productCategory.getId();
    }

    public List<String> registerProductCategories(List<ProductCategoryCdo> productCategoryCdos) {
        //
        return productCategoryCdos.stream().map(this::registerProductCategory).collect(Collectors.toList());
    }

    public ProductCategory findProductCategory(String productCategoryId) {
        //
        return productCategoryStore.retrieve(productCategoryId);
    }

    public List<ProductCategory> findProductCategories(List<String> productCategoryIds) {
        //
        return productCategoryStore.retrieveAll(productCategoryIds);
    }

    public List<ProductCategory> findProductCategories(Offset offset) {
        //
        return productCategoryStore.retrieveList(offset);
    }

    public void modifyProductCategory(String productCategoryId, NameValueList nameValues) {
        //
        ProductCategory productCategory = findProductCategory(productCategoryId);
        productCategory.modify(nameValues);
        productCategoryStore.update(productCategory);
    }

    public void modifyProductCategory(ProductCategory productCategory) {
        //
        ProductCategory oldProductCategory = findProductCategory(productCategory.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldProductCategory, productCategory);
        if (!nameValues.list().isEmpty()) {
            modifyProductCategory(productCategory.getId(), nameValues);
        }
    }

    public void removeProductCategory(String productCategoryId) {
        //
        productCategoryStore.delete(productCategoryId);
    }

    public boolean existsProductCategory(String productCategoryId) {
        //
        return productCategoryStore.exists(productCategoryId);
    }
}

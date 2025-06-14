package ing.beribtur.feature.shared.action;

import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.sdo.ProductImageCdo;
import ing.beribtur.aggregate.item.logic.ProductImageLogic;
import ing.beribtur.aggregate.item.logic.ProductVariantLogic;
import ing.beribtur.proxy.minio.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductImageHelper {
    //
    private final ProductVariantLogic productVariantLogic;
    private final ProductImageLogic productImageLogic;
    private final MinioService minioService;

    public void modifyVariantImageSequence(int imageSequence, String variantId) {
        //
        NameValueList nameValues = NameValueList.newInstance();
        nameValues.add(NameValue.of("imageSequence", String.valueOf(imageSequence)));
        productVariantLogic.modifyProductVariant(variantId, nameValues);
    }

    public int registerProductImages(List<MultipartFile> images, String variantId) throws Exception {
        //
        int imageSequence = 1;
        List<ProductImageCdo> imageCdos = new ArrayList<>();
        for (MultipartFile image : images) {
            String path = minioService.uploadFile(image);
            ProductImageCdo imageCdo = ProductImageCdo.builder()
                .variantId(variantId)
                .url(path)
                .order(imageSequence)
                .sequence(imageSequence++)
                .build();
            imageCdos.add(imageCdo);
        }
        productImageLogic.registerProductImages(imageCdos);
        return imageSequence;
    }

    public void removeProductImageFromStorage(ProductImage image) throws Exception {
        //
        minioService.deleteFile(image.getUrl());
        productImageLogic.removeProductImage(image.getId());
    }
}

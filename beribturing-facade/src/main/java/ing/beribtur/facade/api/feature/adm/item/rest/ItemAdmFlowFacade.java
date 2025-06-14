package ing.beribtur.facade.api.feature.adm.item.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.item.command.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemAdmFlowFacade {
    //
    CommandResponse<String> registerProductCategory(RegisterProductCategoryAdmCommand command);
    CommandResponse<String> modifyProductCategory(ModifyProductCategoryAdmCommand command);
    CommandResponse<String> removeProductCategory(RemoveProductCategoryAdmCommand command);

    CommandResponse<String> registerProduct(RegisterProductAdmCommand command);
    CommandResponse<String> modifyProduct(ModifyProductAdmCommand command);
    CommandResponse<String> removeProduct(RemoveProductAdmCommand command);

    CommandResponse<String> modifyProductImage(ModifyProductImageAdmCommand command);
    CommandResponse<String> removeProductImage(RemoveProductImageAdmCommand command);

    CommandResponse<String> registerProductVariant(RegisterProductVariantAdmCommand command,  List<MultipartFile> images) throws Exception;
    CommandResponse<String> modifyProductVariant(ModifyProductVariantAdmCommand command);
    CommandResponse<String> removeProductVariant(RemoveProductVariantAdmCommand command);
}

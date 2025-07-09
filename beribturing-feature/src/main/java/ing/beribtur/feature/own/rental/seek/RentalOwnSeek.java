package ing.beribtur.feature.own.rental.seek;

import ing.beribtur.accent.context.SpaceContext;
import ing.beribtur.accent.message.Offset;
import ing.beribtur.aggregate.account.entity.vo.Role;
import ing.beribtur.aggregate.item.entity.Product;
import ing.beribtur.aggregate.item.entity.ProductImage;
import ing.beribtur.aggregate.item.entity.ProductVariant;
import ing.beribtur.aggregate.item.logic.ProductImageLogic;
import ing.beribtur.aggregate.item.logic.ProductLogic;
import ing.beribtur.aggregate.item.logic.ProductVariantLogic;
import ing.beribtur.aggregate.rental.entity.ItemConditionCheck;
import ing.beribtur.aggregate.rental.entity.ItemConditionPhoto;
import ing.beribtur.aggregate.rental.entity.RentalRecord;
import ing.beribtur.aggregate.rental.entity.Reservation;
import ing.beribtur.aggregate.rental.entity.vo.RentalStatus;
import ing.beribtur.aggregate.rental.entity.vo.ReservationStatus;
import ing.beribtur.aggregate.rental.logic.ItemConditionCheckLogic;
import ing.beribtur.aggregate.rental.logic.ItemConditionPhotoLogic;
import ing.beribtur.aggregate.rental.logic.RentalRecordLogic;
import ing.beribtur.aggregate.rental.logic.ReservationLogic;
import ing.beribtur.aggregate.user.entity.Lendee;
import ing.beribtur.aggregate.user.entity.Lender;
import ing.beribtur.aggregate.user.logic.LendeeLogic;
import ing.beribtur.aggregate.user.logic.LenderLogic;
import ing.beribtur.feature.shared.action.AuthHelper;
import ing.beribtur.feature.shared.customstore.RentalRecordCustomStore;
import ing.beribtur.feature.shared.customstore.ReservationCustomStore;
import ing.beribtur.feature.shared.sdo.RentalRecordRdo;
import ing.beribtur.feature.shared.sdo.ReservationDetailRdo;
import ing.beribtur.feature.shared.sdo.ReservationRdo;
import ing.beribtur.proxy.minio.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalOwnSeek {
    //
    private final RentalRecordLogic rentalRecordLogic;
    private final ItemConditionCheckLogic itemConditionCheckLogic;
    private final ItemConditionPhotoLogic itemConditionPhotoLogic;
    private final RentalRecordCustomStore rentalRecordCustomStore;
    private final ReservationCustomStore reservationCustomStore;
    private final AuthHelper authHelper;
    private final LenderLogic lenderLogic;
    private final ReservationLogic reservationLogic;
    private final ProductVariantLogic productVariantLogic;
    private final ProductLogic productLogic;
    private final ProductImageLogic productImageLogic;
    private final LendeeLogic lendeeLogic;
    private final MinioService minioService;

    public RentalRecord findRentalRecordById(String rentalRecordId) {
        //
        return rentalRecordLogic.findRentalRecord(rentalRecordId);
    }

    public ItemConditionCheck findItemConditionCheckById(String itemConditionCheckId) {
        //
        return itemConditionCheckLogic.findItemConditionCheck(itemConditionCheckId);
    }

    public ItemConditionPhoto findItemConditionPhotoById(String itemConditionPhotoId) {
        //
        return itemConditionPhotoLogic.findItemConditionPhoto(itemConditionPhotoId);
    }

    public Page<ReservationRdo> findReservationRdos(ReservationStatus status, Offset offset) {
        //
        String ownerId = authHelper.currentUserId(Role.ROLE_OWNER);
        return reservationCustomStore.findReservationRdos(ownerId, status, offset);
    }

    public ReservationDetailRdo findReservationDetail(String reservationId) throws Exception {
        //
        Reservation reservation = reservationLogic.findReservation(reservationId);
        String variantId = reservation.getProductVariantId();
        ProductVariant variant = productVariantLogic.findProductVariant(variantId);
        Product product = productLogic.findProduct(variant.getProductId());
        List<ProductImage> images = productImageLogic.findProductImagesByVariantId(variantId);
        this.checkAndUpdateProductImageExpireDate(images);
        Lender owner = lenderLogic.findLender(reservation.getOwnerId());
        Lendee requester = lendeeLogic.findLendee(reservation.getRequesterId());

        return ReservationDetailRdo.builder()
            .reservation(reservation)
            .variant(variant)
            .product(product)
            .images(images)
            .owner(owner)
            .requester(requester)
            .build();
    }

    public Page<RentalRecordRdo> findRentalRecords(RentalStatus status, String searchKeyword, Offset offset) {
        //
        String username = SpaceContext.get().getUsername();
        Lender lender = lenderLogic.findByPhoneNumber(username);
        return rentalRecordCustomStore.findRentalRecordsByOwner(lender.getId(), status, searchKeyword, offset);
    }

    private void checkAndUpdateProductImageExpireDate(List<ProductImage> productImages) throws Exception {
        //
        for (ProductImage productImage : productImages) {
            if (productImage.getExpiresAt().isBefore(LocalDateTime.now())) {
                String newUrl = minioService.generatePresignedUrl(productImage.getFilename());
                productImage.setUrl(newUrl);
                productImage.setExpiresAt(productImage.getExpiresAt().plusDays(minioService.getPresignedExpirySeconds()));
                this.productImageLogic.modifyProductImage(productImage);
            }
        }
    }
}

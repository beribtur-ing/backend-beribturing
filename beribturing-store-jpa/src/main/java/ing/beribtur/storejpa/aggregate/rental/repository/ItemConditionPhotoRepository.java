package ing.beribtur.storejpa.aggregate.rental.repository;

import ing.beribtur.storejpa.aggregate.rental.jpo.ItemConditionPhotoJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemConditionPhotoRepository extends JpaRepository<ItemConditionPhotoJpo, String> {
    List<ItemConditionPhotoJpo> findByCheckId(String checkId);
    List<ItemConditionPhotoJpo> findByUrlContaining(String urlPattern);
}
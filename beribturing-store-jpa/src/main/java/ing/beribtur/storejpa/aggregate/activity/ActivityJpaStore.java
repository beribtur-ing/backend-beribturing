package ing.beribtur.storejpa.aggregate.activity;

import ing.beribtur.aggregate.activity.entity.Activity;
import ing.beribtur.aggregate.activity.entity.vo.ActivityType;
import ing.beribtur.aggregate.activity.store.ActivityStore;
import ing.beribtur.storejpa.aggregate.activity.jpo.ActivityJpo;
import ing.beribtur.storejpa.aggregate.activity.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ActivityJpaStore implements ActivityStore {
    //
    private final ActivityRepository activityRepository;

    @Override
    public void create(Activity activity) {
        ActivityJpo activityJpo = new ActivityJpo(activity);

        activityRepository.save(activityJpo);
        activity.setId(activityJpo.getId());
    }

    @Override
    public Activity retrieve(String id) {
        ActivityJpo activityJpo = activityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Activity not found: " + id));
        return activityJpo.toDomain();
    }

    @Override
    public List<Activity> retrieveAll(List<String> ids) {
        List<ActivityJpo> activityJpos = activityRepository.findAllById(ids);
        return activityJpos.stream()
                .map(ActivityJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Activity activity) {
        ActivityJpo existingJpo = activityRepository.findById(activity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Activity not found: " + activity.getId()));

        ActivityJpo updatedJpo = new ActivityJpo(activity);
        updatedJpo.setEntityVersion(existingJpo.getEntityVersion());
        updatedJpo.setRegisteredBy(existingJpo.getRegisteredBy());
        updatedJpo.setRegisteredOn(existingJpo.getRegisteredOn());

        activityRepository.save(updatedJpo);
    }

    @Override
    public void delete(String id) {
        if (!activityRepository.existsById(id)) {
            throw new IllegalArgumentException("Activity not found: " + id);
        }
        activityRepository.deleteById(id);
    }

    @Override
    public List<Activity> findByUserId(String userId) {
        List<ActivityJpo> activityJpos = activityRepository.findByUserId(userId);
        return activityJpos.stream()
                .map(ActivityJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> findByUserIdAndType(String userId, ActivityType type) {
        List<ActivityJpo> activityJpos = activityRepository.findByUserIdAndType(userId, type.name());
        return activityJpos.stream()
                .map(ActivityJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> findByRelatedEntityId(String relatedEntityId) {
        List<ActivityJpo> activityJpos = activityRepository.findByRelatedEntityId(relatedEntityId);
        return activityJpos.stream()
                .map(ActivityJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> findLatestActivitiesByUserId(String userId, Integer limit) {
        //
        List<ActivityJpo> activityJpos = activityRepository.findLatestActivitiesByUserId(userId, PageRequest.of(0, limit));
        return activityJpos.stream()
                .map(ActivityJpo::toDomain)
                .collect(Collectors.toList());
    }
}

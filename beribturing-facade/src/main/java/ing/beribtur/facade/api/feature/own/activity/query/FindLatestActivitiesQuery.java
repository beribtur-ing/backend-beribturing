package ing.beribtur.facade.api.feature.own.activity.query;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class FindLatestActivitiesQuery {
    //
    private Integer limit;
}

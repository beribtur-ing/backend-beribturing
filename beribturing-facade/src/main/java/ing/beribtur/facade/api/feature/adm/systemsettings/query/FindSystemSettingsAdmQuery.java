package ing.beribtur.facade.api.feature.adm.systemsettings.query;

import ing.beribtur.accent.message.QueryRequest;
import ing.beribtur.aggregate.account.entity.SystemSettings;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindSystemSettingsAdmQuery extends QueryRequest<SystemSettings> {
    //

    public void validate() {
        //
    }
}

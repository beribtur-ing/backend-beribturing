package ing.beribtur.facade.api.feature.rnt.review.command;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class ModifyReviewRntCommand extends CommandRequest<String> {
    //
    private String reviewId;
    private NameValueList nameValueList;

    public void validate() {
        //
        Assert.hasText(reviewId, "'reviewId' is required.");
        Assert.notNull(nameValueList, "'nameValueList' is required.");
    }
}

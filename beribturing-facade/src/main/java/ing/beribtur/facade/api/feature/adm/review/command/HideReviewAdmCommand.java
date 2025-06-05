package ing.beribtur.facade.api.feature.adm.review.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class HideReviewAdmCommand extends CommandRequest<String> {
    //
    private String reviewId;

    public void validate() {
        //
        Assert.notNull(reviewId, "'reviewId' is required.");
    }
}

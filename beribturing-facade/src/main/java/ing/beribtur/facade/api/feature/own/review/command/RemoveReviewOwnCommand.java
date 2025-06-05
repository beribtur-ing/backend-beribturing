package ing.beribtur.facade.api.feature.own.review.command;

import ing.beribtur.accent.message.CommandRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;


@Getter
@Setter
public class RemoveReviewOwnCommand extends  CommandRequest<String> {
    //
    private String reviewId;

    public void validate() {
        //
        Assert.hasText(reviewId, "'reviewId' is required.");
    }
}

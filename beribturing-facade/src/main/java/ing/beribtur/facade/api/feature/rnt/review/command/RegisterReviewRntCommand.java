package ing.beribtur.facade.api.feature.rnt.review.command;

import ing.beribtur.accent.message.CommandRequest;
import ing.beribtur.aggregate.review.entity.sdo.ReviewCdo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
public class RegisterReviewRntCommand extends CommandRequest<String> {
    //
    private ReviewCdo reviewCdo;

    public void validate() {
        //
        Assert.notNull(reviewCdo, "'reviewCdo' is required.");
    }
}

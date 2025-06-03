package ing.beribtur.facade.api.feature.rnt.review.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.rnt.review.command.ModifyReviewRntCommand;
import ing.beribtur.facade.api.feature.rnt.review.command.RegisterReviewRntCommand;
import ing.beribtur.facade.api.feature.rnt.review.command.RemoveReviewRntCommand;

public interface ReviewRntFlowFacade {
    //
    CommandResponse<String> registerReview(RegisterReviewRntCommand command);
    CommandResponse<String> modifyReview(ModifyReviewRntCommand command);
    CommandResponse<String> removeReview(RemoveReviewRntCommand command);
}

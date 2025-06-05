package ing.beribtur.facade.api.feature.own.review.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.own.review.command.ModifyReviewOwnCommand;
import ing.beribtur.facade.api.feature.own.review.command.RegisterReviewOwnCommand;
import ing.beribtur.facade.api.feature.own.review.command.RemoveReviewOwnCommand;

public interface ReviewOwnFlowFacade {
    //
    CommandResponse<String> registerReview(RegisterReviewOwnCommand command);
    CommandResponse<String> modifyReview(ModifyReviewOwnCommand command);
    CommandResponse<String> removeReview(RemoveReviewOwnCommand command);
}

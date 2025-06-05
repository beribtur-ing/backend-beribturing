package ing.beribtur.facade.api.feature.adm.review.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.review.command.HideReviewAdmCommand;

public interface ReviewAdmFlowFacade {
    //
    CommandResponse<String> hideReview(HideReviewAdmCommand command);
}

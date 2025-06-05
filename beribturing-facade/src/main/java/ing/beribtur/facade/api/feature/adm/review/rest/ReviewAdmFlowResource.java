package ing.beribtur.facade.api.feature.adm.review.rest;

import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.facade.api.feature.adm.review.command.HideReviewAdmCommand;
import ing.beribtur.feature.adm.review.flow.ReviewAdmFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/admin/review")
public class ReviewAdmFlowResource implements ReviewAdmFlowFacade {
    //
    private final ReviewAdmFlow reviewAdmFlow;

    @Override
    @PostMapping("/hide-review/command")
    public CommandResponse<String> hideReview(HideReviewAdmCommand command) {
        //
        command.validate();
        String reviewId = command.getReviewId();
        String entityId = reviewAdmFlow.hideReview(reviewId);
        return new CommandResponse<>(entityId);
    }
}


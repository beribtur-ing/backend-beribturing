package ing.beribtur.facade.api.feature.own.review.rest;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.review.entity.sdo.ReviewCdo;
import ing.beribtur.facade.api.feature.own.review.command.ModifyReviewOwnCommand;
import ing.beribtur.facade.api.feature.own.review.command.RegisterReviewOwnCommand;
import ing.beribtur.facade.api.feature.own.review.command.RemoveReviewOwnCommand;
import ing.beribtur.feature.own.review.flow.ReviewOwnFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/owner/review")
public class ReviewOwnFlowResource implements ReviewOwnFlowFacade {
    //
    private final ReviewOwnFlow reviewOwnFlow;

    @Override
    @PostMapping("/register-review/command")
    public CommandResponse<String> registerReview(@RequestBody RegisterReviewOwnCommand command) {
        //
        command.validate();
        ReviewCdo reviewCdo = command.getReviewCdo();
        String entityId = reviewOwnFlow.registerReview(reviewCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-review/command")
    public CommandResponse<String> modifyReview(@RequestBody ModifyReviewOwnCommand command) {
        //
        command.validate();
        String reviewId = command.getReviewId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = reviewOwnFlow.modifyReview(reviewId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-review/command")
    public CommandResponse<String> removeReview(@RequestBody RemoveReviewOwnCommand command) {
        //
        command.validate();
        String reviewId = command.getReviewId();
        String entityId = reviewOwnFlow.removeReview(reviewId);
        return new CommandResponse<>(entityId);
    }
}


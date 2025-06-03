package ing.beribtur.facade.api.feature.rnt.review.rest;

import ing.beribtur.accent.domain.NameValueList;
import ing.beribtur.accent.message.CommandResponse;
import ing.beribtur.aggregate.review.entity.sdo.ReviewCdo;
import ing.beribtur.facade.api.feature.rnt.review.command.ModifyReviewRntCommand;
import ing.beribtur.facade.api.feature.rnt.review.command.RegisterReviewRntCommand;
import ing.beribtur.facade.api.feature.rnt.review.command.RemoveReviewRntCommand;
import ing.beribtur.feature.rnt.review.flow.ReviewRntFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feature/renter/review")
public class ReviewRntFlowResource implements ReviewRntFlowFacade {
    //
    private final ReviewRntFlow reviewRntFlow;

    @Override
    @PostMapping("/register-review/command")
    public CommandResponse<String> registerReview(@RequestBody RegisterReviewRntCommand command) {
        //
        command.validate();
        ReviewCdo reviewCdo = command.getReviewCdo();
        String entityId = reviewRntFlow.registerReview(reviewCdo);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/modify-review/command")
    public CommandResponse<String> modifyReview(@RequestBody ModifyReviewRntCommand command) {
        //
        command.validate();
        String reviewId = command.getReviewId();
        NameValueList nameValueList = command.getNameValueList();
        String entityId = reviewRntFlow.modifyReview(reviewId, nameValueList);
        return new CommandResponse<>(entityId);
    }

    @Override
    @PostMapping("/remove-review/command")
    public CommandResponse<String> removeReview(@RequestBody RemoveReviewRntCommand command) {
        //
        command.validate();
        String reviewId = command.getReviewId();
        String entityId = reviewRntFlow.removeReview(reviewId);
        return new CommandResponse<>(entityId);
    }
}


package ing.beribtur.aggregate.review.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCdo extends CreationDataObject {
    //
    private String reviewerId;
    private int rating;
    private String comment;
    private String recordId;

    public String genId() {
        //
        return super.genId();
    }
}

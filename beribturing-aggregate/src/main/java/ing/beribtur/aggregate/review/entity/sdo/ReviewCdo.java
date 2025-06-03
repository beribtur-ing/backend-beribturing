package ing.beribtur.aggregate.review.entity.sdo;

import ing.beribtur.accent.domain.CreationDataObject;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

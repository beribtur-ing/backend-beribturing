package ing.beribtur.facade.api.feature.rnt.item.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindProductCategoryRntQuery {
    //
    private String categoryId;

    public void validate() {
        //
        Assert.hasText(categoryId, "'categoryId' is required.");
    }
}
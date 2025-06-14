package ing.beribtur.facade.api.feature.own.item.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindProductCategoryOwnQuery {
    //
    private String categoryId;

    public void validate() {
        //
        Assert.hasText(categoryId, "'categoryId' is required.");
    }
}
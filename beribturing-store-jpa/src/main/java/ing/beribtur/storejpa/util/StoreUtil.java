package ing.beribtur.storejpa.util;

import ing.beribtur.accent.message.Offset;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class StoreUtil {
    //
    public static Pageable genPageable(Offset offset) {
        //
        if (offset == null) {
            throw new IllegalArgumentException("Offset can't be null");
        }
        if (offset.page() < 0) {
            return Pageable.unpaged();
        }
        return PageRequest.of(offset.page(), offset.limit());
    }
}

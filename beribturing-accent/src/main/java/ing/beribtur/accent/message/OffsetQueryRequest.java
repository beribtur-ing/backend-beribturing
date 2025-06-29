/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
 */

package ing.beribtur.accent.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class OffsetQueryRequest<T> extends QueryRequest<T> {
    //
    protected OffsetQueryRequest() {
        //
        super();
    }
}

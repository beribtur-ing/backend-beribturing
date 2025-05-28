package ing.beribtur.accent.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FailureResponse {
    //
    private boolean requestFailed;
    private FailureMessage failureMessage;

    public FailureResponse(FailureMessage failureMessage) {
        //
        this.requestFailed = true;
        this.failureMessage = failureMessage;
    }
}


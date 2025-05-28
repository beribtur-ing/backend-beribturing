package ing.beribtur.accent.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FailureMessage {
    //
    private String exceptionName;
    private String exceptionMessage;
    private String exceptionCode;

    public  FailureMessage(String exceptionName, String exceptionMessage, String exceptionCode) {
        this.exceptionName = exceptionName;
        this.exceptionMessage = exceptionMessage;
        this.exceptionCode = exceptionCode;
    }

    public  FailureMessage(String exceptionMessage) {
        //
        this.exceptionMessage = exceptionMessage;
    }

    public static FailureMessage build(String exceptionName, String exceptionMessage, String exceptionCode) {
        //
        return new FailureMessage(exceptionName, exceptionMessage, exceptionCode);
    }
}

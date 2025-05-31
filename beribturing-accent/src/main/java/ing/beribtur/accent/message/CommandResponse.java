package ing.beribtur.accent.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommandResponse<T> {
    //
    private T response;

    public CommandResponse(T response) {
        //
        this.response = response;
    }
}

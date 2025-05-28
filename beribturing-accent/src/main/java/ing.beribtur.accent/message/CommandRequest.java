package ing.beribtur.accent.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CommandRequest<T> {
    //
    private CommandResponse<T> response;

    public CommandResponse<T> getResponse() {
        //
        if (this.response == null) {
            this.response = new CommandResponse<>();
        }
        return this.response;
    }

    @JsonSetter
    @JsonProperty
    public void setResponse(CommandResponse<T> response) {
        //
        this.response = response;
    }
}

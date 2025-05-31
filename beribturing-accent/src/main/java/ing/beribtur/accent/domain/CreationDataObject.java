package ing.beribtur.accent.domain;

import java.util.UUID;

public class CreationDataObject {
    public String genId() {
        return UUID.randomUUID().toString();
    }
}

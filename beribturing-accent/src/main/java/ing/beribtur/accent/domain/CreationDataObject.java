package ing.beribtur.accent.domain;

import ing.beribtur.accent.util.JsonSerializable;

import java.util.UUID;

public class CreationDataObject implements JsonSerializable {
    public String genId() {
        return UUID.randomUUID().toString();
    }
}

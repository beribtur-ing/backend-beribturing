package ing.beribtur.accent.util;

import ing.beribtur.accent.domain.DomainEntity;
import ing.beribtur.accent.domain.NameValue;
import ing.beribtur.accent.domain.NameValueList;
import java.util.Objects;
import java.util.Set;

public class Entities {
    //
    public static NameValueList getModifiedNameValues(DomainEntity oldEntity, DomainEntity newEntity) {
        Set<String> updatableAttributes = getUpdatableAttributes(oldEntity);
        NameValueList nameValues = NameValueList.newInstance();
        updatableAttributes.forEach((attribute) -> {
            Object oldValue = Beans.readValue(oldEntity, attribute);
            Object newValue = Beans.readValue(newEntity, attribute);
            if (!Objects.deepEquals(oldValue, newValue)) {
                if (isCompositeType(oldEntity, attribute)) {
                    nameValues.add(new NameValue(attribute, newValue == null ? null : JsonUtil.toJson(newValue)));
                } else {
                    nameValues.add(new NameValue(attribute, newValue == null ? null : newValue.toString()));
                }
            }
        });
        return nameValues;
    }

    private static Set<String> getUpdatableAttributes(DomainEntity entity) {
        return entity == null ? Set.of() : Beans.getProperties(entity.getClass());
    }

    private static boolean isCompositeType(Object bean, String property) {
        return false; // Simplified - assuming no composite types
    }

    private Entities() {
    }
}

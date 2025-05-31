package ing.beribtur.accent.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

public class AssertUtil {
    //
    public static void assertAllFieldsNonNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object is null");
        }
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) {
                    throw new IllegalArgumentException("Field '" + field.getName() + "' is null");
                }
                if (field.get(obj).getClass() == String.class && !StringUtils.hasLength((String) field.get(obj))) {
                    throw new IllegalArgumentException("Field '" + field.getName() + "' is empty");
                }
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Cannot access field: " + field.getName(), e);
            }
        }
    }
}

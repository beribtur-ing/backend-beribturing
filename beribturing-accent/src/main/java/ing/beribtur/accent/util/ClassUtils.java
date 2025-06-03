package ing.beribtur.accent.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Map;

public class ClassUtils {
    private static final Map<Class<?>, Class<?>> WRAPPER_TYPE_MAP;

    public static boolean isPrimitiveType(Class clazz) {
        return clazz == null || WRAPPER_TYPE_MAP.containsKey(clazz);
    }

    public static boolean isCompositeType(Class clazz) {
        return clazz != null && !isPrimitiveType(clazz) && !clazz.isEnum() && !String.class.equals(clazz) && !Timestamp.class.equals(clazz) && !LocalDate.class.equals(clazz) && !LocalTime.class.equals(clazz) && !LocalDateTime.class.equals(clazz) && !OffsetDateTime.class.equals(clazz);
    }

    private ClassUtils() {
    }

    static {
        WRAPPER_TYPE_MAP = Map.of(Integer.class, Integer.TYPE, Byte.class, Byte.TYPE, Character.class, Character.TYPE, Boolean.class, Boolean.TYPE, Double.class, Double.TYPE, Float.class, Float.TYPE, Long.class, Long.TYPE, Short.class, Short.TYPE, Void.class, Void.TYPE);
    }
}

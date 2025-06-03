package ing.beribtur.accent.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Beans {
    public static boolean hasGetter(Object bean, String property) {
        return getGetter(bean, property) != null;
    }

    public static boolean hasSetter(Object bean, String property) {
        return getSetter(bean, property) != null;
    }

    public static Object readValue(Object bean, String property) {
        if (hasGetter(bean, property)) {
            Method getter = getGetter(bean, property);

            try {
                if (getter != null) {
                    return getter.invoke(bean);
                }
            } catch (Exception var4) {
            }

            return null;
        } else {
            throw new IllegalArgumentException("No such property on bean, property = " + property);
        }
    }

    public static Object writeValue(Object bean, String property, Object... values) {
        if (hasSetter(bean, property)) {
            Method setter = getSetter(bean, property);

            try {
                if (setter != null) {
                    return setter.invoke(bean, values);
                }
            } catch (Exception var5) {
            }

            return null;
        } else {
            throw new IllegalArgumentException("No such property on bean, property = " + property);
        }
    }

    private static Method getGetter(Object bean, String property) {
        Method[] methods = bean.getClass().getMethods();
        Method getter = null;

        for(Method method : methods) {
            String getterMethodName = String.format("get%s%s", property.substring(0, 1).toUpperCase(), property.substring(1));
            String getterMethodNameForBoolean = String.format("is%s%s", property.substring(0, 1).toUpperCase(), property.substring(1));
            if (method.getParameters().length == 0 && (method.getName().equals(getterMethodName) || method.getName().equals(getterMethodNameForBoolean))) {
                getter = method;
                break;
            }
        }

        return getter;
    }

    private static Method getSetter(Object bean, String property) {
        Method[] methods = bean.getClass().getMethods();
        Method setter = null;

        for(Method method : methods) {
            String setterMethodName = String.format("set%s%s", property.substring(0, 1).toUpperCase(), property.substring(1));
            if (method.getParameters().length == 1 && method.getName().equals(setterMethodName)) {
                setter = method;
                break;
            }
        }

        return setter;
    }

    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        Set<String> ignores;
        if (ignoreProperties == null) {
            ignores = Collections.emptySet();
        } else {
            ignores = new HashSet(Arrays.asList(ignoreProperties));
        }

        for(String attribute : getProperties(source.getClass())) {
            if (!ignores.contains(attribute) && hasGetter(source, attribute) && hasSetter(target, attribute)) {
                Object value = readValue(source, attribute);
                writeValue(target, attribute, value);
            }
        }

    }

    public static Set<String> getProperties(Class clazz) {
        Set<String> proerties = new HashSet();
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields) {
            if (!Modifier.isTransient(field.getModifiers())) {
                proerties.add(field.getName());
            }
        }

        return proerties;
    }

    private Beans() {
    }
}


package ing.beribtur.accent.context;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SpaceContext {
    //
    private static final ThreadLocal<SpaceRequest> context = new ThreadLocal<>();

    public static SpaceRequest get() {
        //
        if (!exists()) {
            setDefault();
        }

        return context.get();
    }

    public static void set(SpaceRequest request) {
        //
        context.set(request);
    }

    public static void clear() {
        //
        context.remove();
    }

    public static void setDefault() {
        //
        context.set(SpaceRequest.anonymous());
    }

    private static boolean exists() {
        //
        return context.get() != null;
    }
}

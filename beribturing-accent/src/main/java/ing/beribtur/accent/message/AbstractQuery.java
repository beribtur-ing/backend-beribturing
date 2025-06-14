package ing.beribtur.accent.message;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

@Getter
@Setter
public abstract class AbstractQuery<T> {
    //
    private QueryResponse<T> response;
    private Offset offset;

    protected AbstractQuery() {
        //
        this.offset = Offset.newUnlimited();
    }

    protected AbstractQuery(Offset offset) {
        //
        this.offset = offset;
    }

    public QueryResponse<T> getResponse() {
        if (this.response == null) {
            this.response = new QueryResponse();
        }

        return this.response;
    }



    public void setResponse(Object fetchResult) {
        if (fetchResult == null) {
            this.response = new QueryResponse();
            this.response.setOffset(this.offset);
        } else {
            FetchResultType fetchResultType = this.getResponseType(fetchResult);
            switch (fetchResultType.ordinal()) {
                case 0:
                case 1:
                    this.response = new QueryResponse(this.getContent(fetchResult));
                    if (fetchResultType == AbstractQuery.FetchResultType.PAGE) {
                        this.offset.setTotalCount(this.getTotalElements(fetchResult));
                    }

                    this.offset.setPrevious(this.hasPrevious(fetchResult));
                    this.offset.setNext(this.hasNext(fetchResult));
                    this.response.setOffset(this.offset);
                    break;
                default:
                    this.response = new QueryResponse(fetchResult);
                    this.response.setOffset(this.offset);
            }

        }
    }

    private boolean hasNext(Object fetchResult) {
        String nameForNext = "hasNext";

        try {
            return (Boolean)fetchResult.getClass().getMethod(nameForNext).invoke(fetchResult);
        } catch (Exception var4) {
            return false;
        }
    }

    private boolean hasPrevious(Object fetchResult) {
        String nameForPrevious = "hasPrevious";

        try {
            return (Boolean)fetchResult.getClass().getMethod(nameForPrevious).invoke(fetchResult);
        } catch (Exception var4) {
            return false;
        }
    }

    private FetchResultType getResponseType(Object fetchResult) {
        String nameForContent = "getContent";
        String nameForTotal = "getTotalElements";
        String nameForNext = "hasNext";
        String nameForPrevious = "hasPrevious";
        boolean hasMethodForContent = false;
        boolean hasMethodForTotal = false;
        boolean hasMethodForNext = false;
        boolean hasMethodForPrevious = false;
        Method[] methods = fetchResult.getClass().getMethods();

        for(Method method : methods) {
            if (method.getName().equals(nameForContent)) {
                hasMethodForContent = true;
            } else if (method.getName().equals(nameForTotal)) {
                hasMethodForTotal = true;
            } else if (method.getName().equals(nameForNext)) {
                hasMethodForNext = true;
            } else if (method.getName().equals(nameForPrevious)) {
                hasMethodForPrevious = true;
            }
        }

        if (hasMethodForContent && hasMethodForNext && hasMethodForPrevious && hasMethodForTotal) {
            return AbstractQuery.FetchResultType.PAGE;
        } else if (hasMethodForContent && hasMethodForNext && hasMethodForPrevious) {
            return AbstractQuery.FetchResultType.SLICE;
        } else {
            return AbstractQuery.FetchResultType.OBJECT;
        }
    }

    private T getContent(Object fetchResult) {
        String nameForContent = "getContent";

        try {
            return (T)fetchResult.getClass().getMethod(nameForContent).invoke(fetchResult);
        } catch (Exception var4) {
            return null;
        }
    }

    private long getTotalElements(Object fetchResult) {
        String nameForTotalName = "getTotalElements";

        try {
            return (Long)fetchResult.getClass().getMethod(nameForTotalName).invoke(fetchResult);
        } catch (Exception var4) {
            return 0L;
        }
    }

    private static enum FetchResultType {
        PAGE,
        SLICE,
        OBJECT;

        private FetchResultType() {
        }
    }
}

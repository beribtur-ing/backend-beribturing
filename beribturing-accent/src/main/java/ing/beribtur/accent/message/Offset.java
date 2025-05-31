package ing.beribtur.accent.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Offset {
    //
    private int offset;
    private int limit;
    private long totalCount;
    private boolean previous;
    private boolean next;

    public Offset(int offset, int limit) {
        //
        this.offset = offset;
        this.limit = limit > 0 ? limit : 10;
        this.previous = false;
        this.next = false;
    }

    public static Offset newDefault() {
        //
        return new Offset(0, 10);
    }

    public static Offset newUnlimited() {
        //
        return new Offset(0, Integer.MAX_VALUE);
    }

    public static Offset from(int offset, int limit) {
        //
        return new Offset(offset, limit);
    }

    public boolean hasPrevious() {
        //
        return this.previous;
    }

    public boolean hasNext() {
        //
        return this.next;
    }

    public int page() {
        return this.offset <= 0 ? 0 : this.offset / this.limit;
    }

    public int offset() {
        return this.offset;
    }

    public int limit() {
        return this.limit;
    }

    public int sum() {
        return this.offset <= 0 ? this.limit : this.offset + this.limit;
    }
}

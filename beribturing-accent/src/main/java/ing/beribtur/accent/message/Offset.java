package ing.beribtur.accent.message;

import ing.beribtur.accent.util.JsonSerializable;
import ing.beribtur.accent.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Offset implements JsonSerializable {
    //
    private int offset;
    private int limit;
    private long totalCount;
    private boolean previous;
    private boolean next;
    private String sortingField;
    private SortDirection sortDirection;

    public Offset(int offset, int limit) {
        this.offset = offset;
        this.limit = limit > 0 ? limit : 10;
        this.sortDirection = Offset.SortDirection.ASCENDING;
        this.previous = false;
        this.next = false;
    }

    public Offset(int offset, int limit, SortDirection sortDirection, String sortingField) {
        this.offset = offset;
        this.limit = limit > 0 ? limit : 10;
        this.sortDirection = sortDirection;
        this.sortingField = sortingField;
        this.previous = false;
        this.next = false;
    }

    public Offset() {
    }

    public static Offset newDefault() {
        return new Offset(0, 10);
    }

    public static Offset newUnlimited() {
        return new Offset(0, Integer.MAX_VALUE);
    }

    public static Offset from(int offset, int limit) {
        return new Offset(offset, limit);
    }

    public static Offset from(int offset, int limit, SortDirection sortDirection, String sortingField) {
        return new Offset(offset, limit, sortDirection, sortingField);
    }

    public static Offset fromJson(String json) {
        return JsonUtil.fromJson(json, Offset.class);
    }

    public static Offset sample() {
        return new Offset(0, 20);
    }

    public static void main(String[] args) {
        System.out.println(sample().toPrettyJson());
        System.out.println(sample().ascendingSort());
    }

    public boolean ascendingSort() {
        return Offset.SortDirection.ASCENDING == this.sortDirection;
    }

    public boolean hasPrevious() {
        return this.previous;
    }

    public boolean hasNext() {
        return this.next;
    }

    public String toString() {
        return this.toJson();
    }

    public int offset() {
        return this.offset;
    }

    public int limit() {
        return this.limit;
    }

    public int page() {
        return this.offset <= 0 ? 0 : this.offset / this.limit;
    }

    public int sum() {
        return this.offset <= 0 ? this.limit : this.offset + this.limit;
    }

    public enum SortDirection {
        ASCENDING,
        DESCENDING;

        SortDirection() {
        }
    }
}
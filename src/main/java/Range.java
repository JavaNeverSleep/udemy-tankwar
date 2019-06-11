import java.util.Objects;

class Range {

    private final int begin;

    private final int end;

    Range(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return begin == range.begin &&
            end == range.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, end);
    }
}

public class Box<T extends Comparable<T>> implements Comparable<Box<T>> {   // T must be comparable
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    @Override
    public int compareTo(Box<T> other) {
        return this.value.compareTo(other.get());
    }

    public static <T extends Comparable<T>> int count(T[] arr, T ele) {
        int cnt = 0;
        for (T e : arr) {
            if (e.compareTo(ele) > 0) cnt++;
        }
        return cnt;
    }
}
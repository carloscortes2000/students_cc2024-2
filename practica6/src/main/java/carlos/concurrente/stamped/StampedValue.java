package carlos.concurrente.stamped;

public class StampedValue<T> {
    private long stamp;
    private T value;

    // initial value with zero timestamp
    public StampedValue(T init) {
        this.stamp = 0;
        this.value = init;
    }
    
    // later values with timestamp provided
    public StampedValue(long stamp, T value) {
        this.stamp = stamp;
        this.value = value;
    }

    public static <T> StampedValue<T> max(StampedValue<T> x, StampedValue<T> y) {
        if (x.stamp > y.stamp) {
            return x;
        } else {
            return y;
        }
    }

    public static final StampedValue MIN_VALUE = new StampedValue<>(null);

    public long getStamp(){
        return stamp;
    }

    public T value(){
        return value;
    }

    public void setStamp(long stamp){
        this.stamp = stamp;
    }

    public void setValue(T value){
        this.value = value;
    }
}
package carlos.concurrente.snapshots;

public interface Snapshot<T> {
    
    public void update(T data);

    public T[] scan();
}

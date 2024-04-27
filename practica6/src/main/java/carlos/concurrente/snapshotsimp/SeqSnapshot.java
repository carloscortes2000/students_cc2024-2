package carlos.concurrente.snapshotsimp;

import carlos.concurrente.snapshots.Snapshot;

public class SeqSnapshot<T> implements Snapshot<T> {
    private T[] aValue;

    public SeqSnapshot(int capacity, T init) {
        aValue = (T[]) new Object[capacity];
        
        for (int i = 0; i < aValue.length; i++) {
            aValue[i] = init;
        }
    }

    @Override
    public synchronized void update(T v) {
        int id = Integer.parseInt(Thread.currentThread().getName());
        aValue[id] = v;
    }

    @Override
    public synchronized T[] scan() {
        T[] result = (T[]) new Object[aValue.length];
        for(int i = 0; i < aValue.length; i++){
            result[i] = aValue[i]; //checar sonalint "Arrays.copyOf", "Arrays.asList", "Collections.addAll" or "System.arraycopy"
        }
        return result;
    }
}
package carlos.concurrente.snapshotsimp;

import carlos.concurrente.snapshots.Snapshot;
import carlos.concurrente.stamped.StampedSnap;

public class WFSnapshot<T> implements Snapshot<T> {
    private StampedSnap<T>[] aTable;

    public WFSnapshot(int capacity, T init) {
        aTable = (StampedSnap<T>[]) new StampedSnap[capacity];
        for (int i = 0; i < aTable.length; i++) {
            aTable[i] = new StampedSnap<>(init);
        }
    }
    private StampedSnap<T>[] collect() {
        StampedSnap<T>[] copy = (StampedSnap<T>[])
        new StampedSnap[aTable.length];
        
        for (int j = 0; j < aTable.length; j++)
            copy[j] = aTable[j]; //checar sonalint "Arrays.copyOf", "Arrays.asList", "Collections.addAll" or "System.arraycopy"
        return copy;
    }

    public void update(T value) {
        int id = Integer.parseInt(Thread.currentThread().getName());
        T[] snap = scan();
        StampedSnap<T> oldValue = aTable[id];
        StampedSnap<T> newValue = new StampedSnap<>(oldValue.getStamp()+1,value,snap);
        aTable[id] = newValue;
    }
    //necesita modificaciones
    @Override
    public T[] scan() {
        StampedSnap<T>[] oldCopy;
        StampedSnap<T>[] newCopy;
        boolean[] moved = new boolean[aTable.length];
        oldCopy = collect();
        collect: while (true) {
            newCopy = collect();
            for (int i = 0; i < aTable.length; i++) {
                if(oldCopy[i].stamp != newCopy[i].stamp) {
                    if(moved[i])
                        return oldCopy[i].snap;
                    else {
                        moved[i] = true;
                        oldCopy = newCopy;
                        continue collect;
                    }
                }
            }
            T[] result = (T[]) new Object[aTable.length];
            for(int i = 0; i < aTable.length; i++)
                result[i] = newCopy[i].value;

            return result;
        }
    } 
}
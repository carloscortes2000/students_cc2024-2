package kas.concurrente;

import java.util.concurrent.atomic.AtomicBoolean;

public class BackoffLock implements Lock {
    private AtomicBoolean state = new AtomicBoolean(false);
    private static final int MIN_DELAY=50;
    private static final int MAX_DELAY=1000;

    @Override
    public void lock() {
        Backoff backoff = new Backoff(MIN_DELAY, MAX_DELAY);
        while (true) {
            while (state.get()) {
                if (!state.getAndSet(true)) {
                    return;
                }else{
                    try {
                        backoff.backoff();
                        Thread.yield();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }   
    }

    @Override
    public void unlock() {
        state.set(false);
    }
    
}

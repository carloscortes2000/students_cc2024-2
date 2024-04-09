package kas.concurrente;

import java.util.concurrent.atomic.AtomicReference;

public class MCSLock implements Lock {
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myNode;

    public MCSLock(){
        myNode = new ThreadLocal<QNode>(){

            @Override
            protected QNode initialValue(){
                return new QNode();
            }
        };
    }
    class QNode{
        private volatile boolean locked = false;
        QNode next = null;
    }
    
    @Override
    public void lock() {
        QNode qnode = myNode.get();
        QNode pred = tail.getAndSet(qnode);
        if (pred != null) {
            qnode.locked = true;
            pred.next = qnode;
            while (qnode.locked) {
                Thread.yield();
            }
        }
    }

    @Override
    public void unlock() {
       QNode qnode = myNode.get();
       if (qnode.next == null) {
            if (tail.compareAndSet(qnode, null))
                return;
            while (qnode.next == null) {}
       }
       qnode.next.locked = false;
       qnode.next = null;
    }
}

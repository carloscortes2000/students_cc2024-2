package kas.concurrente;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock implements Lock {
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;

    public CLHLock(){
        tail= new AtomicReference<>(new QNode());
        myNode = new ThreadLocal<QNode>(){
            @Override
            protected QNode initialValue(){
                return new QNode();
            }
        };
        myPred = new ThreadLocal<QNode>(){
            @Override
            protected QNode initialValue(){
                return null;
            }
        };
    } 
    
    @Override
    public void lock() {
        QNode qnode = myNode.get();
        qnode.locked = true;
        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);
        while (pred.locked) {}
    }

    @Override
    public void unlock(){
        QNode qnode = myNode.get();
        qnode.locked = false;
        myNode.set(myPred.get());
    }

    class QNode {
        private volatile boolean locked = false;
    }
    
}

package kass.concurrente.candadosImpl;

import kass.concurrente.candados.Lock;

/**
* Clase que implementa el candado usando el Legendario
* algoritmo de PeterGod.
* No hay mucho que decir, ya saben que hacer
* @version 1.0
* @author Kassandra Mirael
*/
public class PetersonLock implements Lock {

   private volatile boolean[] intentandoEntrarA = new boolean[2];
   private volatile int victima;

   @Override
   public void lock() {
       int i = Integer.parseInt(Thread.currentThread().getName());
       int j = 1 - i;
       intentandoEntrarA[i] = true;
       victima = i;
       while (intentandoEntrarA[j] && victima == i) ;
   }

   @Override
   public void unlock() {
       int i = Integer.parseInt(Thread.currentThread().getName());
       intentandoEntrarA[i] = false;
   }
  
}

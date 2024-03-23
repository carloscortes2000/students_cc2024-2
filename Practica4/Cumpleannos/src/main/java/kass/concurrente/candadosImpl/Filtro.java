package kass.concurrente.candadosImpl;

import kass.concurrente.candados.Semaphore;

/**
 * Clase que modela el Algoritmo del Filtro Modificado
 * Este algoritmo es similar al del filtro, lo diferente es que
 * permite una cantidad m de hilos SIMULTANEOS en la seccion critica
 * Todo es casi igual, solo realiza la modificacion pertinente para esto
 * @version 1.0
 * @author Kassandra Mirael
 */
public class Filtro implements Semaphore {

    private volatile boolean[] entrarA_SC;
    private volatile int[] prioridad;
    private int hilos;
    private int maxHilosConcurrentes;

    /**
     * Constructor del Filtro
     * @param hilos El numero de Hilos Permitidos
     * @param maxHilosConcurrentes EL numero de hilos concurrentes simultaneos
     */
    public Filtro(int hilos, int maxHilosConcurrentes) {
        this.hilos = hilos;
        this.maxHilosConcurrentes = maxHilosConcurrentes;
        this.entrarA_SC = new boolean[hilos];
        this.prioridad = new int[hilos];

        for (int i = 0; i < hilos; i++) {
            entrarA_SC[i] = false;
            prioridad[i] = 0;
        }
    }

    @Override
    public int getPermitsOnCriticalSection() {
        return maxHilosConcurrentes; 
    }

    @Override
    public void acquire() {
        int id = Integer.parseInt(Thread.currentThread().getName()); 

        for (int i = 1; i < hilos; i++) {
            entrarA_SC[id] = true; 
            prioridad[id] = i; 

            
            for (int j = 0; j < hilos; j++) {
                if (j != id) {
                    
                    while (entrarA_SC[j] && (prioridad[j] >= prioridad[id])) {
                        try {
                            Thread.sleep(1); 
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void release() {
        int id = Integer.parseInt(Thread.currentThread().getName()); // Obtiene el ID del hilo actual
        entrarA_SC[id] = false; // El hilo actual no quiere entrar en la sección crítica
    }
}
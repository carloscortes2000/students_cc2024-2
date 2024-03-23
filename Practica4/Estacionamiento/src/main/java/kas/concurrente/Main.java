package kas.concurrente;

import kas.concurrente.modelos.Estacionamiento;

/**
 * Clase principal, la usarán para SUS pruebas.
 * Pueden modificar los valores estáticos para ver cómo funciona.
 * NO USEN VALORES EXTREMADAMENTE ALTOS, puede alentar mucho su computadora.
 * AQUÍ EJECUTAN LA SIMULACIÓN.
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Main implements Runnable{

    private static final int NUM_CARROS = 15;
    private Estacionamiento estacionamiento;

    /**
     * Método constructor.
     * Se inicializa el Semaforo Modificado con el número de permisos adecuado.
     * Se inicializa el Estacionamiento con la capacidad adecuada.
     */
    public Main() {
        estacionamiento = new Estacionamiento(5, 40); 
    }

    /**
     * Una documentacion del main xD, esta bien 
     * Paso 0: Lee estas instrucciones
     * Paso 1: Crea el Objeto de tipo main
     * Paso 2: Crea Una estructura de datos que contenga a nuestros hilos
     * Paso 3: Genera con un ciclo, el cual inialice un numero igual de NUM_CARROS
     * Paso 4: No olvides agregarlos a la estructura e inicializarlos
     * Paso 5: Finalmente has un Join a tus hilos
     * @param args Los Argumentos
     * @throws InterruptedException Por si explota su compu al ponerle medio millon de hilos xD
     */
    public static void main(String[] args) throws InterruptedException{
        Main main = new Main();
        Thread[] hilos = new Thread[NUM_CARROS];

        for (int i = 0; i < NUM_CARROS; i++) {
            hilos[i] = new Thread(main);
        }

        for (Thread hilo : hilos) {
            hilo.start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }
    }

    /**
     * Aquí está su primer sección crítica.
     * Paso 1: Mantén la calma y ...
     * Paso 2: Ten cuidado con el código concurrente.
     * Paso 3: Trata de recordar algunos conceptos básicos de Java y POO.
     * Paso 4: Obtén el ID de tu hilo.
     * Paso 5: TU CARRO (HILO) ENTRARÁ AL ESTACIONAMIENTO (Los Hilos simulan ser carros,
     * no es necesario que generes clase Carro (puedes hacerlo si quieres)).
     */
    @Override
    public void run(){
        try {
            estacionamiento.entraCarro(Integer.parseInt(Thread.currentThread().getName()));
        } catch (InterruptedException e) {
            System.err.println("El hilo fue interrumpido mientras intentaba entrar al estacionamiento: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
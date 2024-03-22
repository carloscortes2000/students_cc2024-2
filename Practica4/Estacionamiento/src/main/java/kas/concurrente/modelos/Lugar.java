package kas.concurrente.modelos;

import java.util.concurrent.Semaphore;

/**
 * Clase que modela un Lugar
 * El lugar consta de un id
 * un booleano que nos dice si esta disponible
 * y un objeto del tipo Semaphore (El semaforo)
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Lugar {
    private Integer id;
    private boolean disponible;
    private Semaphore semaforo;
    private Integer vecesEstacionado;

    /**
     * Metodo constructor
     * El lugar por defecto esta disponible
     * Pueden llegar un numero n de carros en el peor de los casos
     * veces estacionado sera el numero de veces que se han estacianado en el lugar
     * Si llegan 2 carros y ambos se estacionan, entonces, su valor sera de 2
     * @param id El id del Lugar
     */
    public Lugar(int id) {
        this.id = id;
        this.disponible = true;
        this.semaforo = new Semaphore(1); // Creación del semáforo con un permiso
        this.vecesEstacionado = 0;
    }

    /**
     * En este metodo se simula que se estaciona
     * PELIGRO: ESTAS ENTRANDO A LA 2da SECCION CRITICA
     * Cambia el valor de disponible a false
     * Y se simula que vas pastel de cumple :D
     * Al final, imprime un texto color ROJO diciendo que va salir (Esperen instrucciones para esto)
     * @throws InterruptedException Si algo falla
     */
    public void estaciona() throws InterruptedException {
        semaforo.acquire(); // Adquiere un permiso del semáforo
        disponible = false;
        vecesEstacionado++;
        System.out.println("El carro se estaciono en el lugar " + id);
        semaforo.release(); 
    }

    /**
     * En este método se genera la simulación de espera
     * Se genera un tiempo entre 1 y 5 segundos
     * Es pseudo aleatorio
     * @throws InterruptedException En caso de que falle
     */
    public void vePorPastel() throws InterruptedException {
        long tiempoEspera = (long) (Math.random() * 5000) + 1000; 
        Thread.sleep(tiempoEspera);
        System.out.println("El carro está yendo por pastel y saliendo del lugar ");
        disponible = true;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public int getVecesEstacionado() {
        return vecesEstacionado;
    }

    public int getId() {
        return id;
    }

    public boolean getDisponible(){
        return disponible;
    }
}
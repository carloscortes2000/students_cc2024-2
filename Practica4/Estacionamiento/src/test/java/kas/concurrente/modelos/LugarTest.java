package kas.concurrente.modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LugarTest {
    Semaphore semaforo;
    Lugar lugar;
    List<Thread> hilos;
    static final int numHilos = 15;

    @BeforeEach
    void setUp() throws InterruptedException{
        lugar = new Lugar(1);
        semaforo = new Semaphore(1);
        initHilos();
    }

    @Test
    void constructorTest(){
        assertTrue(lugar.getId() == 1 && lugar.getDisponible() == true);
    }

    @Test
    void estacionaTest() throws InterruptedException{
        lugar.estaciona();
        assertFalse(lugar.getDisponible());
    }

    /**
     * AGREGA 2 TEST MAS
     * TEST bien hechos
     */

    @Test
    void noDisponibleDespuesDeEstacionarTest() throws InterruptedException {
        lugar.estaciona();
        assertFalse(lugar.getDisponible());
    }

    @Test
    void lugarLibreDespuesDeSalirCarroTest() throws InterruptedException {
        lugar.estaciona();
        lugar.vePorPastel();
        assertTrue(lugar.getDisponible());
    }

    void initHilos() {
        hilos = new ArrayList<>();

        for (int i = 0; i < numHilos; ++i) {
            Thread t = new Thread(this::simulaEstacionamiento, "" + i);
            hilos.add(t);
        }
    }

    void simulaEstacionamiento() {
        try {
            lugar.estaciona();
            lugar.vePorPastel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}




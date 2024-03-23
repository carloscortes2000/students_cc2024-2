package kas.concurrente.modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstacionamientoTest {
    Estacionamiento es;
    final static int pisos = 2;
    final static int lugaresPorPiso = 100;
    final static int NUMLUGARES = pisos * lugaresPorPiso;
    List<Thread> hilos;

    @BeforeEach
    void setUp(){
        es = new Estacionamiento(pisos, lugaresPorPiso);
        initHilos();
    }

    /**
     * Teste que revisa si tiene todos los lugares disponibles al iniciar
     */
    @Test
    void lugaresDisponiblesITest(){
        assertEquals(NUMLUGARES,es.getLugaresDisponibles());
    }

    /**
     * Test que comprueba que el numero de veces que se estaciona sea correcto
     * @throws InterruptedException
     */
    @Test
    void conteoVecesEstacionado() throws InterruptedException{
        for(int i = 0; i < pisos; i++){
            for(int j=0; j<lugaresPorPiso; j++){
                es.getLugares()[i][j].estaciona();
            }
        }
        assertEquals(NUMLUGARES, verificaVecesEstacionado());
    }

    @Test
    void conteoGlobalVecesEstacionado() throws InterruptedException{
        for(Thread t : hilos){
            t.start();
        }

        for(Thread t : hilos){
            t.join();
        }

        assertEquals(NUMLUGARES*2,verificaVecesEstacionado());
    }

    int verificaVecesEstacionado(){
        int res = 0;
        for(int i = 0; i < es.getLugares().length; ++i){
            for(int j=0; j< es.getLugares()[i].length; j++){
                res += es.getLugares()[i][j].getVecesEstacionado();
            }
            
        }

        return res;
    }

    /**
     * AGREGA 2 TEST MAS
     * TEST bien hechos
     */

    /**
     * Test que verifica si el número de lugares disponibles disminuye después de que un carro entre al estacionamiento
     * @throws InterruptedException
     */
    @Test
    void entradaCarroDecrementaLugaresDisponibles() throws InterruptedException {
        int lugaresAntes = es.getLugaresDisponibles();
        // Simular la entrada de un carro
        es.entraCarro(1);
        int lugaresDespues = es.getLugaresDisponibles();
        assertEquals(lugaresAntes - 1, lugaresDespues);
    }

    /**
     * Test que verifica que el estacionamiento no esté lleno al inicio
     */
    @Test
    void estacionamientoNoLlenoTest() {
        assertFalse(es.estaLleno());
    }

    void initHilos(){
        hilos = new ArrayList<>();

        for(int i=0; i < NUMLUGARES*2; ++i){
            Thread t = new Thread(this::simulaCS,""+i);
            hilos.add(t);
        }
    }

    void simulaCS() {
        try{
            int id = Integer.parseInt(Thread.currentThread().getName());
            es.entraCarro(id);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
    }
}

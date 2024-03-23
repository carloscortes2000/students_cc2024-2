package kass.concurrente.invitados;

import kass.concurrente.tenedor.Tenedor;
import kass.concurrente.candados.Semaphore;

/**
 * Clase que modela al inversionista, pero esta vez
 * usando el filtro.
 * No se sobreescribe el run, si hicieron bien las cosas
 * Entonces se pasara sin problemas para acá
 * Good Luck!
 * @version 1.1
 * @author Kassandra Mirael
 */
public class InversionistaFiltro extends Inversionista {

    private Semaphore semaforo;

    public InversionistaFiltro(int id, Tenedor tenedorIzq, Tenedor tenedorDer, Semaphore filtro) {
        super(id, tenedorIzq, tenedorDer);
        semaforo = filtro;
    }

    @Override
    public void entraALaMesa() throws InterruptedException {
        semaforo.acquire(); 
        super.entraALaMesa(); 
        semaforo.release(); 
    }

    @Override
    public void tomaTenedores() {
            super.tomaTenedores();
    }

    @Override
    public void sueltaTenedores() {
            super.sueltaTenedores();
    }
}
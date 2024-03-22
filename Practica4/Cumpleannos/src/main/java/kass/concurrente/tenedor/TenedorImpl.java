package kass.concurrente.tenedor;

import kass.concurrente.candados.PetersonLock;

/**
 * Clase que implementa el tenedor
 * Tenemos una variable entera que cuenta el número de veces que fue tomado
 * Tiene una variable que simboliza su id
 * @version 1.1
 * @author <Lone Wolf>
 */
public class TenedorImpl implements Tenedor {

    private int id;
    private int vecesTomado;
    private PetersonLock peterson = new Peterson();

    public TenedorImpl(int id) {
        this.id = id;
        this.vecesTomado = 0;
    }

    @Override
    public void tomar() {
        peterson.lock(); 
        vecesTomado++;
    }

    @Override
    public void soltar() {
        peterson.unlock();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getVecesTomado() {
        return this.vecesTomado;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setVecesTomado(int vecesTomado) {
        this.vecesTomado = vecesTomado;
    }
}

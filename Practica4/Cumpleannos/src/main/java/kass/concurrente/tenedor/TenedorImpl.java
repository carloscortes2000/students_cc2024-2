package kass.concurrente.tenedor;

import kass.concurrente.candadosImpl.PetersonLock;

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
    private PetersonLock peterson = new PetersonLock(); 

    public TenedorImpl(int id) {
        this.id = id;
        this.vecesTomado = 0;
    }

    @Override
    public void tomar() {
        peterson.lock(); 
        vecesTomado++;
        System.out.println("Se tomó el tenedor"+id);
    }

    @Override
    public void soltar() {
        System.out.println("El tenedor "+id+" se dejó de usar");
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

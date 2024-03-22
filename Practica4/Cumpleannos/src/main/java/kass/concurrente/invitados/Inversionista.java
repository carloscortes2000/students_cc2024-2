package kass.concurrente.invitados;

import kass.concurrente.tenedor.Tenedor;
import kass.concurrente.tenedor.TenedorImpl;

/**
 * Clase abstracta que modela al inversionista.
 * El inversionista tiene 2 tenedores a sus lados.
 * El inversionista posee un ID para que se pueda identificar.
 * El inversionista tiene una variable que indica el numero de veces que ha comido.
 * @version 1.0
 * @author Kassandra Mirael
 */
public abstract class Inversionista implements Runnable {

    private Integer id;
    private Integer vecesComido;
    private Tenedor tenedorIzq;
    private Tenedor tenedorDer;

    public Inversionista(int id, Tenedor tenedorIzq, Tenedor tenedorDer) {
        this.id = id;
        this.vecesComido = 0;
        this.tenedorIzq = tenedorIzq;
        this.tenedorDer = tenedorDer;
    }

    @Override
    public void run() {
        // El inversionista debe pensar y entrar a la mesa un período de veces
        // puesto en el test, agrega el valor aquí.
        for (int i = 0; i < 5; i++) {
            try {
                piensa();
                entraALaMesa();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que nos permite entrar a la mesa.
     * El inversionista por fin dejo de pensar y de escribir en su
     * servilleta y se digna en entrar.
     * PRIMERO toma los tenedores.
     * DESPUES come.
     * FINALMENTE los suelta para que los demás los puedan usar.
     * @throws InterruptedException Si hay una interrupción mientras espera
     */
    public void entraALaMesa() throws InterruptedException {
        tomaTenedores();
        come();
        sueltaTenedores();
    }

    /**
     * Una vez que termino de pensar sobre finanzas el inversionista
     * se prepara para comer.
     * El inversionista le toma un par de milisegundos comer.
     * ESTA ES LA SECCION CRITICA, SIGNIFICA PELIGRO
     * Incrementa el número de veces que ha comido.
     * @throws InterruptedException Si hay una interrupción mientras espera
     */
    public void come() throws InterruptedException {
        // Simula el tiempo que tarda en comer
        // piensa(); 
        Thread.sleep(generaTiempoDeEspera());
        this.vecesComido++;
    }

    /**
     * Metodo que hace que el inversionista piense.
     * El inversionista pensara por una par de milisegundos.
     * Esto pasa antes de que tome sus tenedores.
     * @throws InterruptedException Si hay una interrupción mientras espera
     */
    public void piensa() throws InterruptedException {
        Thread.sleep(generaTiempoDeEspera());
    }

    /**
     * Metodo que nos permite tomar los tenedores.
     * Los toma uno por uno.
     */
    public void tomaTenedores() {
        tenedorIzq.tomar();
        tenedorDer.tomar();
    }

    /**
     * Metodo que hace que el inversionista suelte ambos tenedores una vez
     * que termina de comer. 
     * De esta manera otro los puede usar.
     * Suelta los tenedores uno por uno.
     */
    public void sueltaTenedores() {
        tenedorIzq.soltar();
        tenedorDer.soltar();
    }

    /**
     * Metodo que genera un numero pseudoaleatorio entre 1 y 10
     * @return El tiempo de espera
     */
    private long generaTiempoDeEspera() {
        return (long) (Math.random() * 10.0);
    }

    /*
     * Rellena Getter and Setters primero
     * Documenta los metodos.
     * Cuando acabes borra este comentario
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tenedor getTenedorIzq() {
        return this.tenedorIzq;
    }

    public void setTenedorIzq(Tenedor tenedorIzq) {
        this.tenedorIzq = tenedorIzq;
    }

    public Tenedor getTenedorDer() {
        return this.tenedorDer;
    }

    public void setTenedorDer(Tenedor tenedorDer) {
        this.tenedorDer = tenedorDer;
    }

    public int getVecesComido() {
        return this.vecesComido;
    }

    public void setVecesComido(int vecesComido) {
        this.vecesComido = vecesComido;
    }
}
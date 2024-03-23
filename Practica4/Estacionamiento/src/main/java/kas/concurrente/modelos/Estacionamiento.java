package kas.concurrente.modelos;

/**
 * En esta clase se simula el estacionamiento en si
 * Posee un conjunto de arreglos de tipo Lugar (o arreglo bidimensional?)
 * Posee un entero de lugaresDisponibles (Se podra hacer por pisos?) (Habra otra manera de hacerlo mejor?)
 * @author Kassandra Mirael
 * @version 1.0
 */
public class Estacionamiento {
    private Lugar[][] lugares;
    private volatile int lugaresDisponibles;

    /**
     * Metodo constructor
     * Modifica el constructor o crea otro segun consideres necesario
     * @param capacidad La capacidad del estacionamiento
     */
    public Estacionamiento(int pisos, int lugaresPorPiso) {
        lugares = new Lugar[pisos][lugaresPorPiso];
        for (int i = 0; i < pisos; i++) {
            for (int j = 0; j < lugaresPorPiso; j++) {
                lugares[i][j] = new Lugar(i * lugaresPorPiso + j);
            }
        }
        lugaresDisponibles = pisos * lugaresPorPiso;
    }

    public int getLugaresDisponibles() {
        return lugaresDisponibles;
    }

    public void setLugaresDisponibles(int lugaresDisponibles) {
        this.lugaresDisponibles = lugaresDisponibles;
    }

    /**
     * Metodo que nos indica si esta lleno el estacionamiento.
     * @return true si esta lleno, false en otro caso.
     */
    public boolean estaLleno() {
        return lugaresDisponibles == 0;
    }

    /**
     * Metodo que inicaliza los lugares del arreglo
     * Este es un método optativo
     */
    public void inicializaLugares(){
        /**
         * Aqui va tu codigo
         */
    }

    /**
     * Metodo en el que se simula la entrada de un carro.
     * Imprime un texto que dice que el carro a entrado de color AZUL.
     * @param nombre El nombre del carro.
     * @throws InterruptedException Si llega a fallar.
     */
    public void entraCarro(int nombre) throws InterruptedException {
        int lugar = obtenLugar();
        asignaLugar(lugar);
        System.out.println("El carro " + nombre + " entro al estacionamiento");
    }

    /**
    * Metodo que asigna el lugar, una vez asignado ESTACIONA su nave.
    * @param lugar El lugar que corresponde en la matriz de lugares.
    * @throws InterruptedException 
    */
    public void asignaLugar(int lugar) throws InterruptedException {
        int piso = lugar / lugares[0].length;
        int lugarEnPiso = lugar % lugares[0].length;
        lugares[piso][lugarEnPiso].estaciona();
        lugaresDisponibles--;
    }
    

    /**
     * Se obtiene un lugar de forma pseudoAleatoria
     * Aqui necesito que revisen el repaso de estadistica que mande en 
     * repaso, quiero que expliquen porque lo pedimos en forma pseudoAleatoria
     * @return Retorna el indice del lugar
     */
    public int obtenLugar() {
        int piso = (int) (Math.random() * lugares.length);
        int lugarEnPiso = (int) (Math.random() * lugares[0].length);
        return piso * lugares[0].length + lugarEnPiso;
    }

    public Lugar [][] getLugares(){
        return lugares;
    }
}
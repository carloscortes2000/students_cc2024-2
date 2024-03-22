package kass.concurrente.modelos;

/**
 * Clase que modela un prisioner
 * @version 1.0
 * @author <Su equipo>
 */
public class Prisionero {
    protected Integer id;
    protected Boolean esVocero;
    protected Boolean marcado;

    /**
     * Metodo constructor para generar un prisionero
     * @param id El identificador del prisionero
     * @param esVocero true si es Vocero false en otro caso
     * @param marcado true si ya paso
     */
    public Prisionero(Integer id, Boolean esVocero, Boolean marcado){
        this.id=id;
        this.esVocero=esVocero;
        this.marcado=marcado;
    }

    public Integer getId(){
        return is;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public Boolean getEsVocero(){
        return esVocero;
    }

    public void setEsVocero(Boolean esVocero){
        this.esVocero=esVocero;
    }

    public Boolean getMarcado(){
        return marcado;
    }

    public void setMarcado(Boolean marcado){
        this.marcado=marcado;
    }

    public String toString() {
        return "Prisionero{" +
                "id=" + id +
                ", esVocero=" + esVocero +
                ", marcado=" + marcado +
                '}';
    }
}

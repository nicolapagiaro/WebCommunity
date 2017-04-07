package util;

import pojo.Evento;

/**
 *
 * @author nicola
 */
public class EventoUtil {
    private Evento e;
    private float votoMedio;

    /**
     * Costruttore parametrico
     * @param e evento
     * @param votoMedio voto medio di quel evento 
     */
    public EventoUtil(Evento e, float votoMedio) {
        this.e = e;
        this.votoMedio = votoMedio;
    }

    public Evento getE() {
        return e;
    }

    public void setE(Evento e) {
        this.e = e;
    }

    public float getVotoMedio() {
        return votoMedio;
    }

    public void setVotoMedio(float votoMedio) {
        this.votoMedio = votoMedio;
    }
}

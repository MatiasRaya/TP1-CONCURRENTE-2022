package task;

import java.util.ArrayList;
import java.util.List;

public class Dato {
    private int reviews;
    private String nombre;
    private List<String> revisores;
    private static int contador = 0;

    public Dato(String nombre) {
        reviews = 0;
        this.nombre = (nombre + contador++);
        revisores = new ArrayList<>(2);
    }


    public void addRevision(String nombre){
        revisores.add(nombre);
        reviews++;
    }
    
    public List<String> getRevisores() {
        return revisores;
    }

    public int getReviews() {
        return reviews;
    }
}

package task;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Dato {

    private int reviews;
    private Boolean validado;
    private Semaphore semCrear;
    private Semaphore semRevisar;
    private Semaphore semConsumir;
    private final Object llaveLectura;

    public Dato() {
        reviews = 0;
        validado = false;
        semCrear = new Semaphore(1, true);
        semRevisar = new Semaphore(2, true);
        semConsumir = new Semaphore(1, true);
        llaveLectura = new Object();
        ArrayList<String> revisores = new ArrayList<>();    //Lista con los nombres de los revisores que ya revisaron el Dato
        
    }

    public void escribir() throws InterruptedException {
    }

    public void revisar() throws InterruptedException {
    }

    public void eliminar() throws InterruptedException {
    }

    public int getReviews() {
        return reviews;
    }

    public Boolean getValidado() {
        return validado;
    }
}

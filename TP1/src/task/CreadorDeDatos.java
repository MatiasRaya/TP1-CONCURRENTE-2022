package task;

import java.util.ArrayList;
import java.util.Random;

public class CreadorDeDatos implements Runnable{

    private ArrayList<Dato> bufferInicial;

    public CreadorDeDatos(ArrayList<Dato> bufferInicial) {
        this.bufferInicial = (ArrayList) bufferInicial.clone();
    }

    @Override
    public void run() {

    }
}

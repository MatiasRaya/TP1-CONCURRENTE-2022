package task;

import java.util.ArrayList;

public class CreadorDeDatos implements Runnable{

    private ArrayList<Dato> bufferInicial;

    public CreadorDeDatos(ArrayList<Dato> bufferInicial) {
        this.bufferInicial = (ArrayList<Dato>) bufferInicial.clone();
    }

    @Override
    public void run() {
        bufferInicial.add(0, new Dato("E"));
    }
}

package task;

import java.util.ArrayList;

public class CreadorDeDatos implements Runnable{

    private ArrayList<Dato> bufferInicial;
    int lenght;

    public CreadorDeDatos(ArrayList<Dato> bufferInicial) {
<<<<<<< HEAD
=======
        this.bufferInicial = (ArrayList<Dato>) bufferInicial.clone();
>>>>>>> 62a8b1e625e6c32d37ce595abcdd1b9e2c8a6e76
    }

    @Override
    public void run() {
        bufferInicial.add(0, new Dato("E"));
    }
}

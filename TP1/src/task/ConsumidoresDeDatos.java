package task;

import java.util.ArrayList;
import java.util.Random;

public class ConsumidoresDeDatos implements Runnable {

    private ArrayList<Dato> bufferInicial;
    private ArrayList<Dato> bufferDeValidados;

    public ConsumidoresDeDatos(ArrayList<Dato> bufferInicial, ArrayList<Dato> bufferDeValidados) {
        this.bufferInicial = (ArrayList) bufferInicial.clone();
        this.bufferDeValidados = (ArrayList) bufferDeValidados.clone();
    }

    @Override
    public void run() {

    }
}

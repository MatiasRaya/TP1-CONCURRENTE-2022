package task;

import java.util.ArrayList;
import java.util.Random;

public class RevisoresDeDatos implements Runnable {

    private ArrayList<Dato> bufferInicial;
    private ArrayList<Dato> bufferDeValidados;

    public RevisoresDeDatos(ArrayList<Dato> bufferInicial, ArrayList<Dato> bufferDeValidados) {
        this.bufferInicial = (ArrayList) bufferInicial.clone();
        this.bufferDeValidados = (ArrayList) bufferDeValidados.clone();
    }

    @Override
    public void run() {
        Random random = new Random();
        int numeroRandom;
        Dato datoElegido;

        while (!Thread.currentThread().isInterrupted()) {
            try {
                //Elijo una posición del buffer inicial para leerlo
                numeroRandom = random.nextInt(bufferInicial.size());

                //Selecciono el dato que tengo en esa posición
                datoElegido = bufferInicial.get(numeroRandom);
                datoElegido.revisar();

                //Verificamos si el dato se leyó dos veces, en caso de ser así lo copiamos al buffer de validados
                if (datoElegido.getValidado()) {
                    bufferDeValidados.add(datoElegido);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

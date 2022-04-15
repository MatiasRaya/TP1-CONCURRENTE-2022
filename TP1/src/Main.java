import task.ConsumidoresDeDatos;
import task.CreadorDeDatos;
import task.Dato;
import task.RevisoresDeDatos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main (String[] args) {
        ArrayList<Dato> bufferInicial = new ArrayList<>(100);
        ArrayList<Dato> bufferDeValidados = new ArrayList<>(100);
        ArrayList<CreadorDeDatos> listaDeCreador = new ArrayList<>(4);
        ArrayList<RevisoresDeDatos> revisoresDeDatos = new ArrayList<>(2);
        ArrayList<ConsumidoresDeDatos> consumidoresDeDatos = new ArrayList<>(2);

        Thread thread[] = new Thread[4];
        for(int i=0; i<4; i++){
            thread[i]= new Thread(new CreadorDeDatos(bufferInicial));
        }

        for(int i=0; i<4; i++){
            thread[i].start();
        }


        //crearCreadores(listaDeCreador, bufferInicial);
        //
        //iniciarCreadores(listaDeCreador);
        //
        //logger(bufferInicial, bufferDeValidados);
    }

    public static void crearCreadores (ArrayList<CreadorDeDatos> listaDeCreador, ArrayList<Dato> bufferInicial) {
        for (int i = 0; i < 4; i++) {
            CreadorDeDatos creadorDeDatos = new CreadorDeDatos(bufferInicial);
            listaDeCreador.add(i, creadorDeDatos);
        }
    }

    public static void iniciarCreadores (ArrayList<CreadorDeDatos> listaDeCreador) {
        Thread hilosCreadores[] = new Thread[4];
        for (int i = 0; i < 4; i++) {
            hilosCreadores[i] = new Thread(listaDeCreador.get(i));
            hilosCreadores[i].start();
        }
    }

    public static void logger(ArrayList<Dato> bufferInicial, ArrayList<Dato> bufferDeValidados){
        try (FileWriter file = new FileWriter(".\\Data\\log.txt"); PrintWriter pw = new PrintWriter(file);) {
            do {
                TimeUnit.SECONDS.sleep(2);
                pw.println("\tTamaño del buffer inial:\t" + bufferInicial.size() +
                        "\tTamaño del buffer de validados:\t" + bufferDeValidados.size() );
                pw.println("=========================================================================\n");
            } while (!finalizado(bufferInicial,bufferDeValidados));
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean finalizado (ArrayList<Dato> bufferInicial, ArrayList<Dato> bufferDeValidados) {
        if(bufferInicial.size() > 0 && bufferDeValidados.size() > 0) {
            return false;
        }
        return true;
    }
}

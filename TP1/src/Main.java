import task.ConsumidoresDeDatos;
import task.CreadorDeDatos;
import task.Dato;
import task.RevisoresDeDatos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
    ArrayList<Dato> bufferInicial;
    ArrayList<Dato> bufferFinal;
    //ArrayList<CreadorDeDatos> listaDeCreador = new ArrayList<>(4);
    //ArrayList<RevisoresDeDatos> revisoresDeDatos = new ArrayList<>(2);
    //ArrayList<ConsumidoresDeDatos> consumidoresDeDatos = new ArrayList<>(2);

    public static void main (String[] args) {
    }



    public static void logger(ArrayList<String> charcito){
        try (FileWriter file = new FileWriter("Data/log.txt"); PrintWriter pw = new PrintWriter(file);) {

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

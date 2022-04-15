package task;

import java.util.ArrayList;
import java.util.Random;

public class CreadorDeDatos implements Runnable{

    private ArrayList<Dato> bufferInicial;
    int lenght;

    public CreadorDeDatos(ArrayList<Dato> bufferInicial) {
        //this.bufferInicial = (ArrayList) bufferInicial.clone();
        int lenght = bufferInicial.size();
    }

    @Override
    public void run() {
        new Dato();
        
        if(lenght <100){
            
        }


    }
}

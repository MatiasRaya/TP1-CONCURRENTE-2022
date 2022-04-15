package task;

import java.util.ArrayList;

public class BufferInicial {

    private int cantidad;                   //Cantidad de datos en el buffer
    private ArrayList<Dato> datos;

    public BufferInicial(){
        datos = new ArrayList<>();

    }

    public void agregarDato(Dato dato){
        //Se debe sincronizar y agregar de un dato a la vez
        if(cantidad < 100){
            datos.add(dato);
        }
    }

    public void revisarDato(int indice){
        if(datos.get(indice).getRevisores().contains(new Object())){        //CORREGIR NEW OBJECTO CON NOMBRE DEL HILO
            //Revisa el dato por un tiempo aleatorio
        }
    }
    
}

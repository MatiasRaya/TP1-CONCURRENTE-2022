package task;

public class CreadorDeDatos implements Runnable{

    private final Buffer buffer;

    public CreadorDeDatos(Buffer bufferInicial){
        this.buffer = bufferInicial;
    }

    @Override
    public void run() {
        while(buffer.getContadorDatosRevisados() < VarGlobales.num_revisiones) {
            buffer.cargarDato();
        }
    }
}

package task;

public class ConsumidorDeDatos implements Runnable {

    private final Buffer buffer;

    public ConsumidorDeDatos(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.getContadorDatosRevisados() < VarGlobales.num_revisiones) {
            try {
                buffer.consumirDato();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

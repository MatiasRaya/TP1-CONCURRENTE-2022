package task;

public class RevisorDeDatos implements Runnable {

    private Buffer buffer;

    public RevisorDeDatos(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.getContadorDatosRevisados() < VarGlobales.num_revisiones) {
            try {
                //Se duerme el hilo ya que los 4 confluyen conjuntamente y para que salten entre hilos los dormimos 0 segundos
                Thread.currentThread().sleep(0);
            }
            catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if (buffer.getBufferInicial().size() > 5) {
                try {
                    buffer.revisarDato();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
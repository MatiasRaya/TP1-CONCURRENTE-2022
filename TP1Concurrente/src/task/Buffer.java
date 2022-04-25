package task;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Buffer {
    private final ArrayList<Dato> bufferInicial;
    private final ArrayList<Dato> bufferDeValidacion;
    private int contadorDatosCreados = 0;
    private int contadorDatosConsumidos = 0;
    private int contadorDatosRevisados = 0;
    private final Semaphore semCrear = new Semaphore(1, true);
    private final Semaphore semConsumir = new Semaphore(1, true);
    private final Semaphore semRevisar = new Semaphore(2, true);
    private final Object llaveBI = new Object();
    private final Object llaveDatos = new Object();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public Buffer (int capacidad) {
        bufferInicial = new ArrayList<>(capacidad);
        bufferDeValidacion = new ArrayList<>(capacidad);
    }

    public ArrayList<Dato> getBufferInicial () {
        return bufferInicial;
    }

    public ArrayList<Dato> getBufferDeValidacion () {
        return bufferDeValidacion;
    }

    public int getcontadorDatosCreados() {
        return contadorDatosCreados;
    }

    public int getContadorDatosRevisados() {
        return contadorDatosRevisados;
    }

    public int getcontadorDatosConsumidos() {
        return contadorDatosConsumidos;
    }

    public void cargarDato () {
        Dato nuevoDato;

        synchronized (this) {
            nuevoDato = new Dato("Dato: ");
        }

        try {
            semCrear.acquire();
            if (bufferInicial.size() < VarGlobales.capacidad_buffer) {
                bufferInicial.add(nuevoDato);
                contadorDatosCreados++;
                Random random = new Random();
                TimeUnit.MILLISECONDS.sleep(random.nextInt(5));
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            semCrear.release();
        }
    }


    public void revisarDato () throws RuntimeException, InterruptedException {
        semRevisar.acquire();

        Random random = new Random();
        Dato aux;

        lock.writeLock().lock();
        int indice = random.nextInt(bufferInicial.size());
        aux = bufferInicial.get(indice);
        lock.writeLock().unlock();

        if ((aux != null) && !(aux.getRevisores().contains(Thread.currentThread().getName()))) {
            TimeUnit.MILLISECONDS.sleep(1);
            aux.addRevision(Thread.currentThread().getName());
            
            if (aux.getReviews() == VarGlobales.num_revisores) {
                synchronized (llaveDatos){
                    bufferDeValidacion.add(aux);
                    contadorDatosRevisados++;
                }
            }
        }
        semRevisar.release();
    }

    public void consumirDato() throws InterruptedException {
        semConsumir.acquire();

        if (bufferDeValidacion.size()>0) {
            Random random = new Random();
            TimeUnit.MILLISECONDS.sleep(2);
            Dato aux;
            aux = bufferDeValidacion.remove(random.nextInt(bufferDeValidacion.size()));

            lock.writeLock().lock();
            bufferInicial.remove(aux);
            lock.writeLock().unlock();

            contadorDatosConsumidos++;
        }
        
        semConsumir.release();
    }
}
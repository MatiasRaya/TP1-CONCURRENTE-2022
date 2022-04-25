import task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Buffer buffer = new Buffer(VarGlobales.capacidad_buffer);
    private static LocalTime horaComienzo;
    private static LocalTime horaFinalizacion;
    private static int contador = 0;

    public static void main (String[] args) {
        horaComienzo = LocalTime.parse(LocalTime.now().toString());
        System.out.println("Hora de comienzo: " + horaComienzo);

        int cantCreadores = VarGlobales.num_creadores;
        int cantRevisores = VarGlobales.num_revisores;
        int cantConsumidores = VarGlobales.num_consumidores;

        crearCreadores(buffer, cantCreadores);

        crearRevisores(buffer, cantRevisores);

        crearConsumidores(buffer, cantConsumidores);

        logger(buffer);
    }

    public static void crearCreadores(Buffer buffer, int cantCreadores) {
        Thread[] hiloCreador = new Thread[cantCreadores];
        for (int i = 0; i < cantCreadores; i++) {
            hiloCreador[i] = new Thread(new CreadorDeDatos(buffer), "Creador " + (i + 1));
            hiloCreador[i].start();
        }
        System.out.println("Se crearon " + cantCreadores + " creadores");
    }

    public static void  crearRevisores(Buffer buffer, int cantRevisores){
        Thread[] hiloRevisor = new Thread[cantRevisores];
        for (int i = 0; i < cantRevisores; i++) {
            hiloRevisor[i] = new Thread(new RevisorDeDatos(buffer), "Revisor " + (i + 1));
            hiloRevisor[i].start();
        }
        System.out.println("Se crearon " + cantRevisores + " revisores");
    }
    
    public static void crearConsumidores(Buffer buffer, int cantConsumidores){
        Thread[] hiloConsumidores = new Thread[cantConsumidores];
        for (int i = 0; i < cantConsumidores; i++) {
            hiloConsumidores[i] = new Thread(new ConsumidorDeDatos(buffer), "Consumidor " + (i + 1));
            hiloConsumidores[i].start();
        }
        System.out.println("Se crearon " + cantConsumidores + " consumidores");
    }

    public static void logger(Buffer buffer){
        try (FileWriter file = new FileWriter(".\\Data\\log.txt"); PrintWriter pw = new PrintWriter(file)) {
            do {
                TimeUnit.SECONDS.sleep(2);
                pw.println("\t" + LocalDateTime.now() + "\t");
                pw.println("\n\tLa cantidad de datos creados es de:\t" + buffer.getcontadorDatosCreados() +
                        "\n\tLa cantidad de datos revisados es de:\t" + buffer.getContadorDatosRevisados() +
                        "\n\tLa cantidad de datos consumidos es de:\t" + buffer.getcontadorDatosConsumidos() +
                        "\n\n\tTamaño del buffer inicial:\t" + buffer.getBufferInicial().size() +
                        "\n\tTamaño del buffer de validados:\t" + buffer.getBufferDeValidacion().size() +
                        "\n\n=====================================================================================================================\n");
                contador++;
            } while (finalizado(buffer));
            horaFinalizacion = LocalTime.parse(LocalTime.now().toString());
            pw.println("\t" + tiempoEjecucion());
            System.out.println("Hora de finalizacion " + horaFinalizacion);
            System.out.println(tiempoEjecucion());
            System.out.println("Se imprimieron " + contador + " veces en el log.txt");
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Agarre una excepcion");
            e.printStackTrace();
        }
    }

    public static boolean finalizado (Buffer buffer) {
        return buffer.getContadorDatosRevisados() < VarGlobales.num_revisiones;
    }

    public static String tiempoEjecucion () {
        return "Tiempo que demoro la ejecución: " +
                Duration.between(horaComienzo,horaFinalizacion).toHours() + ":" +
                Duration.between(horaComienzo,horaFinalizacion).toMinutes() + ":" +
                Duration.between(horaComienzo,horaFinalizacion).toSeconds() + "." +
                Duration.between(horaComienzo,horaFinalizacion).toMillis() +
                Duration.between(horaComienzo,horaFinalizacion).toNanos();
    }
}

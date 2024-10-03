package p;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria();
        
        // Crea e avvia il thread del forno
        Thread fornoThread = new Thread(new Forno(pizzeria));
        fornoThread.start();
        
        // Crea e avvia i thread dei camerieri
        List<Thread> camerieri = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Thread cameriereThread = new Thread(new Cameriere(pizzeria, i));
            camerieri.add(cameriereThread);
            cameriereThread.start();
        }
        
        // Simulazione dell'ingresso dei clienti
        Random random = new Random();
        while (true) {
            int clienti = random.nextInt(5) + 1;
            if (!pizzeria.clienteEntra(clienti)) {
                System.out.println("Pizzeria piena. Attesa che si liberi un tavolo...");
                try {
                    Thread.sleep(2000); // Attesa prima che nuovi clienti provino a entrare
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
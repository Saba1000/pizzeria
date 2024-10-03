package p;

import java.util.Random;

public class Cameriere implements Runnable {
    private final Pizzeria pizzeria;
    private final int id;
    private final Random random = new Random();
    
    public Cameriere(Pizzeria pizzeria, int id) {
        this.pizzeria = pizzeria;
        this.id = id;
    }

    public void run() {
        while (true) {
            try {
                int azione = random.nextInt(2); // Decide se prendere un ordine o servire pizze
                int tavolo = random.nextInt(Pizzeria.MAX_TAVOLI) + 1;
                if (azione == 0) {
                    pizzeria.prendiOrdine(tavolo);
                } else {
                    int pizze = pizzeria.cuociPizze();
                    pizzeria.serviPizze(tavolo, pizze);
                    pizzeria.clienteEsce();
                }
                Thread.sleep(random.nextInt(2000) + 1000); // Simula il tempo di lavoro
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
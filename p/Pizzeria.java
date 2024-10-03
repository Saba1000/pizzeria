package p;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pizzeria {
    private static final int MAX_TAVOLI = 20;
    private static final int MAX_CLIENTI = 100;
    private static final int MAX_PIZZE_PER_FORNO = 8;
    
    private int tavoliOccupati = 0;
    private int clientiTotali = 0;
    private List<Integer> ordini = new ArrayList<>(); // Lista degli ordini in attesa
    private final Random random = new Random();
    
    public synchronized boolean clienteEntra(int persone) {
        if (clientiTotali + persone <= MAX_CLIENTI && tavoliOccupati < MAX_TAVOLI) {
            clientiTotali += persone;
            tavoliOccupati++;
            System.out.println(persone + " clienti sono stati assegnati ad un tavolo. Tavoli occupati: " + tavoliOccupati);
            return true;
        }
        return false;
    }
    
    public synchronized void clienteEsce() {
        tavoliOccupati--;
        System.out.println("Un tavolo si è liberato. Tavoli occupati: " + tavoliOccupati);
    }
    
    public synchronized void prendiOrdine(int tavolo) {
        int pizze = random.nextInt(MAX_PIZZE_PER_FORNO) + 1;
        ordini.add(pizze);
        System.out.println("Cameriere ha preso l'ordine per il tavolo " + tavolo + ": " + pizze + " pizze.");
        notifyAll(); // Notifica il forno che ci sono nuove pizze da cuocere
    }
    
    public synchronized int cuociPizze() throws InterruptedException {
        while (ordini.isEmpty()) {
            wait(); // Attende che ci siano ordini disponibili
        }
        int pizze = ordini.remove(0); // Preleva il primo ordine
        System.out.println("Forno sta cuocendo " + pizze + " pizze...");
        Thread.sleep(random.nextInt(3000) + 1000); // Simula il tempo di cottura
        System.out.println("Forno ha terminato di cuocere " + pizze + " pizze.");
        return pizze;
    }
    
    public void serviPizze(int tavolo, int pizze) {
        System.out.println("Cameriere ha servito " + pizze + " pizze al tavolo " + tavolo);
    }
}
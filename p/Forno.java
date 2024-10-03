package p;

public class Forno implements Runnable {
    private final Pizzeria pizzeria;
    
    public Forno(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }
    
    public void run() {
        while (true) {
            try {
                pizzeria.cuociPizze(); // Il forno cuoce le pizze in modo continuo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
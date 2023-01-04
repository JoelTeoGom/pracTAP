package otros;

public class PruebaRingActor {
    public static void main(String[] args) throws InterruptedException {
        RingActor hola = new RingActor(5);
        hola.afegir();
        hola.sendp("hola mundo soy Joel");
    }
}

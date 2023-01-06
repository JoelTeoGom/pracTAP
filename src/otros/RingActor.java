package otros;

import Estructura.*;
import HelloWorld.*;


public class RingActor extends Actor {
    private int tamany;
    private ActorProxy[] elems;

    /**
     * constructor del Ring Actor
     * @param tamany
     */
    public RingActor(int tamany) {
        super();
        this.tamany = tamany;
        this.elems = new ActorProxy[this.tamany];
    }

    /**
     * metode per inicialitzar el ring actor, és a dir, crear el actors per a que després puguin enviar el missatge
     * - Es crearan tants de actors com el tamany al que s'inicialitza la taula
     */
    public void afegir() {
        for (int i = 0; i < tamany; i++) {
            String name = "Actor" + i;
            elems[i] = ActorContext.getInstance().spawnActor(name, new Actor());
        }
    }

    /**
     * metode per enviar el missatge entre els actors del ring actor
     * quan arriba al ultim actor aquest li retorna el missatge al primer
     * @param text
     * @throws InterruptedException
     */
    public void sendp(String text) throws InterruptedException {
        for (int i = 0; i < tamany; i++) {
            if(i == tamany-1){
                elems[0].send(new HelloWorldMessage(elems[i], text + " " + i));
            }else {
                elems[i + 1].send(new HelloWorldMessage(elems[i], text + " " + i));
            }
        }
    }
}
package otros;

import Estructura.*;
import HelloWorld.*;


public class RingActor extends Actor {
    private int tamany;
    private ActorProxy[] elems;

    public RingActor(int tamany) {
        super();
        this.tamany = tamany;
        this.elems = new ActorProxy[this.tamany];
    }

    public void afegir() {
        for (int i = 0; i < tamany; i++) {
            String name = "Actor" + i;
            elems[i] = ActorContext.getInstance().spawnActor(name, new Actor());
        }
    }

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
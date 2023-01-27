package Aplicacion;

import Estructura.Actor;
import Estructura.ActorContext;
import Message.*;
import RingActor.RingActor;

import java.util.ArrayList;


public class App {
    public static void main(String[] args) throws InterruptedException {

        ArrayList<RingActor> ring = new ArrayList<>();
        ring.add(0,new RingActor());
        int i = 1;
        while (i < 100){
            RingActor anterior = ring.get(i-1);
            RingActor actual = new RingActor();
            anterior.setNext(ActorContext.getInstance().spawnActor("Ring "+i, actual));
            ring.add(i, actual);
            if(i == 99)
                actual.setNext(ActorContext.getInstance().spawnActor("Primero",ring.get(0)));
            i++;
        }
        System.out.println("EMPIEZA EL TIEMPO");
        long start = System.currentTimeMillis();
        for (i = 1; i<=100; i++){
            RingActor inicial = ring.get(1);
            inicial.process(new Message(ActorContext.getInstance().lookup("Primero"),""+i ));
        }

        for (Actor actor : ActorContext.getInstance().getActorThreadHashMap().keySet()) {
            Thread thread = ActorContext.getInstance().getActorThreadHashMap().get(actor);
            thread.join();
        }

        long end = System.currentTimeMillis();
        double total = (double) (end - start) / 1000;
        System.out.println("Total time: "+total+"s");


    }

}

package Aplicacion;

import Estructura.Actor;
import Estructura.ActorContext;
import Estructura.ActorProxy;
import PimPom.PimPomActor;
import PimPom.PimPomMessage;

public class PimPomMain {
    public static void main(String[] args) throws InterruptedException {
        ActorProxy pim = ActorContext.getInstance().spawnActor("Pim",new PimPomActor());
        ActorProxy pom = ActorContext.getInstance().spawnActor("Pom",new PimPomActor());

        System.out.println("EMPIEZA EL TIEMPO");
        long start = System.currentTimeMillis();

        pim.send(new PimPomMessage(pom,"5"));

        for (Actor actor : ActorContext.getInstance().getActorThreadHashMap().keySet()) {
            Thread thread = ActorContext.getInstance().getActorThreadHashMap().get(actor);
            thread.join();
        }

        long end = System.currentTimeMillis();
        double total = (double) (end - start) / 1000;
        System.out.println("Total time: "+total+"s");
    }
}

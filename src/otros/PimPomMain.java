package otros;

import Estructura.ActorContext;
import Estructura.ActorProxy;

public class PimPomMain {
    public static void main(String[] args) throws InterruptedException {
        ActorProxy pim = ActorContext.getInstance().spawnActor("Pim",new PimPomActor());
        ActorProxy pom = ActorContext.getInstance().spawnActor("Pom",new PimPomActor());

        pim.send(new PimPomMessage(pom,"5"));
        System.out.println("/n "+pim.receive().getMessage());

    }
}

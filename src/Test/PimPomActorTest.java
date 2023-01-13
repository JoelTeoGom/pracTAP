package Test;

import Estructura.ActorContext;
import Estructura.ActorProxy;
import org.junit.jupiter.api.Test;
import PimPom.PimPomActor;
import PimPom.PimPomMessage;

class PimPomActorTest {

    @Test
    void PimPomActorProcess() throws InterruptedException {
        ActorProxy pim = ActorContext.getInstance().spawnActor("Pim",new PimPomActor());
        ActorProxy pom = ActorContext.getInstance().spawnActor("Pom",new PimPomActor());
        pim.send(new PimPomMessage(pom,"5"));
    }
}
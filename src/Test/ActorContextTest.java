package Test;

import Estructura.Actor;
import Estructura.ActorContext;
import HelloWorld.HelloWorldActor;
import Insult.InsultActor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorContextTest {

    @Test
    void spawnActor() {
        Actor actor = new HelloWorldActor();
        assertEquals(actor, ActorContext.getInstance().spawnActor("actor", actor).getSourceActor());
    }

    @Test
    void notFoundspawnActor() {
        Actor actor = new InsultActor();
        assertNotEquals(actor, ActorContext.getInstance().spawnActor("Actor2", new InsultActor()).getSourceActor());
    }


}
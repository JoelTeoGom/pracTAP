package Test;

import DynamicProxy.*;
import Estructura.*;
import Insult.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProxyTest {

    @Test
    void invoke() throws InterruptedException {
        ActorProxy actor = ActorContext.getInstance().spawnActor("Actor1",new InsultActor());
        Iservice insulter = (Iservice) DynamicProxy.newInstance(new InsultService(), actor);
        insulter.addInsult("stupid");
        assertEquals("stupid",insulter.getInsult());
    }

    @Test
    void invokeWrongInsult() throws InterruptedException {
        ActorProxy actor = ActorContext.getInstance().spawnActor("Actor1",new InsultActor());
        Iservice insulter = (Iservice) DynamicProxy.newInstance(new InsultService(), actor);
        insulter.addInsult("stupid");
        assertNotEquals("retarded",insulter.getInsult());
    }

}
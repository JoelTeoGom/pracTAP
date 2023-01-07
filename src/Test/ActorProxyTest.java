package Test;

import Estructura.*;
import HelloWorld.HelloWorldActor;
import HelloWorld.HelloWorldMessage;
import Insult.*;
import Message.Message;
import Observer.Event;
import Observer.MonitorService;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ActorProxyTest {

    @Test
    void send() throws InterruptedException {
        ActorProxy hello = ActorContext.getInstance().spawnActor("HelloWorldActor",new HelloWorldActor());
        for(int i = 0; i<5;i++){
            ActorProxy proxy = ActorContext.getInstance().spawnActor("Actor "+i,new Actor());
            hello.send(new HelloWorldMessage(proxy,"Hello World"));
        }
    }

    @Test
    void receiveGetTest() throws InterruptedException {
        ActorProxy insult = ActorContext.getInstance().spawnActor("insulter",new InsultActor());
        insult.send(new AddInsultMessage(null, "Stupid"));
        insult.send(new GetInsultMessage(null));
        assertEquals("Stupid",insult.receive().getMessage());
    }

    @Test
    void receiveGetAllTest() throws InterruptedException {
        var a = new InsultActor();
        ActorProxy insult = ActorContext.getInstance().spawnActor("insulter",a);
        insult.send(new AddInsultMessage(null, "Stupid"));
        insult.send(new AddInsultMessage(null, "Retarded"));
        insult.send(new GetAllInsultMessage(null));
        assertEquals("[Stupid, Retarded]",insult.receive().getMessage());
    }

    @Test
    void receivefromDiferentActorTest() throws InterruptedException {
        ActorProxy pxy = ActorContext.getInstance().spawnActor("insulter", new InsultActor());
        for(int i = 0; i<5; i++) {
            ActorProxy insulter = ActorContext.getInstance().spawnActor("Actor "+i,new Actor());
            pxy.send(new AddInsultMessage(insulter,"taco"+i));
        }
        pxy.send(new GetAllInsultMessage(null));
        assertEquals("[taco0, taco1, taco2, taco3, taco4]",pxy.receive().getMessage());
    }

}
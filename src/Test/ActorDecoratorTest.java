package Test;

import Decorator.EncryptionDecorator;
import Decorator.FirewallDecorator;
import Decorator.LambdaFirewallDecorator;
import Estructura.Actor;
import Estructura.ActorContext;
import Estructura.ActorProxy;
import HelloWorld.HelloWorldMessage;
import Insult.InsultActor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorDecoratorTest {

    @Test
    void process() {
    }

    @Test
    void send() throws InterruptedException {
        ActorProxy p1 = ActorContext.getInstance().spawnActor("Actor1",new FirewallDecorator(new InsultActor()));
        ActorProxy p2 = ActorContext.getInstance().spawnActor("Actor2",new EncryptionDecorator(new InsultActor()));
        ActorProxy p3 = ActorContext.getInstance().spawnActor("Actor3",new LambdaFirewallDecorator(new InsultActor()));
        ActorProxy p = ActorContext.getInstance().spawnActor2("Actor",new  InsultActor());

        String h = "holaaa soc pepito";
        p2.getSourceActor().send(new HelloWorldMessage(p,h));
        p2.getSourceActor().process(new HelloWorldMessage(p,p.receive().getMessage()));

        p3.getSourceActor().send(new HelloWorldMessage(p,h));

        p1.getSourceActor().process(new HelloWorldMessage(p,h));

        p1.getSourceActor().process(new HelloWorldMessage(p2,h));
    }
}
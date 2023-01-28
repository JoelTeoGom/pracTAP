package Test;

import Decorator.EncryptionDecorator;
import Decorator.FirewallDecorator;
import Decorator.LambdaFirewallDecorator;
import Estructura.Actor;
import Estructura.ActorContext;
import Estructura.ActorProxy;
import HelloWorld.HelloWorldMessage;
import org.junit.jupiter.api.Test;





class DecoratorTest {
    ActorProxy p1 = ActorContext.getInstance().spawnActor("Actor1",new FirewallDecorator(new Actor()));
    ActorProxy p2 = ActorContext.getInstance().spawnActor("Actor2",new EncryptionDecorator(new Actor()));
    ActorProxy p3 = ActorContext.getInstance().spawnActor("Actor3",new LambdaFirewallDecorator(new Actor()));
    ActorProxy p = ActorContext.getInstance().spawnActor2("Actor",new Actor());
    String h = "abc";


    @Test
    void testEncryptionDecorator() throws InterruptedException {
        p2.send(new HelloWorldMessage(p,h));
    }



    @Test
    void testLambdaFirewallDecorator() throws InterruptedException {
        p3.send(new HelloWorldMessage(p,h));

    }

    @Test
    void testLambdaFirewallDecorator2() throws InterruptedException {
        p3.send(new HelloWorldMessage(p,"hola"));

    }
    @Test
    void testFirewallDecoratorFindCorrectly() throws InterruptedException {
        p1.send(new HelloWorldMessage(p2,h));
        
    }
    @Test
    void testFirewallDecoratorNotFind() throws InterruptedException {
        p1.send(new HelloWorldMessage(p,h));

    }



}
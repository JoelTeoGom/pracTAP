package Decorator;

import Estructura.*;
import HelloWorld.*;

public class DecoratorDemo {

    public static void main(String[] args) throws InterruptedException {
        ActorProxy p1 = ActorContext.getInstance().spawnActor("Actor1",new FirewallDecorator(new Actor()));
        ActorProxy p2 = ActorContext.getInstance().spawnActor("Actor2",new EncryptionDecorator(new Actor()));
        ActorProxy p3 = ActorContext.getInstance().spawnActor("Actor3",new LambdaFirewallDecorator(new Actor()));
        ActorProxy p = ActorContext.getInstance().spawnActor2("Actor",new Actor());

        String h = "holaaa soc pepito";
        p2.getSourceActor().send(new HelloWorldMessage(p,h));
        p2.getSourceActor().process(new HelloWorldMessage(p,p.receive().getMessage()));

        p3.getSourceActor().send(new HelloWorldMessage(p,h));

        p1.getSourceActor().process(new HelloWorldMessage(p,h));

        p1.getSourceActor().process(new HelloWorldMessage(p2,h));
    }

}

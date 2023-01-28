package Aplicacion;

import Decorator.EncryptionDecorator;
import Decorator.FirewallDecorator;
import Decorator.LambdaFirewallDecorator;
import Estructura.*;
import HelloWorld.*;
import Insult.*;

public class DecoratorDemo {

    public static void main(String[] args) throws InterruptedException {
        ActorProxy p1 = ActorContext.getInstance().spawnActor("Actor1",new FirewallDecorator(new InsultActor()));
        ActorProxy p2 = ActorContext.getInstance().spawnActor("Actor2",new FirewallDecorator(new EncryptionDecorator(new InsultActor())));
        ActorProxy p3 = ActorContext.getInstance().spawnActor("Actor3",new FirewallDecorator(new LambdaFirewallDecorator(new EncryptionDecorator(new HelloWorldActor()))));
        ActorProxy p = ActorContext.getInstance().spawnActor("Actor",new  InsultActor());
        String h = "olaaa soc pepito";
        p3.send(new HelloWorldMessage(p,h));
        //p3.send(new HelloWorldMessage(p,h));
    }

}

package Aplicacion;

import Estructura.Actor;
import Estructura.ActorContext;
import Estructura.ActorProxy;
import HelloWorld.HelloWorldActor;
import HelloWorld.HelloWorldMessage;
import Insult.*;
import Message.*;


public class App {
    public static void main(String[] args) throws InterruptedException {

        ActorProxy insult = ActorContext.getInstance().spawnActor("ACTOR",new InsultActor());
        insult.send(new GetInsultMessage(null));
        Message result = insult.receive();
        System.out.println(result.getMessage());

    }

}

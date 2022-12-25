package Aplicacion;

import Decorator.FirewallDecorator;
import Estructura.Actor;
import Estructura.ActorContext;
import Estructura.ActorProxy;
import HelloWorld.HelloWorldActor;
import HelloWorld.HelloWorldMessage;
import Insult.*;
import Message.*;
import Observer.*;


public class App {
    public static void main(String[] args) throws InterruptedException {

        ActorProxy insult = ActorContext.getInstance().spawnActor("ACTOR",new InsultActor());
        ActorProxy hello = ActorContext.getInstance().spawnActor("HELLO", new HelloWorldActor());
        MonitorService.getInstance().monitorAllActor();

        Observer o1 = new ActionListener();
        Observer o2 = new ActionListener();
        Observer o3 = new ActionListener();
        MonitorService.getInstance().subscribe("ACTOR",o1);
        MonitorService.getInstance().subscribe("ACTOR",o2);
        MonitorService.getInstance().subscribe("ACTOR",o3);
        MonitorService.getInstance().subscribe("HELLO",o1);
        MonitorService.getInstance().subscribe("HELLO",o2);
        MonitorService.getInstance().subscribe("HELLO",o3);

        hello.send(new HelloWorldMessage(null,"hello"));
        insult.send(new GetInsultMessage(null));
        Message result = insult.receive();
        System.out.println(result.getMessage());

        hello.send(new QuitMessage(null,"adios"));
        insult.send(new QuitMessage(null, "ADIOS"));
//        ActorProxy proxy1 = ActorContext.getInstance().spawnActor("Actor1", new HelloWorldActor());
//        ActorProxy proxy2 = ActorContext.getInstance().spawnActor("Actor2", new Actor());

//        proxy1.send(new HelloWorldMessage(proxy2, "Hola Mundo!!!!!!!!"));

//        proxy2.send(new QuitMessage(null,"adios"));





    }

}

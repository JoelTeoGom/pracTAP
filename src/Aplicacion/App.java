package Aplicacion;

import Estructura.Actor;
import Estructura.ActorContext;
import Estructura.ActorProxy;
import Message.*;
import Observer.*;


public class App {
    public static void main(String[] args) throws InterruptedException {

        ActorProxy p1 = ActorContext.getInstance().spawnActor("Actor1", new Actor());
        ActorProxy p2 = ActorContext.getInstance().spawnActor("Actor2", new Actor());

        MonitorService.getInstance().monitorAllActor();

        int i = 0;
        while (i < 9){
            p1.send(new Message(null,"message"+i));
            p1.send(new Message(p2,"message"+i));
            i++;
        }
        p1.send(new QuitMessage(p2,"adios"));
        System.out.println("El mensaje es :"+p2.receive().getMessage());

        Thread.sleep(100);       //sleep para procesar los mensajes
        System.out.println(MonitorService.getInstance().getTraffic().toString());

        MonitorService.getInstance().getReceivedMessages(p1.getSourceActor()).forEach(m -> System.out.println(m.getMessage()));
        MonitorService.getInstance().getNumberOfMessages(p1.getSourceActor()).forEach(m -> System.out.println(m.getMessage()));
        MonitorService.getInstance().getSentMessages(p1.getSourceActor()).forEach(m -> System.out.println(m.getMessage()));
        MonitorService.getInstance().getEventsActor(p1.getSourceActor()).forEach(e -> System.out.println(e.toString()));
//        ActorProxy insult = ActorContext.getInstance().spawnActor("ACTOR",new InsultActor());
//        ActorProxy hello = ActorContext.getInstance().spawnActor("HELLO", new HelloWorldActor());
//
//        MonitorService.getInstance().monitorAllActor();
//
//        Observer o1 = new ActionListener();
//        Observer o2 = new ActionListener();
//        Observer o3 = new ActionListener();
//        MonitorService.getInstance().subscribe("ACTOR",o1);
//        MonitorService.getInstance().subscribe("ACTOR",o2);
//        MonitorService.getInstance().subscribe("ACTOR",o3);
//        MonitorService.getInstance().subscribe("HELLO",o1);
//        MonitorService.getInstance().subscribe("HELLO",o2);
//        MonitorService.getInstance().subscribe("HELLO",o3);
//
//        hello.send(new HelloWorldMessage(null,"hello"));
//        insult.send(new GetInsultMessage(null));
//        Message result = insult.receive();
//        System.out.println(result.getMessage());
//
//        hello.send(new QuitMessage(null,"adios"));
//        insult.send(new QuitMessage(null, "ADIOS"));

    }

}

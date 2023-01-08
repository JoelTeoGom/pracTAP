package Test;

import Estructura.Actor;
import Estructura.ActorContext;
import Estructura.ActorProxy;
import HelloWorld.HelloWorldActor;
import HelloWorld.HelloWorldMessage;
import Insult.InsultActor;
import Message.Message;
import org.junit.jupiter.api.Test;
import otros.RingActor;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RingActorTest {

    @Test
    void ringActorTest() throws InterruptedException {
        ArrayList<RingActor> ring = new ArrayList<>();
        ring.add(0,new RingActor());
        int i = 1;
        while (i < 100){
            RingActor anterior = ring.get(i-1);
            RingActor actual = new RingActor();
            anterior.setNext(ActorContext.getInstance().spawnActor("Ring "+i, actual));
            ring.add(i, actual);
            if(i == 99)
                actual.setNext(ActorContext.getInstance().spawnActor("Primero",ring.get(0)));
            i++;
        }
        long start = System.currentTimeMillis();
        for (i = 1; i<=100; i++){
            RingActor inicial = ring.get(1);
            inicial.process(new Message(ActorContext.getInstance().lookup("Primero"),""+i ));
        }
        long end = System.currentTimeMillis();
        double total = (double) (end - start) / 1000;
        System.out.println("Total time: "+total+"s");
    }
}
package Test;

import Estructura.Actor;
import Estructura.ActorContext;
import Estructura.ActorProxy;
import HelloWorld.HelloWorldMessage;
import Insult.AddInsultMessage;
import Insult.GetInsultMessage;
import Insult.InsultActor;
import Message.*;
import Observer.ActionListener;
import Observer.MonitorService;
import Observer.Observer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonitorServiceTest {



    @Test
    void monitorActor() {
        Actor actor = new InsultActor();
        ActorContext.getInstance().spawnActor("actor",actor);
        assertTrue(MonitorService.getInstance().getMonitoredActor().containsKey(actor));
    }


    @Test
    void subscribe() {
        Actor actor = new InsultActor();
        ActorContext.getInstance().spawnActor("actor",actor);
        Observer o1 = new ActionListener();
        MonitorService.getInstance().subscribe("actor",o1);
        assertTrue(MonitorService.getInstance().getLlistaActorsObserver().get(actor).contains(o1));
    }

    @Test
    void unsubscribe() {
        Actor actor = new InsultActor();
        ActorContext.getInstance().spawnActor("actor",actor);
        Observer o1 = new ActionListener();
        Observer o2 = new ActionListener();
        MonitorService.getInstance().subscribe("actor",o1);
        MonitorService.getInstance().subscribe("actor",o2);
        MonitorService.getInstance().unsubscribe("actor",o1);
        assertFalse(MonitorService.getInstance().getLlistaActorsObserver().get(actor).contains(o1));
    }

    @Test
    void notifyAllObservers() throws InterruptedException {

        ActorProxy insult = ActorContext.getInstance().spawnActor("ACTOR",new InsultActor());

        MonitorService.getInstance().subscribe("ACTOR",new ActionListener());
        MonitorService.getInstance().subscribe("ACTOR",new ActionListener());
        MonitorService.getInstance().subscribe("ACTOR",new ActionListener());

        insult.send(new AddInsultMessage(null,"stupid"));
        insult.send(new GetInsultMessage(null));
        insult.send(new QuitMessage(null, "ADIOS"));
    }
}
package DynamicProxy;

import Estructura.*;
import Insult.*;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public static void main(String[] args) throws InterruptedException {


        ActorProxy actor = ActorContext.getInstance().spawnActor("Actor1",new InsultActor());
        Iservice insulter = (Iservice) DynamicProxy.newInstance(new InsultService(), actor);
        insulter.addInsult("stupid");
        System.out.println(insulter.getInsult());


    }
}

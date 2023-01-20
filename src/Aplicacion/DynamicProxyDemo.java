package Aplicacion;

import DynamicProxy.DynamicProxy;
import DynamicProxy.Iservice;
import Estructura.*;
import Insult.*;
import DynamicProxy.*;
import Observer.*;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public static void main(String[] args) throws InterruptedException {

        ActorProxy actor = ActorContext.getInstance().spawnActor("Actor1",new InsultActor());
        Iservice insulter = (Iservice) DynamicProxy.newInstance(new InsultService(), actor);
        insulter.addInsult("stupid");
        insulter.addInsult("noob");
        insulter.addInsult("retarded");
        System.out.println(insulter.getInsult());
        System.out.println(insulter.getAllInsult());


    }
}

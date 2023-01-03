package DynamicProxy;

import Estructura.ActorProxy;
import Insult.AddInsultMessage;
import Insult.GetAllInsultMessage;
import Insult.GetInsultMessage;
import Message.Message;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

    private Object target = null;
    private ActorProxy actorProxy = null;

    public static Object newInstance(Object target, ActorProxy actorProxy){
        Class<?> targetClass = target.getClass();
        Class[] interfaces = targetClass.getInterfaces();
        return Proxy.newProxyInstance(targetClass.getClassLoader(), interfaces, new DynamicProxy(target, actorProxy));
    }
    private DynamicProxy(Object target, ActorProxy actorProxy) {
        this.target = target;
        this.actorProxy = actorProxy;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        Object invocationResult = null;
        try
        {
            switch (method.getName()){
                case "addInsult":
                    actorProxy.send(new AddInsultMessage(actorProxy,args[0].toString()));
                    break;
                case "getAllInsult":
                    actorProxy.send(new GetAllInsultMessage(actorProxy));
                    invocationResult = actorProxy.receive().getMessage();
                    break;
                case "getInsult":
                    actorProxy.send(new GetInsultMessage(actorProxy));
                    invocationResult = actorProxy.receive().getMessage();
                    break;
                default:
                    System.out.println("Before method " + method.getName());
                    invocationResult = method.invoke(this.target, args);
                    System.out.println("After method " + method.getName());
            }

        }
        catch(InvocationTargetException ite)
        {
            throw ite.getTargetException();
        }
        catch(Exception e)
        {
            System.err.println("Invocation of " + method.getName() + " failed");
        }
        finally{
            return invocationResult;
        }
    }




}

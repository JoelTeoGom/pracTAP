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

    private Object target;
    private ActorProxy actorProxy;

    /**
     *  metode static que fa una nova instancia del objecte
     * @param target
     * @param actorProxy
     * @return object
     */

    public static Object newInstance(Object target, ActorProxy actorProxy){
        Class<?> targetClass = target.getClass();
        Class[] interfaces = targetClass.getInterfaces();
        return Proxy.newProxyInstance(targetClass.getClassLoader(), interfaces, new DynamicProxy(target, actorProxy));
    }

    /**
     * constructor
     * @param target
     * @param actorProxy
     */
    private DynamicProxy(Object target, ActorProxy actorProxy) {
        this.target = target;
        this.actorProxy = actorProxy;
    }

    /**
     *
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
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

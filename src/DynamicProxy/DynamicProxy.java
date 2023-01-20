package DynamicProxy;

import Estructura.ActorProxy;
import Insult.AddInsultMessage;
import Insult.GetAllInsultMessage;
import Insult.GetInsultMessage;
import Message.Message;

import java.lang.annotation.Target;
import java.lang.reflect.*;

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

            for(Field field: target.getClass().getDeclaredFields()){
                if(field.getType().equals(ActorProxy.class))
                    field.set(target,actorProxy);
            }
            invocationResult = method.invoke(target, args);

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

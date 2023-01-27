package Estructura;

import Message.Message;

public class ProxyProxy implements Iactor{

    private final ActorProxy sourceProxy;

    public ProxyProxy(ActorProxy sourceProxy) {
        this.sourceProxy = sourceProxy;
    }


    /**
     * @param message
     * @throws InterruptedException
     */
    @Override
    public void send(Message message) throws InterruptedException {
        this.sourceProxy.getQueue().put(message);
    }
}

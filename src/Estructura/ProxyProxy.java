package Estructura;

import Message.Message;

public class ProxyProxy implements Iactor {
    private final ActorProxy sourceProxy;

    public ProxyProxy(ActorProxy sourceProxy) {
        this.sourceProxy = sourceProxy;
    }

    @Override
    public void send(Message message) {

    }
}

package DynamicProxy;

import Estructura.ActorProxy;
import Insult.AddInsultMessage;
import Insult.GetAllInsultMessage;
import Insult.GetInsultMessage;
import Message.Message;

public class InsultService implements Iservice{

    ActorProxy actorProxy = null;
    /**
     * Afegeix insult
     * @param insult
     * @throws InterruptedException
     */
    @Override
    public void addInsult(String insult) throws InterruptedException {
        actorProxy.send(new AddInsultMessage(actorProxy,insult));
    }

    /**
     * agafa els insults
     * @throws InterruptedException
     */
    @Override
    public String getAllInsult() throws InterruptedException {
        actorProxy.send(new GetAllInsultMessage(actorProxy));
        return actorProxy.receive().getMessage();
    }

    /**
     * retorna insult
     */
    @Override
    public String getInsult() throws InterruptedException {
        actorProxy.send(new GetInsultMessage(actorProxy));
        return actorProxy.receive().getMessage();
    }
}

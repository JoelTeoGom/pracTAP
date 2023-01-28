package Decorator;

import Estructura.Actor;
import Message.Message;

/**
 * will encrypt (send) and decrypt message text (process) between
 *     communicating Actors
 */


public class EncryptionDecorator extends ActorDecorator {
    /**
     * Constructor
     * @param actor
     */
    public EncryptionDecorator (Actor actor){
        super(actor);
    }

    /**
     * Encripta el missatge
     * @param message misstage
     * @throws InterruptedException
     */
    @Override
    public void send(Message message) throws InterruptedException {
        String text = message.getMessage();
        char[] chars = text.toCharArray();
        for (int i=0;i<chars.length;i++){
            chars[i] += 3;
        }
        message.setMessage(String.valueOf(chars));
        System.out.println("missatge encriptat: "+message.getMessage());
        actor.send(message);

    }

    /**
     * Desencripta el missatge
     * @param m misstage
     * @throws InterruptedException
     */
    @Override
    public void process(Message m) throws InterruptedException {
        String text = m.getMessage();

        m.setMessage(String.valueOf(m.getMessage()));
        char[] chars = text.toCharArray();

        for (int i=0;i<chars.length;i++){
            chars[i] -= 3;
        }

        m.setMessage(String.valueOf(chars));
        System.out.println("missatge desencriptat: "+m.getMessage());
        actor.process(m);
    }
}

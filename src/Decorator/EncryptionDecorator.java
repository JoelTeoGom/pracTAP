package Decorator;

import Estructura.Actor;
import Message.Message;



/*
    will encrypt (send) and decrypt message text (process) between
    communicating Actors
 */
public abstract class EncryptionDecorator extends ActorDecorator {
    public EncryptionDecorator (Actor actor){super(actor);}

    @Override
    public void send(Message message) throws InterruptedException {
        String text = message.getMessage();
        char[] chars = text.toCharArray();
        for (int i=0;i<chars.length;i++){
            chars[i] -= 5;
        }
        message.setMessage(String.valueOf(chars));
        super.send(message);

    }

    @Override
    public void process(Message m) throws InterruptedException {
        String text = m.getMessage();
        char[] chars = text.toCharArray();
        for (int i=0;i<chars.length;i++){
            chars[i] -= 5;
        }
        m.setMessage(String.valueOf(chars));
        super.process(m);
    }
}

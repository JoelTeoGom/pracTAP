package Decorator;

import Estructura.Actor;
import Message.Message;
import java.util.Arrays;


/*
    will encrypt (send) and decrypt message text (process) between
    communicating Actors
 */
public class EncryptionDecorator extends ActorDecorator {
    public EncryptionDecorator (Actor actor){super(actor);}

    @Override
    public void send(Message message) throws InterruptedException {
        String text = message.getMessage();
        char[] chars = text.toCharArray();
        for (char c: chars){
            c +=5;
        }
        message.setMessage(Arrays.toString(chars));
        super.send(message);

    }

    @Override
    public void process(Message m) throws InterruptedException {
        String text = m.getMessage();
        char[] chars = text.toCharArray();
        for (char c: chars){
            c -=5;
        }
        m.setMessage(Arrays.toString(chars));
        super.process(m);
    }
}

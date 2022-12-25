package Decorator;

import Message.Message;

import java.util.Queue;

public interface ActorInterface {

    public void send(Message message) throws InterruptedException;

    public void process(Message m) throws InterruptedException;

    public Queue<Message> getQueue();

    public void setQueue(Queue<Message> queue);

    public String getState();

    public void setState(String state);


    public Boolean getExit();

    public void setExit(Boolean exit);


}

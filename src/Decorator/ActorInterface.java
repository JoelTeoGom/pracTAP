package Decorator;

import Message.Message;

import java.util.Queue;

public interface ActorInterface {
    public void run();

    public void send(Message message) throws InterruptedException;

    public void setExit();

    public void process(Message m) throws InterruptedException;

    public Queue<Message> getQueue();

    public void setQueue(Queue<Message> queue);

    public String getState();

    public void setState(String state);

    public Thread getThread();

    public void setThread(Thread thread);

    public Boolean getExit();

    public void setExit(Boolean exit);


}

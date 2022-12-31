package Decorator;

import Message.Message;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public interface ActorInterface {

    public void send(Message message) throws InterruptedException;

    public void process(Message m) throws InterruptedException;

    public BlockingQueue<Message> getQueue();

    public void setQueue(BlockingQueue<Message> queue);

    public Boolean getExit();

    public void setExit(Boolean exit);


}

package Estructura;

import Message.Message;

public interface Iactor {
    void send(Message message) throws InterruptedException;
}

package HelloWorld;

import Estructura.Actor;
import Message.Message;

public class HelloWorldActor extends Actor{
    public HelloWorldActor() {
        super();
    }

    @Override
    public void process(Message m){  //en esta funcion actualizaremos estado
        switch (m){
            case HelloWorldMessage m1:
                System.out.printf(m1.getMessage());
                break;
            default : System.out.printf("No se ha registrado");
        }

    }
}

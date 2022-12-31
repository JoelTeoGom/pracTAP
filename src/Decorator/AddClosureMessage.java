package Decorator;

import Estructura.Actor;

import java.util.function.Predicate;

/*
    Create a LambdaFirewallDecorator that accepts closures to filter the messages than can be received
    using an AddClosureMessage.

    predicate->
 */
public abstract class AddClosureMessage<T> extends LambdaFirewallDecorator {

    public AddClosureMessage(Actor actor) {
        super(actor);
    }




    /*
    public Predicate<String> hasLengthOf10 = new Predicate<String>() {
        @Override
        public boolean test(String t)
        {
            return t.length() > 10;
        }
    }

     */
}


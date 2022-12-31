package Decorator;

import Estructura.Actor;

import Message.Message;

/*
Create a LambdaFirewallDecorator that accepts closures to filter the messages than can be received
using an AddClosureMessage.
 */
public abstract class LambdaFirewallDecorator extends ActorDecorator {

    public LambdaFirewallDecorator(Actor actor) {super(actor);}


}



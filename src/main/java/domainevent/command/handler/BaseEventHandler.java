package domainevent.command.handler;

import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventData;
import msa.commons.event.EventId;

public abstract class BaseEventHandler implements CommnadHandler {
    protected IEventPublisher jmsEventPublisher;

    @Override
    public void handle(EventData data) {
        this.jmsEventPublisher.publish(this.sendEventId(), data);
    }

    public abstract void setJmsEventPublisher(IEventPublisher jmsEventPublisher);
    public abstract EventId sendEventId(); 
}
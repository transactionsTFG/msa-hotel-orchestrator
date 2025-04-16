package domainevent.command.room;

import javax.ejb.Local;
import javax.ejb.Stateless;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelroom.qualifier.ValidateRoomsQualifier;

@Stateless
@ValidateRoomsQualifier
@Local(CommnadHandler.class)
public class ValidateRoomsEvent extends BaseEventHandler {

    @Override
    public void setJmsEventPublisher(IEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.VALIDATE_HOTEL_ROOMS;
    }

}

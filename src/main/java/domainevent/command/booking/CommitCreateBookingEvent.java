package domainevent.command.booking;

import javax.ejb.Local;
import javax.ejb.Stateless;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelbooking.qualifier.CommitCreateBookingEventQualifier;

@Stateless
@CommitCreateBookingEventQualifier
@Local(CommnadHandler.class)
public class CommitCreateBookingEvent extends BaseEventHandler {

    @Override
    public void setJmsEventPublisher(IEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.COMMIT_CREATE_HOTEL_BOOKING;
    }
    
}

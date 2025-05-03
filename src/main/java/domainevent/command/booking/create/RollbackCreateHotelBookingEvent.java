package domainevent.command.booking.create;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.hotelbooking.RollbackCreateHotelBookingEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommandHandler;
import domainevent.publisher.bookingqueue.JMSBookingPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

@Stateless
@RollbackCreateHotelBookingEventQualifier
@Local(CommandHandler.class)
public class RollbackCreateHotelBookingEvent extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventPublisher(@JMSBookingPublisherQualifier IEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.ROLLBACK_CREATE_HOTEL_BOOKING;
    }
    
}

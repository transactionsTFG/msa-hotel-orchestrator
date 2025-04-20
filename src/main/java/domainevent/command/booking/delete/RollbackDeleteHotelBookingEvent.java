package domainevent.command.booking.delete;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommandHandler;
import domainevent.publisher.bookingqueue.JMSBookingPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelbooking.qualifier.RollbackDeleteHotelBookingEventQualifier;

@Stateless
@RollbackDeleteHotelBookingEventQualifier
@Local(CommandHandler.class)
public class RollbackDeleteHotelBookingEvent extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventPublisher(@JMSBookingPublisherQualifier IEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.ROLLBACK_DELETE_HOTEL_BOOKING;
    }

}

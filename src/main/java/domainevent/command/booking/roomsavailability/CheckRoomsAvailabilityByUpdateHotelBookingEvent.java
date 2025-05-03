package domainevent.command.booking.roomsavailability;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.hotelbooking.CheckRoomsAvailabilityByUpdateHotelBookingEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommandHandler;
import domainevent.publisher.bookingqueue.JMSBookingPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;

@Stateless
@CheckRoomsAvailabilityByUpdateHotelBookingEventQualifier
@Local(CommandHandler.class)
public class CheckRoomsAvailabilityByUpdateHotelBookingEvent extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventPublisher(@JMSBookingPublisherQualifier IEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.CHECK_ROOMS_AVAILABILITY_BY_UPDATE_HOTEL_BOOKING;
    }

}

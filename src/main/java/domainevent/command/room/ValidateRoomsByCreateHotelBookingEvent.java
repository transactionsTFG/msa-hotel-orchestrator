package domainevent.command.room;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.hotelroom.ValidateHotelRoomsByCreateHotelBookingQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommandHandler;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.roomqueue.JMSRoomPublisherQualifier;
import msa.commons.event.EventId;

@Stateless
@ValidateHotelRoomsByCreateHotelBookingQualifier
@Local(CommandHandler.class)
public class ValidateRoomsByCreateHotelBookingEvent extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventPublisher(@JMSRoomPublisherQualifier IEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.VALIDATE_HOTEL_ROOMS_BY_CREATE_HOTEL_BOOKING;
    }

}

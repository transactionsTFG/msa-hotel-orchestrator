package domainevent.command.room;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.roomqueue.JMSRoomPublisherQualifier;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelroom.qualifier.ValidateRoomsByCreateHotelBookingEventQualifier;

@Stateless
@ValidateRoomsByCreateHotelBookingEventQualifier
@Local(CommnadHandler.class)
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

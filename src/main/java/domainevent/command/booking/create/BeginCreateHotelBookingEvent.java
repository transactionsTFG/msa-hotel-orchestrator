package domainevent.command.booking.create;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import business.qualifier.hotelbooking.BeginCreateHotelBookingEventQualifier;
import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommandHandler;
import domainevent.publisher.bookingqueue.JMSBookingPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import domainevent.publisher.orchestratoragencyqueue.JMSOrchestratorAgencyQualifier;
import msa.commons.event.EventData;
import msa.commons.event.EventId;
import msa.commons.event.eventoperation.reservation.CreateReservation;

@Stateless
@BeginCreateHotelBookingEventQualifier
@Local(CommandHandler.class)
public class BeginCreateHotelBookingEvent extends BaseEventHandler {
    private IEventPublisher jmsEventDispatcherAgency;

    @Override
    public void handle(EventData data) {
        if (CreateReservation.CREATE_RESERVATION_ONLY_HOTEL_BEGIN.equals(data.getOperation())) 
            this.jmsEventPublisher.publish(this.sendEventId(), data);
        else 
            this.jmsEventDispatcherAgency.publish(this.sendEventId(), data);
    }

    @Override
    @Inject
    public void setJmsEventPublisher(@JMSBookingPublisherQualifier IEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }

    @Inject
    public void setJmsEventDispatcherAgency(@JMSOrchestratorAgencyQualifier IEventPublisher jmsEventDispatcher) {
        this.jmsEventDispatcherAgency = jmsEventDispatcher;
    }


    @Override
    public EventId sendEventId() {
        return EventId.CREATE_RESERVATION_TRAVEL;
    }

}

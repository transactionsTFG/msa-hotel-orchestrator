package domainevent.command.customer;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommandHandler;
import domainevent.publisher.customerqueue.JMSCustomerPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelcustomer.qualifier.GetCustomerByCreateHotelBookingEventQualifier;

@Stateless
@GetCustomerByCreateHotelBookingEventQualifier
@Local(CommandHandler.class)
public class GetCustomerByCreateHotelBookingEvent extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventPublisher(@JMSCustomerPublisherQualifier IEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.GET_HOTEL_CUSTOMER;
    }

}

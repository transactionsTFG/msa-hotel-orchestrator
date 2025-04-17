package domainevent.command.customer;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.customerqueue.JMSCustomerPublisherQualifier;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelcustomer.qualifier.ValidateHotelCustomerByCreateHotelBookingEventQualifier;

@Stateless
@ValidateHotelCustomerByCreateHotelBookingEventQualifier
@Local(CommnadHandler.class)
public class ValidateHotelCustomerByCreateHotelBookingEvent extends BaseEventHandler {

    @Override
    @Inject
    public void setJmsEventPublisher(@JMSCustomerPublisherQualifier IEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.VALIDATE_HOTEL_CUSTOMER_BY_CREATE_HOTEL_BOOKING;
    }
    
}

package domainevent.command.customer;

import javax.ejb.Local;
import javax.ejb.Stateless;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelcustomer.qualifier.GetCustomerByCreateBookingEventQualifier;

@Stateless
@GetCustomerByCreateBookingEventQualifier
@Local(CommnadHandler.class)
public class GetCustomerByCreateBookingEvent extends BaseEventHandler {

    @Override
    public void setJmsEventPublisher(IEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.GET_HOTEL_CUSTOMER;
    }

}

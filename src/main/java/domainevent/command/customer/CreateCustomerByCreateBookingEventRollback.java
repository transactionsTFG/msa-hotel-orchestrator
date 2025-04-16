package domainevent.command.customer;

import javax.ejb.Local;
import javax.ejb.Stateless;

import domainevent.command.handler.BaseEventHandler;
import domainevent.command.handler.CommnadHandler;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelcustomer.qualifier.CreateCustomerByCreateBookingEventRollbackQualifier;

@Stateless
@CreateCustomerByCreateBookingEventRollbackQualifier
@Local(CommnadHandler.class)
public class CreateCustomerByCreateBookingEventRollback extends BaseEventHandler {

    @Override
    public void setJmsEventPublisher(IEventPublisher jmsEventPublisher) {
        this.jmsEventPublisher = jmsEventPublisher;
    }

    @Override
    public EventId sendEventId() {
        return EventId.ROLLBACK_CREATE_CUSTOMER_BY_HOTEL_BOOKING;
    }

}

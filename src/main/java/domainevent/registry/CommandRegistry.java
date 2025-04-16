package domainevent.registry;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import domainevent.command.handler.CommnadHandler;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelbooking.qualifier.CommitCreateBookingEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.RollbackCreateBookingEventQualifier;
import msa.commons.microservices.hotelcustomer.qualifier.CreateCustomerByCreateBookingEventCommitQualifier;
import msa.commons.microservices.hotelcustomer.qualifier.CreateCustomerByCreateBookingEventRollbackQualifier;
import msa.commons.microservices.hotelcustomer.qualifier.GetCustomerByCreateBookingEventQualifier;
import msa.commons.microservices.hotelroom.qualifier.ValidateRoomsQualifier;

@Singleton
@Startup
public class CommandRegistry {
    private Map<EventId, CommnadHandler> handlers = new EnumMap<>(EventId.class);
    private CommnadHandler commitCreateBookingEvent;
    private CommnadHandler rollbackCreateBookingEvent;
    private CommnadHandler createCustomerByCreateBookingEventCommit;
    private CommnadHandler createCustomerByCreateBookingEventRollback;
    private CommnadHandler getCustomerByCreateBookingEvent;
    private CommnadHandler validateRoomsEvent;

    @PostConstruct
    public void init() {
        this.handlers.put(EventId.COMMIT_CREATE_HOTEL_BOOKING, commitCreateBookingEvent);
        this.handlers.put(EventId.ROLLBACK_CREATE_HOTEL_BOOKING, rollbackCreateBookingEvent);
        this.handlers.put(EventId.COMMIT_CREATE_CUSTOMER_BY_HOTEL_BOOKING, createCustomerByCreateBookingEventCommit);
        this.handlers.put(EventId.ROLLBACK_CREATE_CUSTOMER_BY_HOTEL_BOOKING,
                createCustomerByCreateBookingEventRollback);
        this.handlers.put(EventId.GET_HOTEL_CUSTOMER, getCustomerByCreateBookingEvent);
        this.handlers.put(EventId.VALIDATE_HOTEL_ROOMS, validateRoomsEvent);
    }

    public CommnadHandler getHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

    @Inject
    public void setCommitCreateBookingEvent(
            @CommitCreateBookingEventQualifier CommnadHandler commitCreateBookingEvent) {
        this.commitCreateBookingEvent = commitCreateBookingEvent;
    }

    @Inject
    public void setRollbackCreateBookingEvent(
            @RollbackCreateBookingEventQualifier CommnadHandler rollbackCreateBookingEvent) {
        this.rollbackCreateBookingEvent = rollbackCreateBookingEvent;
    }

    @Inject
    public void setCreateCustomerByCreateBookingEventCommit(
            @CreateCustomerByCreateBookingEventCommitQualifier CommnadHandler createCustomerByCreateBookingEventCommit) {
        this.createCustomerByCreateBookingEventCommit = createCustomerByCreateBookingEventCommit;
    }

    @Inject
    public void setCreateCustomerByCreateBookingEventRollback(
            @CreateCustomerByCreateBookingEventRollbackQualifier CommnadHandler createCustomerByCreateBookingEventRollback) {
        this.createCustomerByCreateBookingEventRollback = createCustomerByCreateBookingEventRollback;
    }

    @Inject
    public void setGetCustomerByCreateBookingEvent(
            @GetCustomerByCreateBookingEventQualifier CommnadHandler getCustomerByCreateBookingEvent) {
        this.getCustomerByCreateBookingEvent = getCustomerByCreateBookingEvent;
    }

    @Inject
    public void setvalidateRoomsEvent(@ValidateRoomsQualifier CommnadHandler validateRoomsEvent) {
        this.validateRoomsEvent = validateRoomsEvent;
    }

}
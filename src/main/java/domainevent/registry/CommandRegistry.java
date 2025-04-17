package domainevent.registry;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import domainevent.command.handler.CommnadHandler;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelbooking.qualifier.CommitCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.RollbackCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelcustomer.qualifier.CommitCreateCustomerByCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelcustomer.qualifier.RollbackCreateCustomerByCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelcustomer.qualifier.ValidateHotelCustomerByCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelcustomer.qualifier.GetCustomerByCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelroom.qualifier.ValidateRoomsByCreateHotelBookingEventQualifier;

@Singleton
@Startup
public class CommandRegistry {
    private Map<EventId, CommnadHandler> handlers = new EnumMap<>(EventId.class);
    private CommnadHandler commitCreateBookingEvent;
    private CommnadHandler rollbackCreateBookingEvent;
    private CommnadHandler createCustomerByCreateBookingEventCommit;
    private CommnadHandler createCustomerByCreateBookingEventRollback;
    private CommnadHandler getCustomerByCreateBookingEvent;
    private CommnadHandler validateHotelCustomerByCreateHotelBookingEvent;
    private CommnadHandler validateRoomsEvent;

    @PostConstruct
    public void init() {
        this.handlers.put(EventId.COMMIT_CREATE_HOTEL_BOOKING, commitCreateBookingEvent);
        this.handlers.put(EventId.ROLLBACK_CREATE_HOTEL_BOOKING, rollbackCreateBookingEvent);
        this.handlers.put(EventId.COMMIT_CREATE_CUSTOMER_BY_HOTEL_BOOKING, createCustomerByCreateBookingEventCommit);
        this.handlers.put(EventId.ROLLBACK_CREATE_CUSTOMER_BY_HOTEL_BOOKING,
                createCustomerByCreateBookingEventRollback);
        this.handlers.put(EventId.GET_HOTEL_CUSTOMER, getCustomerByCreateBookingEvent);
        this.handlers.put(EventId.VALIDATE_HOTEL_CUSTOMER_BY_CREATE_HOTEL_BOOKING, validateHotelCustomerByCreateHotelBookingEvent);
        this.handlers.put(EventId.VALIDATE_HOTEL_ROOMS_BY_CREATE_HOTEL_BOOKING, validateRoomsEvent);
    }

    public CommnadHandler getHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

    @Inject
    public void setCommitCreateBookingEvent(
            @CommitCreateHotelBookingEventQualifier CommnadHandler commitCreateBookingEvent) {
        this.commitCreateBookingEvent = commitCreateBookingEvent;
    }

    @Inject
    public void setRollbackCreateBookingEvent(
            @RollbackCreateHotelBookingEventQualifier CommnadHandler rollbackCreateBookingEvent) {
        this.rollbackCreateBookingEvent = rollbackCreateBookingEvent;
    }

    @Inject
    public void setCreateCustomerByCreateBookingEventCommit(
            @CommitCreateCustomerByCreateHotelBookingEventQualifier CommnadHandler createCustomerByCreateBookingEventCommit) {
        this.createCustomerByCreateBookingEventCommit = createCustomerByCreateBookingEventCommit;
    }

    @Inject
    public void setCreateCustomerByCreateBookingEventRollback(
            @RollbackCreateCustomerByCreateHotelBookingEventQualifier CommnadHandler createCustomerByCreateBookingEventRollback) {
        this.createCustomerByCreateBookingEventRollback = createCustomerByCreateBookingEventRollback;
    }

    @Inject
    public void setGetCustomerByCreateBookingEvent(
            @GetCustomerByCreateHotelBookingEventQualifier CommnadHandler getCustomerByCreateBookingEvent) {
        this.getCustomerByCreateBookingEvent = getCustomerByCreateBookingEvent;
    }

    @Inject
    public void setValidateHotelCustomerByCreateHotelBookingEvent(
        @ValidateHotelCustomerByCreateHotelBookingEventQualifier CommnadHandler validateHotelCustomerByCreateHotelBookingEvent
    ) {
        this.validateHotelCustomerByCreateHotelBookingEvent = validateHotelCustomerByCreateHotelBookingEvent;
    }

    @Inject
    public void setValidateRoomsEvent(@ValidateRoomsByCreateHotelBookingEventQualifier CommnadHandler validateRoomsEvent) {
        this.validateRoomsEvent = validateRoomsEvent;
    }

}
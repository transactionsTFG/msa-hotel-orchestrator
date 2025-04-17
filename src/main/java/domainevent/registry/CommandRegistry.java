package domainevent.registry;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import domainevent.command.handler.CommandHandler;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelbooking.qualifier.CheckRoomsAvailabilityByCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.CommitCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.RollbackCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelcustomer.qualifier.CommitCreateCustomerByCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelcustomer.qualifier.GetCustomerByCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelcustomer.qualifier.RollbackCreateCustomerByCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelcustomer.qualifier.ValidateHotelCustomerByCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelroom.qualifier.ValidateHotelRoomsByCreateHotelBookingQualifier;

@Singleton
@Startup
public class CommandRegistry {
    private Map<EventId, CommandHandler> handlers = new EnumMap<>(EventId.class);
    private CommandHandler commitCreateBookingEvent;
    private CommandHandler rollbackCreateBookingEvent;
    private CommandHandler createCustomerByCreateBookingEventCommit;
    private CommandHandler createCustomerByCreateBookingEventRollback;
    private CommandHandler getCustomerByCreateBookingEvent;
    private CommandHandler validateHotelCustomerByCreateHotelBookingEvent;
    private CommandHandler checkRoomsAvailabilityByCreateHotelBookingEvent;
    private CommandHandler validateRoomsEvent;

    @PostConstruct
    public void init() {
        this.handlers.put(EventId.COMMIT_CREATE_HOTEL_BOOKING, commitCreateBookingEvent);
        this.handlers.put(EventId.ROLLBACK_CREATE_HOTEL_BOOKING, rollbackCreateBookingEvent);
        this.handlers.put(EventId.COMMIT_CREATE_CUSTOMER_BY_HOTEL_BOOKING, createCustomerByCreateBookingEventCommit);
        this.handlers.put(EventId.ROLLBACK_CREATE_CUSTOMER_BY_HOTEL_BOOKING,
                createCustomerByCreateBookingEventRollback);
        this.handlers.put(EventId.GET_HOTEL_CUSTOMER, getCustomerByCreateBookingEvent);
        this.handlers.put(EventId.VALIDATE_HOTEL_CUSTOMER_BY_CREATE_HOTEL_BOOKING,
                validateHotelCustomerByCreateHotelBookingEvent);
        this.handlers.put(EventId.CONFIRM_VALIDATE_HOTEL_CUSTOMER_BY_CREATE_HOTEL_BOOKING,
                validateRoomsEvent);
        this.handlers.put(EventId.CANCEL_VALIDATE_HOTEL_CUSTOMER_BY_CREATE_HOTEL_BOOKING,
                rollbackCreateBookingEvent);
        this.handlers.put(EventId.VALIDATE_HOTEL_ROOMS_BY_CREATE_HOTEL_BOOKING, validateRoomsEvent);
        this.handlers.put(EventId.CONFIRM_VALIDATE_HOTEL_ROOMS_BY_CREATE_HOTEL_BOOKING,
                checkRoomsAvailabilityByCreateHotelBookingEvent);
        this.handlers.put(EventId.CANCEL_VALIDATE_HOTEL_ROOMS_BY_CREATE_HOTEL_BOOKING, rollbackCreateBookingEvent);
        this.handlers.put(EventId.CONFIRM_CHECK_ROOMS_AVAILABILITY_BY_CREATE_HOTEL_BOOKING, commitCreateBookingEvent);
        this.handlers.put(EventId.CANCEL_CHECK_ROOMS_AVAILABILITY_BY_CREATE_HOTEL_BOOKING, rollbackCreateBookingEvent);
    }

    public CommandHandler getHandler(EventId eventId) {
        return this.handlers.get(eventId);
    }

    @Inject
    public void setCommitCreateBookingEvent(
            @CommitCreateHotelBookingEventQualifier CommandHandler commitCreateBookingEvent) {
        this.commitCreateBookingEvent = commitCreateBookingEvent;
    }

    @Inject
    public void setRollbackCreateBookingEvent(
            @RollbackCreateHotelBookingEventQualifier CommandHandler rollbackCreateBookingEvent) {
        this.rollbackCreateBookingEvent = rollbackCreateBookingEvent;
    }

    @Inject
    public void setCreateCustomerByCreateBookingEventCommit(
            @CommitCreateCustomerByCreateHotelBookingEventQualifier CommandHandler createCustomerByCreateBookingEventCommit) {
        this.createCustomerByCreateBookingEventCommit = createCustomerByCreateBookingEventCommit;
    }

    @Inject
    public void setCreateCustomerByCreateBookingEventRollback(
            @RollbackCreateCustomerByCreateHotelBookingEventQualifier CommandHandler createCustomerByCreateBookingEventRollback) {
        this.createCustomerByCreateBookingEventRollback = createCustomerByCreateBookingEventRollback;
    }

    @Inject
    public void setGetCustomerByCreateBookingEvent(
            @GetCustomerByCreateHotelBookingEventQualifier CommandHandler getCustomerByCreateBookingEvent) {
        this.getCustomerByCreateBookingEvent = getCustomerByCreateBookingEvent;
    }

    @Inject
    public void setValidateHotelCustomerByCreateHotelBookingEvent(
            @ValidateHotelCustomerByCreateHotelBookingEventQualifier CommandHandler validateHotelCustomerByCreateHotelBookingEvent) {
        this.validateHotelCustomerByCreateHotelBookingEvent = validateHotelCustomerByCreateHotelBookingEvent;
    }

    @Inject
    public void setValidateRoomsEvent(
            @ValidateHotelRoomsByCreateHotelBookingQualifier CommandHandler validateRoomsEvent) {
        this.validateRoomsEvent = validateRoomsEvent;
    }

    @Inject
    public void setCheckRoomsAvailabilityByCreateHotelBookingEvent(
            @CheckRoomsAvailabilityByCreateHotelBookingEventQualifier CommandHandler checkRoomsAvailabilityByCreateHotelBookingEvent) {
        this.checkRoomsAvailabilityByCreateHotelBookingEvent = checkRoomsAvailabilityByCreateHotelBookingEvent;
    }

}
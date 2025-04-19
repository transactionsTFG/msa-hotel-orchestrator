package domainevent.registry;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import domainevent.command.handler.CommandHandler;
import msa.commons.event.EventId;
import msa.commons.microservices.hotelbooking.qualifier.RollbackDeleteHotelBookingEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.RollbackDeleteHotelBookingLineEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.CancelGetHotelBookingEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.CheckRoomsAvailabilityByCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.CommitCreateHotelBookingEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.CommitDeleteHotelBookingEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.CommitDeleteHotelBookingLineEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.ConfirmGetHotelBookingEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.BeginDeleteHotelBookingEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.BeginDeleteHotelBookingLineEventQualifier;
import msa.commons.microservices.hotelbooking.qualifier.GetHotelBookingEventQualifier;
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
    private CommandHandler validateHotelRoomsByCreateHotelBookingEvent;
    private CommandHandler getHotelBookingEvent;
    private CommandHandler confirmGetHotelBookingEvent;
    private CommandHandler cancelGetHotelBookingEvent;
    private CommandHandler beginDeleteHotelBookingEvent;
    private CommandHandler commitDeleteHotelBookingEvent;
    private CommandHandler rollbackDeleteHotelBookingEvent;
    private CommandHandler beginDeleteHotelBookingLineEvent;
    private CommandHandler commitDeleteHotelBookingLineEvent;
    private CommandHandler rollbackDeleteHotelBookingLineEvent;

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
                validateHotelRoomsByCreateHotelBookingEvent);
        this.handlers.put(EventId.CANCEL_VALIDATE_HOTEL_CUSTOMER_BY_CREATE_HOTEL_BOOKING,
                rollbackCreateBookingEvent);
        this.handlers.put(EventId.VALIDATE_HOTEL_ROOMS_BY_CREATE_HOTEL_BOOKING,
                validateHotelRoomsByCreateHotelBookingEvent);
        this.handlers.put(EventId.CONFIRM_VALIDATE_HOTEL_ROOMS_BY_CREATE_HOTEL_BOOKING,
                checkRoomsAvailabilityByCreateHotelBookingEvent);
        this.handlers.put(EventId.CANCEL_VALIDATE_HOTEL_ROOMS_BY_CREATE_HOTEL_BOOKING, rollbackCreateBookingEvent);
        this.handlers.put(EventId.CONFIRM_CHECK_ROOMS_AVAILABILITY_BY_CREATE_HOTEL_BOOKING, commitCreateBookingEvent);
        this.handlers.put(EventId.CANCEL_CHECK_ROOMS_AVAILABILITY_BY_CREATE_HOTEL_BOOKING, rollbackCreateBookingEvent);
        this.handlers.put(EventId.GET_HOTEL_BOOKING, getHotelBookingEvent);
        this.handlers.put(EventId.CONFIRM_GET_HOTEL_BOOKING, confirmGetHotelBookingEvent);
        this.handlers.put(EventId.CANCEL_GET_HOTEL_BOOKING, cancelGetHotelBookingEvent);
        this.handlers.put(EventId.BEGIN_DELETE_HOTEL_BOOKING, beginDeleteHotelBookingEvent);
        this.handlers.put(EventId.COMMIT_DELETE_HOTEL_BOOKING, commitDeleteHotelBookingEvent);
        this.handlers.put(EventId.ROLLBACK_DELETE_HOTEL_BOOKING, rollbackDeleteHotelBookingEvent);
        this.handlers.put(EventId.BEGIN_DELETE_HOTEL_BOOKINGLINE, beginDeleteHotelBookingLineEvent);
        this.handlers.put(EventId.COMMIT_DELETE_HOTEL_BOOKINGLINE, commitDeleteHotelBookingLineEvent);
        this.handlers.put(EventId.ROLLBACK_DELETE_HOTEL_BOOKINGLINE, rollbackDeleteHotelBookingLineEvent);
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
    public void setValidateHotelRoomsByCreateHotelBookingEvent(
            @ValidateHotelRoomsByCreateHotelBookingQualifier CommandHandler validateRoomsEvent) {
        this.validateHotelRoomsByCreateHotelBookingEvent = validateRoomsEvent;
    }

    @Inject
    public void setCheckRoomsAvailabilityByCreateHotelBookingEvent(
            @CheckRoomsAvailabilityByCreateHotelBookingEventQualifier CommandHandler checkRoomsAvailabilityByCreateHotelBookingEvent) {
        this.checkRoomsAvailabilityByCreateHotelBookingEvent = checkRoomsAvailabilityByCreateHotelBookingEvent;
    }

    @Inject
    public void setGetHotelBookingEvent(
            @GetHotelBookingEventQualifier CommandHandler getHotelBookingEvent) {
        this.getHotelBookingEvent = getHotelBookingEvent;
    }

    @Inject
    public void setConfirmGetHotelBookingEvent(
            @ConfirmGetHotelBookingEventQualifier CommandHandler confirmGetHotelBookingEvent) {
        this.confirmGetHotelBookingEvent = confirmGetHotelBookingEvent;
    }

    @Inject
    public void setCancelGetHotelBookingEvent(
            @CancelGetHotelBookingEventQualifier CommandHandler cancelGetHotelBookingEvent) {
        this.cancelGetHotelBookingEvent = cancelGetHotelBookingEvent;
    }

    @Inject
    public void setBeginDeleteHotelBookingEvent(
            @BeginDeleteHotelBookingEventQualifier CommandHandler deleteHotelBookingEvent) {
        this.beginDeleteHotelBookingEvent = deleteHotelBookingEvent;
    }

    @Inject
    public void setCommitDeleteHotelBookingEvent(
            @CommitDeleteHotelBookingEventQualifier CommandHandler confirmDeleteHotelBookingEvent) {
        this.commitDeleteHotelBookingEvent = confirmDeleteHotelBookingEvent;
    }

    @Inject
    public void setRollbackDeleteHotelBookingEvent(
            @RollbackDeleteHotelBookingEventQualifier CommandHandler cancelDeleteHotelBookingEvent) {
        this.rollbackDeleteHotelBookingEvent = cancelDeleteHotelBookingEvent;
    }

    @Inject
    public void setBeginDeleteHotelBookingLineEvent(
            @BeginDeleteHotelBookingLineEventQualifier CommandHandler deleteHotelBookingLineEvent) {
        this.beginDeleteHotelBookingLineEvent = deleteHotelBookingLineEvent;
    }

    @Inject
    public void setCommitDeleteHotelBookingLineEvent(
            @CommitDeleteHotelBookingLineEventQualifier CommandHandler confirmDeleteHotelBookingLineEvent) {
        this.commitDeleteHotelBookingLineEvent = confirmDeleteHotelBookingLineEvent;
    }

    @Inject
    public void setRollbackDeleteHotelBookingLineEvent(
            @RollbackDeleteHotelBookingLineEventQualifier CommandHandler cancelDeleteHotelBookingLineEvent) {
        this.rollbackDeleteHotelBookingLineEvent = cancelDeleteHotelBookingLineEvent;
    }

}
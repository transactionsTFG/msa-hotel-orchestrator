package domainevent.registry;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.oracle.state.ext.listener.StateCallback.Event;

import business.qualifier.hotelbooking.BeginCreateHotelBookingEventQualifier;
import business.qualifier.hotelbooking.BeginDeleteHotelBookingEventQualifier;
import business.qualifier.hotelbooking.BeginDeleteHotelBookingLineEventQualifier;
import business.qualifier.hotelbooking.BeginUpdateHotelBookingEventQualifier;
import business.qualifier.hotelbooking.CancelGetHotelBookingEventQualifier;
import business.qualifier.hotelbooking.CheckRoomsAvailabilityByCreateHotelBookingEventQualifier;
import business.qualifier.hotelbooking.CheckRoomsAvailabilityByUpdateHotelBookingEventQualifier;
import business.qualifier.hotelbooking.CommitCreateHotelBookingEventQualifier;
import business.qualifier.hotelbooking.CommitDeleteHotelBookingEventQualifier;
import business.qualifier.hotelbooking.CommitDeleteHotelBookingLineEventQualifier;
import business.qualifier.hotelbooking.CommitUpdateHotelBookingEventQualifier;
import business.qualifier.hotelbooking.ConfirmGetHotelBookingEventQualifier;
import business.qualifier.hotelbooking.GetHotelBookingEventQualifier;
import business.qualifier.hotelbooking.RollbackCreateHotelBookingEventQualifier;
import business.qualifier.hotelbooking.RollbackDeleteHotelBookingEventQualifier;
import business.qualifier.hotelbooking.RollbackDeleteHotelBookingLineEventQualifier;
import business.qualifier.hotelbooking.RollbackUpdateHotelBookingEventQualifier;
import business.qualifier.hotelcustomer.CommitCreateCustomerByCreateHotelBookingEventQualifier;
import business.qualifier.hotelcustomer.GetCustomerByCreateHotelBookingEventQualifier;
import business.qualifier.hotelcustomer.RollbackCreateCustomerByCreateHotelBookingEventQualifier;
import business.qualifier.hotelcustomer.ValidateHotelCustomerByCreateHotelBookingEventQualifier;
import business.qualifier.hotelcustomer.ValidateHotelCustomerByUpdateHotelBookingEventQualifier;
import business.qualifier.hotelroom.ValidateHotelRoomsByCreateHotelBookingQualifier;
import business.qualifier.hotelroom.ValidateHotelRoomsByUpdateHotelBookingEventQualifier;
import domainevent.command.handler.CommandHandler;
import jnr.ffi.annotations.In;
import msa.commons.event.EventId;

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
    private CommandHandler commitUpdateHotelBookingEvent;
    private CommandHandler rollbackUpdateHotelBookingEvent;
    private CommandHandler validateHotelCustomerByUpdateHotelBookingEvent;
    private CommandHandler validateHotelRoomsByUpdateHotelBookingEvent;
    private CommandHandler checkRoomsAvailabilityByUpdateHotelBookingEvent;
    private CommandHandler beginCreateReservationHotel;
    private CommandHandler beginUpdateReservationHotel;

    @PostConstruct
    public void init() {
        this.handlers.put(EventId.CREATE_RESERVATION_TRAVEL, beginCreateReservationHotel);
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
        
        this.handlers.put(EventId.REMOVE_RESERVATION_TRAVEL, beginDeleteHotelBookingEvent);
        this.handlers.put(EventId.COMMIT_DELETE_HOTEL_BOOKING, commitDeleteHotelBookingEvent);
        this.handlers.put(EventId.ROLLBACK_DELETE_HOTEL_BOOKING, rollbackDeleteHotelBookingEvent);
        this.handlers.put(EventId.BEGIN_DELETE_HOTEL_BOOKINGLINE, beginDeleteHotelBookingLineEvent);
        this.handlers.put(EventId.COMMIT_DELETE_HOTEL_BOOKINGLINE, commitDeleteHotelBookingLineEvent);
        this.handlers.put(EventId.ROLLBACK_DELETE_HOTEL_BOOKINGLINE, rollbackDeleteHotelBookingLineEvent);
        this.handlers.put(EventId.COMMIT_UPDATE_HOTEL_BOOKING, commitUpdateHotelBookingEvent);
        this.handlers.put(EventId.ROLLBACK_UPDATE_HOTEL_BOOKING, rollbackUpdateHotelBookingEvent);

        this.handlers.put(EventId.UPDATE_RESERVATION_TRAVEL, beginUpdateReservationHotel);
        this.handlers.put(EventId.VALIDATE_HOTEL_CUSTOMER_BY_UPDATE_HOTEL_BOOKING,
                validateHotelCustomerByUpdateHotelBookingEvent);
        this.handlers.put(EventId.CONFIRM_VALIDATE_HOTEL_CUSTOMER_BY_UPDATE_HOTEL_BOOKING,
                validateHotelRoomsByUpdateHotelBookingEvent);
        this.handlers.put(EventId.CANCEL_VALIDATE_HOTEL_CUSTOMER_BY_UPDATE_HOTEL_BOOKING,
                rollbackUpdateHotelBookingEvent);
        this.handlers.put(EventId.VALIDATE_HOTEL_ROOMS_BY_UPDATE_HOTEL_BOOKING,
                validateHotelRoomsByUpdateHotelBookingEvent);
        this.handlers.put(EventId.CONFIRM_VALIDATE_HOTEL_ROOMS_BY_UPDATE_HOTEL_BOOKING,
                checkRoomsAvailabilityByUpdateHotelBookingEvent);
        this.handlers.put(EventId.CANCEL_VALIDATE_HOTEL_ROOMS_BY_UPDATE_HOTEL_BOOKING, rollbackUpdateHotelBookingEvent);
        this.handlers.put(EventId.CONFIRM_CHECK_ROOMS_AVAILABILITY_BY_UPDATE_HOTEL_BOOKING,
                commitUpdateHotelBookingEvent);
        this.handlers.put(EventId.CANCEL_CHECK_ROOMS_AVAILABILITY_BY_UPDATE_HOTEL_BOOKING,
                rollbackUpdateHotelBookingEvent);
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

    @Inject
    public void setCommitUpdateHotelBookingEvent(
            @CommitUpdateHotelBookingEventQualifier CommandHandler commitUpdateHotelBookingEvent) {
        this.commitUpdateHotelBookingEvent = commitUpdateHotelBookingEvent;
    }

    @Inject
    public void setRollbackUpdateHotelBookingEvent(
            @RollbackUpdateHotelBookingEventQualifier CommandHandler rollbackUpdateHotelBookingEvent) {
        this.rollbackUpdateHotelBookingEvent = rollbackUpdateHotelBookingEvent;
    }

    @Inject
    public void setValidateHotelCustomerByUpdateHotelBookingEvent(
            @ValidateHotelCustomerByUpdateHotelBookingEventQualifier CommandHandler validateHotelCustomerByUpdateHotelBookingEvent) {
        this.validateHotelCustomerByUpdateHotelBookingEvent = validateHotelCustomerByUpdateHotelBookingEvent;
    }

    @Inject
    public void setValidateHotelRoomsByUpdateHotelBookingEvent(
            @ValidateHotelRoomsByUpdateHotelBookingEventQualifier CommandHandler validateHotelRoomsByUpdateHotelBookingEvent) {
        this.validateHotelRoomsByUpdateHotelBookingEvent = validateHotelRoomsByUpdateHotelBookingEvent;
    }

    @Inject
    public void setCheckRoomsAvailabilityByUpdateHotelBookingEvent(
            @CheckRoomsAvailabilityByUpdateHotelBookingEventQualifier CommandHandler checkRoomsAvailabilityByUpdateHotelBookingEvent) {
        this.checkRoomsAvailabilityByUpdateHotelBookingEvent = checkRoomsAvailabilityByUpdateHotelBookingEvent;
    }

    @Inject
    public void setBeginCreateReservationHotel(
            @BeginCreateHotelBookingEventQualifier CommandHandler beginCreateReservationHotel) {
        this.beginCreateReservationHotel = beginCreateReservationHotel;
    }

    @Inject
    public void setBeginUpdateReservationHotel(@BeginUpdateHotelBookingEventQualifier CommandHandler beginUpdateReservationHotel) {
        this.beginUpdateReservationHotel = beginUpdateReservationHotel;
    }
}
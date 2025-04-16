package integration.producer;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import integration.producer.qualifiers.BookingQueue;
import integration.producer.qualifiers.CustomerQueue;
import integration.producer.qualifiers.RoomQueue;
import msa.commons.consts.JMSQueueNames;

@ApplicationScoped
public class ResourceProducer {
    @Produces
    @Resource(lookup = "jms/connectionFactory")
    private ConnectionFactory connectionFactory;

    @Produces
    @Resource(lookup = JMSQueueNames.HOTEL_BOOKING_QUEUE)
    @BookingQueue
    private Queue bookingQueue;

    @Produces
    @Resource(lookup = JMSQueueNames.HOTEL_CUSTOMER_QUEUE)
    @CustomerQueue
    private Queue customerQueue;

    @Produces
    @Resource(lookup = JMSQueueNames.HOTEL_ROOM_QUEUE)
    @RoomQueue
    private Queue roomQueue;

}
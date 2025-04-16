package domainevent.publisher.bookingqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.BookingQueue;
import msa.commons.consts.JMSQueueNames;

@Stateless
@JMSBookingPublisherQualifier
@Local(IEventPublisher.class)
public class JMSBookingPublisher extends BaseJMSEventPublisher {

    @Inject
    @Override
    public void setQueueInject(@BookingQueue Queue queueInject) {
        this.queue = queueInject;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.HOTEL_BOOKING_QUEUE;
    }

}

package domainevent.publisher.roomqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.RoomQueue;
import msa.commons.consts.JMSQueueNames;

@Stateless
@JMSRoomPublisherQualifier
@Local(IEventPublisher.class)
public class JMSRoomPublisher extends BaseJMSEventPublisher {

    @Inject
    @Override
    public void setQueueInject(@RoomQueue Queue queueInject) {
        this.queue = queueInject;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.HOTEL_ROOM_QUEUE;
    }

}

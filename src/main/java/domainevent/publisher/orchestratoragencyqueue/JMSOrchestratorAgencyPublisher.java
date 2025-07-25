package domainevent.publisher.orchestratoragencyqueue;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Queue;

import domainevent.publisher.jmseventpublisher.BaseJMSRemoteAgencyEventPublisher;
import domainevent.publisher.jmseventpublisher.IEventPublisher;
import integration.producer.qualifiers.OrchestratorAgencyQueue;
import msa.commons.consts.JMSQueueNames;

@Stateless
@JMSOrchestratorAgencyQualifier
@Local(IEventPublisher.class)
public class JMSOrchestratorAgencyPublisher extends BaseJMSRemoteAgencyEventPublisher {

    @Override
    @Inject
    public void setQueueInject(@OrchestratorAgencyQueue Queue queueInject) {
        this.queue = queueInject;
    }

    @Override
    public String getQueueName() {
        return JMSQueueNames.REMOTE_ORCHESTRATOR_AGENCY_QUEUE;
    }
    
}

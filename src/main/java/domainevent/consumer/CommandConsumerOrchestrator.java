package domainevent.consumer;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import domainevent.command.handler.CommnadHandler;
import domainevent.registry.CommandRegistry;
import msa.commons.consts.JMSQueueNames;
import msa.commons.event.Event;

@MessageDriven(mappedName = JMSQueueNames.HOTEL_ORCHESTRATOR_QUEUE)
public class CommandConsumerOrchestrator implements MessageListener {
    private Gson gson;
    private CommandRegistry commandRegistry;
    private static final Logger LOGGER = LogManager.getLogger(CommandConsumerOrchestrator.class);

    @Inject
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @EJB
    public void setCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @Override
    public void onMessage(Message msg) {
        try {
            if (msg instanceof TextMessage m) {
                Event event = this.gson.fromJson(m.getText(), Event.class);
                LOGGER.info("Recibido en Cola {}, Evento Id: {}, Mensaje: {}", JMSQueueNames.HOTEL_ORCHESTRATOR_QUEUE,
                        event.getEventId(), event.getValue());
                CommnadHandler commandHandler = this.commandRegistry.getHandler(event.getEventId());
                if (commandHandler != null)
                    commandHandler.handle(event.getValue());
            }
        } catch (Exception e) {
            LOGGER.error("Error al recibir el mensaje: {}", e.getMessage());
        }
    }

}

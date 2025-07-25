package domainevent.consumer;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import domainevent.command.handler.CommandHandler;
import domainevent.registry.CommandRegistry;
import msa.commons.consts.JMSQueueNames;
import msa.commons.event.Event;

@MessageDriven(mappedName = JMSQueueNames.REMOTE_ORCHESTRATOR_AIRLINE_QUEUE) //Se crea esta clase para enganchar con el orquestador Agencia, esto es simplemente cuando se consiga arreglar la conexion entre servidores JNDI
public class CommandConsumerRemoteOrchestrator implements MessageListener {
    private Gson gson;
    private CommandRegistry eventHandlerRegistry;
    private static final Logger LOGGER = LogManager.getLogger(CommandConsumerRemoteOrchestrator.class);

    @Override
    public void onMessage(Message msg) {
         try {
            if(msg instanceof TextMessage m) {
                Event event = this.gson.fromJson(m.getText(), Event.class);
                LOGGER.info("Recibido en Cola {}, Evento Id: {}, Mensaje: {}", JMSQueueNames.AIRLINE_ORCHESTATOR_QUEUE, event.getEventId(), event.getValue());
                CommandHandler commandHandler = this.eventHandlerRegistry.getHandler(event.getEventId());
                if(commandHandler != null)
                    commandHandler.handle(event.getValue());
            }
        } catch (Exception e) {
            LOGGER.error("Error al recibir el mensaje: {}", e.getMessage());
        }
    }

    @Inject
    public void setGson(Gson gson) { this.gson = gson; }
    @EJB
    public void setCommandHandlerRegistry(CommandRegistry commandHandlerRegistry) { this.eventHandlerRegistry = commandHandlerRegistry; }
}
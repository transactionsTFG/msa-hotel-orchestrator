package domainevent.command.handler;

import msa.commons.event.EventData;

public interface CommandHandler {
    void handle(EventData data);
}
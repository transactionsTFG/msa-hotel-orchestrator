package domainevent.command.handler;

import msa.commons.event.EventData;

public interface CommnadHandler {
    void handle(EventData data);
}
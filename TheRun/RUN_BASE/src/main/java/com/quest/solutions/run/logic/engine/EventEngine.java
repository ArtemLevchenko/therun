package com.quest.solutions.run.logic.engine;

import com.quest.solutions.run.db.entity.events.Event;
import com.quest.solutions.run.db.entity.events.GlobalEvent;
import com.quest.solutions.run.db.entity.events.ResponseEventType;
import com.quest.solutions.run.db.entity.events.TypeEvent;
import com.quest.solutions.run.logic.analyzer.EventProxyListener;
import com.quest.solutions.run.logic.random.GameGenerator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arle0814
 */
public class EventEngine extends BaseEngine {

    private static final int[] generateEvent = new int[]{0, 1, 0, 1, 0, 1};

    private List<Event> events;
    private Event eventToExecute;

    public EventEngine(List<Event> events) {
        this.proxyText = new EventProxyListener();
        this.events = null;
        this.eventToExecute = null;
        if (events != null && !events.isEmpty()) {
            this.eventToExecute = new GlobalEvent();
            this.events = new ArrayList<>();
            for (Event event : events) {
                if (TypeEvent.LOAD != ((GlobalEvent) event).getTypeEvent()) {
                    this.events.add(event);
                }
            }
        }
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Event getEventToExecute() {
        return eventToExecute;
    }

    public void setEventToExecute(Event eventToExecute) {
        this.eventToExecute = eventToExecute;
    }

    @Override
    public void run() {
        if (this.events != null && !this.events.isEmpty()) {
            int ev = GameGenerator.randomIntegerIndex(0, 5);
            if (generateEvent[ev] == 1) { // event
                ev = GameGenerator.randomIntegerIndex(0, events.size() - 1);
                eventToExecute = events.get(ev);
            }
        }
    }

}

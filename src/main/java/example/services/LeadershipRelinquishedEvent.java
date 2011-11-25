package example.services;

import org.springframework.context.ApplicationEvent;

public class LeadershipRelinquishedEvent extends ApplicationEvent {

    public LeadershipRelinquishedEvent(Object source) {
        super(source);
    }
}

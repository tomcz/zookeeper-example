package example.services;

import example.SpikeMessage;
import example.SpikeMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpikeMessageListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SpikeMessageRepository repository;

    @Autowired
    public SpikeMessageListener(SpikeMessageRepository repository) {
        this.repository = repository;
    }

    public void handleMessage(SpikeMessage message) {
        logger.info("Received: " + message);
        repository.add(message);
    }
}

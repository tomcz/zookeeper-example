package example.web;

import example.SpikeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequestMapping("/send")
public class SendMessageCommand {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final JmsOperations template;

    // for CGLIB proxies
    public SendMessageCommand() {
        this(null);
    }

    @Autowired
    public SendMessageCommand(JmsOperations template) {
        this.template = template;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity send(@RequestBody String payload) {
        String correlationId = UUID.randomUUID().toString();

        SpikeMessage message = new SpikeMessage(correlationId, payload);
        logger.info("Sending: " + message);
        template.convertAndSend(message);

        return new ResponseEntity<String>(correlationId + "\n", HttpStatus.OK);
    }
}

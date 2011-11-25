package example.web;

import example.SpikeMessage;
import example.SpikeMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
@RequestMapping("/list")
public class ListMessagesPresenter {

    private final SpikeMessageRepository repository;

    // for CGLIB proxies
    public ListMessagesPresenter() {
        this(null);
    }

    @Autowired
    public ListMessagesPresenter(SpikeMessageRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity list() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(messages(), headers, HttpStatus.OK);
    }

    private String messages() {
        Collection<SpikeMessage> messages = repository.list();
        if (messages.isEmpty()) {
            return "NONE\n";
        }
        StringBuilder buf = new StringBuilder();
        for (SpikeMessage message : messages) {
            buf.append(message.getCorrelationId()).append(": ").append(message.getPayload()).append("\n");
        }
        return buf.toString();
    }
}

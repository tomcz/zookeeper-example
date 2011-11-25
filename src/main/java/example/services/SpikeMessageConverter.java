package example.services;

import example.SpikeMessage;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class SpikeMessageConverter implements MessageConverter {

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        SpikeMessage message = (SpikeMessage) object;
        TextMessage msg = session.createTextMessage(message.getPayload());
        msg.setJMSCorrelationID(message.getCorrelationId());
        return msg;
    }

    @Override
    public Object fromMessage(Message msg) throws JMSException, MessageConversionException {
        TextMessage message = (TextMessage) msg;
        String correlationID = message.getJMSCorrelationID();
        return new SpikeMessage(correlationID, message.getText());
    }
}

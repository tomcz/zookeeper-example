package example.services;

import example.SpikeMessage;
import example.SpikeMessageRepository;
import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.buffer.CircularFifoBuffer;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.collections.BufferUtils.synchronizedBuffer;

@Repository
@SuppressWarnings({"unchecked"})
public class BufferedSpikeMessageRepository implements SpikeMessageRepository {

    private final Buffer buffer = synchronizedBuffer(new CircularFifoBuffer(100));

    @Override
    public void add(SpikeMessage message) {
        buffer.add(message);
    }

    @Override
    public Collection<SpikeMessage> list() {
        Collection<SpikeMessage> messages = newArrayList();
        messages.addAll(buffer);
        return messages;
    }
}

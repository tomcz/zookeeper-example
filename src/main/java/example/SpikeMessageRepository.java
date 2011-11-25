package example;

import java.util.Collection;

public interface SpikeMessageRepository {

    void add(SpikeMessage message);

    Collection<SpikeMessage> list();
}

package example;

import com.google.common.base.Objects;

public class SpikeMessage {

    private final String correlationId;
    private final String payload;

    public SpikeMessage(String correlationId, String payload) {
        this.correlationId = correlationId;
        this.payload = payload;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(correlationId)
                .addValue(payload)
                .toString();
    }
}

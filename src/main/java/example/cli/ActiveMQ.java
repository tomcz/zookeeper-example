package example.cli;

import org.apache.activemq.broker.BrokerService;

public class ActiveMQ {

    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        broker.setUseShutdownHook(true);
        broker.setBrokerName("Broker");
        broker.start();
    }
}

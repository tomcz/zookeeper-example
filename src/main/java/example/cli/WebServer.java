package example.cli;

import com.google.common.base.Predicate;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import java.net.ServerSocket;
import java.util.Collection;

import static com.google.common.collect.Collections2.filter;
import static java.util.Arrays.asList;

public class WebServer {

    private Server server;

    public WebServer(int port) {
        server = new Server(port);
    }

    public WebServer stopAtShutdown() {
        server.setStopAtShutdown(true);
        return this;
    }

    public WebServer start() throws Exception {
        WebAppContext context = new WebAppContext("src/main/webapp", "/spike");
        server.addHandler(withoutTaglibs(context));
        server.start();
        return this;
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new WebServer(findFreePort()).stopAtShutdown().start();
    }

    private WebAppContext withoutTaglibs(WebAppContext context) {
        Collection<String> configurationClasses = withoutTagLibs(context.getConfigurationClasses());
        context.setConfigurationClasses(configurationClasses.toArray(new String[configurationClasses.size()]));
        return context;
    }

    private Collection<String> withoutTagLibs(String[] configurationClasses) {
        return filter(asList(configurationClasses), new Predicate<String>() {
            public boolean apply(String input) {
                return !input.endsWith("TagLibConfiguration");
            }
        });
    }

    private static int findFreePort() throws Exception {
        ServerSocket socket = new ServerSocket(0);
        int port = socket.getLocalPort();
        socket.close();
        return port;
    }
}

package example.services;

import com.netflix.curator.framework.recipes.leader.LeaderSelectorListener;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncLeadershipNotifier implements LeadershipNotifier, ApplicationEventPublisherAware, DisposableBean {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void leadershipRelinquished(final LeaderSelectorListener source) {
        executor.execute(new Runnable() {
            public void run() {
                publisher.publishEvent(new LeadershipRelinquishedEvent(source));
            }
        });
    }

    @Override
    public void destroy() throws Exception {
        executor.shutdownNow();
    }
}

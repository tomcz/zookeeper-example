package example.services;

import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.recipes.leader.LeaderSelectorListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.Lifecycle;

public class LifecycleLeaderSelectorListener implements LeaderSelectorListener, DisposableBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Object lock = new Object();

    private final Lifecycle delegate;
    private final long leadershipTimeout;
    private final LeadershipNotifier notifier;

    public LifecycleLeaderSelectorListener(Lifecycle delegate) {
        this(delegate, new NullLeadershipNotifier(), 0);
    }

    public LifecycleLeaderSelectorListener(Lifecycle delegate, LeadershipNotifier notifier, long leadershipTimeout) {
        this.leadershipTimeout = leadershipTimeout;
        this.delegate = delegate;
        this.notifier = notifier;
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        logger.info("Starting: " + delegate);
        delegate.start();

        waitForLockToExpire();

        logger.info("Stopping: " + delegate);
        delegate.stop();

        notifier.leadershipRelinquished(this);
    }

    @Override
    public void notifyClientClosing(CuratorFramework client) {
        logger.info("Client closing");
        destroy();
    }

    @Override
    public void unhandledError(CuratorFramework client, Throwable e) {
        logger.info("Unexpected error: " + e, e);
        destroy();
    }

    @Override
    public void destroy() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    private void waitForLockToExpire() throws Exception {
        synchronized (lock) {
            try {
                lock.wait(leadershipTimeout);
            } catch (InterruptedException e) {
                // expired
            }
        }
    }
}

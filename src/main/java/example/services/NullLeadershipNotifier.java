package example.services;

import com.netflix.curator.framework.recipes.leader.LeaderSelectorListener;

public class NullLeadershipNotifier implements LeadershipNotifier {

    @Override
    public void leadershipRelinquished(LeaderSelectorListener source) {
    }
}

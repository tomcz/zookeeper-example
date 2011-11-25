package example.services;

import com.netflix.curator.framework.recipes.leader.LeaderSelectorListener;

public interface LeadershipNotifier {

    void leadershipRelinquished(LeaderSelectorListener source);
}

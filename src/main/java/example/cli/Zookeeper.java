package example.cli;

import org.apache.zookeeper.server.ZooKeeperServerMain;

import java.io.File;

public class Zookeeper {

    public static void main(String[] args) {
        File dataDir = new File(System.getProperty("user.dir"), "zookeeper-data");
        String[] config = {"2181", dataDir.getAbsolutePath()};
        ZooKeeperServerMain.main(config);
    }
}

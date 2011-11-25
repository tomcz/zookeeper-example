Zookeeper Leadership Selection
==============================

In some large enterprise deployments even though all nodes have the capability to perform a certain task not all
nodes should actually perform that task. Leadership Selection deals with how we can ensure that a task is performed
by one node (the leader) and should the leader become unavailable that another node will take its place.

This project is an example of how to integrate the [Apache Zookeeper](http://zookeeper.apache.org/) service using
the [Curator](https://github.com/Netflix/curator) framework in order to select a single JMS message listener from
among a number of [Spring](http://www.springsource.com/developer/spring) web application nodes.

The three classes involved in this example are:

* example.cli.Zookeeper
  * Starts a single Zookeeper service instance. Do NOT use this class in production, instead please use a
    [quorum](http://zookeeper.apache.org/doc/trunk/zookeeperStarted.html#sc_RunningReplicatedZooKeeper).

* example.cli.ActiveMQ
  * This starts the example ActiveMQ JMS broker.

* example.cli.WebServer
  * This is the Spring web application that you can use to send messages onto the example JMS queue.
  * You can start as many of these as you want for this example since each listens on a random TCP port.

You can send messages to the JMS queue via:

    # this assumes that one of the web application nodes is listening on port 52921
    curl --header "Content-Type: text/plain" --data "Hello Leader" http://localhost:52921/spike/send

You can list all the messages received on the JMS queue by a node via:

    # this assumes that the web application node is listening on port 52921
    curl http://localhost:52921/spike/list

This project is released under the [MIT License](http://www.opensource.org/licenses/mit-license.php).

package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by luxin on 6/7/17.
 */
public class ProxyTestPool {
    public static BlockingQueue<AgentResource> proxyTestPool = new ArrayBlockingQueue<AgentResource>(100000);
}

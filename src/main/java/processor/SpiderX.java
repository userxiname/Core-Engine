package processor;

import consts.BaseConsts;
import model.SiteX;
import utils.StringUtils;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by luxin on 6/8/17.
 */
public class SpiderX {
    private int thread;
    private ProcessorX processorX;
    private SiteX siteX;
    private String proxyIp;
    private String proxyPort;
    private BlockingQueue<String> UrlQueue;

    public SpiderX(ProcessorX processorX) {
        UrlQueue = new ArrayBlockingQueue<String>(BaseConsts.UrlQueue_Max);
        if (StringUtils.isNotEmpty(processorX)) {
            this.processorX = processorX;
        }
    }

    public SpiderX setSiteX(SiteX siteX) {
        this.siteX = siteX;
        return this;
    }

    public SpiderX addUrl(String url) {
        try {
            UrlQueue.put(url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SpiderX setProxy(String proxyIp, String proxyPort) {
        this.proxyIp = proxyIp;
        this.proxyPort = proxyPort;
        return this;
    }

    public void run() {

    }
}

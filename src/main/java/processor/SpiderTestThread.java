/**
 * Created by luxin on 6/30/17.
 */
package processor;

import model.PageX;
import model.ProxyInfoX;
import model.SiteX;
import utils.StringUtils;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by luxin on 6/30/17.
 * Email: luxin0311@live.com
 */
public class SpiderTestThread implements Runnable {

    private SiteX siteX;
    private BlockingQueue<String> taskQ;
    private HttpDownloaderX downloaderX;
    private List<ProxyInfoX> proxyInfoXes;
    private boolean useProxy;
    private ProcessorX processorX;

    public SpiderTestThread(ProcessorX processorX) {
        this.processorX = processorX;
        downloaderX = new HttpDownloaderX();
        useProxy = false;
    }

    //Proxy setting
    public void setUseProxy(boolean useProxy) {
        this.useProxy = useProxy;
    }

    //Tasks pool
    public void setTaskQ(BlockingQueue<String> taskQ) {
        this.taskQ = taskQ;
    }

    //Header
    public void setSiteX(SiteX siteX) {
        this.siteX = siteX;
    }

    //Proxy pool
    public void setProxyInfoXes(List<ProxyInfoX> proxyInfoXes) {
        this.proxyInfoXes = proxyInfoXes;
    }

    public void run() {
        while (true) {
            connect();
        }
    }

    void connect() {
        try {
            String url = taskQ.take();
            if (StringUtils.isNotEmpty(url)) {
                if (useProxy) {
                    //Use proxy
                    downloaderX.setProxy(proxyInfoXes.get(0).getIp(), proxyInfoXes.get(0).getPort());
                }
                downloaderX.get(siteX, url);
            }
            processorX.processor(new PageX().setCurrentUrl(url).setHtml(downloaderX.getHtml()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

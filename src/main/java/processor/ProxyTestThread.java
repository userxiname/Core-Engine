package processor;

import model.AgentResource;
import model.SiteX;
import model.ProxyTestPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AgentResourceService;
import serviceImpl.AgentResourceServiceImpl;
import utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by luxin on 6/7/17.
 */
public class ProxyTestThread implements Runnable {

    private SiteX site = null;
    private String url;
    private AgentResource agentResource;
    private HttpDownloaderX downloader = new HttpDownloaderX();

    private static ApplicationContext context = null;
    private static AgentResourceService agentResourceService = null;

    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        agentResourceService = (AgentResourceServiceImpl) context.getBean("agentResourceServiceImpl");
    }

    public ProxyTestThread(String url) {
        this.url = url;
        site = new SiteX();
        site.setSocketTimeout(10000);
        site.setConnectionRequestTimeout(10000);
        site.setReadTimeout(10000);
        site.setConnectTimeout(10000);
        site.setPageCharset("GB2312");
    }
    
    public void run() {
        while (true) {
            try {
                agentResource = ProxyTestPool.proxyTestPool.take();
                downloader.setProxy(agentResource.getIp(), Integer.parseInt(agentResource.getPort()));
                try {
                    downloader.get(site, url);
                }catch (Exception e){

                }
                //次数++
                agentResource.setTestTimes(agentResource.getTestTimes() + 1);
                //检查时间now
                agentResource.setLastCheckTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                if (StringUtils.isNotEmpty(downloader.getResponse())) {
                    System.out.println(downloader.getResponse().getStatusLine() + " ID:" + Thread.currentThread());
                    System.out.println(StringUtils.xpath(downloader.getHtml(), "//html/body/center/text()"));
                    //成功++
                    agentResource.setTestSuccTimes(agentResource.getTestSuccTimes() + 1);
                    agentResource.setResponseTime(String.valueOf(downloader.getResponseTimeCost()));
                    agentResource.setLastSuccTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                }
                //成功率计算
                agentResource.setTestSuccRate((int) ((agentResource.getTestSuccTimes() / (float) agentResource.getTestTimes()) * 100));
                agentResourceService.update(agentResource);
            } catch (InterruptedException e) {
                System.out.println("Retrieve queue task failed! From thread:" + Thread.currentThread());
            }
        }
    }
}

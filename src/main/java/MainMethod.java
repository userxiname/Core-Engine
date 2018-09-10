import model.AgentResource;
import model.SiteX;
import model.ProxyTestPool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import processor.HttpDownloaderX;
import processor.ProxyTestThread;
import service.AgentResourceService;
import serviceImpl.AgentResourceServiceImpl;

import java.util.List;

/**
 * Created by luxin on 6/4/17.
 */
public class MainMethod {

    private static ApplicationContext context = null;

    private static AgentResourceService agentResourceService = null;

    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        agentResourceService = (AgentResourceServiceImpl) context.getBean("agentResourceServiceImpl");
    }

    public static void main(String args[]) {
        HttpDownloaderX downloader;
        SiteX site = new SiteX();
        site.setConnectTimeout(20000);
        site.setConnectionRequestTimeout(20000);
        site.setSocketTimeout(20000);
        List<AgentResource> resources = agentResourceService.queryByDate(0, 10000);
        for (int i = 0; i < resources.size(); i++) {
            try {
                ProxyTestPool.proxyTestPool.put(resources.get(i));
            } catch (InterruptedException e) {
                System.out.println("加入Queue失败!");
            }
        }

        for (int i = 0; i < 100; i++) {
            new Thread(new ProxyTestThread("http://1212.ip138.com/ic.asp")).start();
        }
//        for (int i = 0; i < resources.size(); i++) {
//            downloader = new HttpDownloaderX(site, resources.get(i).getIp(), Integer.parseInt(resources.get(i).getPort()));
//            downloader.get("http://1212.ip138.com/ic.asp");
//            if (StringUtils.isEmpty(downloader.getResponse())) {
//                System.out.println("连接超时或者网络异常");
//                continue;
//            }
//            System.out.println(downloader.getResponse().getStatusLine() + "Time: " + downloader.getResponseTimeCost());
//            if (StringUtils.isNotEmpty(downloader.getContent())) {
//                System.out.println(StringUtils.xpath(downloader.getContent(), "//html/body/center/text()"));
//            }
//        }
    }
}

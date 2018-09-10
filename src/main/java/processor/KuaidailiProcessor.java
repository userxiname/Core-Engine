package processor;

import model.AgentResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AgentResourceService;
import serviceImpl.AgentResourceServiceImpl;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

/**
 * Created by luxin on 6/4/17.
 */
public class KuaidailiProcessor implements PageProcessor {

    //loadInfo
    private static ApplicationContext context = null;

    private static AgentResourceService agentResourceService = null;

    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        agentResourceService = (AgentResourceServiceImpl) context.getBean("agentResourceServiceImpl");
    }

    private Site site = null;

    public void process(Page page) {
        int index = 0;
        if (page.getUrl().toString().contains("inha")) {
            index = Integer.parseInt(page.getUrl().toString().split("inha/")[1].split("/")[0]);
        } else if (page.getUrl().toString().contains("intr")) {
            index = Integer.parseInt(page.getUrl().toString().split("intr/")[1].split("/")[0]);
        } else if (page.getUrl().toString().contains("outha")) {
            index = Integer.parseInt(page.getUrl().toString().split("outha/")[1].split("/")[0]);
        } else if (page.getUrl().toString().contains("outtr")) {
            index = Integer.parseInt(page.getUrl().toString().split("outtr/")[1].split("/")[0]);
        } else {
            System.out.println("URL有误!");
            return;
        }
        List<AgentResource> agentResources = new ArrayList<AgentResource>();
        List<Selectable> list = page.getHtml().xpath("//*[@id=\"list\"]/table/tbody/tr").nodes();
        for (int i = 0; i < list.size(); i++) {
            AgentResource agentResource = new AgentResource();
            agentResource.setId(1);
            agentResource.setIp(list.get(i).xpath("/tr/td[1]/text()").toString());
            agentResource.setPort(list.get(i).xpath("/tr/td[2]/text()").toString());
            agentResource.setCrtTime(new Date().toString());
            agentResource.setUsedTimes(0);
            agentResource.setPing(0);
            agentResource.setSource("kuaidaili");
            agentResource.setAgentType(list.get(i).xpath("/tr/td[4]/text()").toString());
            agentResource.setLastCheckTime(list.get(i).xpath("/tr/td[7]/text()").toString());
            agentResource.setLastSuccTime(list.get(i).xpath("/tr/td[7]/text()").toString());
            agentResource.setResponseTime(list.get(i).xpath("/tr/td[6]/text()").toString());
            agentResource.setValue("0");
            agentResource.setLocation(list.get(i).xpath("/tr/td[5]/text()").toString());
            agentResources.add(agentResource);
        }
        agentResourceService.addList(agentResources);
        System.out.println(index + "  SUCCEED!   COUNT:" + agentResources.size());
        if (agentResources.size() < 14) {
            System.out.println("EXCEPTION: " + index);
        }
    }

    public Site getSite() {
        return site;
    }

    public KuaidailiProcessor() {
        if (null == site) {
            this.site = Site.me().setDomain("sz.lianjia.com")
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .addHeader("Accept-Encoding", "gzip, deflate, sdch, br")
                    .addHeader("Accept-Language", "en,zh-CN;q=0.8,zh;q=0.6")
                    .setTimeOut(8000)
                    .setSleepTime(1000)
                    .setRetryTimes(3)
                    .setCycleRetryTimes(3);
        }
    }

    public static void main(String args[]) {
        Spider spider = new Spider(new KuaidailiProcessor());
        spider.thread(1);
        for (int i = 1; i <= 1668; i++) {
//            spider.addUrl("http://www.kuaidaili.com/free/inha/" + i + "/");     //Domestic Anonymity
//            spider.addUrl("http://www.kuaidaili.com/free/intr/" + i + "/");     //Domestic Normal
//            spider.addUrl("http://www.kuaidaili.com/free/outha/" + i + "/");    //International Anonymity
//            spider.addUrl("http://www.kuaidaili.com/free/outtr/" + i + "/");    //International Normal
        }
        spider.run();
    }
}

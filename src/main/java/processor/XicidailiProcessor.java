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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luxin on 6/4/17.
 */
public class XicidailiProcessor implements PageProcessor {

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
        if (page.getUrl().toString().contains("nn")) {
            index = Integer.parseInt(page.getUrl().toString().split("nn/")[1]);
        } else if (page.getUrl().toString().contains("nt")) {
            index = Integer.parseInt(page.getUrl().toString().split("nt/")[1]);
        } else if (page.getUrl().toString().contains("wn")) {
            index = Integer.parseInt(page.getUrl().toString().split("wn/")[1]);
        } else if (page.getUrl().toString().contains("wt")) {
            index = Integer.parseInt(page.getUrl().toString().split("wt/")[1]);
        } else {
            System.out.println("URL Error! -->" + page.getUrl());
            return;
        }
        List<AgentResource> agentResources = new ArrayList<AgentResource>();
        List<Selectable> list = page.getHtml().xpath("//*[@id=\"ip_list\"]/tbody/tr").nodes();
        for (int i = 1; i < list.size(); i++) {
            AgentResource agentResource =  new AgentResource();
            agentResource.setId(1);
            agentResource.setIp(list.get(i).xpath("/tr/td[2]/text()").toString());
            agentResource.setPort(list.get(i).xpath("/tr/td[3]/text()").toString());
            agentResource.setCrtTime(new Date().toString());
            agentResource.setUsedTimes(0);
            agentResource.setPing(0);
            agentResource.setSource("xicidaili");
            agentResource.setAgentType(list.get(i).xpath("/tr/td[6]/text()").toString());
            agentResource.setLastCheckTime("20" + list.get(i).xpath("/tr/td[10]/text()").toString());
            agentResource.setLastSuccTime("20" + list.get(i).xpath("/tr/td[10]/text()").toString());
            agentResource.setValue("0");
            agentResource.setLocation(list.get(i).xpath("/tr/td[4]/a/text()").toString());
            agentResource.setLiveTime(list.get(i).xpath("/tr/td[9]/text()").toString());
            agentResource.setSecurity(list.get(i).xpath("/tr/td[5]/text()").toString());
            agentResources.add(agentResource);
        }
        agentResourceService.addList(agentResources);
        System.out.println(index + "  SUCCEED!   COUNT:" + agentResources.size());
        if (agentResources.size() < 99) {
            System.out.println("EXCEPTION: " + index);
        }
    }

    public Site getSite() {
        return site;
    }

    public XicidailiProcessor() {
        if (null == site) {
            this.site = Site.me().setDomain("www.xicidaili.com")
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .addHeader("Accept-Encoding", "gzip, deflate, sdch, br")
                    .addHeader("Accept-Language", "en,zh-CN;q=0.8,zh;q=0.6")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("Host", "www.xicidaili.com")
                    .setTimeOut(10000)
                    .setSleepTime(300)
                    .setRetryTimes(3);
        }
    }

    public static void main(String args[]) {
        Spider spider = new Spider(new XicidailiProcessor());
        spider.thread(5);
        for (int i = 1; i <= 20; i++) {
//            spider.addUrl("http://www.xicidaili.com/nn/" + i);      //Domestic Anonymity
//            spider.addUrl("http://www.xicidaili.com/nt/" + i);      //Domestic nromal
//            spider.addUrl("http://www.xicidaili.com/wn/" + i);      //https
//            spider.addUrl("http://www.xicidaili.com/wt/" + i);      //http
        }
        spider.run();
    }
}

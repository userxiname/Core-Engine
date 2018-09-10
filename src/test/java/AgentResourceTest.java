import model.AgentResource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AgentResourceService;
import serviceImpl.AgentResourceServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by luxin on 6/2/17.
 */
public class AgentResourceTest {

    private ApplicationContext context = null;

    private AgentResourceService agentResourceService = null;

    {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        agentResourceService = (AgentResourceServiceImpl) context.getBean("agentResourceServiceImpl");
    }

    @Test
    public void test(){
        BasicDataSource basicDataSource = (BasicDataSource) context.getBean("dataSource");
        System.out.println(basicDataSource);
    }

    @Test
    public void test2(){
        AgentResource agentResource = agentResourceService.queryById(100);
        System.out.println(agentResource);
    }

    @Test
    public void test3(){
        List<AgentResource> list = agentResourceService.queryAll();
        System.out.println(list.get(1).getPort());
        System.out.println(list);
    }

    @Test
    public void test4(){
        AgentResource agentResource = new AgentResource();
        agentResource.setId(2);
        agentResource.setIp("192.168.1.1");
        agentResource.setPort("8080");
        agentResource.setValue("2");
        agentResource.setSource("baidu");
        agentResource.setUsedTimes(1);
        agentResource.setCrtTime(new Date().toString());
        agentResource.setPing(300);
        agentResourceService.add(agentResource);
        System.out.println();
    }

    @Test
    public void test5(){
        List<AgentResource> agentResource = agentResourceService.queryAllBySource("kuaidsaili");
        System.out.println();
        System.out.println(agentResource.get(5).getSecurity());
    }
}

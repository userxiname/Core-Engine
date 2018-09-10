package serviceImpl;

import dao.AgentResourceDao;
import model.AgentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AgentResourceService;

import java.util.List;

/**
 * Created by luxin on 6/2/17.
 */
@Service("agentResourceServiceImpl")
public class AgentResourceServiceImpl implements AgentResourceService {

    @Autowired
    private AgentResourceDao agentResourceDaoImpl;

    public List<AgentResource> queryAll() {
        return agentResourceDaoImpl.queryAll();
    }

    public void add(AgentResource agentResource) {
        agentResourceDaoImpl.add(agentResource);
    }

    public AgentResource queryById(int id) {
        return agentResourceDaoImpl.queryById(id);
    }

    public void addList(List<AgentResource> agentResources) {
        agentResourceDaoImpl.addList(agentResources);
    }

    public List<AgentResource> queryByDate(int page, int capacity) {
        return agentResourceDaoImpl.queryByDate(page, capacity);
    }

    public List<AgentResource> queryAllBySource(String source) {
        return agentResourceDaoImpl.queryAllBySource(source);
    }

    public AgentResource queryByIp(String ip) {
        return agentResourceDaoImpl.queryByIp(ip);
    }

    public void update(AgentResource agentResource) {
        agentResourceDaoImpl.update(agentResource);
    }
}

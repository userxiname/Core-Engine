package service;

import model.AgentResource;

import java.util.List;

/**
 * Created by luxin on 6/2/17.
 */
public interface AgentResourceService {

    AgentResource queryById(int id);

    void add(AgentResource agentResource);

    List<AgentResource> queryAll();

    void addList(List<AgentResource> agentResources);

    List<AgentResource> queryByDate(int page, int capacity);

    List<AgentResource> queryAllBySource(String source);

    AgentResource queryByIp(String ip);

    void update(AgentResource agentResource);
}

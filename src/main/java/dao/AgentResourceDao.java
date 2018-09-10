package dao;

import model.AgentResource;

import java.util.List;

/**
 * Created by luxin on 6/2/17.
 */
public interface AgentResourceDao {

    void add(AgentResource agentResource);

    void update(AgentResource agentResource);

    void delete(AgentResource agentResource);

    AgentResource queryById(int id);

    void addList(List<AgentResource> agentResources);

    List<AgentResource> queryAll();

    List<AgentResource> queryByDate(int page, int capacity);

    List<AgentResource> queryAllBySource(String source);

    AgentResource queryByIp(String ip);
}

package dao.impl;

import dao.AgentResourceDao;
import model.AgentResource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import utils.StringUtils;

import java.util.List;

/**
 * Created by luxin on 6/2/17.
 */
@Repository
public class AgentResourceDaoImpl implements AgentResourceDao {

    @Autowired
    private SessionFactory sessionFactory;

    //获取和当前线程绑定的Session
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void add(AgentResource agentResource) {
        //IP是否为空
        if (StringUtils.isNotEmpty(agentResource.getIp())) {
            //没有就保存
            AgentResource old = queryByIp(agentResource.getIp());
            if (StringUtils.isEmpty(old)) {
                getSession().save(agentResource);
            } else {
                agentResource.setId(old.getId());
                getSession().merge(agentResource);
            }
        }
    }

    public void addList(List<AgentResource> agentResources) {
        for (int i = 0; i < agentResources.size(); i++) {
            add(agentResources.get(i));
        }
        getSession().flush();
    }

    public void update(AgentResource agentResource) {
        if (StringUtils.isNotEmpty(agentResource)) {
            getSession().update(agentResource);
        }
    }

    public void delete(AgentResource agentResource) {

    }

    public AgentResource queryById(int id) {
        String hql = "SELECT * FROM agent_resource WHERE id=:id";
        Query query = getSession().createSQLQuery(hql).addEntity(AgentResource.class).setParameter("id", id);
        return (AgentResource) query.uniqueResult();
    }

    public List<AgentResource> queryAll() {
        String hql = "SELECT * FROM agent_resource";
        Query query = getSession().createSQLQuery(hql).addEntity(AgentResource.class);
        return query.list();
    }

    public AgentResource queryByIp(String ip) {
        String hql = "SELECT * FROM agent_resource WHERE ip=:ip";
        Query query = getSession().createSQLQuery(hql).addEntity(AgentResource.class).setParameter("ip", ip);
        return (AgentResource) query.uniqueResult();
    }

    public List<AgentResource> queryByDate(int page, int capacity) {
        String hql = "SELECT * FROM agent_resource ORDER BY lastCheckTime DESC LIMIT " + page * capacity + "," + (page * capacity + capacity);
        Query query = getSession().createSQLQuery(hql).addEntity(AgentResource.class);
        return query.list();
    }

    public void updateList(List<AgentResource> resources) {
        for (int i = 0; i < resources.size(); i++) {
            getSession().update(resources.get(i));
        }
    }

    public List<AgentResource> queryAllBySource(String source) {
        String hql = "SELECT * FROM agent_resource WHERE source=:source";
        Query query = getSession().createSQLQuery(hql).addEntity(AgentResource.class).setParameter("source", source);
        return query.list();
    }
}
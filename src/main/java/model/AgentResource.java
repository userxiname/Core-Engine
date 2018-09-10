package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by luxin on 6/2/17.
 */
@Entity
public class AgentResource {

    @Id
    private int id;
    private String ip;
    private String port;
    private String crtTime;
    private String value;
    private String source;
    private int usedTimes;
    private int ping;
    private String agentType;
    private String location;
    private String lastCheckTime;
    private String responseTime;
    private String security;
    private String liveTime;
    private int testTimes;
    private int testSuccTimes;
    private int testSuccRate;
    private String lastSuccTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getUsedTimes() {
        return usedTimes;
    }

    public void setUsedTimes(int usedTimes) {
        this.usedTimes = usedTimes;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(String lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    public int getTestTimes() {
        return testTimes;
    }

    public void setTestTimes(int testTimes) {
        this.testTimes = testTimes;
    }

    public int getTestSuccTimes() {
        return testSuccTimes;
    }

    public void setTestSuccTimes(int testSuccTimes) {
        this.testSuccTimes = testSuccTimes;
    }

    public int getTestSuccRate() {
        return testSuccRate;
    }

    public void setTestSuccRate(int testSuccRate) {
        this.testSuccRate = testSuccRate;
    }

    public String getLastSuccTime() {
        return lastSuccTime;
    }

    public void setLastSuccTime(String lastSuccTime) {
        this.lastSuccTime = lastSuccTime;
    }
}

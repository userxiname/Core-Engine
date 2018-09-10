package model;

/**
 * Created by luxin on 6/5/17.
 */
public class SiteX {
    private String accept = "*/*";
    private String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";
    private String AcceptEncoding = "gzip, deflate, sdch, br";
    private String AcceptLanguage = "en,zh-CN;q=0.8,zh;q=0.6";
    private String Connection = "keep-alive";
    private String Host;
    private int ConnectTimeout = 10000;
    private int ReadTimeout = 10000;
    private int SocketTimeout = 10000;
    private int ConnectionRequestTimeout = 10000;
    private String PageCharset = "utf-8";
    private int maxRedirect = 3;

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAcceptEncoding() {
        return AcceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        AcceptEncoding = acceptEncoding;
    }

    public String getAcceptLanguage() {
        return AcceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        AcceptLanguage = acceptLanguage;
    }

    public String getConnection() {
        return Connection;
    }

    public void setConnection(String connection) {
        Connection = connection;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public int getConnectTimeout() {
        return ConnectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        ConnectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return ReadTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        ReadTimeout = readTimeout;
    }

    public int getSocketTimeout() {
        return SocketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        SocketTimeout = socketTimeout;
    }

    public int getConnectionRequestTimeout() {
        return ConnectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        ConnectionRequestTimeout = connectionRequestTimeout;
    }

    public String getPageCharset() {
        return PageCharset;
    }

    public void setPageCharset(String pageCharset) {
        PageCharset = pageCharset;
    }

    public int getMaxRedirect() {
        return maxRedirect;
    }

    public void setMaxRedirect(int maxRedirect) {
        this.maxRedirect = maxRedirect;
    }
}

package processor;

import model.SiteX;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import utils.StringUtils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * Created by luxin on 6/5/17.
 * <p>
 * --------> GET
 */
public class HttpDownloaderX {

    private String html = null;
    private CloseableHttpResponse response;
    private long responseTimeCost = 0;
    private HttpHost httpHost = null;
    private String currentUrl;

    public HttpDownloaderX setProxy(String proxyIp, int proxyPort) {
        if (StringUtils.isNotEmpty(proxyIp) && StringUtils.isNotEmpty(proxyPort)) {
            httpHost = new HttpHost(proxyIp, proxyPort);
        }
        return this;
    }

    /**
     * @param url
     * @return
     * @throws Exception Exception catch
     *                   ConnectTimeoutException
     *                   SocketException
     *                   SocketTimeoutException
     *                   Exception
     */
    public HttpDownloaderX get(SiteX site, String url) {
        this.currentUrl = url;
        CloseableHttpClient httpClient;
        clearCache();
        try {
            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(site.getSocketTimeout())
                    .setConnectTimeout(site.getConnectTimeout())
                    .setConnectionRequestTimeout(site.getConnectionRequestTimeout())
                    .build();
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(config)
                    .build();
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.copy(config)
                    .setProxy(httpHost)
                    .build();
            httpGet.setConfig(requestConfig);
            long startTime = System.currentTimeMillis();
            response = httpClient.execute(httpGet);
            long endTime = System.currentTimeMillis();
            responseTimeCost = endTime - startTime;
            html = EntityUtils.toString(response.getEntity(), site.getPageCharset());
            response.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public CloseableHttpResponse getResponse() {
        return response;
    }

    public String getHtml() {
        return html;
    }

    public long getResponseTimeCost() {
        return responseTimeCost;
    }

    public void clearCache() {
        html = null;
        response = null;
        responseTimeCost = 0;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public static void main(String args[]) {
//        SiteX site = new SiteX();
//        HttpDownloaderX httpProxyDownloader = new HttpDownloaderX();
//        httpProxyDownloader.setSite(site).setProxy("123.55.152.216", 808);
//        httpProxyDownloader.get("http://1212.ip138.com/ic.asp");
//        if (StringUtils.isEmpty(httpProxyDownloader.getResponse())) {
//            System.out.println("Request failed!");
//            return;
//        }
//        if (!httpProxyDownloader.getResponse().getStatusLine().toString().contains("200")) {
//            System.out.println("Error return code! code:" + httpProxyDownloader.getResponse().getStatusLine());
//            return;
//        }
//        System.out.println(httpProxyDownloader.getResponse().getStatusLine().getStatusCode());
//        System.out.println("Response：" + StringUtils.xpath(httpProxyDownloader.getHtml(), "//html/body/center/text()"));

        SiteX siteX = new SiteX();
        HttpDownloaderX downloaderX = new HttpDownloaderX();
        try {
            downloaderX.get(siteX, "http://qy.58.com/ajax/getBusinessInfo?userName=金融信息服务有限公司");
            System.out.println(downloaderX.getHtml());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

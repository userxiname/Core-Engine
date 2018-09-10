package model;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import org.apache.http.client.methods.CloseableHttpResponse;
import utils.StringUtils;

import java.util.List;

/**
 * Created by luxin on 6/8/17.
 */
public class PageX {

    private String html;
    private String currentUrl;
    private CloseableHttpResponse response;
    private long responseTimeCost = 0;

    public String getHtml() {
        return html;
    }

    public PageX setHtml(String html) {
        this.html = html;
        return this;
    }

    public List<Object> xpath(String xpathExpression) {
        if (StringUtils.isEmpty(xpathExpression)) {
            return null;
        }
        try {
            JXDocument document = new JXDocument(html);
            return document.sel(xpathExpression);
        } catch (XpathSyntaxErrorException e) {
            return null;
        }
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public PageX setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
        return this;
    }
}

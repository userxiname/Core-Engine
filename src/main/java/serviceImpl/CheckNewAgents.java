package serviceImpl;

import processor.KuaidailiProcessor;
import processor.XicidailiProcessor;
import us.codecraft.webmagic.Spider;

/**
 * Created by luxin on 6/7/17.
 */
public class CheckNewAgents {
    public static void main(String args[]) {
        //快代理
        Spider kSpider = new Spider(new KuaidailiProcessor());
        Spider xcSpider = new Spider(new XicidailiProcessor());
        kSpider.thread(1);
        xcSpider.thread(5);
        for (int i = 81; i <= 120; i++) {
            kSpider.addUrl("http://www.kuaidaili.com/free/inha/" + i + "/");     //Domestic Anonymity
            kSpider.addUrl("http://www.kuaidaili.com/free/intr/" + i + "/");     //Domestic Normal
            kSpider.addUrl("http://www.kuaidaili.com/free/outha/" + i + "/");    //International Anonymity
            kSpider.addUrl("http://www.kuaidaili.com/free/outtr/" + i + "/");    //International Normal
        }
        for (int i = 81; i <= 120; i++) {
            xcSpider.addUrl("http://www.xicidaili.com/nn/" + i);      //Domestic Anonymity
            xcSpider.addUrl("http://www.xicidaili.com/nt/" + i);      //Domestic Normal
            xcSpider.addUrl("http://www.xicidaili.com/wn/" + i);      //https
            xcSpider.addUrl("http://www.xicidaili.com/wt/" + i);      //http
        }
        new Thread(kSpider).start();
        new Thread(xcSpider).start();
    }
}

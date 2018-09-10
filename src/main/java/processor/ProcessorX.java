package processor;

import model.PageX;
import model.ResultX;
import model.SiteX;

/**
 * Created by luxin on 6/8/17.
 */
public interface ProcessorX {

    void setSite(SiteX site);

    ResultX processor(PageX page);
}

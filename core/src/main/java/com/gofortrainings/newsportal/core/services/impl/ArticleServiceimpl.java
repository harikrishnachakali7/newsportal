package com.gofortrainings.newsportal.core.services.impl;

import com.adobe.versioncue.nativecomm.IResult;
import com.gofortrainings.newsportal.core.services.ArticleService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Component(service= ArticleService.class)
@Designate(ocd=ArticleConfiguration.class)
public  class ArticleServiceimpl implements ArticleService {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleService.class);
private String articleRestAPI;
private boolean enable;

    @Activate
    public void active(ArticleConfiguration config) {
       this.articleRestAPI= config.articleRestAPI();
      this.enable=config.enable();
        LOG.info("Activated..........");

    }

    @Deactivate
    public void deactivate(ArticleConfiguration config) {
        LOG.info("Deactivated..........");
    }

    @Modified
    public void updated(ArticleConfiguration config) {
        this.articleRestAPI= config.articleRestAPI();
        this.enable=config.enable();
        LOG.info("updated..........");
    }

    public String getArticle() {
        String result = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(this.articleRestAPI);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                if(entity!=null){
                    result= EntityUtils.toString(entity);

            }
        }
    }
           catch(
    IOException e){
        e.printStackTrace();
    }
    return result;
}
}

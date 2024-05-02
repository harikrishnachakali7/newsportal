package com.gofortrainings.newsportal.core.services;

import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class PressReleaseService {
    private static final Logger LOG= LoggerFactory.getLogger(ArticleService.class);
    @Reference
    ArticleService articleService;
    @Activate
    public void active(){
    LOG.info(articleService.getArticle());
        LOG.info("Activated.. method........");

    }
    @Deactivate
    public void deactivate(){
        LOG.info("Deactivated... method.......");
    }
    @Modified
    public void modified() {
        LOG.info("Modified..... method.....");
    }

}

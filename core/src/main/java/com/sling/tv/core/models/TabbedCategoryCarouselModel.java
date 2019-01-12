package com.sling.tv.core.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.sling.tv.core.beans.SubCategoryPageBean;
import com.sling.tv.core.beans.TabbedPageBean;



@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TabbedCategoryCarouselModel {

    private static Logger LOG = LoggerFactory.getLogger(TabbedCategoryCarouselModel.class);

    @Inject
    @Self
    private TabbedCatCarouselCompModel tabCarouselComponent;

    @Inject
    private ResourceResolver resourceResolver;


    private List<TabbedPageBean> tabbedPages;

    public List<TabbedPageBean> getTabbedPages() {
        return tabbedPages;
    }

    public void setTabbedPages(List<TabbedPageBean> tabbedPages) {
        this.tabbedPages = tabbedPages;
    }

    @PostConstruct
    public final void init() {
        try {
            getTabbedPagesDetail();
        } catch(Exception ex) {
            LOG.error("Error in init :::: ", ex);
        }
    }

    /**
     *
     */
    private void getTabbedPagesDetail() {
        try {
            LOG.info("Entering getTabbedPagesDetaill ...");
            tabbedPages = new ArrayList<TabbedPageBean>();
            String tabbedpagePath = tabCarouselComponent.getCategoryPath();
            if(tabbedpagePath != null) {
                Page tabbedPage = resourceResolver.resolve(tabbedpagePath).adaptTo(Page.class);
                Iterator<Page> pages = tabbedPage.listChildren();
                while(pages.hasNext()) {
                    TabbedPageBean tabbedPageBean = new TabbedPageBean();
                    Page tabCategory = pages.next();
                    ValueMap pageProperties = tabCategory.getProperties();
                    tabbedPageBean.setTabbedPageTitle(pageProperties.get("jcr:title").toString());
                    if(pageProperties.containsKey("categoryTitle")) {
                    		tabbedPageBean.setTabbedPageCategoryTitle(pageProperties.get("categoryTitle").toString());
                    }
                    List<SubCategoryPageBean> subCategoryPageBeans = new ArrayList<>();
                    Iterator<Page> tabChild = tabCategory.listChildren();
                    while(tabChild.hasNext()) {
                    	
                        SubCategoryPageBean subCategoryPage = new SubCategoryPageBean();
                        Page currentPage=tabChild.next();
                        ValueMap subCategoryPageProperties = currentPage.getProperties();
                        Node currentNode=currentPage.adaptTo(Node.class);
                        LOG.info("Current Node path is {}",currentNode.getPath());
                        if(subCategoryPageProperties.containsKey("jcr:title")) {
                        	subCategoryPage.setSubCategoryPageTitle(subCategoryPageProperties.get("jcr:title").toString());
                        }
                        if(subCategoryPageProperties.containsKey("categoryHeading")) {
                            subCategoryPage.setSubCategoryPackageHeading(subCategoryPageProperties.get("categoryHeading").toString());
                        }
                        if(subCategoryPageProperties.containsKey("categoryLogo")) {
                            subCategoryPage.setSubCategoryPackageLogo(subCategoryPageProperties.get("categoryLogo").toString());
                        }
                        if(subCategoryPageProperties.containsKey("categoryAltLogo")) {
                            subCategoryPage.setSubCategoryPackageAltLogo(subCategoryPageProperties.get("categoryAltLogo").toString());
                        }
                        if(subCategoryPageProperties.containsKey("packageName")) {
	                        subCategoryPage.setSubCategoryPagePackageName(subCategoryPageProperties.get("packageName").toString());
	                        subCategoryPage.setSubCategoryPagePackageClass(subCategoryPageProperties.get("packageName").toString().toLowerCase().replaceAll("\\s+",""));
                        }
                        if(subCategoryPageProperties.containsKey("packageDescription")) {
                            subCategoryPage.setSubCategoryPagePackageDescription(subCategoryPageProperties.get("packageDescription").toString());
                        }
                        if(subCategoryPageProperties.containsKey("packageImage")) {
                             subCategoryPage.setSubCategoryPagePackageImage(subCategoryPageProperties.get("packageImage").toString());
                        }
                        if(subCategoryPageProperties.containsKey("altImage")) {
                             subCategoryPage.setSubCategoryPageAltImage(subCategoryPageProperties.get("altImage").toString());
                        }
                        if(currentNode.hasNode("jcr:content/logos")) {
                     	   Node logNodeParent=currentNode.getNode("jcr:content/logos");
                     	   
                     	   Iterator<Node> logoNodes = logNodeParent.getNodes();
                     	   List<Map<String, String>> logosList = new ArrayList<Map<String,String>>();
                           while(logoNodes.hasNext()) {
                         	   Map<String, String> logoMap = new HashMap<>();
                         	   Node logNode=logoNodes.next();
                         	   if(logNode.hasProperty("logoPath")) {
                         		   logoMap.put("logoPath", logNode.getProperty("logoPath").getString());
                                }
                         	   if(logNode.hasProperty("logoAlt")) {
                         		   logoMap.put("logoAlt", logNode.getProperty("logoAlt").getString());
                                }
                         	   logosList.add(logoMap);
                            }
                     	  
                     	  
                     	   LOG.info("The number of logos for {} is {}",currentNode.getPath(),logosList.size());
                     	   subCategoryPage.setSubCategoryPageLogos(logosList);
                     	   
                        }
                        subCategoryPageBeans.add(subCategoryPage);
                        tabbedPageBean.setSubCategoryPages(subCategoryPageBeans);
                    }
                    tabbedPages.add(tabbedPageBean);
                }
                LOG.info("total items in the tab page " +tabbedPages.size());
                setTabbedPages(tabbedPages);
            }
        } catch (RepositoryException ex) {
            LOG.error("The exception has been occured during the repository operation{}", ex);
        }catch (Exception ex) {
            LOG.error("The exception occured{}", ex);
        }
        LOG.info("exiting from getTabbedPagesDetails");
    }
    
    

}

package com.sling.tv.core.beans;

import java.util.List;
import java.util.Map;

public class SubCategoryPageBean {
    private String subCategoryPageTitle;
    private String subCategoryPagePackageClass;
    private String subCategoryPagePackageName;
    private String subCategoryPagePackageDescription;
    private String subCategoryPagePackageImage;
    private String subCategoryPageAltImage;
    private String subCategoryPackageHeading;
    private String subCategoryPackageLogo;
    private String subCategoryPackageAltLogo;
    private List<Map<String, String>> subCategoryPageLogos;


    public String getSubCategoryPackageHeading() {
        return subCategoryPackageHeading;
    }

    public void setSubCategoryPackageHeading(String subCategoryPackageHeading) {
        this.subCategoryPackageHeading = subCategoryPackageHeading;
    }

    public String getSubCategoryPackageLogo() {
        return subCategoryPackageLogo;
    }

    public void setSubCategoryPackageLogo(String subCategoryPaackageLogo) {
        this.subCategoryPackageLogo = subCategoryPaackageLogo;
    }

    public String getSubCategoryPackageAltLogo() {
        return subCategoryPackageAltLogo;
    }

    public void setSubCategoryPackageAltLogo(String subCategoryPackageAltLogo) {
        this.subCategoryPackageAltLogo = subCategoryPackageAltLogo;
    }


    public String getSubCategoryPageTitle() {
        return subCategoryPageTitle;
    }
    public void setSubCategoryPageTitle(String subCategoryPageTitle) {
        this.subCategoryPageTitle = subCategoryPageTitle;
    }
    public String getSubCategoryPagePackageName() {
        return subCategoryPagePackageName;
    }
    public void setSubCategoryPagePackageName(String subCategoryPagePackageName) {
        this.subCategoryPagePackageName = subCategoryPagePackageName;
    }
    public String getSubCategoryPagePackageDescription() {
        return subCategoryPagePackageDescription;
    }
    public void setSubCategoryPagePackageDescription(
            String subCategoryPagePackageDescription) {
        this.subCategoryPagePackageDescription = subCategoryPagePackageDescription;
    }
    public String getSubCategoryPagePackageImage() {
        return subCategoryPagePackageImage;
    }
    public void setSubCategoryPagePackageImage(String subCategoryPagePackageImage) {
        this.subCategoryPagePackageImage = subCategoryPagePackageImage;
    }
    public String getSubCategoryPageAltImage() {
        return subCategoryPageAltImage;
    }
    public void setSubCategoryPageAltImage(String subCategoryPageAltImage) {
        this.subCategoryPageAltImage = subCategoryPageAltImage;
    }
    public List<Map<String, String>> getSubCategoryPageLogos() {
        return subCategoryPageLogos;
    }
    public void setSubCategoryPageLogos(
            List<Map<String, String>> subCategoryPageLogos) {
        this.subCategoryPageLogos = subCategoryPageLogos;
    }
    public String getSubCategoryPagePackageClass() {
        return subCategoryPagePackageClass;
    }

    public void setSubCategoryPagePackageClass(String subCategoryPagePackageClass) {
        this.subCategoryPagePackageClass = subCategoryPagePackageClass;
    }
}

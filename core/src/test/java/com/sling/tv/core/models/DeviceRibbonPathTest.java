package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.wcm.testing.mock.aem.junit.AemContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arati Jena
 *
 */
public class DeviceRibbonPathTest {

    @Rule
    public final AemContext context = new AemContext();

    private static final String DEVICE_RIBBON_PATH = "/content/sling-tv/en/deviceribbon/whpdeviceribbon/lg";

    @Before
    public void setup() {

        context.load().json("/device-ribbon-path.json", DEVICE_RIBBON_PATH);
        context.addModelsForClasses(DeviceRibbonPath.class);
        context.addModelsForClasses(DevicePage.class);
    }

    @Test
    public void Test_whenImagePathExists() {

        Resource resource = context.resourceResolver().getResource(DEVICE_RIBBON_PATH);
        DevicePage devicePage = resource.adaptTo(DevicePage.class);
        String imagePath = devicePage.getImagePath();
        Assert.assertEquals("/content/dam/sling-tv/devices/Device-Logos -SVGs/LG_Black.svg",imagePath);
    }

    @Test
    public void Test_whenImageTitleExists() {

        Resource resource = context.resourceResolver().getResource(DEVICE_RIBBON_PATH);
        DevicePage devicePage = resource.adaptTo(DevicePage.class);
        String imageTitle = devicePage.getTitle();
        Assert.assertEquals("LG",imageTitle);
    }

    @Test
    public void Test_whenImageDeviceAltExists() {

        Resource resource = context.resourceResolver().getResource(DEVICE_RIBBON_PATH);
        DevicePage devicePage = resource.adaptTo(DevicePage.class);
        String imageAltText = devicePage.getDeviceAlt();
        Assert.assertEquals("lg",imageAltText);
    }

    @Test
    public void Test_whenDevicePagesExists() {

        Resource resource = context.resourceResolver().getResource(DEVICE_RIBBON_PATH);
        DevicePage devicePage = resource.adaptTo(DevicePage.class);
        List<DevicePage> children2 = new ArrayList<>();
        List<DevicePage> children = new ArrayList<>();
        children.add(devicePage);
        children2.add(devicePage);
        Assert.assertEquals(children2,children);
    }

    @Test
    public void Test_buildDeviceTree() {

        Resource resource = context.resourceResolver().getResource(DEVICE_RIBBON_PATH);
        DevicePage devicePage = resource.adaptTo(DevicePage.class);
        List<DevicePage> children = new ArrayList<>();
        children.add(devicePage);
    }

    @Test
    public void nullTest() {

        Resource resource = context.resourceResolver().getResource(DEVICE_RIBBON_PATH);
        DevicePage devicePage = resource.adaptTo(DevicePage.class);
        Assert.assertNotNull(devicePage);

    }

}


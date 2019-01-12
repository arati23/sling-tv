<%--
  ADOBE CONFIDENTIAL

  Copyright 2013 Adobe Systems Incorporated
  All Rights Reserved.

  NOTICE:  All information contained herein is, and remains
  the property of Adobe Systems Incorporated and its suppliers,
  if any.  The intellectual and technical concepts contained
  herein are proprietary to Adobe Systems Incorporated and its
  suppliers and may be covered by U.S. and Foreign Patents,
  patents in process, and are protected by trade secret or copyright law.
  Dissemination of this information or reproduction of this material
  is strictly forbidden unless prior written permission is obtained
  from Adobe Systems Incorporated.
--%><%@page session="false"
            import="java.util.ArrayList,
                    java.util.Arrays,
                    java.util.List,
                    org.apache.commons.lang.StringUtils,
                    org.apache.sling.api.SlingHttpServletRequest,
                    org.apache.sling.api.request.RequestParameter,
                    org.apache.sling.api.resource.SyntheticResource,
                    com.adobe.granite.i18n.LocaleUtil,
                    com.adobe.granite.ui.components.AttrBuilder,
                    com.adobe.granite.ui.components.ExpressionResolver,
                    com.adobe.granite.ui.components.FilteringResourceWrapper,
                    com.adobe.granite.ui.components.FormData,
                    com.adobe.granite.ui.components.FormData.NameNotFoundMode,
                    com.adobe.granite.ui.components.Tag,
                    com.adobe.granite.ui.components.Value,
                    com.adobe.granite.xss.XSSAPI,
                    com.day.cq.i18n.I18n,
                    com.day.cq.wcm.api.Page,
                    com.day.cq.wcm.api.PageManager,
                    com.day.cq.wcm.api.WCMFilteringResourceWrapper,
                    com.day.cq.wcm.api.components.ComponentContext" %><%
%><%@include file="/libs/granite/ui/global.jsp" %><%
%><%@include file="/libs/cq/gui/components/siteadmin/admin/properties/FilteringResourceWrapper.jsp"%><%

/**
 * Dialog is a form component to render dialog of CQ Authoring.
 * It is strongly recommended to use this component for CQ Authoring purpose to maintain compatibility. Any changes to the look and feel will be done in this component.
 *
 * The component is checking the request's suffix to get the context resource that it is acting upon.
 * This context resource is used as the values of the form. Also the form's action is set to that resource.
 *
 * @component
 * @name Dialog
 * @location /libs/cq/gui/components/authoring/dialog
 *
 * @property {String} [jcr:title] The title of the dialog
 * @property {String} [mode] The mode of the form. Possible values are "default" or "edit" or not specified.
 * @property {String} [enctype] The encoding type of the form
 * @property
 * {String[]} [clientlibs="coralui2", "granite.ui.coral.foundation", "cq.authoring.dialog.all"] The clientlibs to be included in this dialog
 * @property {String[]} [extraClientlibs] The additional clientlibs to be included on top of the ones specified at clientlibs property
 * @property {String} [height] The height of the floating dialog
 * @property {String} [width] The width of the floating dialog
 */

/* WARNING: Please be careful when adding new feature to this component.
This component is occupying a good real estate, so don't waste it.
Discuss in the mailing list first when in doubt. */


String dataPath = slingRequest.getRequestPathInfo().getSuffix();
Resource data = resourceResolver.getResource(dataPath);
boolean editMode = (dataPath == null);
RequestParameter param = slingRequest.getRequestParameter("resourceType");

/**
 * The referrer path is the path to the resource that has requested for the dialog
 */
RequestParameter referrerPathParam = slingRequest.getRequestParameter("referrer");

/**
 * The page query parameter, if true, requires the dialog to be rendered as a "fullscreen" autonomous page
 */
RequestParameter renderAsPageParam = slingRequest.getRequestParameter("page");

String resourceType = null;

if (param != null) {
    resourceType = param.getString();

    if (resourceType.equals("undefined")) {
        resourceType = null;
    }
}

if (!editMode && data == null) {
    if (resourceType != null) {
        data = new SyntheticResource(resourceResolver, dataPath, resourceType);
    }
    else {
        response.sendError(400);
        return;
    }
}

String referrerPath = "";
Resource referrerResource = null;

if (referrerPathParam != null) {
    String referrerPathValue = referrerPathParam.getString();

    if (referrerPathValue.equals("undefined")) {
        referrerPathValue = null;
    }

    if (!StringUtils.isEmpty(referrerPathValue)) {
        referrerResource = resourceResolver.getResource(referrerPathValue);
    }

    if (referrerResource == null) {
        referrerResource = new SyntheticResource(resourceResolver, referrerPathValue, null);
    }
}

Page containingPage = null;

if (referrerResource != null) {
    containingPage = getPage(referrerResource);
} else if (data != null) {
    containingPage = getPage(data);
}

if (containingPage != null) {
    referrerPath = containingPage.getPath();
}

Config cfg = cmp.getConfig();

String title = cfg.get("jcr:title", "");
String mode = cfg.get("mode", String.class);
Boolean returnToReferral = cfg.get("returnToReferral", false);
String height = cfg.get("height", "");
String width = cfg.get("width", "");

Tag tag = cmp.consumeTag();

AttrBuilder formAttrs = tag.getAttrs();
AttrBuilder dialogAttrs = new AttrBuilder(request, xssAPI);
dialogAttrs.addClass("cq-Dialog");
dialogAttrs.add("backdrop", "none");

if(renderAsPageParam != null && Boolean.parseBoolean(renderAsPageParam.getString())) {
    // Dialog rendered as an autonomous page
    dialogAttrs.add("fullscreen", true);
    dialogAttrs.add("open", true);
}

formAttrs.addClass("coral-Form--vertical");

//*******************************************************
//The JS script path will be read from here
     String jsClass = cfg.get("js-comp-identifier", "");
     formAttrs.add("js-comp-identifier", jsClass);

//Setting max limit for multifield items
     String maxItems = cfg.get("max-mf-items",""); 
     formAttrs.add("max-mf-items", maxItems);


//*************************************************************

if (editMode) {
    // cq-dialog class names and the like are kept for retro-compatibility reason
    formAttrs.addClass("cq-dialog foundation-form content foundation-layout-form foundation-layout-form-mode-edit");
} else {
    formAttrs.addHref("action", data.getPath());
    formAttrs.add("enctype", cfg.get("enctype", String.class));
    formAttrs.add("method", "post");
    formAttrs.add("data-foundation-form-ajax", true);
    // cq-dialog class names and the like are kept for retro-compatibility reason
    formAttrs.addClass("cq-dialog foundation-form foundation-layout-form");

	if (!StringUtils.isEmpty(referrerPath)) {
        formAttrs.addHref("data-cq-dialog-pageeditor", "/editor.html" + referrerPath + ".html");
    }

    // this prevents the Granite form from attempting to parse the response itself.
    if (cfg.get("suppressFormUiBehavior", false)) {
        formAttrs.add("data-foundation-form-ui", "none");
    }

    if (mode != null) {
        formAttrs.addClass("foundation-layout-form-mode-" + mode);
    }

    if (returnToReferral) {
        formAttrs.add("data-cq-dialog-returntoreferral", true);
    }

    if (!"".equals(height)) {
        dialogAttrs.add("data-cq-dialog-height", height);
    }
    if (!"".equals(width)) {
        dialogAttrs.add("data-cq-dialog-width", width);
    }
}

try {
    if (editMode) {
        request.setAttribute("granite.ui.authoring", true);
    } else {
        // for forms, we ignore freshness
        if(StringUtils.startsWith(dataPath, "/content/forms/af/")) {
            FormData.push(slingRequest, data.getValueMap(), NameNotFoundMode.IGNORE_FRESHNESS);
        } else {
            FormData.push(slingRequest, data.getValueMap(), NameNotFoundMode.CHECK_FRESHNESS);
        }
        request.setAttribute(Value.CONTENTPATH_ATTRIBUTE, data.getPath());
    }

	%><!DOCTYPE html>
	<html class="cq-dialog-page skipCoral2Validation" lang="<%= xssAPI.encodeForHTMLAttr(LocaleUtil.toRFC4646(request.getLocale()).toLowerCase()) %>" data-i18n-dictionary-src="<%= request.getContextPath() %>/libs/cq/i18n/dict.{+locale}.json">
	<head>
	    <meta charset="utf-8">
	    <title><%= outVar(xssAPI, i18n, title) %></title>
	    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	    <meta name="X-UA-Compatible" content="chrome=1" />
	    <sling:include resourceType="cq/gui/components/siteadmin/admin/page/winmode"/>
	    <link rel="shortcut icon" href="<%= request.getContextPath() %>/libs/granite/core/content/login/favicon.ico">
	    
	    <ui:includeClientLib categories="<%= StringUtils.join(cfg.get("clientlibs", new String[] {"coralui2",
	    "granite.ui.coral.foundation", "granite.ui.coral.foundation.addon.coral2", "cq.authoring.dialog.all"}), ",") %>" />
	    <ui:includeClientLib categories="<%= StringUtils.join(cfg.get("extraClientlibs", new String[0]), ",") %>" />
        <% if (editMode) { %>
            <ui:includeClientLib categories="granite.ui.authoring, cq.authoring.page" />
        <% } %>
	</head>
	<body class="coral--light">
        <coral-dialog <%= dialogAttrs.build() %>>
            <form <%= formAttrs.build() %>>
                    <coral-dialog-header class="cq-dialog-header">
                        <div class="cq-dialog-actions u-coral-pullRight">
                            <button is="coral-button" icon="helpCircle" variant="minimal" <%= getHelpAttrs(slingRequest, cfg, xssAPI, i18n).build() %>></button>
                            <button is="coral-button" icon="close" variant="minimal" class="cq-dialog-header-action cq-dialog-cancel" title="<%= i18n.get("Cancel")%>"></button>
                            <button is="coral-button" icon="check" variant="minimal" class="cq-dialog-header-action cq-dialog-submit" title="<%= i18n.get("Done") %>"></button>
                        </div>
                        <%= outVar(xssAPI, i18n, title) %>
                    </coral-dialog-header>
                    <coral-dialog-content>
                        <input type="hidden" name="_charset_" value="utf-8"/>
                        <% if (resourceType != null) { %>
                            <input type="hidden" name="./sling:resourceType" value="<%= resourceType %>"/>
                        <% } %>
                        <input type="hidden" name="./jcr:lastModified"/>
                        <input type="hidden" name="./jcr:lastModifiedBy"/>
                        <%

                AttrBuilder contentAttrs = new AttrBuilder(request, xssAPI);
                contentAttrs.addClass("cq-dialog-content");

                String[] requestSelectors = slingRequest.getRequestPathInfo().getSelectors();
                List<String> requestSelectorList = new ArrayList<String>();

                if (requestSelectors != null) {
                    requestSelectorList = Arrays.asList(requestSelectors);
                }

                if (requestSelectorList.contains("editabletemplate")) {
                    // Policy specific attributes added to the request
                    %><sling:call script="dialog_body_policy.jsp" /><%
                }

                if (requestSelectorList.contains("policydesign")) {
                    // Touch design dialog in the context of an editable template
                    // In that case we embark the content of both dialogs - the policy and the design
                    cmp.include(resourceResolver.getResource("cq/gui/components/authoring/dialog/policywrapper"), new Tag(contentAttrs));
                } else if(resource.getChild("content") != null){
                    // Default content
                    ResourceWrapper hideOnEditWrapper = new FilteringResourceWrapper(resource.getChild("content"));
                    ResourceWrapper contentWrapper = new WCMFilteringResourceWrapper(hideOnEditWrapper, data, sling.getService(ExpressionResolver.class), slingRequest);
                    request.setAttribute(ComponentContext.BYPASS_COMPONENT_HANDLING_ON_INCLUDE_ATTRIBUTE, true);
                    // Standard dialog
                    cmp.include(contentWrapper, new Tag(contentAttrs));
                }
                    %></coral-dialog-content>
            </form>
        </coral-dialog>
	</body><%
} finally {
    if (editMode) {
        request.removeAttribute("granite.ui.authoring");
    } else {
        FormData.pop(slingRequest);
        request.removeAttribute(Value.CONTENTPATH_ATTRIBUTE);
    }
}
%><%!

private Page getPage(Resource content) {
    PageManager pageManager = content.getResourceResolver().adaptTo(PageManager.class);
    Page page = null;

    // First get the page from the Page Manager
    if (pageManager != null) {
        page = pageManager.getContainingPage(content);
    }

    /**
    * If no page has been found by the page manager,
    * iterate over the parents until we reach the first resource that is of type Page
    *
    * The page manager may not return the containing page if the page doesn't reach the minimum requirement
    * for the getContainingPage to define a valid containing page
    */
    if (page == null) {
        Resource parent = content.getParent();
        while (parent != null) {
            page = parent.adaptTo(Page.class);
            if (page != null) {
                break;
            }

            parent = parent.getParent();
        }
    }

    return page;
}

private AttrBuilder getHelpAttrs(SlingHttpServletRequest req, Config cfg, XSSAPI xssAPI, I18n i18n) {
    String helpPath = cfg.get("helpPath", i18n.getVar("https://www.adobe.com/go/aem6_4_docs_en"));
    AttrBuilder attrs = new AttrBuilder(req, xssAPI);
    attrs.add("type", "button");
    attrs.addClass("cq-dialog-header-action cq-dialog-help");
    attrs.addHref("data-href", helpPath);
    attrs.add("title", i18n.get("Help"));

    return attrs;
}
%>

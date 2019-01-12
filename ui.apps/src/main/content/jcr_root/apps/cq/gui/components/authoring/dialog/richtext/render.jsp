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
--%><%
%><%@include file="/libs/granite/ui/global.jsp" %><%
%><%@page session="false"
          import="com.adobe.granite.ui.components.AttrBuilder,
                  com.adobe.granite.ui.components.Config,
                  com.adobe.granite.ui.components.Field,
                  com.adobe.granite.ui.components.Tag" %><%

    Config cfg = cmp.getConfig();
    ValueMap vm = (ValueMap) request.getAttribute(Field.class.getName());

    Tag tag = cmp.consumeTag();
    AttrBuilder attrs = tag.getAttrs();
    AttrBuilder inputAttrs = tag.getAttrs();

    cmp.populateCommonAttrs(attrs);

    attrs.add("placeholder", i18n.getVar(cfg.get("emptyText", String.class)));
    attrs.addDisabled(cfg.get("disabled", false));
    if (cfg.get("required", false)) {
        attrs.add("aria-required", true);
    }

    // Start of inputAttrs compatibility for hidden input field
    inputAttrs.add("id", cfg.get("id", String.class));
    inputAttrs.addClass(cfg.get("class", String.class));
    inputAttrs.addRel(cfg.get("rel", String.class));
    inputAttrs.add("title", i18n.getVar(cfg.get("title", String.class)));
    inputAttrs.addOthers(cfg.getProperties(), "id", "class", "rel", "title", "type", "name", "value", "emptyText", "disabled", "required");
    // End of inputAttrs compatibility

    inputAttrs.add("name", cfg.get("name", String.class));
    String value = "";
    if (cfg.get("disableXSSFiltering", false)) {
        value = vm.get("value", String.class);
    } else {
        value = xssAPI.filterHTML(vm.get("value", String.class));
    }
    inputAttrs.add("value", value);

    String styleSheets = "";
    for (String sheet : cfg.get("externalStyleSheets", new String[0])) {
        styleSheets += sheet + ",";
    }
    if (styleSheets.length() > 0) {
        styleSheets = styleSheets.substring(0, styleSheets.length() - 1);
    }

%><div class="cq-RichText richtext-container">
    <input type="hidden" data-cq-richtext-input="true" <%= inputAttrs.build() %>>
    <input type="hidden" name="<%=xssAPI.encodeForHTMLAttr(cfg.get("richTextFlagPropertyName", "./textIsRich"))%>" value="true">
    <%-- Please don't use coral-RichText-editable coral-RichText (deprecated) --%>
    <div data-cq-richtext-editable="true" class="cq-RichText-editable coral-RichText-editable coral-Form-field coral-RichText"
         data-config-path="<%=xssAPI.getValidHref(resource.getPath())%>.infinity.json"
         data-use-fixed-inline-toolbar="<%=cfg.get("useFixedInlineToolbar", Boolean.class)%>"
         data-custom-start="<%=cfg.get("customStart", Boolean.class)%>"
         data-editor-type="<%=xssAPI.encodeForHTMLAttr(cfg.get("editorType", "text"))%>"
         data-external-style-sheets="<%=xssAPI.getValidHref(styleSheets)%>"
         <%= attrs.build() %>>
    </div>
</div>
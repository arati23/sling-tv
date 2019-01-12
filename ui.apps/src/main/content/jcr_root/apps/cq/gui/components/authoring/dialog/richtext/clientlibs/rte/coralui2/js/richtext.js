/*
 ADOBE CONFIDENTIAL

 Copyright 2015 Adobe Systems Incorporated
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
 */

/* This file contains the code used by Richtext Editor (RTE) when RTE is inside a form in Touch-UI.
   It contains the following configurations :
     * useFixedInlineToolbar : This Boolean Property defined on the RTE node (one with sling:resourceType="/libs/cq/gui/components/authoring/dialog/richtext")
                               should be set to true, when the user wants the RTE toolbar to be always visible (even on out of area clicks) and fixed instead of floating.
                               When this property is true, Richtext editing is ,by default, started on "foundation-contentloaded" event.
                               If you want to stop that, you can set the property 'customStart' to true and trigger the 'rte-start' event to start RTE editing.
                               When this property is 'true', the default behaviour, rte start on click, does not work.
     * customStart : This Boolean Property defined on the RTE node should be set to true, if the user wants to control when to start RTE
                     by triggering the event "rte-start" e.g. (Multifield Implementation)
     * rte-start : This event should be triggered on the contenteditable-div of RTE, when the user wants to start editing RTE. This works
                   only if 'customStart' has been set to true.
     * editorType : This specifies which editor to use - "text" or "table". Default is "text".
*/

(function(window, document, $) {
    "use strict";

    var rteFixedColumnCss = "cq-RichText-FixedColumn-column";
    var rteFixedColumnCssCompat = "coral-RichText-FixedColumn-column";
    var DATA_RTE_INSTANCE = "rteinstance";

    $(document).on("foundation-contentloaded", function(e) {

        var $container = $(e.target).hasClass(".cq-RichText") ? $(e.target) : $(e.target).find(".cq-RichText");

        // Added in 6.4 to ensure Full BC (CQ-4231708)
        // Should ideally be removed in 6.6
        $container.each(function () {
            var $this = $(this);
            $this.closest("." + rteFixedColumnCss).addClass(rteFixedColumnCssCompat);
            $this.closest("." + rteFixedColumnCssCompat + ":not(." + rteFixedColumnCss + ")").addClass(rteFixedColumnCss);
        });

        // Copy hidden text field value to RTE
        // We don't put html value into RTE while rendering, otherwise, the linkchecker
        // converts invalid links to image tags. (See CQ-4219770). So, we add it in the
        // value attribute of hidden input field when rendering and copy it from there now
        $container.each(function() {
            var $this = $(this);
            var $richTextDiv = $this.find(".cq-RichText-editable");
            if (!$richTextDiv.data(DATA_RTE_INSTANCE)) {
                var html = $this.find("input[type='hidden'][data-cq-richtext-input='true']").val();
                $richTextDiv.empty().append(html);
            }
        });

        // Copy RTE text to hidden field
        $container.on("change", "[data-cq-richtext-editable='true']", function() {
            var el = $(this).closest(".cq-RichText");
            var rteInstance = el.find(".cq-RichText-editable").data(DATA_RTE_INSTANCE);
            el.find("input[type=hidden][data-cq-richtext-input='true']").val(rteInstance.getContent());
        });

        var $richTextDiv = $(e.target).find(".cq-RichText>.cq-RichText-editable");
        $richTextDiv.each(function() {
            var $this = $(this);
            if ($this.data("customStart")) {
                $this.on("rte-start", function() {
                    var $this = $(this);
                    if ($this.data("useFixedInlineToolbar") && !$this.data(DATA_RTE_INSTANCE)) {
                        var html = $(this).parent().find("input[type=hidden][data-cq-richtext-input='true']").val();
                        $this.empty().append(html);
                        startRTE($this);
                    }
                });
            } else {
                if ($this.data("useFixedInlineToolbar") && !$this.data(DATA_RTE_INSTANCE)) {
                    startRTE($this);
                }
            }
            $this.on("editing-start", function() {
                var rte = $(this).data(DATA_RTE_INSTANCE);
                rte.editorKernel.getToolbar().hide();
                $(this).closest(".coral-dialog-content").on("click", function(e) {
                    if (!$(e.target).closest(".cq-RichText").length) {
                        if (rte.useFixedInlineToolbar && !rte.sourceEditMode) {
                            rte.editorKernel.toolbar.hide();
                        }
                    }
                });
            });
            $(this).on("click", function() {
                var self = this;
                $richTextDiv.each(function() {
                    var rte = $(this).data(DATA_RTE_INSTANCE);
                    if (this !== self && rte && !rte.sourceEditMode) {
                        rte.editorKernel.getToolbar().hide();
                    }
                });
            });
        });
    });

    var startRTE = function($editable, options) {
        var editorType = $editable.data("editorType"), rtePluginsDefaults, configCallBack;
        var externalStyleSheets = $editable.data("externalStyleSheets"), index, $styleSheet, styleElements;
        if (editorType === "table") {
            rtePluginsDefaults = {
                "useColPercentage": false,
                "rtePlugins": {
                    "table": {
                        "features": "*",
                        "defaultValues": {
                            "width": "100%"
                        },
                        "editMode": CUI.rte.plugins.TablePlugin.EDITMODE_TABLE
                    }
                }
            };
            configCallBack = function(config) {
                return Granite.Util.applyDefaults({}, rtePluginsDefaults, config);
            };
        }
        var rte = new CUI.RichText({
            "element": $editable,
            "componentType": editorType,
            "preventCaretInitialize": true
        });
        if (externalStyleSheets && externalStyleSheets.length > 0) {
            externalStyleSheets = externalStyleSheets.split(",");
            for (index = 0; index < externalStyleSheets.length; index++) {
                $styleSheet = $("head link[href='" + externalStyleSheets[index] +"']");
                if ($styleSheet.length <= 0) {
                    $styleSheet = $("<link rel=\"stylesheet\" href=\"" + externalStyleSheets[index] + "\" type=\"text/css\">");
                    $("head").append($styleSheet);
                    styleElements = $editable.data("externalStyleElements");
                    if (!styleElements) {
                        styleElements = [$styleSheet];
                    } else {
                        styleElements.push($styleSheet);
                    }
                    $editable.data("externalStyleElements", styleElements);
                }
            }
        }
        CUI.rte.ConfigUtils.loadConfigAndStartEditing(rte, $editable, configCallBack);
    };

    var rteFinish = function() {
        var $this = $(this), index;
        var rteInstance = $this.data(DATA_RTE_INSTANCE), styleElements = $this.data("externalStyleElements");
        if (rteInstance) {
            rteInstance.finish(false);
            if (styleElements) {
                for (index = 0; index < styleElements.length; index++) {
                    styleElements[index].remove();
                }
            }
            $this.removeData(DATA_RTE_INSTANCE);
        }
    };

    $(document).on("dialog-beforeclose", ".cq-Dialog", function(e) {
        if (!$(e.target).hasClass("cq-Dialog")) {
            // dialog currently throws dialog-beforeclose twice - once on form and once on coral-dialog element
            // we need to only listen to event on coral-dialog
            return;
        }
        $(this).find(".cq-RichText>.cq-RichText-editable").each(rteFinish);
    });

    $(document).on("dialog-layouttoggle-fullscreen", ".cq-Dialog", function(e) {
        var $richTextDiv = $(e.target).find(".cq-RichText>.cq-RichText-editable");
        $richTextDiv.each(function() {
            var rte = $(this).data(DATA_RTE_INSTANCE);
            if (rte) {
                switchToolbar("dialogFullScreen", rte)
            }
        });
    });

    $(document).on("dialog-layouttoggle-floating", ".cq-Dialog", function(e) {
        var $richTextDiv = $(e.target).find(".cq-RichText>.cq-RichText-editable");
        $richTextDiv.each(function() {
            var rte = $(this).data(DATA_RTE_INSTANCE);
            if (rte) {
                switchToolbar("inline", rte)
            }
        });
    });

    $(document).on("click", ".coral-Wizard-nextButton", function(e) {
        $(this).closest(".foundation-form").find(".cq-RichText>.cq-RichText-editable").each(rteFinish);
    });

    function switchToolbar(toolbarType, rte) {
        var ek = rte.editorKernel;
        if (!ek.hasBackgroundToolbar(toolbarType)) {
            ek.addBackgroundToolbar({
                "tbType": toolbarType,
                "isFullScreen": ek.getEditContext().getState("fullscreenadapter").isFullScreen(),
                "useFixedInlineToolbar": true
            });
        }
        ek.setActiveToolbar(toolbarType);
    }

    CUI.rte.Theme.BLANK_IMAGE = Granite.HTTP.externalize("/libs/clientlibs/granite/coralui2/optional/rte/resources/images/blank.png");

})(window, document, Granite.$);

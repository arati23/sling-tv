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

(function(window, document, $) {
    "use strict";

    var requiredString;
    var DATA_RTE_INSTANCE = "rteinstance";

    function getRequiredString() {
        if (!requiredString) {
            requiredString = Granite.I18n.get("Please fill out this field.");
        }
        return requiredString;
    }

    function handleValidation(el) {
        var api = el.adaptTo("foundation-validation");
        if (api) {
            api.checkValidity();
            api.updateUI();
        }
    }

    var registry = $(window).adaptTo("foundation-registry");

    // Selector for Richtext-editor
    registry.register("foundation.validation.selector", {
        submittable: ".cq-RichText-editable",
        candidate: ".cq-RichText-editable"
    });

    // Validator required for Richtext-editor
    registry.register("foundation.validation.validator", {
        selector: ".cq-RichText-editable",
        validate: function(element) {
            var $el = $(element);
            var html;
            if ($el.data(DATA_RTE_INSTANCE).sourceEditMode) {
                return 'Please exit Source-edit mode before saving the changes';
            }
            if (element.getAttribute("aria-required") === "true") {
                var rteInstance = $el.data(DATA_RTE_INSTANCE);
                if (rteInstance) {
                    html = $el.data(DATA_RTE_INSTANCE).getContent();
                }
                if (html === undefined) {
                    html = $el.html();
                }
                if (html.length === 0) {
                    return getRequiredString();
                }
            }
        }
    });

    $(document).on("foundation-contentloaded", function(e) {
        var $richTextDiv = $(e.target).find(".cq-RichText-editable");

        $richTextDiv.each(function() {
            $(this).on("editing-start", function() {
                var $this = $(this);
                var rte = $this.data(DATA_RTE_INSTANCE);
                var ek = rte.editorKernel;

                // RTE UI Listeners are called in the order they are registered.
                // So, this handler will be called after the handler RTE registers on this event, in which
                // RTE would set 'rte.sourceEditMode' to true
                ek.addUIListener("disablesourceedit", function () {
                    handleValidation($this);
                });
            });
        });

    });

    // Driver for Richtext-editor
    $(document).on("change", ".cq-RichText-editable", function(e) {
        handleValidation($(this));
    });

})(window, document, Granite.$);

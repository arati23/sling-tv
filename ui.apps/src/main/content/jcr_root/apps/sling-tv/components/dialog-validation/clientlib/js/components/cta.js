(function($, $document, ns, CUI) {

	var content = "";
	var WARNING_MESSAGE="Full URLs are not updated when migrating environments.  Please be sure the full URL is necessary.";
    var ERROR_MESSAGE="Please fill Mandatory Fields!";

	function printWarning(message) {
		ns.ui.helpers.prompt({
			title : Granite.I18n.get("Warning"),
			message : message,
			type : ns.ui.helpers.PROMPT_TYPES.WARNING,
			actions : [ {
				id : "OK",
				text : Granite.I18n.get("OK", "OK")
			} ]
		});
	}

    function urlMatcher() {

        var pattern = /^((http|https|ftp|www):\/\/)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/gm;
		var regPattern = new RegExp(pattern);
        var fieldValue = content.find("input[name='./targetURL']").val();
    	return regPattern.test(fieldValue);

	}

    function printError(message) {
		ns.ui.helpers.prompt({
			title : Granite.I18n.get("Error"),
			message : message,
			type : ns.ui.helpers.PROMPT_TYPES.ERROR,
			actions : [ {
				id : "OK",
				text : Granite.I18n.get("OK", "OK")
			} ]
		});
	}

    function validateField(el) {

        var api = el.adaptTo("foundation-validation");
        if (api) {
            api.checkValidity();
            api.updateUI();
        }

	}
	function displayElements(type) {
		
		var tgURL = content.find("input[name='./targetURL']");
		if (type == "link") {
			content.find("input[name='./classification']").parent(".coral-Form-fieldwrapper").hide();
			content.find("input[name='./cartFlow']").parent(".coral-Form-fieldwrapper").hide();
			content.find("input[name='./cartStep']").parent(".coral-Form-fieldwrapper").hide();
			content.find("coral-select[name='./ctaWindowSelection']").parent(".coral-Form-fieldwrapper").show();
			content.find("input[name='./targetURL']").parent(".coral-Form-fieldwrapper").show();
            content.find("input[name='./targetURL']").attr("aria-required",true);
            if(urlMatcher()){
                 tgURL.addClass("domain-name-reg-validation");
                 printWarning(WARNING_MESSAGE);
            }
			content.find(".coral-TabPanel-navigation").children().eq("2").hide();
			content.find(".coral-TabPanel-navigation").children().eq("4").hide();
		} else if (type == "old-cart" || type == "new-cart") {
			content.find("input[name='./classification']").parent(".coral-Form-fieldwrapper").show();
			content.find("input[name='./cartFlow']").parent(".coral-Form-fieldwrapper").show();
			content.find("input[name='./cartStep']").parent(".coral-Form-fieldwrapper").show();
			content.find("coral-select[name='./ctaWindowSelection']").parent(".coral-Form-fieldwrapper").hide();
			content.find("input[name='./targetURL']").parent(".coral-Form-fieldwrapper").hide();
            content.find("input[name='./targetURL']").attr("aria-required",false);
            tgURL.removeClass("domain-name-reg-validation");
            content.find(".coral-TabPanel-navigation").children().eq("1").removeClass("is-invalid");
            var el=content.find("input[name='./targetURL']");
            el.adaptTo("foundation-field").setInvalid(false);
            var error = el.data("foundation-validation.internal.error");
            if (error) {
              error.remove();
            }
          	content.find(".coral-TabPanel-navigation").children().eq("2").show();
			content.find(".coral-TabPanel-navigation").children().eq("4").show();
		}

	}

	Sling.CUI.Component.cta = new Class({

				extend : Sling.CUI.Component.componentbase,

				onDialogReady : function() {

					content = $(".cq-dialog.foundation-form");

                    var destination = content.find("select[name='./destination']").val();
                    var tgURL = content.find("input[name='./targetURL']");

                    if(destination=='link' && urlMatcher()){

                         tgURL.addClass("domain-name-reg-validation");

                    }

                    displayElements(destination);

					$(document).on("selected", ".js-link-select", function(e) {

						displayElements($(this).find(":selected").val());

					});

					$(document).on("change","input[name='./targetURL']",function(e) {


						if (urlMatcher()) {

                             $(this).addClass("domain-name-reg-validation");
                             printWarning(WARNING_MESSAGE);

						} else {

							$(this).removeClass("domain-name-reg-validation");
						}

					});

					return true;
				},

				onSubmit : function() {
					
					var tgURL = content.find("input[name='./targetURL']");
                    var buttonText = content.find("input[name='./buttonText']");
                    var destination = content.find("select[name='./destination']").val()
                    validateField(tgURL);
                    validateField(buttonText);
                    if(!tgURL.checkValidity() || !buttonText.checkValidity()){
					  printError(ERROR_MESSAGE);

                    }else{

					 var $form = $(".cq-dialog.foundation-form");

                    if(destination=='link' && urlMatcher()){

					   ns.ui.helpers.prompt({
	                     title: Granite.I18n.get("Warning"),
	                     type : ns.ui.helpers.PROMPT_TYPES.WARNING,
	                     message: WARNING_MESSAGE,
	                     actions: [{
	                            id: "CANCEL",
	                            text: "CANCEL",
	                            className: "coral-Button"
	                        },{
	                            id: "SUBMIT",
	                            text: "SUBMIT",
	                            className: "coral-Button"
	        
	                        }
	                    ],
	                    callback: function (actionId) {
	                        if (actionId === "SUBMIT") {
	                            $form.submit();
	                        }else if(actionId === "CANCEL") {
	                           return false;
	                        }
	                    }
	                  });

                     }else{

					    return true;
                     }

                }//else
				}

			});
})($, $(document), Granite.author, CUI);
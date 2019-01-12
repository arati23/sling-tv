
(function($, $document,ns){

  var dlgContent;

  //To get the count of the multifields in the dialog
  function multiFieldCount(dlCont){

	  var count= dlCont.find(".coral3-Multifield").find(".coral3-Multifield-item").length;
	  return count;

  }

	    Sling.CUI.Component.tabs = new Class({

		extend: Sling.CUI.Component.componentbase,

		onDialogReady:function(){

			dlgContent = $(".cq-dialog.foundation-form");
			var addButton = dlgContent.find(".coral3-Multifield").find("button").last().addClass("js-multifield-add");
			var maxLimit =  dlgContent.attr("max-mf-items");
			var checkBoxItems = dlgContent.find(".coral3-Multifield").find("coral-checkbox[name='./defaultTab']");
			
			//If a default checkbox is checked make all other default checkboxes disabled
			checkBoxItems.each(function(){
				
				if($(this).prop('checked')){
					$("coral-checkbox[name='./defaultTab']").attr("disabled","disabled");
					$(this). removeAttr('disabled');

				}

			});

			//Disable 'Add' button and show the limit messsage if there are already 5 multifields(maxlimit) on load.
			if(multiFieldCount(dlgContent)>=maxLimit){

				dlgContent.find('.js-multifield-add').attr("disabled","disabled");

                var messageLimit=dlgContent.find(".coral3-Multifield").find(".js-multi-item-max-message");

                    if(messageLimit.length==0){
						$('.js-multifield-add' ).after("<p class='js-multi-item-max-message' style='color:red;'> Maximum of "+maxLimit+" Tabs Reached</p>");
                    }
				}else{
					dlgContent.find('.js-multi-item-max-message').remove();
				}


			  /*On click of add button, allow only a maximum of 5 multifields(maxlimit) in the dialog, 
			  if the limit of 5 has reached, display the limit reached message and disable the add button */

			   $(document).on("click",".js-multifield-add",function() {

                if(multiFieldCount(dlgContent)>=maxLimit ){

					dlgContent.find('.js-multifield-add').attr("disabled","disabled");

                    var messageLimit=dlgContent.find(".coral3-Multifield").find(".js-multi-item-max-message");

                    if(messageLimit.length==0){
						$('.js-multifield-add' ).after("<p class='js-multi-item-max-message' style='color:red;'> Maximum of "+maxLimit+" Tabs Reached</p>");
                    }
				}else{
					dlgContent.find('.js-multi-item-max-message').remove();
				}


				/*Iterate through the default checkboxes to find if any of the checkbox is checked,
				 *if checked, disable all other default checkboxes*/
				var checkBoxItems = dlgContent.find(".coral3-Multifield").find("coral-checkbox[name='./defaultTab']");
				checkBoxItems.each(function(){

					if($(this).prop('checked')){
						$("coral-checkbox[name='./defaultTab']").attr("disabled","disabled");
						$(this).removeAttr('disabled');

					}
				});


			});
            
			/* On deleting a multifield check if the number of multifields is less that the 
			 * max limit, if less enable the 'Add' button and remove the limit reached message.
			 * If the multifield with the default-checkbox checked is removed, enable all other 
			 * default-checkboxes*/
			$(document).on("click",".coral3-Multifield-remove",function() {

				if(multiFieldCount(dlgContent)<maxLimit){
					dlgContent.find('.js-multifield-add').removeAttr('disabled');
					dlgContent.find('.js-multi-item-max-message').remove();
				}

				var checkBoxItems = dlgContent.find(".coral3-Multifield").find("coral-checkbox[name='./defaultTab']");
				$("coral-checkbox[name='./defaultTab']").removeAttr('disabled');
              	checkBoxItems.each(function(){

					if($(this).prop('checked')){
                        $("coral-checkbox[name='./defaultTab']").attr("disabled","disabled");
 						$(this).removeAttr('disabled');
                    }
				});
			});

			/*On change of the default checkbox, if it is checked then disable all other default-checkboxes
			 * except that, if not enable all default-checkboxes*/
			
			$(document).on("change","coral-checkbox[name='./defaultTab']", function() {
				if($(this).prop('checked')){
					$("coral-checkbox[name='./defaultTab']").attr("disabled","disabled");
					$(this).removeAttr('disabled'); 
				}else{

					$("coral-checkbox[name='./defaultTab']").removeAttr('disabled'); 

				}
            


			});

			return true;
		},
		
		//On submit, validate one default-checkbox has been checked */
		onSubmit:function(){

			var bFlag = false;
			var checkBoxItems = dlgContent.find(".coral3-Multifield").find("coral-checkbox[name='./defaultTab']");
			checkBoxItems.each(function(){

				if($(this).prop('checked')){
					bFlag = true;
					return true;
				}
			});

			if(!bFlag){
				ns.ui.helpers.prompt({
					title: "Error!",
					message: "No default tab has been checked!",
					type: ns.ui.helpers.PROMPT_TYPES.ERROR,
					actions: [
						{
							id: "OK",
							text: Granite.I18n.get("OK", "OK")
						}
						]
				});
				
				return false;
			}else{
				return true;
			}

		}

	});
})($, $(document),Granite.author);
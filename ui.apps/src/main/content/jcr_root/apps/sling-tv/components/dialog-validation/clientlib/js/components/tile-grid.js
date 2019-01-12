(function($, $document, ns) {

    function setUniQueID(el){

        var uniqueIdElement = $(el).find('[name="./uniqueId"]');
        uniqueIdElement.prop("readonly",true);
        var uniqueId = uniqueIdElement.val();
        uniqueIdElement.css("background-color","#e5e0e0");

        if (!uniqueId) {
            var randomVal = Math.floor(10000 + Math.random() * 9000);
            uniqueIdElement.attr("value",randomVal);
            uniqueIdElement.closest('.coral-Form-fieldwrapper').hide();
        }


    }

 Sling.CUI.Component.tilegrid = new Class(
			{

				extend : Sling.CUI.Component.componentbase,

				onDialogReady : function() {



					var dlgContent = $(".cq-dialog.foundation-form").find(
							".coral3-Multifield");
                    var dlgForm = $(".cq-dialog.foundation-form");

                    dlgForm.find($("[name='./uniqueId']")).closest('.coral-Form-fieldwrapper').hide();



                    var hideBreakout = dlgForm.find('input[type="checkbox"]');


					if(!(hideBreakout).prop('checked')){

                        dlgForm.find($("[name='./tabUrl']")).closest('.coral-Form-fieldwrapper').hide();


                    }
					var elAdd = dlgContent.find("button").last();

					if (elAdd.length > 0
							&& $(elAdd)[0].hasAttribute('coral-multifield-add')) {

						$(elAdd).addClass('multifield-add-item');
					}

                    var multiItems = dlgContent.find(".coral3-Multifield-item");

					$(".cq-dialog.foundation-form").on(
							"click",
							".multifield-add-item",
							function(event) {
                        if(!(hideBreakout).prop('checked')){

                        dlgForm.find($("[name='./tabUrl']")).closest('.coral-Form-fieldwrapper').hide();
                         }

                             	var i = 0;
                                var multiItems = dlgContent.find(".coral3-Multifield-item");
								$(multiItems).each(function(index, el) {

                                            setUniQueID(el);


										});

							});
                    $(multiItems).each(function(index, el) {

                        setUniQueID(el);
                        
                    });




                    $(document).on("change","coral-checkbox[name='./hideBreakout']", function() {
                    if(!(hideBreakout).prop('checked')){

                        dlgForm.find($("[name='./tabUrl']")).closest('.coral-Form-fieldwrapper').hide();


            		}else{

                    dlgForm.find($("[name='./tabUrl']")).closest('.coral-Form-fieldwrapper').show();

            		}

                });

                    return true;

				}




			});
})($, $(document), Granite.author);
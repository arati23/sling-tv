(function($, $document, ns) {

    function setUniQueID(el){

        var uniqueIdElement = $(el).find('[name="./uniqueId"]');
        uniqueIdElement.prop("readonly",true);
        var uniqueId = uniqueIdElement.val();
        uniqueIdElement.css("background-color","#e5e0e0");

        if (!uniqueId) {
            var randomVal = Math.floor(10000 + Math.random() * 9000);
            uniqueIdElement.attr("value",randomVal);
        }


    }

 Sling.CUI.Component.offersDeals = new Class(
			{

				extend : Sling.CUI.Component.componentbase,

				onDialogReady : function() {

                    console.log("Offers Deals dialog opened");
					var dlgContent = $(".cq-dialog.foundation-form").find(
							".coral3-Multifield");

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
                             	var i = 0;
                                var multiItems = dlgContent.find(".coral3-Multifield-item");
								$(multiItems).each(function(index, el) {

                                            setUniQueID(el);


										});

							});
                    $(multiItems).each(function(index, el) {

                        setUniQueID(el);
                        
                    });

					return true;
				}



			});
})($, $(document), Granite.author);
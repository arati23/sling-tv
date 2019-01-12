(function($, $document,ns){
	
	function printError(errorMessage){
        ns.ui.helpers.prompt({
                title: "Error!",
                message: errorMessage,
                type: ns.ui.helpers.PROMPT_TYPES.ERROR,
                actions: [
                    {
                        id: "OK",
                        text: Granite.I18n.get("OK", "OK")
                    }
                ]
        });
    }	

    function multiElementsDisplay(item, type){

     if(type=="image"){

		 item.find("input[name='./videoPath']").closest(".coral-Form-fieldwrapper").hide();
		 item.find("input[name='./videoURL']").closest(".coral-Form-fieldwrapper").hide();
		 item.find("input[name='./fallBackImageUrl']").closest(".coral-Form-fieldwrapper").hide();
		 item.find("input[name='./altName']").closest(".coral-Form-fieldwrapper").show();
		 item.find("input[name='./carousalImage']").closest(".coral-Form-fieldwrapper").show();
		 item.find("input[name='./imageURL']").closest(".coral-Form-fieldwrapper").show();

     }else if(type=="video"){

		 item.find("input[name='./altName']").closest(".coral-Form-fieldwrapper").hide();
		 item.find("input[name='./carousalImage']").closest(".coral-Form-fieldwrapper").hide();
		 item.find("input[name='./imageURL']").closest(".coral-Form-fieldwrapper").hide();
		 item.find("input[name='./videoPath']").closest(".coral-Form-fieldwrapper").show();
		 item.find("input[name='./videoURL']").closest(".coral-Form-fieldwrapper").show();
		 item.find("input[name='./fallBackImageUrl']").closest(".coral-Form-fieldwrapper").show();

     }else if(type=="select"){

		 item.find("input[name='./altName']").closest(".coral-Form-fieldwrapper").hide();
		 item.find("input[name='./carousalImage']").closest(".coral-Form-fieldwrapper").hide();
		 item.find("input[name='./imageURL']").closest(".coral-Form-fieldwrapper").hide();
		 item.find("input[name='./videoPath']").closest(".coral-Form-fieldwrapper").hide();
		 item.find("input[name='./videoURL']").closest(".coral-Form-fieldwrapper").hide();
		 item.find("input[name='./fallBackImageUrl']").closest(".coral-Form-fieldwrapper").hide();

     }

    }

Sling.CUI.Component.rotatingmarquee = new Class({

extend: Sling.CUI.Component.componentbase,

onDialogReady:function(){


    var dlgContent =$(".cq-dialog.foundation-form").find(".coral3-Multifield");

    $(document).on("selected",".coral-Select.js-media-type",function(e){

    	var curentItem= $(this).closest(".coral3-Multifield-item");
        var selectedVal=$(this).find(":selected").val();
        if(selectedVal=="image"){

            multiElementsDisplay(curentItem,"image");

         }else if(selectedVal=="video"){

            multiElementsDisplay(curentItem,"video");

         }

    });

    var elAdd= dlgContent.find("button").last();
	
	if(elAdd.length>0 && $(elAdd)[0].hasAttribute('coral-multifield-add')){

         $(elAdd).addClass('multifield-add-item');
    }

     $(".cq-dialog.foundation-form").on("click", ".multifield-add-item", function(event) {

        var item=dlgContent.find(".coral3-Multifield-item").last();

        if(item.find('[name="./mediaType"]').val()=='select'){

            multiElementsDisplay(item,"select");
        }

    });

     var multiItems=dlgContent.find(".coral3-Multifield-item");

    $(multiItems).each( function( index, el ) {

        var mediaVal = $(el).find('[name="./mediaType"]').val();
        if( mediaVal == "image"){

             multiElementsDisplay($(el),"image");

        }else if( mediaVal == "video"){

             multiElementsDisplay($(el),"video");
        }
    });


    return true;
 },

 onSubmit:function(){
	 
    var dlgContent =$(".cq-dialog.foundation-form").find(".coral3-Multifield");
    var multiItems=dlgContent.find(".coral3-Multifield-item");

    var flag=true;

    $(multiItems).each( function( index, el ) {

        var mediaVal = $(el).find('[name="./mediaType"]').val();
    
        if( mediaVal == "select"){

            flag=false;
    
            printError("Multifield Item Media type should be either image or video, please correct it!");
        }

    });

    if(flag){
        return true;
    }else{
		return false;
    }
 }
 

});
})($, $(document),Granite.author);

(function($, $document,ns){

     var content="";
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

    Sling.CUI.Component.columncontainer = new Class({

    extend: Sling.CUI.Component.componentbase,

    onDialogReady:function(){
        
        content=$(".cq-dialog.foundation-form");
        if($("coral-checkbox[name='./colOverride']").prop('checked')){
            
           content.find("coral-select[name='./setup']").parent(".coral-Form-fieldwrapper").hide();
        }
     $(document).on("change", "input[name$=Val]", function() {
         
         if($(this).val()!="" && !$.isNumeric($(this).val())){
             $(this).val("");
             printError("Please provide numeric value for the width");

         }
     });
    
    $(document).on("change", "coral-checkbox[name='./colOverride']", function() {
        
        if($(this).prop('checked')){
            content.find("coral-select[name='./setup']").parent(".coral-Form-fieldwrapper").hide();
        }else{
            content.find("coral-select[name='./setup']").parent(".coral-Form-fieldwrapper").show();
        }
    });
     return true;
    },

    onSubmit:function(){

        if(!$("coral-checkbox[name='./colOverride']").prop('checked')){

            return true;

        }

        var totalWidth=0;

        $( "input[name$=Val]" ).each( function( index, element ){

            if($.isNumeric($(this).val())){
				totalWidth+=parseFloat($(this).val());
			}

		});

        if(totalWidth>100){

            printError("The total width is "+totalWidth+" should not be greater than 100");
		    return false;

        }
         return true;
        }

});
})($, $(document),Granite.author);

(function($, $document,ns){

    var content="";

    Sling.CUI.Component.flexcolumncontainer = new Class({

    extend: Sling.CUI.Component.componentbase,

    onDialogReady:function(){
        content=$(".coral-Form.foundation-form");
        if($("coral-checkbox[name='./colOverride']").prop('checked')){

            content.find('.js-dialog-flex-column-container--width-option').parent(".coral-Form-fieldwrapper").hide();

        }
        var column1Val=content.find("input[name='./column1Val']").val();
        $(document).on("change", ".js-dialog-flex-column-container--input-text-width", function() {
            if($(this).val()!="" && !$.isNumeric($(this).val())){
                $(this).val("");
                ns.ui.helpers.notify("Please provide numeric value");
            }
        });
    
        $(document).on("change", "coral-checkbox[name='./colOverride']", function() {
            if($(this).prop('checked')){
                content.find('.js-dialog-flex-column-container--width-option').parent(".coral-Form-fieldwrapper").hide();
            }else{
                content.find('.js-dialog-flex-column-container--width-option').parent(".coral-Form-fieldwrapper").show();
            }
        });
         return true;
    },

    onSubmit:function(){
        if(!$("coral-checkbox[name='./colOverride']").prop('checked')){

            return true;

        }
		var column1Val=content.find("input[name='./column1Val']").val();
		var column2Val=content.find("input[name='./column2Val']").val();
		var column3Val=content.find("input[name='./column3Val']").val();
		var column4Val=content.find("input[name='./column4Val']").val();
		var column5Val=content.find("input[name='./column5Val']").val();
		var column6Val=content.find("input[name='./column6Val']").val();
        var totalWidth=0;
		if($.isNumeric(column1Val)){
			totalWidth+=parseFloat(column1Val);
		}
		if($.isNumeric(column2Val)){
			totalWidth+=parseFloat(column2Val);
		}
		if($.isNumeric(column3Val)){
			totalWidth+=parseFloat(column3Val);
		}
		if($.isNumeric(column4Val)){
			totalWidth+=parseFloat(column4Val);
		}
		if($.isNumeric(column5Val)){
			totalWidth+=parseFloat(column5Val);
		}
		if($.isNumeric(column6Val)){
			totalWidth+=parseFloat(column6Val);
		}
        if(totalWidth>100){
			ns.ui.helpers.prompt({
			title: "Error!",
			message: "The total width is "+totalWidth+" should not be greater than 100",
			type: ns.ui.helpers.PROMPT_TYPES.ERROR,
			actions: [
				{
					id: "OK",
					text: Granite.I18n.get("OK", "OK")
				}
			]
		    });
            return false;

        }
         return true;
        }

});
})($, $(document),Granite.author);
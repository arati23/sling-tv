(function($, $document){
    Sling.CUI.Component.componentbase=new Class({

        toString:"componentbase",
        onDialogReady:function(){
            return true;
        },

        onSubmit:function(){
			return true;
        }
});

})($, $(document));
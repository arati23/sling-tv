
(function ($, $document,ns){

   "use strict";

   var jsClassObject;

   $document.on("dialog-ready", function(){

        var compJsId=$('.cq-dialog.foundation-form').attr('js-comp-identifier');
		if(compJsId){
            compJsId=Sling.CUI.Component[compJsId];
            jsClassObject=new compJsId();
            jsClassObject.onDialogReady();

        }

   });

   $(document).on("click",".cq-dialog-submit",function(e){
       var compValStatus=true;
       var commonValStatus=true;
       if(jsClassObject){
          compValStatus = jsClassObject.onSubmit();
        }

       if(compValStatus && commonValStatus){
          return true;
       }else{
          return false;
       }

 });

})($,$(document),Granite.author);

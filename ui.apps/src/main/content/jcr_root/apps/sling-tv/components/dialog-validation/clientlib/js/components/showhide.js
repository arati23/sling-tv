(function(document, $) {
  "use strict";

  // when dialog gets injected
  $(document).on("foundation-contentloaded", function(e) {
    // if there is already an inital value make sure the according target element becomes visible
    showHide($(".cq-dialog-tab-showhide", e.target));
  });

  $(document).on("selected", ".cq-dialog-tab-showhide", function(e) {
    showHide($(this));
  });

  $(document).on("change", ".cq-dialog-tab-showhide", function(e) {
    showHide($(this));
  });

  function showHide(el){
    el.each(function(i, element) {
      /* get the selector to find the target elements. its stored as data-.. attribute */
      var target = $(element).data("cqDialogTabShowhideTarget");
      if ($(element).data("select")) {

        // get the selected value
        var value = $(element).data("select").getValue();

        // make sure all unselected target elements are hidden.
        $(target).not(".hide").addClass("hide");

        /* show the target element that contains the selected value as data-showhidetargetvalue attribute */
        if(value)
          $(target+'.'+value).removeClass("hide");
      }else if($(element).is('input:checkbox')){

        // toggle the target element that contains the selected value as data-showhidetargetvalue attribute
        if($(element).is(':checked')){
          $(target).removeClass( "hide" );
        }else{
          $(target).addClass( "hide" );
        }

      }
    })
  }

})(document,Granite.$);
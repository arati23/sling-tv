(function(document, $) {
    "use strict";

  $(document).on("foundation-contentloaded", function(e) {
    var ctaStyle = $(e.target).find('select').filter('[name="./ctaBorder"]');
    if(ctaStyle.length) {
      var ctaSize = $(e.target).find('select').filter('[name="./ctaOptions"]');
      var sizeVal = ctaSize.find(':selected').val();
      if(sizeVal == 'cta-button--extraLarge') {
        ctaSize.val('cta-button--extra-large');
      }
    }
  });
})(document,Granite.$);

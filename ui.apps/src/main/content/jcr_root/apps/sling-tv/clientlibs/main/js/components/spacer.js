/* Spacer (That's right, spacer) */

(function(){
  $(document).ready(function() {
    $('.js-spacer').each(function () {
      var elem = $(this);
      var aemGrid = elem.closest('.aem-Grid');
      var parent = elem.parent();
      var style = elem.attr('style');

      if(style.indexOf(' 0px') == -1 && style.indexOf(' 0%') == -1) {
        parent.attr('style', style);
        aemGrid.attr('style', 'height:100%');
      }
    });
  });
})();

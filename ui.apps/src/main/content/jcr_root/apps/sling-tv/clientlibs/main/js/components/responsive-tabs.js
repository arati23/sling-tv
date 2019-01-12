/* Responsive Tabs */

(function() {
  $(document).ready(function(){
    var tabs = $('.tgc-tab');
    tabs.on('click', function(){
      $('.tgc-tab-selected').removeClass('tgc-tab-selected');
      $(this).addClass('tgc-tab-selected');
    });

    if(isAuthorMode()) {
      $('.tab-pane').removeClass('fade');
    }

    lazyLoad(tabs);
  });

})();

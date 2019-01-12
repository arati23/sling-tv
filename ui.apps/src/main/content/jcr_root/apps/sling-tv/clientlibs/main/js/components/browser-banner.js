/* Browser Banner */

(function() {
  $(document).ready(function() {
    var banner = $('.js-browser-banner');
    if(isAuthorMode() || SlingTVUtils.browserType() == "Chrome") {
      banner.show();
    }
    lazyLoad(banner);
  });
})();

(function(){
  $(document).ready(function() {
    $(".js-dynamic-cta-link").click(function (e) {
      var elem = $(this);
      var parent = elem.closest('.js-dynamic-cta-outer');
      var valueSelected = elem.attr('id');
      parent.find(".js-dynamic-cta").hide();
      parent.find('.' + valueSelected).show();
      parent.find('.js-dynamic-cta-image').attr('src', elem.find('img').attr('src'));
    });

    lazyLoad($('.js-dynamic-cta-outer'));
  });
})();

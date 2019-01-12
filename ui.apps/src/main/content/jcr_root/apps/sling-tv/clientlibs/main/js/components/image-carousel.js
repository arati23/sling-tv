/* Image/Carousel */

(function() {

  var carousels = $('.js-image-carousel');
  lazyLoad(carousels, 99, true);
  lazyLoad(carousels, 100, false);

  $(document).ready(function() {
    $('.js-image-carousel-indicator').on('click', function (e) {
      console.log(e);
      e.stopPropagation();
      $('.js-image-carousel').carousel($(this).data('slide-to'));
    });
  });

})();

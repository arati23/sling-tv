/* Device Ribbon */

(function () {

  if (slingUtils.resolution.isMobile()) {
    $('.js-device-ribbon-all-devices').each(function (index) {
      $('.js-device-carousel-large').hide();
      var currentComponent = $(this);
      var imageCount = 0;
      var slide = $('<div class="device-carousel-slides '+'device-ribbon-slide-'+index+'"></div>');
      var rows = $('.js-device-carousel-container').attr('data-sling-one-row');
      $(currentComponent).attr('data-slide-index',1);
      $(currentComponent).attr('data-slide-current-index',index);
      var numberOfImages = 8;
      if (rows == 'true') {
        numberOfImages = 4;
        $('.js-previous-device-carousel, .js-next-device-carousel').css('bottom', '25px');
      }
      var imageCountDevice = $(currentComponent).children().length;
      if (imageCountDevice < numberOfImages) {
        $('.js-device-carousel-container').hide();
        $('.js-device-carousel-large').show();
      } else {
        $(currentComponent).children().each(function () {
          if (imageCount < numberOfImages) {
            slide.append($(this));
            imageCount++;
            imageCountDevice--;
            createSlide(slide, imageCountDevice, currentComponent, index);
          } else if (imageCount == numberOfImages) {
            $(currentComponent).siblings('.js-device-slideshow-container').append(slide);
            $(currentComponent).parent().siblings('.js-device-carousel-dots-container').append($('<span class="dot js-current-carousel-dot '+'device-ribbon-dot-'+index+'"></span>'));
            prepareSwipe(slide);
            slide = $('<div class="device-carousel-slides '+'device-ribbon-slide-'+index+'"></div>');
            slide.append($(this));
            imageCountDevice--;
            imageCount = 1;
            createSlide(slide, imageCountDevice, currentComponent, index);
          }
        });
        showSlides($(currentComponent).attr('data-slide-index'), $(currentComponent));
      }
    });
  } else {
    $('.js-device-carousel-container').hide();
  }

  function prepareSwipe(deviceRibbonSlide) {
    var callbacks = {
      right: function () {
        var chevron = deviceRibbonSlide.parent().parent().find('.js-previous-device-carousel');
        chevron.click();
      },
      left: function () {
        var chevron = deviceRibbonSlide.parent().parent().find('.js-next-device-carousel');
        chevron.click();
      }
    };
    onSwipe(deviceRibbonSlide, callbacks);
  }

  function createSlide(slide, imageCountDevice, currentComponent, index) {
    if (imageCountDevice == 0) {
      $(currentComponent).siblings('.js-device-slideshow-container').append(slide);
      $(currentComponent).parent().siblings('.js-device-carousel-dots-container').append($('<span class="dot js-current-carousel-dot '+'device-ribbon-dot-'+index+'"></span>'));
      prepareSwipe(slide);
      slide = $('<div class="device-carousel-slides '+'device-ribbon-slide-'+index+'"></div>');
      imageCount = 0;
    } else {
      return false;
    }
  }

  lazyLoad($('.js-device-ribbon'));

  $('.js-previous-device-carousel').on('click', function () {
    var indexSlide = $(this).siblings('.js-device-ribbon-all-devices').attr('data-slide-index');
    indexSlide = parseInt(indexSlide) + 1;
    $(this).siblings('.js-device-ribbon-all-devices').attr('data-slide-index', indexSlide);
    showSlides(indexSlide, $(this).siblings('.js-device-ribbon-all-devices'));
  });

  $('.js-next-device-carousel').on('click', function () {
    var indexSlide = $(this).siblings('.js-device-ribbon-all-devices').attr('data-slide-index');
    indexSlide = parseInt(indexSlide) - 1;
    $(this).siblings('.js-device-ribbon-all-devices').attr('data-slide-index', indexSlide);
    showSlides(indexSlide, $(this).siblings('.js-device-ribbon-all-devices'));
  });

  $('.js-current-carousel-dot').on('click', function () {
    var indexSlide = $(this).index() + 1;
    $(this).parent().parent().find('.js-device-ribbon-all-devices').attr('data-slide-index', indexSlide);
    showSlides(indexSlide, $(this).parent().parent().find('.js-device-ribbon-all-devices'));
  });

  function showSlides(n, currentComponentIndex) {
    var i;
    var componentIndex = $(currentComponentIndex).attr('data-slide-current-index');
    var slides = document.getElementsByClassName("device-ribbon-slide-"+componentIndex);
    var dots = document.getElementsByClassName("device-ribbon-dot-"+componentIndex);
    if (n > slides.length) {
      $(currentComponentIndex).attr('data-slide-index', 1);
    }
    if (n < 1) {
      $(currentComponentIndex).attr('data-slide-index', slides.length);
    }
    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[$(currentComponentIndex).attr('data-slide-index') - 1].style.display = "block";
    dots[$(currentComponentIndex).attr('data-slide-index') - 1].className += " active";
  }
})();

$(".js-offer-details-open").on('click', function () {
  $(this).parent('.sublink').siblings('.js-offer-details-modal').removeClass('hide');

  var d = new Date();
  var offerDays = parseInt($(this).attr("data-sling-offer-days"));

  if (isNaN(offerDays)) {
    d.setDate(d.getDate() + 7)
  }
  else {
    d.setDate(d.getDate() + offerDays);
  }

  //This is so brittle...
  $('.js-offer-details-modal').find(".date").text((d.getMonth() + 1) + "/" + d.getDate() + "/" + d.getFullYear());
});


$('.js-offer-details-close').on('click', function () {
  $(this).closest('.js-offer-details-modal').addClass('hide');
});

(function ctaScrollConstructor() {
  var ctaPosition = 0,
    headerHeight,
    lastScroll = 0,
    mobileWidth = 767,
    scrollTimeout;


  /**
   * Set up events
   */
  function onReady() {
    var ctaContainer = $(".js-cta-header");
    var ctaInContainer = $(".js-cta-in-header");

    if (isAuthorMode()) {
      $('.js-cta-in-header .btn').css('display', 'inline-block');
    }


    if (!ctaContainer.length) {
      //If component doesn't exist we are looking for position of cta(Header or Banner)
      if (!ctaInContainer.length) {
        return;
      } else {
        addCtaInHeader();
      }
    } else {
      var parseCta = false, nonparseCta = false;
      if(ctaContainer.length > 1) {
        $(ctaContainer).each(function(index, ele) {
          if($(ele).parents('.js-grid-interaction-pc-grid').length) {
            parseCta = true;
          } else {
            nonparseCta = true;
          }
        });
        if(parseCta && nonparseCta) {
          console.error('More than One header sticky CTA authored');
          $(ctaContainer).first().css('z-index', '1133');
        }
      }
      ctaContainer.removeClass('.js-cta-in-header');
      headerHeight = $('.header_main').height();
      $(window).scroll(slingUtils.debounce(function () { onScroll() }, 5));
    }
  }

  function addCtaInHeader() {
    if (isAuthorMode() || $(window).width() < mobileWidth) {
      //Desktop only
      return;
    }

    var ctaContainer = $(".js-cta-in-header");
    var theCTA = ctaContainer.find('.cta-button');
    if (ctaContainer.length) {
      ctaContainer.first().addClass("cta_float");
      theCTA.addClass("cta-button--small");
      if (theCTA.hasClass('cta-button--large')) {
        theCTA.removeClass('cta-button--large');
      }
      else if (theCTA.hasClass('cta-button--extraLarge') || theCTA.hasClass('cta-button--extra-large')) {
        theCTA.removeClass('cta-button--extraLarge').removeClass('cta-button--extra-large');
      }
    }
  }


  /**
   * Add cta header class when scrolling past
   */
  function onScroll() {
    if (isAuthorMode() || $(window).width() < mobileWidth) {
      //Desktop only
      return;
    }
    scrollTimeout = false;
    var scrollTop = $(this).scrollTop();
    var ctaContainer = $(".js-cta-header");
    var theCTA = ctaContainer.find('.cta-button');
    var elementHeight = ctaContainer.height();
    var elementTop = ctaContainer.get(0).getBoundingClientRect().top;
    if (!ctaPosition) {
      ctaPosition = elementTop - elementHeight;
      ctaPosition = (ctaPosition >= 0) ? ctaPosition : 0;
    }
    if (scrollTop > lastScroll) {
      if (elementTop + elementHeight - headerHeight <= 0) {
        ctaContainer.addClass("cta_float");
      }
    }
    if (scrollTop <= ctaPosition) {
      ctaContainer.removeClass("cta_float");
      $(ctaContainer).first().css('z-index', '1133');
    }
    lastScroll = scrollTop;
  }


  $(document).ready(onReady);
})();

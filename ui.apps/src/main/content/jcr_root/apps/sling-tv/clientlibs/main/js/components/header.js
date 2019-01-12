(function headerConstructor() {
  var finishDelay = 400,
      finishTimeout,
      mobileWidth = 767,
      preHeaderHeight,
      scrollTimeout,
      scrollTop = 0;

  /**
   * Highlight In Page Links based on scroll position
   */
  function highlightLink() {
    var winner;
    var links = $('.header_in-page-nav-link');
    links.removeClass('header_in-page-nav-active');
    links.each(function() {
      var elem = $(this);
      var link = elem.attr('href');
      if(link != '#') {
        var anchor = $(link);
        if(anchor.length && anchor.offset().top <= $(document).scrollTop() + 1) {
          winner = elem;
        }
        else {
          return false;
        }
      }
    });
    if(winner) {
      winner.addClass('header_in-page-nav-active');
    }
  }

  /**
   * Set up events and such
   */
  function onReady() {
    /**
     * Show sign in/out depending on login status
     */
    var signOutLink = $('.js-sign-out');
    if(isLoggedIn()) {
      signOutLink.show();
      $('.js-my-account').show();
    }
    else {
      $('.js-sign-in').show();
    }

    $('.js-header-link').show();
    $('.header_hamburger').addClass('collapsed');

    signOutLink.click(logout);

    $('.header_hamburger').on('click', function() {
      if($(this).hasClass('collapsed')) {
        $('.navbar-mask').show();
      } else {
        $('.navbar-mask').hide();
      }
    });

    $('.navbar-mask').on('click', function() {
      $('.header_hamburger').trigger('click');
    });

    var preHeader = $('.js-preheader');
    if(preHeader.length) {
      preHeaderHeight = preHeader.outerHeight();

      $(document).scroll(slingUtils.debounce(function(){onScroll()},5));
      setTimeout(onScroll, 0);
    }

    lazyLoad($('header'), 1);
  }

  /**
   * onScroll
   */
  function onScroll() {
    scrollTimeout = false;

    highlightLink();

    var header = $('.header_main');
    if(!isAuthorMode() && $(window).width() > mobileWidth && header.closest('.ng-theme').length) {
      var next = $(document).scrollTop();
      if(!scrollTop) {
        scrollTop = next;
      }
      var delta = scrollTop - next;
      var top = header.css('top') ? +header.css('top').split('px')[0] : 0;
      var newTop = Math.max(-preHeaderHeight, Math.min(0, top + delta));
      var theCTA = $(".js-cta-header");
      var theCTAHeader = $(".js-cta-in-header");
      header.css('top', newTop + 'px');
      theCTA.css('top', newTop + 'px');
      theCTAHeader.css('top', newTop + 'px');
      $('.js-cta-in-header .btn').css('display', 'inline-block');
      scrollTop = next;
      if(finishTimeout) {
        clearTimeout(finishTimeout);
      }
      finishTimeout = setTimeout(function(){scrollFinish(delta > 0)}, finishDelay);
    }
  }

  
  /**
   * Prevent stopping in-between states
   *
   * @param {boolean} down Scrolling down?
   */
  function scrollFinish(down) {
    finishTimeout = false;
    var header = $('.header_main');
    var theCTA = $(".js-cta-header");
    var a = {top: down || $(document).scrollTop() < preHeaderHeight ? 0 : -preHeaderHeight};
    header.animate(a);
    theCTA.animate(a);
  }

  $(document).ready(onReady);

})();

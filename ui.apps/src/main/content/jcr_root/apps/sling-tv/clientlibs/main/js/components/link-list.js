/* Link List */

(function () {
  var lists = $('.js-link-list');

  if(!lists.length) {
    /* If no component, do nothing */
    return;
  }


  /**
   * Turn data attributes into URL parameters
   *
   * @param {int} index - Not used, but passed by $.each
   * @param {HTMLElement} link
   */
  function mapCtaLinks(index, link) {
    link = $(this);
    var params = false;
    var data = {};
    var map = {
      classification: 'Classification',
      flow: 'CartFlow',
      step: 'CartStep',
      device: 'DeviceType',
      plan: 'PlanId',
      offer_id: 'OfferId',
      'package': 'PackageId',
      extra: 'Extra'
    };
    $.each(map, function(k, v) {
      var attr = link.data('sling' + v);
      if(attr) {
        data[k] = attr;
        params = true;
      }
    });
    if(params) {
      link.attr('href', link.attr('href') + '?' + $.param(data, true));
    }
  }


  /**
   * Add/remove fade effect based on scroll position
   *
   * @param {jQuery} list
   * @param {boolean} isAtTop
   * @param {boolean} isAtBottom
   */
  function onScroll(list, isAtTop, isAtBottom) {
    if(isAtTop) {
      list.removeClass('link-list--fade-top').addClass('link-list--fade-bottom').removeClass('link-list--fade-both');
    }
    else if(isAtBottom) {
      list.addClass('link-list--fade-top').removeClass('link-list--fade-bottom').removeClass('link-list--fade-both');
    }
    else {
      list.removeClass('link-list--fade-top').removeClass('link-list--fade-bottom').addClass('link-list--fade-both');
    }
  }


  /**
   * Readjust list height based on form factor
   * @param {jQuery} list
   */
  function resize(list) {
    if(slingUtils.resolution.isDesktop()) {
      var desktopHeight = list.data('slingDesktopHeight');
      if(desktopHeight) {
        list.css('max-height', desktopHeight);
      }
    }
    else if(slingUtils.resolution.isTablet()) {
      var tabletHeight = list.data('slingTabletHeight');
      if(tabletHeight) {
        list.css('max-height', tabletHeight);
      }
    }
    else {
      var mobileHeight = list.data('slingMobileHeight');
      if(mobileHeight) {
        list.css('max-height', mobileHeight);
      }
    }
    var api = list.data('jsp');
    api.reinitialise();
  }



  $(document).ready(function() {
    lists.each(function() {
      var list = $(this);
      list.on(
        'jsp-scroll-y',
        function(event, scrollPositionY, isAtTop, isAtBottom) {
            onScroll(list, isAtTop, isAtBottom);
        }
      ).jScrollPane();
      resize(list);
      $(window).on('resize', slingUtils.debounce(function(){resize(list)}, 100));
      list.find('a').each(mapCtaLinks);
    });
  });

})();

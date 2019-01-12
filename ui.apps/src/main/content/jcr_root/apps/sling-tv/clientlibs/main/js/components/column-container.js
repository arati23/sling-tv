/* Column Control */

(function() {

  /**
   * Maximum width to be considered "mobile"
   *
   * @type {number}
   */
  var lastWindowWidth, mobileWidth = 767;


  /**
   * Function generator for filtering carousel children
   * (Prevents grabbing descendants of descendant column containers)
   *
   * @param {jQuery} carousel
   * @returns {Function}
   */
  function carouselFilter(carousel) {
    return (function() {return $(this).closest('.column-control').is(carousel.closest('.column-control'))});
  }


  /**
   * Function generator for filtering container children
   * (Prevents grabbing descendants of descendant column containers)
   *
   * @param {jQuery} container
   * @returns {Function}
   */
  function containerFilter(container) {
    return (function() {return $(this).closest('.column-control').is(container)});
  }


  /**
   * Gets the carousel object from the column container
   *
   * @param {jQuery} container
   * @returns {jQuery}
   */
  function getCarousel(container) {
    return container.find('.js-column-carousel').filter(containerFilter(container));
  }


  /**
   * Gets the columns from the carousel object
   *
   * @param {jQuery} carousel
   * @returns {jQuery}
   */
  function getColumns(carousel) {
    var columns = carousel.find('.js-column-carousel-column').filter(carouselFilter(carousel));
    var remove = [];
    columns.each(function() {
      var column = $(this);
      if(!column.children().length && !column.text().trim().length) {
        remove.push(column);
      }
    });
    $.each(remove, function() {
      columns = columns.not(this);
    });
    return columns;
  }


  /**
   * Calculate the width of the column margin from the container
   *
   * @param {jquery} container
   * @returns {number}
   */
  function getColumnMargin(container) {
    return container.width() * .1;
  }


  /**
   * Calculate the width of the column from the container
   *
   * @param {jQuery} container
   * @returns {number}
   */
  function getColumnWidth(container) {
    return container.width() * .8;
  }


  /**
   * Get the indicator div from the carousel object
   *
   * @param {jQuery} carousel
   * @returns {jQuery}
   */
  function getIndicators(carousel) {
    return carousel.find('.js-column-carousel-indicators').filter(carouselFilter(carousel));
  }


  /**
   * Calculates the highest left position available given the # of columns and column width
   *
   * @param {jQuery} container
   * @returns {number}
   */
  function getLeftMax(container) {
    var columns = getColumns(getCarousel(container));
    var margin = getColumnMargin(container);
    var columnWidth = getColumnWidth(container);
    var leftMost = 0;

    columns.each(function (k, v) {
      var left = margin + (k * columnWidth);
      leftMost = left - margin;
    });
    return leftMost;
  }


  /**
   * Get the outer wrapper (the part the animates) from the carousel object
   *
   * @param {jQuery} carousel
   * @returns {jQuery}
   */
  function getWrapper(carousel) {
    return carousel.find('.js-column-carousel-outer').filter(carouselFilter(carousel));
  }


  /**
   * On Resize
   *
   * @param {jQuery} container
   */
  function onResize(container) {
    if($(window).width() == lastWindowWidth) {
      return;
    }
    var nextWindowWidth = $(window).width();
    setTimeout(function() {
      lastWindowWidth = nextWindowWidth;
    }, 1);
    repaint(container)
  }


  /**
   * Resize the carousel based on the height of the tallest column (+ 30px for the indicators)
   *
   * @param {jQuery} carousel
   */
  function recalculateHeight(carousel) {
    var columns = getColumns(carousel);
    carousel.height(0);
    columns.each(function (k, v) {
      var column = $(this);
      if(carousel.height() < column.height()) {
        carousel.height(column.height());
      }
    });
    carousel.height(carousel.height() + 30);
  }


  /**
   * Repaint
   *
   * @param {jQuery} container
   */
  function repaint(container) {

    if(!isAuthorMode()) {
      var carousel = getCarousel(container);
      var wrapper = getWrapper(carousel);
      var indicators = getIndicators(carousel);

      positionCarouselItems(container);
      recalculateHeight(carousel);
      if($(window).width() > mobileWidth) {
        getWrapper(carousel).css('transform', '');
      }
      else {
        wrapper.css('transform', 'translateX(-' + (indicators.find('.active').index() * getColumnWidth(container)) + 'px)');
      }
    }
  }


  /**
   * Arrange the columns in their appropriate left positions
   *
   * @param {jQuery} container
   */
  function positionCarouselItems(container) {
    var carousel = getCarousel(container);
    var columns = getColumns(carousel);
    var margin = getColumnMargin(container);
    var columnWidth = getColumnWidth(container);

    var isDesktop = $(window).width() > mobileWidth;

    columns.each(function (k, v) {
      var column = $(this);
      var left = margin + (k * columnWidth);
      column.css('left', isDesktop ? '' : left);
    });
  }


  /**
   * Set up the swipe events
   *
   * @param {jQuery} container
   */
  function setUpSwipe(container) {
    var carousel = getCarousel(container);
    var wrapper = getWrapper(carousel);
    var indicators = getIndicators(carousel);

    var callbacks = {
      right: function() {
        var leftMost = getLeftMax(container);
        var xPosition = wrapper.css('transform') ? +wrapper.css('transform').slice(19,-4) : 0;
        var columnWidth = getColumnWidth(container);
        xPosition = xPosition - columnWidth < -leftMost ? -leftMost : xPosition - columnWidth;
        wrapper.css('transform', 'translateX(' + xPosition + 'px)');
        indicators.find('.active').removeClass('active');
        $(indicators.children().get(Math.floor((-xPosition / columnWidth) + .5))).addClass('active');
      },
      left: function() {
        var xPosition = wrapper.css('transform')? +wrapper.css('transform').slice(19,-4) : 0;
        var columnWidth = getColumnWidth(container);
        xPosition = xPosition + columnWidth > 0 ? 0 : xPosition + columnWidth;
        wrapper.css('transform', 'translateX(' + xPosition + 'px)');
        indicators.find('.active').removeClass('active');
        $(indicators.children().get(Math.floor((-xPosition / columnWidth) + .5))).addClass('active');
      }
    };
    onSwipe(container, callbacks);
  }


  /**
   * On Ready
   */
  $(document).ready(function() {
    $('.column-control').each(function() {
      var container = $(this);
      var carousel = getCarousel(container);
      if(!carousel.length) {
        container.find('.column-carousel_inner').filter(containerFilter(container)).removeClass('column-carousel_inner');
        return;
      }
      var wrapper = getWrapper(carousel);
      var columns = getColumns(carousel);
      var indicators = getIndicators(carousel);
      var dot = carousel.find('.js-column-carousel-indicator').filter(carouselFilter(carousel));

      if(columns.length < 2) {
        container.find('.column-carousel_inner').filter(containerFilter(container)).removeClass('column-carousel_inner');
        indicators.remove();
        container.find('.js-column-carousel-fade').filter(containerFilter(container)).remove();
        return;
      }

      while(carousel.find('.js-column-carousel-indicator').length < columns.length) {
        indicators.append(dot.clone());
      }

      dot.addClass('active');

      var columnWidth = getColumnWidth(container);

      indicators.find('.js-column-carousel-indicator').click(function() {
        indicators.find('.active').removeClass('active');
        wrapper.css('transform', 'translateX(' + (-columnWidth * $(this).index()) + 'px)');
        $(this).addClass('active');
      });

      positionCarouselItems(container);
      recalculateHeight(carousel);
      $(window).on('load', function() {
        recalculateHeight(carousel);
      });

      setUpSwipe(container);

      /**
       * On Resize
       */
      $(window).resize(slingUtils.debounce(function(){onResize(container);}));
      if (slingUtils.resolution.isMobile()) {
        $('.js-column-carousel-outer').attr('style', '');
      }
    });
  });

})();


/* Parallax Component */

(function parallaxConstructor() {
  var scrollTimeout;

  /**
   * Set up events and such
   */
  function onReady() {
    var parallax = $('.parallax_container');
    if(!parallax.length) {
      /* Do nothing if component doesn't exist */
      return;
    }

    parallax.each(function() {
      $(this).find('.parallax_image').each(function() {
        $(this).css('width', $(this).data('slingWidth') + '%');
      });
    });

    $(window).on('resize', setHeight);

    setHeight();
    
    $(document).scroll(slingUtils.debounce(function(){onScroll()},5));
    setTimeout(onScroll, 0);
    

    lazyLoad(parallax, 99, true);
    lazyLoad(parallax, 100);
  }

  function calculateMobilePos(elem) {
    return {
      slingStartX : elem.data('slingStartX'),
      slingStartY : elem.data('slingStartY'),
      slingEndX : elem.data('slingEndX'),
      slingEndY : elem.data('slingEndY')
    }
  }

  function onScroll() {
    scrollTimeout = false;

    $('.parallax_container').each(function() {
      var docViewTop = $(document).scrollTop();
      var docViewBottom = docViewTop + $(window).height();
      var elemTop = $(this).offset().top;
      var elemBottom = elemTop + $(this).height();
      var twentyFivePercentHeight = ((elemBottom - elemTop )/4);
      var pc = $(this);
      var windowHeights = ($(window).height() - twentyFivePercentHeight);
      var pos = $(document).scrollTop() + windowHeights;
      //$(this).css('border', '1px solid blue');

      if(pos < pc.offset().top) {
        pc.find('img').each(function() {
          var elem = $(this);
          var imgPos = calculateMobilePos(elem);
          elem.css('left', imgPos.slingStartX + '%');
          elem.css('top', imgPos.slingStartY + '%');
        });
      }
      else if(pos > pc.offset().top + pc.height() / 2) {
        pc.find('img').each(function() {
          var elem = $(this);
          var imgPos = calculateMobilePos(elem);
          elem.css('left', imgPos.slingEndX + '%');
          elem.css('top', imgPos.slingEndY + '%');
        });
      }
      else {
        var delta = (pos - pc.offset().top) / (pc.height() / 2);
        pc.find('img').each(function() {
          var elem = $(this);
          var imgPos = calculateMobilePos(elem);
          elem.css('left', (((imgPos.slingEndX - imgPos.slingStartX) * delta) + imgPos.slingStartX) + '%');
          elem.css('top', (((imgPos.slingEndY - imgPos.slingStartY) * delta) + imgPos.slingStartY) + '%');
        });
      }
      pc.data('slingLastPos', pos);
    });
  }

  function setHeight() {
    var parallax = $('.parallax_container');
    parallax.each(function() {
      var elem = $(this);
      var height = $(window).width() > $(window).height() ? elem.data('slingDesktopHeight') : elem.data('slingMobileHeight');
      elem.height(height);
    });
  }

  
  $(document).ready(onReady);

})();

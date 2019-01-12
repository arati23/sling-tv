/**
 * Rotating Marquee
 */
(function() {

  /**
   * Set up event listeners for video items
   *
   * @param {jQuery} carousel
   */
  function prepareVideos(carousel) {
    var videos = carousel.find('video');

    if(!videos.length) {
      return;
    }

    videos.each(function (k, v) {
      this.pause();
      this.addEventListener('ended', function (e) {
        this.currentTime = 0;
        if(carousel.find('.item').length == 1) {
          this.play();
        }
        else {
          carousel.carousel();
          carousel.carousel('next');
        }
      });
    });

    carousel.on('slid.bs.carousel', function (e) {
      var video = $(e.relatedTarget).find('video');
      if(video.length) {
        carousel.carousel('pause');
        video.get(0).play();
      }
    });
  }


  /**
   * Set up event listeners for image click events
   *
   * @param {jQuery} carousel
   */
  function prepareLinks(carousel) {
    var imageCount = carousel.find('.item').length;

    for(var i = 0; i < imageCount; i++) {
      var className = ".carousel-img" + i;
      carousel.find(className).click(function (e) {
        var imgUrl = $(this).data("slingImageUrl");

        if(imgUrl) {
          //Append .html to local paths
          if(imgUrl.indexOf("/content/") == 0) {
            imgUrl += ".html";
          }
          window.open(imgUrl, '_blank');
        }
      });
    }
  }


  /**
   * Set up event listeners for the carousel indicators (little dots)
   *
   * @param {jQuery} carousel
   */
  function prepareIndicatorEvents(carousel) {
    carousel.find('.carousel-indicator').on('click', function (e) {
      e.stopPropagation();
      $('#rotating-marquee-actual').carousel($(this).data('slide-to'));
    });
  }


  /**
   * Set up events
   */
  function onReady() {
    var rmComponent = $('.rotating-marquee');
    if(!rmComponent.length) {
      return;
    }

    var carousel = rmComponent.find('.carousel');

    carousel.carousel();

    prepareVideos(carousel);
    prepareLinks(carousel);

    prepareIndicatorEvents(carousel);

    lazyLoad(rmComponent, 1, false, function(){startInitialVideo(carousel)});
  }


  /**
   * Start 1st video after lazy load
   *
   * @param {jQuery} carousel
   */
  function startInitialVideo(carousel) {
    var video = carousel.find('.active').find('video');

    if(!video.length) {
      return;
    }

    carousel.carousel('pause');
    video.get(0).play();
  }


  $(document).ready(onReady);

})();

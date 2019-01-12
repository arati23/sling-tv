/* Video component */

(function() {

  /**
   * Click event to start video
   *
   * @param {Event} e
   */
  function clickVideo(e) {
    var elem = $(e.currentTarget);
    var component = elem.closest('.js-video-component');
    startVideo(component);
  }


  function closeVideo(e) {
    var component = $(this).closest('.js-video-component');
    component.find('.js-video-container').empty().css('height', '');
    component.find('.js-video-play-button').show();
    component.find('.js-video-close-button').hide();
  }


  /**
   * Start the video
   *
   * @param {jQuery} component - The component element
   */
  function startVideo(component) {
    var container = component.find('.js-video-container');
    if(component.data('slingYouTube')) {
      var iframe = $('<iframe class="video--iframe"></iframe>');
      container.height(350);//Magic Number!!!!!
      iframe.attr('src', 'https://www.youtube.com/embed/' + component.data('slingYouTube') + '?rel=0&autoplay=1');
      container.append(iframe);
    }
    else {
      var video = $('<video autoplay controls><source src="' + component.data('slingDamVideo') + '"/></video>');
      container.append(video);
      video.get(0).load();
    }
    container.show();
    var poster = component.find('.js-video-poster');
    var close = component.find('.js-video-close-button');
    if(poster.length) {
      container.height(poster.height());
      close.show();
    }
    else {
      close.remove();
      if(iframe) {
        iframe.css('height', '100%');
      }
    }
    component.find('.js-video-play-button').hide();
  }



  $(document).ready(function() {
    var videos = $('.js-video-component');
    if(!videos.length) {
      return;
      //Do nothing if no component
    }

    videos.each(function() {
      var elem = $(this);
      if(elem.data('slingAutoPlay') && !isAuthorMode()) {
        startVideo(elem);
      }
    });

    $('.js-video-play-button').click(clickVideo);
    $('.js-video-close-button').click(closeVideo);

  });
})();

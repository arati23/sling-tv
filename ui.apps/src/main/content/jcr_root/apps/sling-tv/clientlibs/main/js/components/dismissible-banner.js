/* Dismissible Banner */

(function() {
	
	$('.dismissible-banner-close').click(function(e) {
	    $(e.currentTarget).closest('.dismissible-banner').slideUp();
	  });
  lazyLoad($('.dismissible-banner'), 1, true, function(){$('.js-dismissible-spacer').css('visibility', 'hidden')});

  lazyLoad.last(function(){

    //Get alternate version eventually...
    lazyLoad($('.dismissible-banner'));

  }, 10000);
})();

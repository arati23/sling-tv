/* Program Ribbon */

(function() {
  $(document).ready(function() {
	  
	  $(".program-ribbon .center").slick({
	        dots: false,
	        infinite: true,
	        centerMode: false,
	        slidesToShow: 6,
	        slidesToScroll: 1,
			autoplay: true, responsive: [
	    {
	      breakpoint: 1080,
	      settings: {
	        slidesToShow: 4,
	        slidesToScroll: 1,
	        infinite: true,
	        dots: false
	      }
	    },
	    {
	      breakpoint: 1024,
	      settings: {
	        slidesToShow: 4,
	        slidesToScroll: 1,
	        infinite: true,
	        dots: false
	      }
	    },
	    {
	      breakpoint: 600,
	      settings: {
	        slidesToShow: 3,
	        slidesToScroll: 1
	      }
	    },
	    {
	      breakpoint: 480,
	      settings: {
	        slidesToShow: 2,
	        slidesToScroll: 1
	      }
	    }
	  ]
	      });
    lazyLoad($('.js-program-ribbon'));
  });
})();

$(document).ready(function() {
  renameSVGFiltersUniquely();

  $('.js-feedbackModel').on('click', function() {
    $('.modal-backdrop').removeClass('hide').addClass('in');
  });
  $('.js-close').on('click', function() {
    $('.modal-backdrop').removeClass('in').addClass('hide');
  });
  $('#myTabs > div').on('click', function(e) {
    e.preventDefault();
    //  alert('helo');
    $(this).tab('show');
    $(this).addClass('active-tab');
    $(this).siblings().removeClass('active-tab');
  });
  $('#hp-content-ribbon_ul').slick({
                                     dots: false,
                                     slidesToShow: 5,
                                     slidesToScroll: 1,
                                     infinite: false,
                                     prevArrow: '<div id="#slider-next" class="content-arrow left"><span class="fa"></span></div>',
                                     nextArrow: '<div id="#slider-prev" class="content-arrow right"><span class="fa"></span></div>',
                                     touchMove: false
                                   });
  $('.js-services-link').on('click', function() {
    $('.js-sling-modal').removeClass('hide').addClass('in');
  });
  $('.close-modal').on('click', function() {
    $('.js-sling-modal').removeClass('in').addClass('hide');
  });
  try {
    SlingTVUtils.conditionalRedirectToBrowserPlayer();
  }
  catch(e) {
    console.log(e);
  }

  lazyLoad.last(function() {
    $('body').append('<i id="brandon-inject">This forces the browser to download the italic version of the Brandon Font</i>');
    setTimeout(function() {
      $('#brandon-inject').remove();
    }, 1);
  });

  lazyLoad.last(function() {
    $.getScript('https://origin.extole.io/sling/core.js');
  }, 98);

  lazyLoad.last(function() {
    /* Google Tag Manager */
    (function(w, d, s, l, i) {
      w[l] = w[l] || [];
      w[l].push({
                  'gtm.start':
                    new Date().getTime(), event: 'gtm.js'
                });
      var f = d.getElementsByTagName(s)[0],
        j = d.createElement(s), dl = l != 'dataLayer' ? '&l=' + l : '';
      j.async = true;
      j.src =
        'https://www.googletagmanager.com/gtm.js?id=' + i + dl;
      f.parentNode.insertBefore(j, f);
    })(window, document, 'script', 'dataLayer', 'GTM-NH99KB');
    /* End Google Tag Manager */
  });
});


/**
 * Unique (Enough) ID generator
 *
 * @returns {int}
 */
var getEUID = (function() {
  var nextEUID = 0;
  return function() {
    nextEUID++;
    return nextEUID;
  }
})();




/**
 * @return {boolean} - True if in author mode
 */
function isAuthorMode() {
  return $.cookie('wcmmode') && $.cookie('wcmmode') == "edit" &&
  !(typeof URLSearchParams !== 'undefined' && (new URLSearchParams(window.location.search).get('wcmmode')) == 'disabled');
}

/**
 * @return {boolean} - True if user is logged into OTT/New Stack
 */
function isLoggedIn() {
  var data;
  data = $.cookie('AUTHORIZATION:Token');
  if(data && data.length > 0) {
    return true;
  }
  data = sessionStorage.getItem('newco.auth_token');
  return (data && data.length > 0);
}


/**
 * Prepends characters up to a certain length
 *
 * @param {string} str - The initial string
 * @param {int=2} len - The length to pad to
 * @param {string=0} chr - The character to prepend
 *
 * @returns {string}
 */
function leftPad(str, len, chr) {
  var length = len ? len : 2;
  var character = chr ? chr : '0';
  var ret = str.toString();
  while(ret.toString().length < length) {
    ret = character + ret;
  }
  return ret;
}


/**
 * Delete cookies and such
 */
function logout() {
  sessionStorage.removeItem('newco.auth_token');
  $.cookie.raw = true;
  $.removeCookie('userJwt', {domain: '.sling.com', path: '/'});
  $.removeCookie('AUTHORIZATION:Token', {domain: '.sling.com', path: '/'});
  $.cookie.raw = false;
  sessionStorage.clear();
  localStorage.removeItem('state');
  $('.js-sign-out').hide();
  $('.js-my-account').hide();
  $('.js-sign-in').show();
}


/**
 * Lazy Fetch moment.js (and set up the timezone)
 */
var momentReady = (function() {
  var ajaxing = false;
  var multiAjaxDelay = 100;

  /**
   * Check if moment.js is setup
   * Fetch moment.js if needed
   *
   * @param {Function=} callback - Execute this function when moment.js is ready
   * @returns {boolean|void} - Returns whether moment.js is ready (only if no callback is provided)
   */
  function ready(callback) {
    if(!callback) {
      return (typeof moment !== 'undefined' && typeof moment.tz !== 'undefined' && moment.tz.zone('America/New_York'));
    }
    var momentCallback = function() {
      //Call self to do next step
      ajaxing = false;
      ready(callback);
    };
    if(typeof moment === 'undefined') {
      if(ajaxing) {
        setTimeout(momentCallback, multiAjaxDelay);
      }
      else {
        $.getScript('https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js', momentCallback);
        ajaxing = true;
      }
      return;
    }
    if(typeof moment.tz === 'undefined') {
      if(ajaxing) {
        setTimeout(momentCallback, multiAjaxDelay);
      }
      else {
        $.getScript('https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.13/moment-timezone.min.js', momentCallback);
        ajaxing = true;
      }
      return;
    }
    if(!moment.tz.zone('America/New_York')) {
      //This data should be valid until 2022 (Can be updated from here: https://github.com/moment/moment-timezone/blob/develop/data/packed/latest.json)
      moment.tz.add("America/New_York|EST EDT EWT EPT|50 40 40 40|01010101010101010101010101010101010101010101010102301010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010|-261t0 1nX0 11B0 1nX0 11B0 1qL0 1a10 11z0 1qN0 WL0 1qN0 11z0 1o10 11z0 1o10 11z0 1o10 11z0 1o10 11z0 1qN0 11z0 1o10 11z0 1o10 11z0 1o10 11z0 1o10 11z0 1qN0 WL0 1qN0 11z0 1o10 11z0 1o10 11z0 1o10 11z0 1o10 11z0 1qN0 WL0 1qN0 11z0 1o10 11z0 RB0 8x40 iv0 1o10 11z0 1o10 11z0 1o10 11z0 1o10 11z0 1qN0 WL0 1qN0 11z0 1o10 11z0 1o10 11z0 1o10 11z0 1o10 1fz0 1cN0 1cL0 1cN0 1cL0 1cN0 1cL0 1cN0 1cL0 1cN0 1fz0 1cN0 1cL0 1cN0 1cL0 1cN0 1cL0 1cN0 1cL0 1cN0 1fz0 1a10 1fz0 1cN0 1cL0 1cN0 1cL0 1cN0 1cL0 1cN0 1cL0 1cN0 1fz0 1cN0 1cL0 1cN0 1cL0 s10 1Vz0 LB0 1BX0 1cN0 1fz0 1a10 1fz0 1cN0 1cL0 1cN0 1cL0 1cN0 1cL0 1cN0 1cL0 1cN0 1fz0 1a10 1fz0 1cN0 1cL0 1cN0 1cL0 1cN0 1cL0 14p0 1lb0 14p0 1nX0 11B0 1nX0 11B0 1nX0 14p0 1lb0 14p0 1lb0 14p0 1nX0 11B0 1nX0 11B0 1nX0 14p0 1lb0 14p0 1lb0 14p0 1lb0 14p0 1nX0 11B0 1nX0 11B0 1nX0 14p0 1lb0 14p0 1lb0 14p0 1nX0 11B0 1nX0 11B0 1nX0 Rd0 1zb0 Op0 1zb0 Op0 1zb0 Rd0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Rd0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Rd0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Rd0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Rd0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0 Op0 1zb0|21e6");
    }
    callback();
  }

  return ready;
})();



function onSwipe(element, callbacks) {
  if(callbacks.up || callbacks.down) {
    throw new Error('Not implemented');
  }

  var touchX = null;
  var touchY = null;

  element.on('touchstart', function(e){
    touchX = e.originalEvent.touches[0].clientX;
    touchY = e.originalEvent.touches[0].clientY;
  });
  element.on('touchend', function(e) {
    if((Math.abs(e.originalEvent.changedTouches[0].clientX - touchX) < Math.abs(e.originalEvent.changedTouches[0].clientY - touchY)) ||
      (Math.abs(e.originalEvent.changedTouches[0].clientX - touchX) < 15)) {
      touchX = null;
      touchY = null;
      return;
    }

    if(touchX > e.originalEvent.changedTouches[0].clientX) {
      callbacks.right();
    }
    else if(touchX < e.originalEvent.changedTouches[0].clientX) {
      callbacks.left();
    }

    touchX = null;
    touchY = null;

    //Don't count as a swipe for the outer content
    e.stopImmediatePropagation();
  });

}


/**
 * If SVG filter IDs are not unique, they don't work :(
 */
function renameSVGFiltersUniquely() {
  $('svg').each(function(idx, elem) {
    var svg = $(elem);
    var children = svg.children();
    if(children.get(0).tagName == 'filter') {
      var filter = children.first();
      var id = filter.prop('id');
      filter.prop('id', id + '-' + idx);
      id = filter.prop('id');
      svg.find('image').attr('filter', 'url(#' + id + ')');
    }
  });
}

/**
 * Limit Zip Fields to 5 characters, numeric only
 *
 * @param e
 */
function validateZip(e) {
  var key = e.keyCode || e.which;

  if(!(window.getSelection().toString() || e.currentTarget.selectionStart != e.currentTarget.selectionEnd) && !(e.ctrlKey || e.metaKey) &&
    ((key > 64 && key < 91) || key > 106 || (e.shiftKey && key > 47) || (e.target.value.length >= 5 && key > 47 && key < 58))) {
    e.preventDefault();
  }
}

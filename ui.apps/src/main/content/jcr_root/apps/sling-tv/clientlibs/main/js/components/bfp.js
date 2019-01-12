/* Browser Free Preview */
(function() {
  var jwt;
  var environment = 'p';
  var urlParts = window.location.host.split('.');
  if(urlParts.length > 3) {
    urlParts.pop();
    urlParts.pop();
    environment = urlParts.pop();
  }

  var umsUrl = 'https://ums.' + environment + '.sling.com/v5/user/';
  var createPreviewUserApi = umsUrl + 'create_preview_user';
  var updateUserApi = umsUrl + 'update_user';
  var cookieDomain = '.' + (environment == 'p' ? '' : environment + '.') + 'sling.com';
  var watchDomain = 'https://watch' + cookieDomain;
  var watchUrl = watchDomain + '/watch';



  $('.js-bfp-container').each(function() {
    var cartTimeout, overlayTimeout, clickJack, fullscreen = false;
    var bfp = $(this);
    var iframe = bfp.find('.js-bfp');
    var poster = bfp.find('.js-bfp-poster');
    var overlay = bfp.find('.js-bfp-overlay');
    var clickJack = bfp.find('.js-bfp-click-jack');
    var emailInput = bfp.find('.js-bfp-email');
    var emailErrorMessage = bfp.find('.js-bfp-user-error');
    var passInput = bfp.find('.js-bfp-pass');
    var passErrorMessage = bfp.find('.js-bfp-pass-error');
    var previewId = bfp.data('slingBfpPreviewId');
    var channelId = bfp.data('slingBfpChannelId');
    var registerTime = ~~bfp.data('slingBfpRegisterTime');
    registerTime = registerTime ? registerTime : 1;
    var cartTime = ~~bfp.data('slingBfpCartTime');
    cartTime = cartTime ? cartTime : 5;
    var signupUrl = bfp.data('slingBfpCtaUrl');
    signupUrl = signupUrl ? signupUrl : '/signup';
    var defaultErrorText = bfp.find('.js-bfp-error').text().trim();
    defaultErrorText = defaultErrorText ? defaultErrorText : "There was an unknown error";
    var messageDiv = bfp.find('.js-bfp-message');
    var errorDiv = bfp.find('.js-bfp-error');
    var registerButton = bfp.find('.js-bfp-register');

    /**
     * Make the iFrame take up the entire window
     */
    function enterFullScreen() {
      fullscreen = true;
      iframe.attr('style', '')
            .css({
                   position:'fixed',
                   height: '100%',
                   left: 0,
                   top: 0,
                   "padding-bottom": 0,
                   "z-index": 99999
                 });
      clickJack.css({
                      bottom: '20px',
                      left: '',
                      right: '20px',
                      top: '',
                      "z-index": 100000,
                      position: 'fixed'
                    });
    }


    /**
     * Return the iFrame to original size
     */
    function exitFullScreen() {
      fullscreen = false;
      iframe.attr('style', '')
            .css({
                   position: false,
                   height: iframe.width() * 9/16,
                   "padding-bottom": '10px',
                   "z-index": 0
                 });
      clickJack.css({
                      bottom: '',
                      left: iframe.offset().left + iframe.width() - 52 + 'px',
                      position: 'absolute',
                      right: '',
                      top: iframe.offset().top + iframe.height() - $('header').outerHeight() - 53 + 'px',
                      "z-index": 1
                    });
    }


    /**
     * Make clickjack decision based on the state
     */
    function hijackFullScreenButton() {
      if(overlayTimeout) {
        overlay.show();
        clearTimeout(overlayTimeout);
        // But don't set it to false until we register
      }
      else if(!fullscreen) {
        enterFullScreen();
      }
      else {
        exitFullScreen();
      }
    }


    /**
     * Setup and such
     */
    function onReady() {
      iframe.height(iframe.width() * 9/16);
      if(isAuthorMode()) {
        return;
      }
      var msg;
      if(window.location.href.indexOf('sling.com') === -1) {
        errorDiv.html("Browser player not available in 'View as Published'").show();
        return;
      }

      clickJack.on('click', hijackFullScreenButton);

      $.ajax(createPreviewUserApi, {
        contentType: "application/json",
        data: JSON.stringify({
                               preview_id: previewId
                             }),
        method: 'post',
        success: function(data) {
          jwt = data.token;
          $.cookie('userJwt', jwt, {path: '/', domain: cookieDomain});
          $.cookie('AUTHORIZATION:Token', jwt, {path: '/', domain: cookieDomain});
          iframe.attr('src', watchUrl + '?channelId=' + channelId + '&previewId=' + previewId);
          clickJack.css('top', iframe.offset().top + iframe.height() - $('header').outerHeight() - 53 + 'px');
          clickJack.css('left', iframe.offset().left + iframe.width() - 52 + 'px');
        },
        error: function(xhr) {
          messageDiv.hide();
          var msg;
          try {
            msg = JSON.parse(xhr.responseText).error;
          }
          catch(e) {
            msg = xhr.responseText ? xhr.responseText : defaultErrorText;
          }
          errorDiv.html(msg).show();
        }
      });
      poster.hide();
      overlayTimeout = setTimeout(function () {
        overlay.show();
      }, registerTime * 60000);
      cartTimeout = setTimeout(function () {
        if(!$.cookie('AUTHORIZATION:Token')) {
          $.cookie('AUTHORIZATION:Token', jwt, {path: '/', domain: cookieDomain});
          $.cookie('userJwt', jwt, {path: '/', domain: cookieDomain});
        }
        window.location.href = signupUrl;
      }, cartTime * 60000);
      registerButton.click(function () {
        if(validateEmail() && validatePassword()) {
          register(emailInput.val(), passInput.val());
        }
      });
      emailInput.on('keyup', submitOnEnter);
      passInput.on('keyup', submitOnEnter);
    }


    /**
     * Update user w/ email && pass
     *
     * @param {string} email
     * @param {string} pass
     */
    function register(email, pass) {
      $.ajax(updateUserApi, {
        beforeSend: function (request) {
          request.setRequestHeader("authorization", 'Bearer ' + jwt);
          request.setRequestHeader("x-jwt-authorization", 'Bearer ' + jwt);
        },
        contentType: "application/json",
        data: JSON.stringify({
                               email: email,
                               password: pass
                             }),
        method: 'post',
        success: function (data, textStatus, XMLHttpRequest) {
          clearTimeout(overlayTimeout);
          overlayTimeout = false;
          overlay.hide();
          iframe.get(0).contentWindow.postMessage('stageCompleted', watchUrl);
          messageDiv.show();
          errorDiv.hide();
          emailInput.prop('disabled', true);
          passInput.prop('disabled', true);
          enterFullScreen();
          $(document).on('keyup', function(e) {
            //ESCAPE
            if(e.keyCode == 27) {
              exitFullScreen();
            }
          });
          var btn = document.activeElement;
          $(btn).blur(function() {
            //Wait for focus event to complete before stealing back (otherwise keyboard events are sent to iFrame)
            setTimeout(function() {$(btn).focus();}, 200);
          });
        },
        error: function(xhr) {
          messageDiv.hide();
          var msg;
          try {
            msg = JSON.parse(xhr.responseText).error;
          }
          catch (e) {
            msg = xhr.responseText ? xhr.responseText : defaultErrorText;
          }
          errorDiv.show().html(msg);
        }
      });
    }


    function submitOnEnter(e) {
      if(e.which === 13) {
        registerButton.click();
      }
    }


    /**
     * Check whether email is valid (and highlight failure)
     *
     * @returns {boolean}
     */
    function validateEmail() {
      function emailError() {
        emailInput.addClass('error');
        emailErrorMessage.show();
      }

      emailInput.removeClass('error');
      emailErrorMessage.hide();

      var emailParts = emailInput.val().split('@');
      if(emailParts.length != 2) {
        emailError();
        return false;
      }
      if(emailParts[0].length < 1) {
        emailError();
        return false;
      }
      var domainParts = emailParts[1].split('.');
      if(domainParts.length < 2) {
        emailError();
        return false;
      }
      for(var part in domainParts) {
        if(part.length < 1) {
          emailError();
          return false;
        }
      }
      if(domainParts.pop().length < 2) {
        emailError();
        return false;
      }
      return true;
    }


    /**
     * Check whether password is valid (and highlight failure)
     *
     * @returns {boolean}
     */
    function validatePassword() {
      var pass = passInput.val();

      passErrorMessage.hide();
      passInput.removeClass('error');

      if(pass.length < 4) {
        passInput.addClass('error');
        passErrorMessage.show();
        return false;
      }
      return true;
    }


    $(document).ready(onReady);
  });
})();

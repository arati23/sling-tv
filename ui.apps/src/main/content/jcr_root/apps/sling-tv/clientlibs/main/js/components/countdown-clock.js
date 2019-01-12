/**
 * Countdown Clock
 */
(function countdownConstructor() {
  var MILLISECONDS_IN_SECONDS = 1000, SECONDS_IN_MINUTES = 60, MINUTES_IN_HOURS = 60, HOURS_IN_DAY = 24;
  var SECONDS_IN_DAY = SECONDS_IN_MINUTES * MINUTES_IN_HOURS * HOURS_IN_DAY;
  var SECONDS_IN_HOUR = SECONDS_IN_MINUTES * MINUTES_IN_HOURS;
  var data = {
    initial: {
      element: null,
      start: null,
      showDays: true,
      showTime: true,
      tempLength: 0
    },
    countdowns: []
  };


  /**
   * Update countdown clock HTML
   */
  function go() {
    $.each(data.countdowns, function(i, elem) {
      var countdown = data.countdowns[i];
      var now = new Date();
      var delta = parseInt((countdown.start - now) / MILLISECONDS_IN_SECONDS);
      if(delta < 0) {
        if(countdown.element.find('.js-countdown-end').text() && -delta < countdown.tempLength * SECONDS_IN_MINUTES) {
          countdown.element.addClass('countdown-clock-_-finished');
          countdown.element.removeClass('countdown-clock-_-datetime-only');
        }
        else {
          countdown.element.addClass('countdown-clock-_-datetime-only');
          countdown.element.removeClass('countdown-clock-_-finished');
        }
        return;
      }
      if(delta > countdown.secondsBeforeSwitch) {
        countdown.element.addClass('countdown-clock-_-datetime-only');
      }
      else {
        countdown.element.removeClass('countdown-clock-_-datetime-only');
      }
      if((delta > SECONDS_IN_DAY && countdown.showDays) || !countdown.showTime) {
        var days = parseInt(delta / SECONDS_IN_DAY);
        countdown.element.find('.js-countdown-days').find('.js-countdown-counter').text(days);
        delta -= days * SECONDS_IN_DAY;
      }
      else {
        countdown.element.find('.js-countdown-days').hide();
        countdown.element.find('.js-countdown-days-separator').hide();
      }
      var hours = parseInt(delta / SECONDS_IN_HOUR);
      countdown.element.find('.js-countdown-hours').find('.js-countdown-counter').text((days || !countdown.showTime) ? leftPad(hours) : hours);
      delta -= hours * SECONDS_IN_HOUR;
      var minutes = parseInt(delta / SECONDS_IN_MINUTES);
      countdown.element.find('.js-countdown-minutes').find('.js-countdown-counter').text(leftPad(minutes));
      delta -= minutes * SECONDS_IN_MINUTES;
      countdown.element.find('.js-countdown-seconds').find('.js-countdown-counter').text(leftPad(delta));
    });
  }


  /**
   * Capture data attributes and start interval
   */
  function onReady() {
    var countdownElements = $('.js-countdown-container');
    if(!countdownElements.length) {
      return;
    }
    if(!momentReady()) {
      momentReady(onReady);
    }
    countdownElements.each(function(){
      var countdown = $.extend({}, data.initial);
      countdown.element = $(this);
      countdown.start = new Date(moment.tz(countdown.element.data('slingCountdownStart'), 'America/New_York').format());
      countdown.showDays = (countdown.element.data('slingCountdownDays') + '').toLowerCase() == "true";
      countdown.showTime = (countdown.element.data('slingCountdownTime') + '').toLowerCase() == "true";
      countdown.tempLength = countdown.element.data('slingCountdownTempLength');
      countdown.secondsBeforeSwitch = countdown.element.data('slingCountdownDaysSwitch') *
        SECONDS_IN_MINUTES * MINUTES_IN_HOURS * HOURS_IN_DAY;
      data.countdowns.push(countdown);
    });
    countdownElements.removeClass('countdown-clock-_-not-started');
    go();
    setInterval(go, 1000);
    
    var imgs = countdownElements.find('img');
    imgs.each(function() {
    	if(!$(this).data('slingSrc')) {
    		$(this).remove();
    	}
    });
    lazyLoad(countdownElements);
  }


  $(document).ready(onReady);

})();

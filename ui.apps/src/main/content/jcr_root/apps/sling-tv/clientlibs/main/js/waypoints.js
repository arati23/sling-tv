/**
 * Waypoints Scroll event
 */
(function () {
  var lowest;
  var temp = ['spacer', 'rich-text'];
  var waypointTimeout;

  function saveLowest() {
    if(typeof ga !== 'undefined') {
      ga('send', 'event', {
        'eventCategory': 'Waypoint',
        'eventAction': location.href,
        'eventLabel': '{ "Scroll Until" : ' + lowest + '}'
      });
      waypointTimeout = false;
    }
    else {
      setTimeout(saveLowest, 1000);
    }
  }

  $('section').children().each(function (k, v) {
    var me = $(this);
    var className = me.prop('classList')[0];
    if(temp.indexOf(className) != -1) {
      if(!me.prop('id')) {
        me.prop('id', className + '-' + k);
      }
    }
    $(this).waypoint(function (direction) {
      if(direction === 'up') {
        return;
      }
      var $this = $(this.element);

      if($this.prop('id')) {
        if(!lowest || +(lowest.split('-').pop()) < +($this.prop('id').split('-').pop())) {
          lowest = $this.prop('id');

          if(!waypointTimeout) {
            waypointTimeout = setTimeout(saveLowest, 5000);
          }
        }
      }
    });
  });
})();

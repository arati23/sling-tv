/* Append query params to all links */

var slingUtils = (function preserveQueryStringConstructor(sling) {
  var excludedSubdomains = ['whatson', 'help', 'news'];

  var cartParams = ['classification', 'flow', 'step', 'device', 'plan', 'offer_id', 'package', 'extra', 'hd', 'dsc', 'ats', 'sp', 'sb', 'var'];
  var extoleParams = ['extole_zone_name', 'extole_campaign_id', 'extole_share_channel'];
  var avoidTheseQueryParams = cartParams.concat(extoleParams, [
    'locale', 'gamefinder_zip', 'gamefinder_search'
  ]);

  /**
   *
   * @param {*} query
   */
  function appendQuery(query) {
    var queryString = $.param(query, true);

    if(!queryString) {
      return;
    }
    $('a').each(function() {
      var anchor = '';
      var elem = $(this);
      var currentTarget = elem.attr('href');
      if(currentTarget && currentTarget.indexOf('#') != 0) {
        for(var i = 0; i < excludedSubdomains.length; i++) {
          if(currentTarget.indexOf(excludedSubdomains[i] + '.sling.com') != -1) {
            return; // continue -> $('a').each()
          }
        }
        if(currentTarget.indexOf('#') != -1) {
          var a = currentTarget.split('#');
          currentTarget = a[0];
          anchor = a[1];
        }
        if(currentTarget.indexOf('#') == -1) {
          elem.attr('href', currentTarget + (currentTarget.indexOf('?') == -1 ? '?' : '&') + queryString + (anchor ? '#' + anchor : ''));
        }
      }
    });
  }

  function preserveQueryString() {
    var query = {};
    if(window.location.href.indexOf('?') != -1) {
      $.each(window.location.href.split('?').pop().split('&'), function(k, v) {
        var split = v.split('=');
        if(split[0] && avoidTheseQueryParams.indexOf(split[0]) == -1) {
          query[split[0]] = split[1];
        }
      });

      appendQuery(query);
    }
  }

  $(document).ready(function() {
    preserveQueryString();
  });

  sling.appendQuery = appendQuery;

  return sling;
})(slingUtils || {});

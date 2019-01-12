/**
 * Gamefinder
 */
(function gamefinderLoader() {

  /**
   * Go fetch && execute the generated code
   */
  function onReady() {
    if($('#gamefinder--discovery-edition').length) {
      $.getScript('https://cdnjs.cloudflare.com/ajax/libs/react/16.5.2/umd/react.production.min.js').done(function() {
        $.getScript('https://cdnjs.cloudflare.com/ajax/libs/react-dom/16.5.2/umd/react-dom.production.min.js').done(function() {
          $.getScript('/etc.clientlibs/sling-tv/clientlibs/react.js');
        });
      });
    }
  }


  $(document).ready(onReady);

})();

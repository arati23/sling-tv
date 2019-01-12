/**
 * Consistent debounce
 */

var slingUtils = (function debounceConstructor(sling) {

  /**
   * Prevents a function from firing too frequently
   *
   * NOTE: Not tested w/ this or w/ arguments
   *
   * @param {Function} func
   * @param {int=} delay
   * @returns {Function}
   */
  function debounce(func, delay) {
    var timeout;

    return function() {
      var context = this, args = arguments;
      var later = function() {
        timeout = null;
        func.apply(context, args);
      };
      if(!timeout) {
        timeout = setTimeout(later, delay ? delay : 5);
      }
    };
  }



  sling.debounce = debounce;

  return sling;

})(slingUtils || {});

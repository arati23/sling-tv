/**
 * Consistent device width measurement
 */

var slingUtils = (function resolutionHandlerConstructor(sling) {
  var tabletMin = 768;
  var tabletMax = 1280;
  var mobileMax = tabletMin - 1;
  var desktopMin = tabletMax + 1;


  /**
   * @returns {string} - The device type as a string
   */
  function device() {
    if(resolution.isMobile()) {
      return 'MOBILE';
    }
    if(resolution.isTablet()) {
      return 'TABLET';
    }
    return 'DESKTOP';
  }


  /**
   * @returns {boolean} - Whether the device is desktop resolution
   */
  function isDesktop() {
    return $(window).width() >= desktopMin;
  }


  /**
   * @returns {boolean} - Whether the device is mobile resolution
   */
  function isMobile() {
    return $(window).width() <= mobileMax;
  }


  /**
   * @returns {boolean} - Whether the device is tablet resolution
   */
  function isTablet() {
    return $(window).width() <= tabletMax && $(window).width() >= tabletMin;
  }




  var resolution = {};
  resolution.isMobile = isMobile;
  resolution.isTablet = isTablet;
  resolution.isDesktop = isDesktop;
  resolution.device = device;

  sling.resolution = resolution;

  return sling;

})(slingUtils || {});

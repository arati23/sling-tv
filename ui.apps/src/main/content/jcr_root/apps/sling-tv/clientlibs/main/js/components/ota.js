 // OTA Component JS


(function otaConstructor() {

  var addressInput, zipInput, searchBtn;

  var keyHandlers = (function keyHandlerConstructor() {

    /**
     * Key Event Handler for Address Input
     *
     * @param {Event} e - The key event
     */
    function address(e) {
      var addressVal = addressInput.val().trim();
      var key = e.keyCode || e.which;

      if(addressVal.length == 0) {
        addressInput.addClass("ota-input-error");
        $("#ota-address-input-error").show();
      }
      else {
        addressInput.removeClass("ota-input-error");
        $("#ota-address-input-error").hide();
      }

      if(addressVal && zipInput.val().length == 5) {
        searchBtn.removeClass('disable-ota-search-btn').addClass("btn-primary");
        if(key == 13) {
          go();
        }
      }
      else {
        searchBtn.addClass('disable-ota-search-btn').removeClass("btn-primary");
      }
    }


    /**
     * Key Event Handler for Zip Code Input
     *
     * @param {Event} e - The key event
     */
    function zip(e) {
      var zipVal = zipInput.val().trim();
      var key = e.keyCode || e.which;

       validateZip(e);

      
      if(zipVal.trim().length == 0) {
        zipInput.addClass("ota-input-error");
        $("#ota-zip-input-error").show();
      }
      else {
        zipInput.removeClass("ota-input-error");
        $("#ota-zip-input-error").hide();
      }

      if(addressInput.val() && zipVal.length==5) {
        searchBtn.removeClass('disable-ota-search-btn').addClass("btn-primary");
        if(key == 13) {
          go();
        }
      }
      else {
        searchBtn.addClass('disable-ota-search-btn').removeClass("btn-primary");
      }
    }


    return {
      address: address,
      zip: zip
    }
  })();


  var addressScrub = (function addressScrubConstructor() {

    var addressScrubURL;


    /**
     * Do AJAX call for Address Scrub service
     */
    function go() {
      var addressVal = addressInput.val().trim();
      var zipVal = zipInput.val().trim();



      if(addressVal && zipVal.length == 5) {
           reset();
        $.ajax({
          type: 'POST',
          url: addressScrubURL,
          contentType: 'application/json',
          dataType: 'json',
          data: JSON.stringify({
            address1: addressVal,
            zip: zipVal
          }),
          success: antennaRecommendation,
          error: fail
        });
      }
    }


    /**
     * Failure Handler for Address Scrub
     *
     * @param {XMLHttpRequest} xhr - The AJAX request
     */
    function fail(xhr) {
      console.log('Address Scrub Error');
      console.log(xhr);
      $("#address-error-container").show();
      if(xhr.status == 400) {
        $("#address-400-error").show();
      }
      else {
        $("#address-other-errors").show();
      }
    }


    /**
     * Grab Address Scrub URL from HTML
     */
    function onReady() {
      if(!$('.ota').length) {
        /* Component does not exist */
        return;
      }
      addressScrubURL = $("#ota-address-scrub-url").html()
    }

    $(document).ready(onReady);


    return go;
  })();


  var antennaRecommendation = (function antennaRecommendationConstructor() {

    var antennaServiceUrl;
    var icons = {};


    /**
     *
     * @param {Object[]} stations - The list of stations
     * @param {string} stations[].network - The major network the channel is associated with
     * @param {string} stations[].channelCallSign - The channel's call letters
     * @param {boolean} stations[].inSling - Whether the station is available via Sling
     * @param {boolean} stations[].indoorAntenna - Whether the station is available via indoor antenna
     * @param {boolean} stations[].outdoorAntenna - Whether the station is available via outdoor antenna
     *
     * @returns {boolean} Whether the stations contain CBS
     */
    function createResultsRows(stations) {
      var station, rowOutput, i;
      var hasCBS = false;
      for(i = 0; i < stations.length; i++) {
        station = stations[i];
        if(station.network.indexOf('CBS') > -1) {
          hasCBS = true;
        }

        rowOutput = '<tr class="ota-result-row"><td>' + station.network + '</td><td>' +
          station.channelCallSign + '</td><td class="ota-icon-cell">';

        if(station.inSling) {
          rowOutput += '<span class="ota-icon ota-icon-sling" style="background: url(' + icons.sling + ');"></span>';
        }
        if(station.indoorAntenna) {
          rowOutput += '<span class="ota-icon ota-icon-indoor" style="background: url(' + icons.indoor + ');"></span>';
          $("#ota-offers").show();
        }
        if(station.outdoorAntenna) {
          rowOutput += '<span class="ota-icon ota-icon-outdoor" style="background: url(' + icons.outdoor + ');"></span>';
          $("#ota-outdoor-info").show();
          $("#desktop-only-hrule").show();
        }

        rowOutput += '</td></tr>';

        $("#ota-results-list").append(rowOutput);
      }

      return hasCBS;
    }


    /**
     * Grab the icon URLs from the legend
     *
     * @returns {Object} icons - The icons
     * @returns {string} icons.sling - The sling icon
     * @returns {string} icons.indoor - The indoor antenna icon
     * @returns {string} icons.outdoor - The outdoor antenna icon
     */
    function parseIconURLs() {
      var slingStyle = $("#ota-results-icon-legend-icon-sling").attr("style");
      var indoorStyle = $("#ota-results-icon-legend-icon-indoor").attr("style");
      var outdoorStyle = $("#ota-results-icon-legend-icon-outdoor").attr("style");
      if(slingStyle.indexOf('"') != -1) {
        icons.sling = slingStyle.split('"')[1];
      }
      else {
        icons.sling = slingStyle.split("'")[1];
      }
      if(indoorStyle.indexOf('"') != -1) {
        icons.indoor = indoorStyle.split('"')[1];
      }
      else {
        icons.indoor = indoorStyle.split("'")[1];
      }
      if(outdoorStyle.indexOf('"') != -1) {
        icons.outdoor = outdoorStyle.split('"')[1];
      }
      else {
        icons.outdoor = outdoorStyle.split("'")[1];
      }
      return icons;
    }


    /**
     * Do AJAX call for Antenna Recommendation service
     *
     * @param {Object} location - The address && coordinates to check
     * @param {Object} location.address - The address information
     * @param {string} location.address1 - The street address
     * @param {string} location.zip - The zip code
     * @param {Object} location.coordinates - The geolocation information
     * @param {string} location.coordinates.latitude
     * @param {string} location.coordinates.longitude
     */
    function go(location) {
      $("#ota-search-results-header-address").html(location.address.address1 + ", " + location.address.zip);
      $.ajax({
        type: 'GET',
        url: antennaServiceUrl,
        contentType: 'application/json',
        dataType: 'json',
        data: {
          latitude: location.coordinates.latitude,
          longitude: location.coordinates.longitude,
          zipcode: location.address.zip
        },
        success: win,
        error: fail
      });
    }


    /**
     * Failure Handler for Antenna Recommendation Service
     *
     * @param {XMLHttpRequest} xhr
     */
    function fail(xhr) {
      console.log("Antenna Recommendation Error");
      console.log(xhr);

      $("#results-error-container").show();
      if(xhr.status == 400) {
        $("#results-400-error").show();
      }
      else {
        $("#results-other-errors").show();
      }
    }


    /**
     * Success handler for Antenna Recommendation service
     *
     * @param {Object[]} stations - The response from the service
     */
    function win(stations) {
      var cbsRow;
      var hasCBS = false;

      $("#ota-search-results").show();
      $("#ota-results-icon-legend").show();
      $("#ota-results-container").show();


      if(stations.length != 0) {
        hasCBS = createResultsRows(stations);

        if(!hasCBS) {
          if(stations.length < 5) {
            cbsRow = '<tr class="ota-result-row"><td>' + 'CBS' + '</td><td>' + '<div class="ota-all-access">Get this channel with <a href="https://www.cbs.com/all-access/" id="redirect" target="_blank">CBS All Access</a> — the perfect complement to Sling TV</div>' + '</td></tr>';
            $("#ota-results-list").append(cbsRow);
          }
          else {
            cbsRow = '<tr class="ota-result-row"><td>' + 'CBS' + '</td><td>' + '<div class="ota-all-access">Get this channel with <a href="https://www.cbs.com/all-access/" id="redirect" target="_blank">CBS All Access</a> — the perfect complement to Sling TV</div>' + '</td></tr>';
            $(cbsRow).insertBefore($("#ota-results-list").children().children().filter(':eq(5)'));
          }
        }

        $("#ota-results-stations-header").html((stations.length) + " Station(s)");

        results.less();
      }
      else {
        $("#results-error-container").show();
        $("#no-results").show();
        $("#ota-results-icon-legend").hide();
        $("#ota-results-container").hide();
      }
    }


    /**
     * Grab URL && icons from the HTML
     */
    function onReady() {
      if(!$('.ota').length) {
        /* Component does not exist */
        return;
      }
      antennaServiceUrl = $("#ota-antenna-titan-service-url").html();
      parseIconURLs();
    }

    $(document).ready(onReady);

    return go;
  })();


  var results = (function resultsConstructor() {

    /**
     * Show fewer results
     */
    function less() {
      var moreBtn = $("#ota-results-more-btn");
      var lessBtn = $("#ota-results-less-btn");
      var rowCount = 0;

      $(".ota-result-row").each(function () {
        rowCount += 1;
        if(rowCount > 5) {
          $(this).addClass('hide-result');
        }
      });

      lessBtn.hide();
      if(rowCount <= 5) {
        moreBtn.hide();
      }
      else {
        moreBtn.show();
      }
    }


    /**
     * Show more results
     */
    function more() {
      $(".ota-result-row").removeClass('hide-result');
      $("#ota-results-less-btn").show();
      $("#ota-results-more-btn").hide();
    }

    return {
      less: less,
      more: more
    };
  })();


  /**
   * Set up click handlers and such
   */
  function onReady() {
    if(!$('.ota').length) {
      /* Component does not exist */
      return;
    }
    addressInput = $("#ota-address-input");
    zipInput = $("#ota-zip-input");
    searchBtn = $("#ota-address-search-btn");

    reset();

    if(isAuthorMode()) {
      $("#ota-offers").show();
      $("#cta-ota").show();
    }

    addressInput.on("change keyup keydown paste", keyHandlers.address);
    zipInput.on("change keyup keydown paste", keyHandlers.zip);

    searchBtn.click(addressScrub);

    $("#ota-results-more-btn").click(results.more);
    $("#ota-results-less-btn").click(results.less);

    lazyLoad($('.js-ota'));
  }


  /**
   * Hide offers, etc. and remove any rows from the results table
   */
  function reset() {
    $("#ota-search-results").hide();
    $("#ota-offers").hide();
    $("#ota-outdoor-info").hide();
    $("#desktop-only-hrule").hide();


    $("#address-error-container").hide();
    $("#address-400-error").hide();
    $("#address-other-errors").hide();
    $("#no-results").hide();
    $("#results-error-container").hide();

    $(".ota-result-row").remove();
  }


  $(document).ready(onReady);

})();
// End of OTA js

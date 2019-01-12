(function () {

  var channelListData = {};
  var top, headerHeight;
  var previousScrollPosition = 0;
  var tabsPrepared = {};

  $(document).ready(function () {
    if (!$('.pc-grid-header').length) {
      // Do nothing if component doesn't exist
      return;
    }

    var classification = $('#classificationOne').val();
    var planId = $('#plan1').val();
    var packageId = $('#planOnePackageOne').val();

    $(document).scroll(slingUtils.debounce(function () {
      alignPcGridHeaders();
    }));

    callCommonFunction(classification, planId, packageId);

    if (slingUtils.resolution.isMobile()) {
      $('.js-pc-grid-package-row').remove();
      var packageRow = $('.package-grid-mobile-carousel');
    } else {
      $(".package-grid-mobile-carousel").remove();
      var packageRow = $('.js-dyn-grid-package-row');
    }


    if ($('#planOne').hasClass('active-tab')) {
      activePlan(this, 'planOne', $('#classificationOne').val(), $('#plan1').val(), $('#planOnePackageOne').val(), false);
    }
    if ($('#planTwo').hasClass('active-tab')) {
      activePlan(this, 'planTwo', $('#classificationTwo').val(), $('#plan2').val(), $('#planTwoPackageOne').val(), false);
    }
    if ($('#planThree').hasClass('active-tab')) {
      activePlan(this, 'planThree', $('#classificationThree').val(), $('#plan3').val(), $('#planThreePackageOne').val(), false);
    }
    $("#planOne").click(function (e) {
      activePlan(this, 'planOne', $('#classificationOne').val(), $('#plan1').val(), $('#planOnePackageOne').val(), true);
    });
    $("#planTwo").click(function (e) {
      activePlan(this, 'planTwo', $('#classificationTwo').val(), $('#plan2').val(), $('#planTwoPackageOne').val(), true);
    });
    $("#planThree").click(function (e) {
      activePlan(this, 'planThree', $('#classificationThree').val(), $('#plan3').val(), $('#planThreePackageOne').val(), true);
    });

    packageRow.on('click', '#pc-grid-package-tab-1', function () {
      pcPackageClickHandler(1);
      $(".package-grid-mobile-carousel").slick('slickGoTo', 0);
      showLinkToGridParse();
    });

    packageRow.on('click', '#pc-grid-package-tab-2', function () {
      pcPackageClickHandler(2);
      $(".package-grid-mobile-carousel").slick('slickGoTo', 1);
      showLinkToGridParse();
    });

    packageRow.on('click', '#pc-grid-package-tab-3', function () {
      pcPackageClickHandler(3);
      $(".package-grid-mobile-carousel").slick('slickGoTo', 2);
      showLinkToGridParse();
    });

    if (slingUtils.resolution.isMobile()) {
      initCarousel();
    }
    $(".package-grid-mobile-carousel").on("afterChange", function () {
      pcPackageClickHandler($('.package-grid-slide.slick-center').index() + 1);
    });
    showLinkToGridParse();
    $(window).on('load', function () {
      headerHeight = $('header').outerHeight();
    });

    $(window).on('resize', function () {
      top = $('.pc-grid-header').offset().top;
      headerHeight = $('header').outerHeight();
    });
  });

  function activePlan(self, plan, classification, planId, packageId, click) {
    $('#RowTwo').html("");
    $('#RowTwo').html(buildPackageTabs(plan));
    plan !== 'planOne' ? $('#planOne').removeClass('active-tab') : '';
    plan !== 'planTwo' ? $('#planTwo').removeClass('active-tab') : '';
    plan !== 'planThree' ? $('#planThree').removeClass('active-tab') : '';
    $(self).addClass("active-tab");
    click ? initCarousel() : '';
    callCommonFunction(classification, planId, packageId);
    click ? showLinkToGridParse() : '';
  }

  function alignPcGridHeaders() {
    var isStickyHeaderEnabled = false;
    var scrollUp = false, scrollDown = false;
    var currentScrollPosition = $(window).scrollTop();

    if (!slingUtils.resolution.isMobile()) {
      if ($('.dyn-grid').attr('data-sling-sticky-tab') == 'dyn-sticky-header') {
        isStickyHeaderEnabled = true;
      }
    } else {
      if ($('.dyn-grid').attr('data-sling-sticky-tab-mobile') == 'dyn-sticky-header-mobile') {
        isStickyHeaderEnabled = true;
      }
    }

    if (isStickyHeaderEnabled) {
      var searchHeight, sportHeight = 0,
        fakeHeaderHeight = 0,
        pastIt, magicPadding;
      var resultsContainer = $('.js-all-channels');
      var gridContainer = $('.pc-grid-header');
      var bottom = resultsContainer.offset().top + resultsContainer.outerHeight();
      var preHeaderHeight = $('.js-preheader').outerHeight();
      if (currentScrollPosition > previousScrollPosition) {
        scrollDown = true;
        scrollUp = false;
      } else if (currentScrollPosition < previousScrollPosition) {
        scrollUp = true;
        scrollDown = false;
      }
      previousScrollPosition = currentScrollPosition;

      if ($(document).scrollTop() > $('.pc-grid-header').offset().top || top === undefined) {
        top = $('.pc-grid-header').offset().top;
      }
      if (!headerHeight) {
        headerHeight = $('header').children().first().outerHeight();
      }
      if (scrollUp) {
        var inIt = $(document).scrollTop() > (top - (headerHeight));
      } else if (scrollDown) {
        var inIt = $(document).scrollTop() > (top - (headerHeight - preHeaderHeight));
      }
      if (inIt) {

        gridContainer.addClass('pc-grid--fixed');

        if (scrollDown) {
          gridContainer.css('top', headerHeight - preHeaderHeight);
        } else if (scrollUp) {
          gridContainer.css('top', headerHeight);
        }
        resultsContainer.css('margin-top', gridContainer.height() + 'px');
        searchHeight = gridContainer.outerHeight();

        pastIt = $(document).scrollTop() > (bottom - ((scrollDown ? (headerHeight - preHeaderHeight) : headerHeight) + fakeHeaderHeight + sportHeight + searchHeight));
        if (pastIt) {
          magicPadding = headerHeight + 1;
          gridContainer.css('top', -($(document).scrollTop() - (bottom - (headerHeight + fakeHeaderHeight + sportHeight + searchHeight - magicPadding))));

        }
      } else {
        resultsContainer.css('margin-top', 0);
        gridContainer.removeClass('pc-grid--fixed').css('top', 0);
      }
    }
  }

  function initCarousel() {
    if ($(".package-grid-mobile-carousel").hasClass('slick-initialized')) {
      $(".package-grid-mobile-carousel").removeClass('slick-initialized');
      $(".package-grid-mobile-carousel").removeClass('slick-slider');
    }
    if ($('.package-grid-slide').length >= 3) {
      $(".package-grid-mobile-carousel").slick({
        dots: false,
        infinite: false,
        centerMode: true,
        variableWidth: true,
        initialSlide: 1,
        accessibility: false,
        prevArrow: "<a href='#left-carousel-package-grid-chevron' class='package-grid-left-chevron fa fa-chevron-left pull-left water sg-large'></a>",
        nextArrow: "<a href='#right-carousel-package-grid-chevron' class='package-grid-right-chevron fa fa-chevron-right pull-rigt water sg-large'></a>"
      });
    }
    if (slingUtils.resolution.isMobile()) {
      pcPackageClickHandler(2);
    }
  }

  function showLinkToGridParse() {
    var currentPlan = null;
    var currentPackage = null;

    $('.dyn-grid_package-tab').each(function () {
      if ($(this).hasClass('active-tab')) {
        currentPlan = $(this).attr('id');
      }
    });

    if (slingUtils.resolution.isMobile()) {
      $('.package-grid-package').each(function () {
        if ($(this).hasClass('active-tab')) {
          currentPackage = $(this).attr('id');
        }
      });
    } else {
      $('.dyn-grid_plain-package-tab').each(function () {
        if ($(this).hasClass('active-tab')) {
          currentPackage = $(this).attr('id');
        }
      });
    }
    $( document ).trigger( "linkToGridParseEvent", [ currentPlan, currentPackage ] );
  }

  function callCommonFunction(classification, planId, packageId) {
    var key = classification + '-' + planId + '-' + packageId;
    if (channelListData.hasOwnProperty()) {
      setChannels(channelListData[key]);
    } else if (classification && planId && packageId) {
      $.ajax({
        url: '/bin/getDynamicChannels?classification=' + classification + '&planId=' + planId + '&packageId=' + packageId + '&channelLogoPath=' + $('#channelLogoPath').val(),
        type: 'GET',
        dataType: 'json',
        async: true,
        success: function (resp) {
          channelListData[key] = resp;
          setChannels(resp);
          document.dispatchEvent(new CustomEvent('PCGridLoaded'));
        },

        error: function (jqXHR, textStatus, errorThrown) {
          console.log('getDynamicChannels fetch failed');
        }
      });
    }
  }

  function setChannels(resp) {
    $("#channelList").html("");
    var newHtml = "";
    $.each(resp, function (i, item) {
      newHtml = newHtml + '<li class="all-channels-list-item all-channels--list-item">' +
        '<img class="all-channels-image all-channels--image active" alt="' + item.altText + '" title="' + item.altText + '" src="' + item.logo + '">' +
        '</li>';
    });
    $("#channelList").html(newHtml);
  }

  function pcPackageClickHandler(tabNum) {
    var tabOne = $('#pc-grid-package-tab-1');
    var tabTwo = $('#pc-grid-package-tab-2');
    var tabThree = $('#pc-grid-package-tab-3');
    if ($('#pc-grid-package-tab-' + tabNum).length && $('#pc-grid-package-tab-' + tabNum).length > 0) {
      tabOne.removeClass('active-tab');
      tabTwo.removeClass('active-tab');
      tabThree.removeClass('active-tab');
      $('#pc-grid-package-tab-' + tabNum).addClass('active-tab');
      var packageNumber = tabNum == 3 ? 'PackageThree' : (tabNum == 2 ? 'PackageTwo' : 'PackageOne');

      if ($('#planOne').hasClass('active-tab')) {
        var classification = $('#classificationOne').val();
        var planId = $('#plan1').val();
        var packageId = $('#planOne' + packageNumber).val();
        callCommonFunction(classification, planId, packageId)
      } else if ($('#planTwo').hasClass('active-tab')) {
        var classification = $('#classificationTwo').val();
        var planId = $('#plan2').val();
        var packageId = $('#planTwo' + packageNumber).val();
        callCommonFunction(classification, planId, packageId)
      } else if ($('#planThree').hasClass('active-tab')) {
        var classification = $('#classificationThree').val();
        var planId = $('#plan3').val();
        var packageId = $('#planThree' + packageNumber).val();
        callCommonFunction(classification, planId, packageId)
      }
    }
  }

  function buildPackageTabs(type) {
    var channelTitleLow = $('#' + type + 'PackageOneTitle').val();
    var channelTitleMid = $('#' + type + 'PackageTwoTitle').val();
    var channelTitleHigh = $('#' + type + 'PackageThreeTitle').val();
    var channelSubTitleLow = $('#' + type + 'PackageOneSubTitle').val();
    var channelSubTitleMid = $('#' + type + 'PackageTwoSubTitle').val();
    var channelSubTitleHigh = $('#' + type + 'PackageThreeSubTitle').val();
    var channelPriceLow = $('#' + type + 'PackageOnePrice').val();
    var channelPriceMid = $('#' + type + 'PackageTwoPrice').val();
    var channelPriceHigh = $('#' + type + 'PackageThreePrice').val();
    var carouselStartElement = '';
    var carouselEndElement = '';
    var carouselClass = !slingUtils.resolution.isMobile() ? 'dyn-grid_plain-package-tab' : 'package-grid-package';

    if (slingUtils.resolution.isMobile()) {
      carouselStartElement = "<div class='package-grid-slide'>";
      carouselEndElement = "</div>";
    }

    if (channelTitleLow != '' && channelTitleMid == '' && channelTitleHigh == '') {
      if(!tabsPrepared.hasOwnProperty(type)) {
        tabsPrepared[type] = carouselStartElement + "<a href='#dyn-grid-package-tab-1-25" + type + "' class='" + carouselClass + " pull-left active-tab' id='pc-grid-package-tab-1'>" +
        "<h3 class='dyn-grid_plain-package-single-title'>" + channelTitleLow + "</h3>" +
        "<h6 class='dyn-grid_plain-package-single-subtitle'>" + channelSubTitleLow + "</h6>" +
        "<p class='dyn-grid_plain-package-price' value='" + channelPriceLow + "'></p></a>" + carouselEndElement;
      }
    } else if (channelTitleLow != '' && channelTitleMid != '' && channelTitleHigh == '') {
      if(!tabsPrepared.hasOwnProperty(type)) {
        tabsPrepared[type] = carouselStartElement + "<a href='#dyn-grid-package-tab-1-25" + type + "' class='" + carouselClass + " pull-left active-tab' id='pc-grid-package-tab-1'>" +
        "<h3 class='dyn-grid_plain-package-title" + (channelSubTitleLow.length > 0 ? "" : " active-single") + "' data-sling-prefix='$'>" + channelTitleLow + "</h3>" +
        "<h6 class='dyn-grid_plain-package-subtitle'>" + channelSubTitleLow + "</h6>" +
        "<p class='dyn-grid_plain-package-price' id='25Price' value='" + channelPriceLow + "'></p>" +
        "</a>" + carouselEndElement + carouselStartElement +
        "<a href='#dyn-grid-package-tab-2-30" + type + "' class='" + carouselClass + "' id='pc-grid-package-tab-2'>" +
        "<h3 class='dyn-grid_plain-package-title" + (channelSubTitleMid.length > 0 ? "" : " active-single") + "'>" + channelTitleMid + "</h3>" +
        "<h6 class='dyn-grid_plain-package-subtitle'>" + channelSubTitleMid + "</h6>" +
        "<p class='dyn-grid_plain-package-price' value='" + channelPriceMid + "'></p>" +
        "</a>" + carouselEndElement;
      }
    } else if (channelTitleLow != '' && channelTitleMid != '' && channelTitleHigh != '') {
      if(!tabsPrepared.hasOwnProperty(type)) {
        tabsPrepared[type] = carouselStartElement + "<a href='#dyn-grid-package-tab-1-25" + type + "' class='" + carouselClass + " active-tab' id='pc-grid-package-tab-1'>" +
        "<h3 class='dyn-grid_plain-package-title" + (channelSubTitleLow.length > 0 ? "" : " active-single") + "'>" + channelTitleLow + "</h3>" +
        "<h6 class='dyn-grid_plain-package-subtitle'>" + channelSubTitleLow + "</h6>" +
        "<p class='dyn-grid_plain-package-price' value='" + channelPriceLow + "'></p>" +
        "</a>" + carouselEndElement + carouselStartElement +
        "<a href='#dyn-grid-package-tab-2-30" + type + "' class='" + carouselClass + "' id='pc-grid-package-tab-2'>" +
        "<h3 class='dyn-grid_plain-package-title" + (channelSubTitleMid.length > 0 ? "" : " active-single") + "'>" + channelTitleMid + "</h3>" +
        "<h6 class='dyn-grid_plain-package-subtitle'>" + channelSubTitleMid + "</h6>" +
        "<p class='dyn-grid_plain-package-price' value='" + channelPriceMid + "'></p>" +
        "</a>" + carouselEndElement + carouselStartElement +
        "<a href='#dyn-grid-package-tab-3-60" + type + "' class='" + carouselClass + "' id='pc-grid-package-tab-3'>" +
        "<h3 class='dyn-grid_plain-package-title" + (channelSubTitleHigh.length > 0 ? "" : " active-single") + "'>" + channelTitleHigh + "</h3>" +
        "<h6 class='dyn-grid_plain-package-subtitle'>" + channelSubTitleHigh + "</h6>" +
        "<p class='dyn-grid_plain-package-price' value='" + channelPriceHigh + "'></p>" +
        "</a>" + carouselEndElement;
      }
    }
    return tabsPrepared[type] ? tabsPrepared[type] : '?';
  }
})();

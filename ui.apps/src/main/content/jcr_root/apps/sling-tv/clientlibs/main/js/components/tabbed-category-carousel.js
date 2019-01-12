/* Tabbed Category Carousel */

(function () {

  var lastWindowWidth, margin, tccTabHeight = true;


  /**
   * Cycle the channel carousel left or right
   *
   * @param {jQuery} channelCarousel
   * @param {boolean} fromLeft
   */
  function changeChannels(channelCarousel, fromLeft) {
    var next;
    var current = channelCarousel.find('.js-tcc-channel-group-active');

    if(fromLeft) {
      next = current.prev();
      if(!next.length) {
        next = channelCarousel.find('.js-tcc-channel-group').last();
      }
    }
    else {
      next = current.next();
      if(!next.length) {
        next = channelCarousel.find('.js-tcc-channel-group').first();
      }
    }

    transitionChannel(channelCarousel, next.index(), fromLeft);
  }


  /**
   * Switch the tab content
   *
   * @param {jQuery} target
   */
  function changeTab(target) {
    var first, last, firstLeft, nextLeft, lastLeft;
    var contentClass = target.data('slingFor');

    var center = $(window).outerWidth() / 2;
    var parent = target.closest('.js-tcc-tab-list');
    var tcc = parent.closest('.js-tcc-outer');

    parent.addClass('no-transition');
    parent[0].offsetHeight; //Flush CSS to finish active transitions
    parent.removeClass('no-transition');

    var currentPosition = parent.attr('style') ? +parent.attr('style').slice(22, -3) : 0;
    var offset = center - (parent.offset().left + target.width() - currentPosition);
    var xPosition = offset - (+target.css('left').slice(0, -2));

    //Must activate toggle before transitioning content, or height measurements are wrong...
    changeToggle(tcc.find('.' + contentClass).find('.js-tcc-toggle').first());

    if(currentPosition < xPosition) {
      //From Left
      first = parent.children().first();
      firstLeft = +first.css('left').slice(0, -2);
      while(firstLeft > -xPosition - center) {
        last = parent.children().last();
        nextLeft = firstLeft - (last.outerWidth() + margin);

        last.css('left', nextLeft);
        parent.prepend(last);

        first = parent.children().first();
        firstLeft = +first.css('left').slice(0, -2);
      }
      transitionContent(tcc, contentClass, true);
    }
    else {
      //From Right
      last = parent.children().last();
      lastLeft = +last.css('left').slice(0, -2);
      while(lastLeft < -xPosition + center * 2) {
        first = parent.children().first();
        nextLeft = lastLeft + last.outerWidth() + margin;

        first.css('left', nextLeft);
        parent.append(first);

        last = parent.children().last();
        lastLeft = +last.css('left').slice(0, -2);
      }
      transitionContent(tcc, contentClass, false);
    }

    parent.attr('style', 'transform: translateX(' + xPosition + 'px)');

    parent.find('.js-tcc-tab').removeClass('carousel-jam_tab-list-item--selected').removeClass('js-tcc-tab-active');
    target.addClass('carousel-jam_tab-list-item--selected').addClass('js-tcc-tab-active');

    fadeTabText(tcc, currentPosition - xPosition);
  }


  /**
   * Click event to switch the active tab
   *
   * @param {Event} e
   */
  function clickTab(e) {
    var target = $(e.currentTarget);
    changeTab(target);
  }


  /**
   * Switch the active toggle
   *
   * @param {jQuery} target
   */
  function changeToggle(target) {
    var targetClass = target.data('slingFor');
    var parent = target.closest('.js-tcc-tab-content');
    var content = parent.find('.' + targetClass);
    console.log(targetClass);

    parent.find('.js-tcc-toggle-content').hide();
    content.show();

    parent.find('.js-tcc-toggle').removeClass('carousel-jam_toggle--active');
    parent.find('[data-sling-for="' + targetClass + '"]').addClass('carousel-jam_toggle--active');

    var channels = content.find('.js-tcc-channel-group-active');

    channels.width(channels.parent().width());
    if(channels.parent().height() < channels.height()) {
      channels.parent().height(channels.height());
    }
  }


  /**
   * Click event to switch the active toggle
   *
   * @param {Event} e
   */
  function clickToggle(e) {
    var target = $(e.currentTarget);
    changeToggle(target);
  }


  /**
   * Switch the active tab using the Chevron
   *
   * @param {Event} e
   */
  function chevronChannel(e) {
    var target = $(e.currentTarget);
    var channelCarousel = target.closest('.js-tcc-channel-carousel');

    if(target.hasClass('fa-chevron-right')) {
      changeChannels(channelCarousel, false);
    }
    else {
      changeChannels(channelCarousel, true);
    }
  }


  /**
   * Switch the active tab using the Chevron
   *
   * @param {Event} e
   */
  function chevronTab(e) {
    var target = $(e.currentTarget);
    var current = target.closest('.js-tcc-outer').find('.js-tcc-tab-active');

    if(target.hasClass('fa-chevron-right')) {
      changeTab(current.next());
    }
    else {
      changeTab(current.prev());
    }
  }


  /**
   * Set opacity for the tabs
   *
   * @param {jQuery} tcc - Parent
   * @param {int} delta - How far are we moving from the current position
   */
  function fadeTabText(tcc, delta) {
    var tabs = tcc.find('.js-tcc-tab');
    var center = $(window).outerWidth() / 2;
    var halfWidth = Math.abs(center - tcc.find('.js-tcc-chevron').first().offset().left);
    tabs.find('span').css('opacity', 1);
    tabs.find('span').each(function () {
      $(this).css('opacity', 1 - Math.min(1, (Math.abs(center - ($(this).offset().left - delta)) / halfWidth)));
    });
  }


  /**
   * Switch the channel carousel using the indicators
   *
   * @param {Event} e
   */
  function indicatorChannel(e) {
    var target = $(e.currentTarget);

    if(target.hasClass('active')) {
      return;
    }

    var channelCarousel = target.closest('.js-tcc-channel-carousel');
    var current = channelCarousel.find('.js-tcc-channel-group-active');

    transitionChannel(channelCarousel, target.index(), current.index() > target.index());
  }


  /**
   * Set up click events and such
   */
  function onReady() {
    var tcc = $('.js-tcc-outer');
    if(!tcc.length) {
      /* If component doesn't exist, do nothing */
      return;
    }

    tcc.each(function() {
      var thisTcc = $(this);

      var selected = prepareTabs(thisTcc);

      thisTcc.find('.js-tcc-chevron').click(chevronTab);

      thisTcc.find('.js-tcc-tab-content').each(function() {
        var toggles = $(this).find('.js-tcc-toggle');
        var tccTab = $(this);
        toggles.each(function() {
          $(this).attr('onClick', "s_objectID='" + tccTab.data('slingTabName').toLowerCase() + "-" +
            $(this).data('slingFor')+"';");
        });
        if(toggles.length == 1) {
          toggles.closest('.js-tcc-toggle-group').remove()
        }
        else {
          toggles.click(clickToggle);
        }
      });

      prepareChannelCarousels(thisTcc);
      prepareSwipe(thisTcc);

      if(!isAuthorMode()) {
        var resizeEvent;
        $(window).on('resize', function () {
          clearTimeout(resizeEvent);
          resizeEvent = setTimeout(function () {
            onResize(thisTcc, selected);
          }, 250);
        });
      }

      lazyLoad(thisTcc, 15, true, function(){repaint(thisTcc, selected)});
      lazyLoad(thisTcc, 25, false);

      changeTab(selected);
    });
  }


  /**
   * Clean up & reset things on resize
   *
   * @param {jQuery} tcc - The component
   * @param {jQuery} defaultTab - The tab that was initially selected (used to transition)
   */
  function onResize(tcc, defaultTab) {
    if($(window).width() == lastWindowWidth) {
      return;
    }
    lastWindowWidth = $(window).width();
    repaint(tcc, defaultTab)
  }


  /**
   * Set up the Channel Carousels
   *
   * @param {jQuery} tcc - The component
   */
  function prepareChannelCarousels(tcc) {
    var channelCarousels = tcc.find('.js-tcc-channel-carousel');
    channelCarousels.each(function () {
      var channelCarousel = $(this);
      var chevrons = channelCarousel.find('.js-tcc-channel-chevron');
      var channels = channelCarousel.find('.js-tcc-channel-logo');
      var group = channelCarousel.find('.js-tcc-channel-group');
      var indicators = channelCarousel.find('.js-tcc-carousel-indicators-wrapper');
      var dot = indicators.find('.js-tcc-carousel-indicator');

      if(channels.length < 9) {
        chevrons.hide();
        group.css('position', 'relative');
        indicators.remove();
        return;
      }

      chevrons.click(chevronChannel);

      while(group.children().length > 8) {
        var clone = group.clone(true);
        clone.empty();
        while(clone.children().length < 8 && group.children().length > 8) {
          clone.append(group.children()[8]);
        }
        clone.hide();
        group.parent().append(clone);
        indicators.find('.js-tcc-carousel-indicators').append(dot.clone());
      }

      group.addClass('js-tcc-channel-group-active');
      dot.addClass('active');

      var dots = indicators.find('.js-tcc-carousel-indicator');
      dots.click(indicatorChannel);
      prepareChannelTouch(channelCarousel);
    });
  }


  /**
   * Sets up swipe events for the channel carousel
   *
   * @param {jQuery} channelCarousel
   */
  function prepareChannelTouch(channelCarousel) {
    var callbacks = {
      right: function() {
        var chevron = channelCarousel.find('.js-tcc-channel-chevron.fa-chevron-right');
        chevron.click();
      },
      left: function() {
        var chevron = channelCarousel.find('.js-tcc-channel-chevron.fa-chevron-left');
        chevron.click();
      }
    };
    onSwipe(channelCarousel, callbacks);
  }


  /**
   * Set up swipe events for outer carousel
   *
   * @param {jQuery} tcc
   */
  function prepareSwipe(tcc) {
    var callbacks = {
      right: function() {
        var chevron = tcc.find('.js-tcc-chevron.fa-chevron-right');
        chevron.click();
      },
      left: function() {
        var chevron = tcc.find('.js-tcc-chevron.fa-chevron-left');
        chevron.click();
      }
    };
    onSwipe(tcc, callbacks);
  }


  /**
   * Associate a tab w/ it's content
   *
   * @param {jQuery} content
   * @param {jQuery} originalTab
   */
  function prepareTab(content, originalTab) {
    var tabName = content.data('slingTabName');
    var sanitizedName = tabName.replace(/[^\x00-\x7F]/g, "").split(' ').join('-').toLowerCase();
    var hookClass = 'js-tcc-tab-' + sanitizedName;
    content.addClass(hookClass);
    content.addClass('no-transition');
    content.removeClass('hidden-on-load').css('left', $(window).width()).width(content.parent().width());
    content[0].offsetHeight; // Trigger a reflow, flushing the CSS changes
    content.removeClass('no-transition');

    var myItem = originalTab.clone();
    myItem.attr('data-sling-for', hookClass);
    myItem.text(tabName.trim());
    myItem.click(clickTab);

    return myItem;
  }


  /**
   * Set up the Tabs
   *
   * @param {jQuery} tcc - The component
   */
  function prepareTabs(tcc) {
    var tabList = tcc.find('.js-tcc-tab-list');
    var tabListItem = tabList.find('.js-tcc-tab');
    tabListItem.remove();
    var tabs = tcc.find('.js-tcc-tab-content');
    tabs.each(function () {
      tabList.append(prepareTab($(this), tabListItem));
    });

    var selected = tabList.find('.js-tcc-tab').first();

    var width = 0;
    var originalChildren = tabList.children();
    if(!originalChildren.length) {
      throw new Error('No tabs!?');
    }
    originalChildren.each(function() {
      var elem = $(this);
      var text = elem.text().split('');
      elem.attr('href', '#'+elem.text().replace(' ', '').toLowerCase()+'-carousel-jam_tab-list-item');
      elem.empty();
      $.each(text, function(k, v) {
        elem.append($('<span style="transition: opacity 1.5s ease 0.25s;">' + v + '</span>'));
      });
    });
    originalChildren.each(function () {
      width += $(this).outerWidth();
    });
    while(width < $(window).outerWidth() * 4) {
      tabList.append(originalChildren.clone(true));
      width = 0;
      tabList.children().each(function () {
        width += $(this).outerWidth()
      });
    }

    margin = Math.max($(window).width() / 25, 15);
    positionTabs(tabList);
    return selected;
  }


  /**
   * Set left property of all tabs, starting at 0
   *
   * @param tabList
   */
  function positionTabs(tabList) {
    var width = 0;
    tabList.children().each(function () {
      var elem = $(this);
      elem.css('left', width);
      width += elem.outerWidth() + margin;
    });
  }


  /**
   * Clean up & reset things
   *
   * @param {jQuery} tcc - The component
   * @param {jQuery} defaultTab - The tab that was initially selected (used to transition)
   */
  function repaint(tcc, defaultTab) {
    margin = Math.max($(window).width() / 25, 15);
    var tabContent = tcc.find('.js-tcc-tab-content');
    tabContent.each(function () {
      var elem = $(this);
      elem.addClass('no-transition');
      elem.removeClass('hidden-on-load').css('left', $(window).width()).width(elem.parent().width());
      elem[0].offsetHeight; // Trigger a reflow, flushing the CSS changes
      elem.removeClass('no-transition');
      elem.find('.js-tcc-channel-group').parent().css('height', '');
    });
    tabContent.parent().height(0);
    var tabs = tcc.find('.js-tcc-tab-list');
    tabs.addClass('no-transition');
    tabs[0].offsetHeight; //Flush CSS
    positionTabs(tabs);
    var current = tcc.find('.js-tcc-tab-active');
    if(defaultTab.text() != current.text()) {
      var target = tcc.find('.' + defaultTab.data('slingFor'));
      target.addClass('no-transition');
      target[0].offsetHeight; //Flush CSS
      changeTab(defaultTab);
    }
    else {
      var target = tcc.find('.' + defaultTab.next().data('slingFor'));
      target.addClass('no-transition');
      target[0].offsetHeight; //Flush CSS
      changeTab(defaultTab.next());
    }
    setTimeout(function () {
      var target = tcc.find('.' + current.data('slingFor'));
      target.addClass('no-transition');
      target.parent().height(target.height());
      target.offsetHeight; //Flush CSS
      changeTab(current);
      tabs.removeClass('no-transition');
    }, 1000);
  }


  /**
   * Prepare & Execute transition animation for channel carousel
   *
   * @param {jQuery} channelCarousel - The channel carousel to switch
   * @param {int} targetIndex - Which group to switch to
   * @param {boolean} fromLeft - Come in from the left (from the right if false)
   */
  function transitionChannel(channelCarousel, targetIndex, fromLeft) {
    var current = channelCarousel.find('.js-tcc-channel-group-active');
    var next = $(channelCarousel.find('.js-tcc-channel-group').get(targetIndex));

    current.removeClass('js-tcc-channel-group-active');
    next.addClass('js-tcc-channel-group-active');

    next.width(next.parent().width());

    var indicators = channelCarousel.find('.js-tcc-carousel-indicator');
    indicators.removeClass('active');

    $(indicators.get(next.index())).addClass('active');

    //First positions
    if(next.parent().height() < current.height()) {
      next.parent().height(next.height());
    }
    current.addClass('no-transition');
    next.addClass('no-transition').show().css('left', (next.width() + margin) * (fromLeft ? -1 : 1));
    current[0].offsetHeight; // Trigger a reflow, flushing the CSS changes
    current.removeClass('no-transition');
    next.removeClass('no-transition');
    //Transition
    current.css('left', (next.width() + margin) * (fromLeft ? 1 : -1));
    next.css('left', 0);
  }


  /**
   * Prepare & Execute transition animation for tab content
   *
   * @param {jQuery} tcc - Parent
   * @param {string} incomingClassName - The class name of the tab content to switch to
   * @param {boolean} fromLeft - Come in from the left (from the right if false)
   */
  function transitionContent(tcc, incomingClassName, fromLeft) {
    var incoming = tcc.find('.' + incomingClassName);
    var current = tcc.find('.js-tcc-tab-content-active');

    incoming.width(incoming.parent().width());
    
    if(!current.hasClass(incomingClassName)) {
      //First positions
      setTimeout(function () {
        incoming.parent().height(incoming.height() + 30);
      }, 200);
      
      current.addClass('no-transition');
      incoming.addClass('no-transition').css('left', (incoming.width() + margin) * (fromLeft ? -1 : 1));
      incoming[0].offsetHeight; // Trigger a reflow, flushing the CSS changes
      current.removeClass('no-transition');
      incoming.removeClass('no-transition');
      //Transition
      current.css('left', (incoming.width() + margin) * (fromLeft ? 1 : -1));
      incoming.css('left', 0);
      current.removeClass('js-tcc-tab-content-active');
      incoming.addClass('js-tcc-tab-content-active');
      incoming.find('.js-tcc-channel-group').width(incoming.find('.js-tcc-channel-group').parent().width());
      incoming.find('.js-tcc-channel-group').parent().height(incoming.find('.js-tcc-channel-group').height());
    }
  }

  $(document).ready(onReady);

})();

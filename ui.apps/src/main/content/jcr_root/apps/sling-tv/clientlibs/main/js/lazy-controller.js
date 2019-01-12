/* Lazy Load Controller */

var lazyLoad = (function lazyLoadConstructor() {
  var componentQueue,
    imageQQ,
    lastQueue,
    lazyTimer;


  /**
   * Poll status of images, scheduling more if necessary
   */
  function checking() {
    clearTimeout(lazyTimer);
    lazyTimer = false;

    var imageCount = countCurrentDownloads();

    if (imageCount < 6 && componentQueue.length) {
      startNextComponent();
    }

    if (imageQQ.length) {
      lazyTimer = setTimeout(checking, 10);
    } else {
      executeLastQueue();
    }
  }


  /**
   * Count unfinished image downloads
   *
   * @returns {number} - The # of images current unfinished
   */
  function countCurrentDownloads() {
    var imageCount = 0;

    if (imageQQ) {
      var newQQ = [];
      $.each(imageQQ, function () {
        var imageQueue = this;
        var newQueue = [];
        $.each(imageQueue.elements, function () {
          var image = this;
          if (image.tagName.toLowerCase() == 'img' && !image.complete) {
            newQueue.push(image);
            imageCount++;
          }
        });
        if (newQueue.length) {
          imageQueue.elements = newQueue;
          newQQ.push(imageQueue);
        } else {
          if (typeof imageQueue.callback == 'function') {
            imageQueue.callback();
          }
        }
      });
      imageQQ = newQQ;
    } else {
      imageQQ = []
    }

    return imageCount;
  }


  /**
   * Execute callbacks in last queue in order
   */
  function executeLastQueue() {
    while (lastQueue.length) {
      var call = lastQueue.shift();
      if (typeof call.callback === 'function') {
        call.callback();
      }
    }
  }


  /**
   * Schedule a function after all components are lazy loaded
   *
   * @param {function} callback
   * @param {int} [priority=99]
   */
  function loadLast(callback, priority) {
    if (!lastQueue) {
      lastQueue = [];
    }
    if (!priority) {
      priority = 99;
    }
    var inserted = false;
    var data = {
      callback: callback,
      priority: priority
    };
    for (var i = 0; i < lastQueue.length; i++) {
      if (lastQueue[i].priority >= priority) {
        lastQueue.splice(i, 0, data);
        inserted = true;
        break;
      }
    }
    if (!inserted) {
      lastQueue.push(data);
    }
  }


  /**
   * Queues a component for lazy loading
   *
   * @param {jQuery} component
   * @param {int} [priority=99]
   * @param {boolean} [visibleOnly=false]
   * @param {function} [callback]
   */
  function queueComponent(component, priority, visibleOnly, callback) {
    var toBeLoaded = component.find('[data-sling-src]');
    if (!toBeLoaded.length) {
      if (typeof callback == 'function') {
        callback();
      }
      return;
    }

    if (!priority) {
      priority = 99;
    }

    if (!componentQueue) {
      componentQueue = [];
    }
    component.each(function () {
      var inserted = false;
      var top = component.offset().top;
      var elements = component.find('[data-sling-src]');
      if (visibleOnly) {
        elements = elements.not(elements.not(':visible'));
      }
      var elementsData = {
        elements: elements,
        top: top,
        callback: callback,
        priority: priority
      };
      for (var i = 0; i < componentQueue.length; i++) {
        if (componentQueue[i].priority > priority || (componentQueue[i].priority == priority && componentQueue[i].top >= top)) {
          componentQueue.splice(i, 0, elementsData);
          inserted = true;
          break;
        }
      }
      if (!inserted) {
        componentQueue.push(elementsData);
      }
    });

    if (!lazyTimer) {
      lazyTimer = setTimeout(checking, 100);
    }
  }


  /**
   * Start the downloads for the next component in the queue
   */
  function startNextComponent() {
    var next = componentQueue.shift();
    imageQQ.push({
      elements: next.elements,
      callback: next.callback
    });
    var resolutionsList = [2048, 1280, 1024, 800, 700, 500, 319, 140, 48];
    
    next.elements.each(function () {
      var elem = $(this);
      var imageRenderedWidth = elem.width();
      var dataSrc = elem.attr('data-sling-src');
      if (dataSrc) {
        if (elem.prop('tagName') == 'svg') {
          elem.find('image').attr('xlink:href', dataSrc);
        } else if (elem.prop('outerHTML').toLowerCase().indexOf('.svg') >= 0 ||
          elem.prop('outerHTML').toLowerCase().indexOf('.mp4') >= 0 ||
          elem.is(':hidden')) {
          elem.attr('src', dataSrc);
        } else if(elem.parent().parent().hasClass('carousel-inner') && !elem.hasClass('image-carousel--image')) {
          elem.attr('src', dataSrc);
        } else {
          var renditionApplied = false;
          for (var i = 0; i < resolutionsList.length; i++) {
            if(imageRenderedWidth <= resolutionsList[i] && imageRenderedWidth >= resolutionsList[i+1]) {
              checkImageExist(elem, dataSrc, dataSrc + '/jcr:content/renditions/cq5dam.thumbnail.'+resolutionsList[i]+'.'+resolutionsList[i]+'.png');
              renditionApplied = true;
            }
          }
          if(!renditionApplied) {
            elem.attr('src', dataSrc);
          }
        }
        elem.removeAttr('data-sling-src');
      }
    });
  }


  function checkImageExist(elem, dataSrc, dataSrcSpecific) {
    $.get(dataSrcSpecific).done(function() {
      elem.attr('src', dataSrcSpecific);
    }).fail(function() {
      elem.attr('src', dataSrc);
    });
  }




  queueComponent.last = loadLast;

  return queueComponent;

})();

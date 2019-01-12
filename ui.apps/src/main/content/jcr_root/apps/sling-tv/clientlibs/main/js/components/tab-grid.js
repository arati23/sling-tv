$(document).ready(function () {
  var tiles = $('.tgc-tile');
  tiles.on('click', function () {
    var elem = $(this);
    var breakout = elem.find('.tgc-tile-breakout');
    if (breakout.length) {
      $('.tgc-tile-selected').removeClass('tgc-tile-selected').css('height', '').find('.tgc-tile-breakout').hide();
      elem.addClass('tgc-tile-selected');

      breakout.show();
      var selectedTile = $('.tgc-tile-selected');
      var padding = (+selectedTile.css('padding-bottom').substr(0, 2));
      selectedTile.height(
        selectedTile.find('.tgc-tile-breakout').outerHeight() + selectedTile.height() - (padding ? padding : 0)
      )
    }
  });


  $('.tgc-close').on('click', function (e) {
    $('.tgc-tile-selected').removeClass('tgc-tile-selected').css('height', '').find('.tgc-tile-breakout').hide();
    e.stopImmediatePropagation();
  });


  if (!isAuthorMode()) {
    $('.tgc-tab-selected').trigger('click');
  }

  lazyLoad(tiles, 99, true);
  lazyLoad(tiles, 100);
});

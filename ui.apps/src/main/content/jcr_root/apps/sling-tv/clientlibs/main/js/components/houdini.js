/* Houdini component */

(function () {

  function onHoudiniLoad() {
    if (isAuthorMode()) {
      return;
    }

    
    $('.js-houdini-parent').each(function () {
      var parent = $(this);
      var childrenList = parent.find('.js-houdini-container').children('.aem-Grid').children();
      if (childrenList.length > 1) {
        childrenList.not(':first').hide();
        parent.find(".js-houdini-see-all").show();
      }
    });

    
    $('.js-houdini-see-all').click(function () {
      var button = $(this);
      var parent = button.closest('.js-houdini-parent');
      parent.find('.js-houdini-container').children('.aem-Grid').children().show();
      button.hide();
      parent.find('.js-houdini-see-less').show();
    });

    
    $('.js-houdini-see-less').click(function () {
      var button = $(this);
      var parent = button.closest('.js-houdini-parent');
      var childrenList = parent.find('.js-houdini-container').children('.aem-Grid').children();
      if (childrenList.length > 1) {
        childrenList.not(':first').hide();
        button.hide();
        parent.find('.js-houdini-see-all').show();
      }
    });
  }


  $(document).ready(onHoudiniLoad);
})();

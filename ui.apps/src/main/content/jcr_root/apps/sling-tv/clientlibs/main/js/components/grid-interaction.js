(function () {
    $( document ).on( "linkToGridParseEvent", function( event, currentPlan, currentPackage ) {
        linkToGridParseListener(event, currentPlan, currentPackage);
    });
    function linkToGridParseListener(event, currentPlan, currentPackage) {
        $('.js-grid-interaction').each(function () {
            var childId = $(this).children('div').attr('id');
            $(this).find('.js-grid-interaction-name').hide();

            if (currentPlan !== null && currentPackage !== null) {
                if (childId === currentPlan + '--' + currentPackage) {
                    $(this).show();
                    if (isAuthorMode() || ($.cookie('wcmmode') && $.cookie('wcmmode') == "preview")) {
                        $(this).css('border', '2px solid #fabd55');
                        $(this).find('.js-grid-interaction-name').show();
                    }
                } else {
                    $(this).hide();
                }
            }
        });
    }
})();
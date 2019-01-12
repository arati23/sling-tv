/* Offers & Deals */

(function () {
    $(document).ready(function () {
        lazyLoad($('.js-deals'));
        if (slingUtils.resolution.isMobile()) {
            initCarousel();
        }
    });

    function initCarousel() {
        $('.offers-slick').slick({
            dots: true,
            infinite: false,
            accessibility: false,
            prevArrow: "<a href='#' class='offers-arrow-left fa fa-chevron-left pull-left water sg-large'></a>",
            nextArrow: "<a href='#' class='offers-arrow-right fa fa-chevron-right pull-rigt water sg-large'></a>"
        });
    }
})();

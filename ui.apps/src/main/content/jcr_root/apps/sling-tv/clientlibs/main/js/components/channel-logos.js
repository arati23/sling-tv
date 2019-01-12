// Channel Logos component

(function () {
    $('.js-logos-container').each(function (index) {
        var currentComponent = $(this);
        var imageCount = 0;
        var slide = $('<div class="logos--carousel-slide js-logo-slide-' + index + '"></div>');
        $(currentComponent).attr('data-slide-index', 1);
        $(currentComponent).attr('data-slide-current-index', index);
        var numberOfImages = 3;
        var imageCountDevice = $(currentComponent).children().length;
        if (imageCountDevice <= numberOfImages) {
            $(currentComponent).css('display', 'flex');
            $(currentComponent).siblings('.js-logos-slideshow-container').hide();
            $(currentComponent).siblings('.js-logos-previous-carousel').hide();
            $(currentComponent).siblings('.js-logos-next-carousel').hide();
        } else {
            $(currentComponent).children().each(function () {
                if (imageCount < numberOfImages) {
                    slide.append($(this));
                    imageCount++;
                    imageCountDevice--;
                    createSlide(slide, imageCountDevice, currentComponent, index);
                } else if (imageCount == numberOfImages) {
                    $(currentComponent).siblings('.js-logos-slideshow-container').append(slide);
                    $(currentComponent).parent().siblings('.js-logos-dots-container').append($('<span class="dot logos--carousel-dot js-logos-carousel-dot js-logo-dot-' + index + '"></span>'));
                    prepareSwipe(slide);
                    slide = $('<div class="logos--carousel-slide js-logo-slide-' + index + '"></div>');
                    slide.append($(this));
                    imageCountDevice--;
                    imageCount = 1;
                    createSlide(slide, imageCountDevice, currentComponent, index);
                }
            });
            showSlides($(currentComponent).attr('data-slide-index'), $(currentComponent));
        }
    });

    $('.js-logos-container').each(function() {
        lazyLoad($(this));
    });
    $('.js-logos-slideshow-container').each(function() {
        lazyLoad($(this));
    });

    function createSlide(slide, imageCountDevice, currentComponent, index) {
        if (imageCountDevice == 0) {
            $(currentComponent).siblings('.js-logos-slideshow-container').append(slide);
            $(currentComponent).parent().siblings('.js-logos-dots-container').append($('<span class="dot logos--carousel-dot js-logos-carousel-dot js-logo-dot-' + index + '"></span>'));
            prepareSwipe(slide);
            slide = $('<div class="device-carousel-slides js-logo-slide-' + index + '"></div>');
        } else {
            return false;
        }
    }

    function prepareSwipe(cardLogoSlide) {
        var callbacks = {
            right: function () {
                var chevron = cardLogoSlide.parent().parent().find('.js-logos-previous-carousel');
                chevron.click();
            },
            left: function () {
                var chevron = cardLogoSlide.parent().parent().find('.js-logos-next-carousel');
                chevron.click();
            }
        };
        onSwipe(cardLogoSlide, callbacks);
    }

    $('.js-logos-previous-carousel').on('click', function () {
        var indexSlide = $(this).siblings('.js-logos-container').attr('data-slide-index');
        indexSlide = parseInt(indexSlide) + 1;
        $(this).siblings('.js-logos-container').attr('data-slide-index', indexSlide);
        showSlides(indexSlide, $(this).siblings('.js-logos-container'));
    });

    $('.js-logos-next-carousel').on('click', function () {
        var indexSlide = $(this).siblings('.js-logos-container').attr('data-slide-index');
        indexSlide = parseInt(indexSlide) - 1;
        $(this).siblings('.js-logos-container').attr('data-slide-index', indexSlide);
        showSlides(indexSlide, $(this).siblings('.js-logos-container'));
    });

    $('.js-logos-carousel-dot').on('click', function () {
        var indexSlide = $(this).index() + 1;
        $(this).parent().parent().find('.js-logos-container').attr('data-slide-index', indexSlide);
        showSlides(indexSlide, $(this).parent().parent().find('.js-logos-container'));
    });

    function showSlides(n, currentComponentIndex) {
        var i;
        var componentIndex = $(currentComponentIndex).attr('data-slide-current-index');
        var slides = document.getElementsByClassName("js-logo-slide-" + componentIndex);
        var dots = document.getElementsByClassName("js-logo-dot-" + componentIndex);
        if (n > slides.length) {
            $(currentComponentIndex).attr('data-slide-index', 1);
        }
        if (n < 1) {
            $(currentComponentIndex).attr('data-slide-index', slides.length);
        }
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[$(currentComponentIndex).attr('data-slide-index') - 1].style.display = "";
        dots[$(currentComponentIndex).attr('data-slide-index') - 1].className += " active";
    }
})();

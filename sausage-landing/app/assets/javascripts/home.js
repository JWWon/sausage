document.addEventListener("DOMContentLoaded", function(event) {
    //navbar event
    $(window).on('scroll', function() {
        if( $(window).scrollTop() > $(window).height() * 0.02 ) {
            $('._navbar').css('background', 'rgba(0, 0, 0, .6)');
        } else {
            $('._navbar').css('background', 'rgba(0, 0, 0, 0)');
        }
    });

    //smooth scrolling
    $('a[href*=\\#]').on('click', function(event){
        event.preventDefault();
        $('html,body').animate({scrollTop:$(this.hash).offset().top},
            600);
    });
});
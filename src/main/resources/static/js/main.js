function setWeather(weather) {
    var button;
    switch (weather){

        case "spring":
            button = document.getElementById("spring");
            button.style.background= "#ef6644 none repeat scroll 0 0";
            button.style.color= "#fff";

            document.getElementById("summer").style.background="#fff none repeat scroll 0 0";
            document.getElementById("summer").style.color="#666"

            document.getElementById("fall").style.background="#fff none repeat scroll 0 0";
            document.getElementById("fall").style.color="#666"

            document.getElementById("winter").style.background="#fff none repeat scroll 0 0";
            document.getElementById("winter").style.color="#666"

            document.getElementById("weather").setAttribute("value","spring");

            break;
        case "summer" :
            button = document.getElementById("summer");
            button.style.background= "#ef6644 none repeat scroll 0 0";
            button.style.color= "#fff";

            document.getElementById("spring").style.background="#fff none repeat scroll 0 0";
            document.getElementById("spring").style.color="#666"

            document.getElementById("fall").style.background="#fff none repeat scroll 0 0";
            document.getElementById("fall").style.color="#666"

            document.getElementById("winter").style.background="#fff none repeat scroll 0 0";
            document.getElementById("winter").style.color="#666"

            document.getElementById("weather").setAttribute("value","summer"); break;
        case "fall" :
            button = document.getElementById("fall");
            button.style.background= "#ef6644 none repeat scroll 0 0";
            button.style.color= "#fff";

            document.getElementById("summer").style.background="#fff none repeat scroll 0 0";
            document.getElementById("summer").style.color="#666"

            document.getElementById("spring").style.background="#fff none repeat scroll 0 0";
            document.getElementById("spring").style.color="#666"

            document.getElementById("winter").style.background="#fff none repeat scroll 0 0";
            document.getElementById("winter").style.color="#666"

            document.getElementById("weather").setAttribute("value","fall"); break;
        case "winter" :
            button = document.getElementById("winter");
            button.style.background= "#ef6644 none repeat scroll 0 0";
            button.style.color= "#fff";

            document.getElementById("summer").style.background="#fff none repeat scroll 0 0";
            document.getElementById("summer").style.color="#666"

            document.getElementById("fall").style.background="#fff none repeat scroll 0 0";
            document.getElementById("fall").style.color="#666"

            document.getElementById("spring").style.background="#fff none repeat scroll 0 0";
            document.getElementById("spring").style.color="#666"

            document.getElementById("weather").setAttribute("value","winter"); break;
    }
}
function setStyle(style) {
    var button;
    switch (style){
        case "casual" :
            button = document.getElementById("casual");
            button.style.background= "#ef6644 none repeat scroll 0 0";
            button.style.color= "#fff";

            document.getElementById("suit").style.background="#fff none repeat scroll 0 0";
            document.getElementById("suit").style.color="#666"

            document.getElementById("unique").style.background="#fff none repeat scroll 0 0";
            document.getElementById("unique").style.color="#666"

            document.getElementById("colorful").style.background="#fff none repeat scroll 0 0";
            document.getElementById("colorful").style.color="#666"

            document.getElementById("style").setAttribute("value","casual"); break;
        case "suit" :
            button = document.getElementById("suit");
            button.style.background= "#ef6644 none repeat scroll 0 0";
            button.style.color= "#fff";

            document.getElementById("casual").style.background="#fff none repeat scroll 0 0";
            document.getElementById("casual").style.color="#666"

            document.getElementById("unique").style.background="#fff none repeat scroll 0 0";
            document.getElementById("unique").style.color="#666"

            document.getElementById("colorful").style.background="#fff none repeat scroll 0 0";
            document.getElementById("colorful").style.color="#666"

            document.getElementById("style").setAttribute("value","suit"); break;
        case "unique" :
            button = document.getElementById("unique");
            button.style.background= "#ef6644 none repeat scroll 0 0";
            button.style.color= "#fff";

            document.getElementById("suit").style.background="#fff none repeat scroll 0 0";
            document.getElementById("suit").style.color="#666"

            document.getElementById("casual").style.background="#fff none repeat scroll 0 0";
            document.getElementById("casual").style.color="#666"

            document.getElementById("colorful").style.background="#fff none repeat scroll 0 0";
            document.getElementById("colorful").style.color="#666"

            document.getElementById("style").setAttribute("value","unique"); break;
        case "colorful" :
            button = document.getElementById("colorful");
            button.style.background= "#ef6644 none repeat scroll 0 0";
            button.style.color= "#fff";

            document.getElementById("suit").style.background="#fff none repeat scroll 0 0";
            document.getElementById("suit").style.color="#666"

            document.getElementById("unique").style.background="#fff none repeat scroll 0 0";
            document.getElementById("unique").style.color="#666"

            document.getElementById("casual").style.background="#fff none repeat scroll 0 0";
            document.getElementById("casual").style.color="#666"

            document.getElementById("style").setAttribute("value","colorful"); break;
    }
}
function setGender(gender) {
    var button;
    if(gender=="male"){
        button = document.getElementById("male");
        button.style.background= "#ef6644 none repeat scroll 0 0";
        button.style.color= "#fff";

        document.getElementById("female").style.background="#fff none repeat scroll 0 0";
        document.getElementById("female").style.color="#666"

        document.getElementById("gender").setAttribute("value","male");
    }else{
        button = document.getElementById("female");
        button.style.background= "#ef6644 none repeat scroll 0 0";
        button.style.color= "#fff";

        document.getElementById("male").style.background="#fff none repeat scroll 0 0";
        document.getElementById("male").style.color="#666"

        document.getElementById("gender").setAttribute("value","female");
    }
}



(function ($) {
 "use strict";

/*----------------------------
 jQuery MeanMenu
------------------------------ */
	jQuery('nav#dropdown').meanmenu();	
/*--------------------------
 menu a active jquery
---------------------------- */
	var pgurl = window.location.href.substr(window.location.href
		.lastIndexOf("/")+1);
		$("ul li a").each(function(){
		if($(this).attr("href") == pgurl || $(this).attr("href") == '' )
		$(this).addClass("active");
	})
	$( "header ul li ul,.header-bottom-two ul li ul ,.magamenu " ).parent('li').addClass('drop-icon');
/*----------------------------
 nivoSlider active
------------------------------ */
	$('#mainSlider').nivoSlider({
		directionNav: true,
		animSpeed: 500,
		effect: 'random',
		slices: 18,
		pauseTime: 10000,
		pauseOnHover: false,
		controlNav: true,
		prevText: '<i class="pe-7s-play nivo-prev-icon"></i>',
		nextText: '<i class="pe-7s-play nivo-next-icon"></i>'
	});
/*----------------------------
 owl active
------------------------------ */  
	$("#view-gallery,#testimonials").owlCarousel({
	  autoPlay: false, 
	  slideSpeed:500,
	  pagination:false,
	  navigation:true,	  
	  items : 1,
	  transitionStyle : "backSlide",     /* [This code for animation ] */
	  navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
	  itemsDesktop : [1199,1],
	  itemsDesktopSmall : [991,1],
	  itemsTablet: [767,1],
	  itemsMobile : [479,1],
	});
/*----------------------------
 owl active
------------------------------ */  
	$("#testimonials-two").owlCarousel({
	  autoPlay: false, 
	  slideSpeed:500,
	  pagination:false,
	  navigation:true,	  
	  items : 2,
	  /* transitionStyle : "fade", */    /* [This code for animation ] */
	  navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
	  itemsDesktop : [1199,2],
	  itemsDesktopSmall : [991,2],
	  itemsTablet: [767,1],
	  itemsMobile : [479,1],
	});
/*----------------------------
 owl active
------------------------------ */  
	$("#new-products,#tab-carousel-1,#tab-carousel-2,#tab-carousel-3,#tab-carousel-4,#tab-carousel-5").owlCarousel({
	  autoPlay: false, 
	  slideSpeed:1000,
	  pagination:false,
	  navigation:true,	  
	  items : 4,
	  /* transitionStyle : "fade", */    /* [This code for animation ] */
	  navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
	  itemsDesktop : [1199,4],
	  itemsDesktopSmall : [991,3],
	  itemsTablet: [767,2],
	  itemsMobile : [479,1],
	});
/*----------------------------
 owl active
------------------------------ */  
	$("#tabs-details").owlCarousel({
	  autoPlay: false, 
	  slideSpeed:1000,
	  pagination:false,
	  navigation:true,	  
	  items : 4,
	  /* transitionStyle : "fade", */    /* [This code for animation ] */
	  navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
	  itemsDesktop : [1199,4],
	  itemsDesktopSmall : [991,3],
	  itemsTablet: [767,3],
	  itemsMobile : [479,3],
	});
/*----------------------------
 owl active
------------------------------ */  
	$("#blog").owlCarousel({
	  autoPlay: false, 
	  slideSpeed:1000,
	  pagination:false,
	  navigation:true,	  
	  items : 3,
	  /* transitionStyle : "fade", */    /* [This code for animation ] */
	  navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
	  itemsDesktop : [1199,3],
	  itemsDesktopSmall : [991,2],
	  itemsTablet: [767,2],
	  itemsMobile : [479,1],
	});
/*----------------------------
 owl active
------------------------------ */  
	$("#brand-logo").owlCarousel({
	  autoPlay: false, 
	  slideSpeed:1000,
	  pagination:false,
	  navigation:true,	  
	  items : 6,
	  /* transitionStyle : "fade", */    /* [This code for animation ] */
	  navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
	  itemsDesktop : [1199,6],
	  itemsDesktopSmall : [991,4],
	  itemsTablet: [767,3],
	  itemsMobile : [479,2],
	});
/*----------------------------
 plus-minus-button
------------------------------ */
	$(".qtybutton").on("click", function() {
		var $button = $(this);
		var oldValue = $button.parent().find("input").val();
		if ($button.text() == "+") {
			var newVal = parseFloat(oldValue) + 1;
		} else {
			// Don't allow decrementing below zero
			if (oldValue > 0) {
				var newVal = parseFloat(oldValue) - 1;
			} else {
				newVal = 0;
			}
		}
		$button.parent().find("input").val(newVal);
	});
/*--------------------------
 tooltip active jquery
---------------------------- */
	$('.actions-btn a').tooltip({
        container: 'body'
    });
/*----------------------------
 price-slider active
------------------------------ */  
	$( "#slider-range" ).slider({
		range: true,
		min: 5000,
		max: 200000,
		values: [ 10000, 50000 ],
		slide: function( event, ui ) {
		$( "#amount" ).val( "" +"￦"+ ui.values[ 0 ] + " - " + "￦"+ui.values[ 1 ] );
		}
	});
	$( "#amount" ).val( "" + "￦"+$( "#slider-range" ).slider( "values", 0 ) +
	" - " + "￦"+$( "#slider-range" ).slider( "values", 1 ) );
/*--------------------------
 scrollUp
---------------------------- */	
	$.scrollUp({
        scrollText: '<i class="fa fa-angle-up"></i>',
        easingType: 'linear',
        scrollSpeed: 900,
        animation: 'fade'
    });
/*--------------------------
 tablist
---------------------------- */
	$('.quick-thumb .tablist a').on('click', function(){
		$('.quick-thumb .tablist a').removeClass('active');
		$('.quick-thumb .tablist').removeClass('active');
		$(this).addClass('active');
	});
	$('#reviews-tab a').on('click',function (e) {
		e.preventDefault()
		$(this).tab('show')
	});
/*--------------------------
 simpleLens
---------------------------- */
	$('.simpleLens-image').simpleLens({
		
	});
	
})(jQuery); 
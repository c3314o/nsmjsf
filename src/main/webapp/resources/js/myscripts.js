var tabCarousel;
$(document).ready(function($) {
	tabCarousel = setInterval("scrollNews()", 3000);
		  $('[data-toggle="tooltip"]').tooltip();
});
// $('#myTab[data-toggle="tab-hover"] > li > a').hover( function(){
// 	$(this).tab('show');
// });
$('#news-list').hover(function(e){
	e.preventDefault();
	clearInterval(tabCarousel);
},function(){
	tabCarousel = setInterval("scrollNews()", 3000);
});
function scrollNews () {
	var tabs = $('#myTab > li'),
        active = tabs.filter('.active'),
        next = active.next('li'),
        toClick = next.length ? next.find('a') : tabs.eq(0).find('a');

    toClick.trigger('click');
}
function MyDashboardTabChecker(){
	$('#content').on('click', 'a.tab-link-second', function(e){
		e.preventDefault();
		$('div#dashboard_tabs_second').find('div[id^=second_dashboard]').each(function(){
			$(this).css('display', 'none').css('position', 'absolute');
		});
		var attr = $(this).attr('id');
		$('#'+'second_dashboard-'+attr).css('display', 'block').css('position', 'relative');
		$(this).closest('.nav').find('li').removeClass('active');
		$(this).closest('li').addClass('active');
	});
}
// sticky top nav bar
//$(window).scroll(function() {
//if ($(this).scrollTop() > 1){  
//    $('#top-nav-wrapper').css('position',"fixed").css('z-index','100');
//  }
//  else{
//   $('#top-nav-wrapper').css('position',"relative");
//  }
//});
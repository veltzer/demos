// TODO:
// - document this example in detail.
//
// This is based on this article:
// http://yensdesign.com/2008/09/how-to-create-a-stunning-and-smooth-popup-using-jquery/

var popupShown=null;
var opacityLevel=0.7;

jQuery.fn.extend({
	center: function() {
		var windowWidth = document.documentElement.clientWidth;
		var windowHeight = document.documentElement.clientHeight;
		var popupHeight = $(this).height();
		var popupWidth = $(this).width();
		$(this).css(
			{
				"position": "absolute",
				"top": windowHeight/2-popupHeight/2,
				"left": windowWidth/2-popupWidth/2,
			}
		);
	},
	fullScreen: function() {
		var windowWidth = document.documentElement.clientWidth;
		var windowHeight = document.documentElement.clientHeight;
		$(this).css({
			"position": "fixed",
			"width": windowWidth,
			"height": windowHeight,
			"z-index": 1,
			"top": 0,
			"left": 0,
		});
	},
	onTop: function() {
		$(this).css({
			"z-index":2,
		});
	},
	popupShow: function() {
		$(this).fadeIn("slow");
	},
	popupHide: function() {
		$(this).fadeOut("slow");
	},
	popupFadeIn: function() {
		$($(this).attr('popupSelector')).fadeIn("slow");
		//$($(this).attr('bgSelector')).fadeIn("slow");
		$($(this).attr('bgSelector')).show().animate({opacity:opacityLevel},{
			duration: 200,
			//complete: function() { alert("in finish"); },
			}
		);
	},
	popupFadeOut: function() {
		$($(this).attr('popupSelector')).fadeOut("slow");
		//$($(this).attr('bgSelector')).fadeOut("slow");
		var selector=$($(this).attr('bgSelector'));
		selector.animate({opacity:0},{
			duration: 200,
			complete: function() { selector.hide(); },
			}
		);
	}
});
$(document).ready(function() {
	// order between the next two actually matters...
	// hide and center the popup
	$(".popup").hide();
	$(".popup").center();
	$(".popup").onTop();
	$(".popup").css({
		"background": "#FFFFFF",
		"border": "2px solid #cecece",
	});
	$(".popupBg").hide();
	$(".popupBg").fullScreen();
	$(".popupBg").css({
		"opacity": "0.0",
		"background": "#000000"
	});
	$(".popupShow").click(function() {
		if(popupShown!=null) {
			$(popupShown.attr('popupSelector')).popupHide();
		}
		popupShown=$(this);
		$(popupShown.attr('popupSelector')).popupShow();
		}
	);
	$(".popupHide").click(function() {
		if(popupShown!=null) {
			$(popupShown.attr('popupSelector')).popupHide();
			popupShown=null;
		}
		}
	);
	$(".popupToggle").click(function() {
		if(popupShown!=null) {
			$(popupShown.attr('popupSelector')).popupHide();
			popupShown=null;
		} else {
			popupShown=$(this);
			$(popupShown.attr('popupSelector')).popupShow();
		}
		//$($(this).attr('popupSelector')).toggle();
		}
	);
	$(".popupFadeIn").click(function() {
		if(popupShown!=null) {
			popupShown.popupFadeOut();
			popupShown=null;
		}
		popupShown=$(this);
		popupShown.popupFadeIn();
		}
	);
	$(".popupFadeOut").click(function() {
		if(popupShown!=null) {
			popupShown.popupFadeOut();
			popupShown=null;
		}
		}
	);
	$(document).keydown(function(e) {
		//alert("e.keyCode is "+e.keyCode);
		if(e.keyCode==27 && popupShown!=null) {
			popupShown.popupFadeOut();
			popupShown=null;
		}
	});
	$(document).keypress(function(e) {
		//alert("e.keyCode is "+e.keyCode);
		if(e.keyCode==27 && popupShown!=null) {
			popupShown.popupFadeOut();
			popupShown=null;
		}
	});
});

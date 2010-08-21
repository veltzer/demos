// TODO:
// - fading of the background color.
// - don't allow interaction with the background
// - don't allow more than one popup.
// - fade in/fade out of the popups instead of regular show/hide.

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
				"left": windowWidth/2-popupWidth/2
			}
		);
	}
});
$(document).ready(function() {
	// order between the next two actually matters...
	// hide and center the popup
	$(".popup").hide();
	$(".popup").center();
	$(".popupShow").click(function() {
		$($(this).attr('popupSelector')).show();
		}
	);
	$(".popupHide").click(function() {
		$($(this).attr('popupSelector')).hide();
		}
	);
	$(".popupToggle").click(function() {
		$($(this).attr('popupSelector')).toggle();
		}
	);
});

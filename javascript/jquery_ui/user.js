jQuery(document).ready(function() {
	// lets instantiate my widget
	var init={
		name:'name',
		init_text:'Put your name here...',
		state:'not validated',
		regex:/^\w+$/,
	};
	jQuery("#myfield").mywidget(init);
});

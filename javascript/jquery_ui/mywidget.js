/*
 * This is a custom jquery ui widget.
 *
 * TODO:
 * - nothing for now.
 */
jQuery(document).ready(function() {
	jQuery.widget('ui.mywidget',{
		// options
		options:{
			name:null,
			init_text:null,
			state:null,
			regex:null,
		},
		_create:function() {
			// comply with jquery ui ?!?
			this.element.addClass('ui-widget');
			// this variable is injected into the closure...
			var widget=this;

			// check that certain options have been passed
			if(this.options.name==null) {
				throw 'must declare name attribute';
			}
			if(this.options.init_text==null) {
				throw 'must declare init_text attribute';
			}
			if(this.options.state==null) {
				throw 'must declare state attribute';
			}
			if(this.options.regex==null) {
				throw 'must declare regex attribute';
			}

			var attrs={
				keyup:function() {
					widget.validate();
				},
				change:function() {
					widget.validate();
				},
			};
			// add the label
			this.w_label=jQuery('<label>');
			this.w_label.html(this.options.name);
			this.w_label.appendTo(this.element);
			// add the input box 
			this.w_input=jQuery('<input>',attrs);
			this.w_input.attr('value',this.options.init_text);
			this.w_input.appendTo(this.element);
			// add the label
			this.w_state=jQuery('<label>');
			this.w_state.html(this.options.state);
			this.w_state.appendTo(this.element);
		},
		validate:function() {
			var result;
			if(this.options.regex.test(this.w_input.val())) {
				result='ok';
			} else {
				result='error';
			}
			this.w_state.html(result);
		},
	});
});

/*
 * This is a roller widget.
 *
 * It consists of two buttons and a text input box.
 * Pushing button 1 increments the value in the box by a certain amount.
 * Pushing button 2 decrements the value in the box by a certain amount.
 *
 * the user can override the default value, the increment value, the decrement value
 * and the max value which causes a wrap around.
 *
 * 					Mark Veltzer
 */

if(!dojo._hasResource["extend.Roller"]){
	dojo._hasResource["extend.Roller"]=true;
	dojo.provide("extend.Roller");
	dojo.require("dijit._Widget");
	dojo.require("dijit._Templated");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.TextBox");
	dojo.declare(
		"extend.Roller",
		[dijit._Widget,dijit._Templated,dijit._Container],
		{
			templateString:'<div><div dojoType=\'dijit.form.Button\' dojoAttachEvent=\'onClick:_onDec\'>dec</div><div dojoType=\'dijit.form.TextBox\' dojoAttachPoint=\'textbox\'></div><div dojoType=\'dijit.form.Button\' dojoAttachEvent=\'onClick:_onInc\'>inc</div></div>',
			widgetsInTemplate:true,
			initVal:"5",
			incVal:"2",
			decVal:"2",
			maxVal:"10",
			arr:[5,6,7,8],
			startup:function() {
				console.debug("In startup");
				console.dir(this);
				this.textbox.attr('value',this.initVal);
			},
			_onDec:function() {
				var iinitVal=parseInt(this.initVal);
				var idecVal=parseInt(this.decVal);
				var imaxVal=parseInt(this.maxVal);
				iinitVal-=idecVal;
				iinitVal%=imaxVal;
				this.textbox.attr('value',iinitVal);
				this.initVal=iinitVal;
				console.dir(this.arr);
			},
			_onInc:function() {
				var iinitVal=parseInt(this.initVal);
				var iincVal=parseInt(this.incVal);
				var imaxVal=parseInt(this.maxVal);
				iinitVal+=iincVal;
				iinitVal%=imaxVal;
				this.textbox.attr('value',iinitVal);
				this.initVal=iinitVal;
				this.arr.push(iinitVal);
				console.dir(this.arr);
			},
		}
	);
}

dojo.require("dijit.form.Button");
dojo.require("dijit.form.NumberTextBox");
dojo.require("dojo.back");

function sumNumbers(e) {
	var w_sum = dijit.byId("id_sum");
	var v_sum = parseInt(w_sum.attr('value'));

	var w_num = dijit.byId("id_val");
	var v_num = parseInt(w_num.attr('value'));
	
	if(v_num%2==1) {
		// now invalidate all previous states...
		for(i=0;i<states.length;i++) {
			states[i].invalidate();
		}
	}
	v_sum=v_sum+v_num;
	w_sum.attr('value',v_sum);
	var s=new MyState(v_sum,v_num);
	dojo.back.addToHistory(s);
}
function Back(e) {
	dojo.back.goBack();
}
function Back2(e) {
	dojo.back.goBack();
	dojo.back.goBack();
}
var states=[];
function MyState(p_sum,p_val) {
	this.p_sum=p_sum;
	this.p_val=p_val;
	this.valid=true;
	states.push(this);
}
MyState.prototype.restoreState=function() {
	if(this.valid) {
		var w_sum=dijit.byId("id_sum");
		w_sum.attr('value',this.p_sum);
		var w_val=dijit.byId("id_val");
		w_val.attr('value',this.p_val);
	} else {
		alert("you can not go back to this state");
	}
}
MyState.prototype.back=MyState.prototype.restoreState;
MyState.prototype.forward=MyState.prototype.restoreState;
MyState.prototype.backButton=MyState.prototype.restoreState;
MyState.prototype.forwardButton=MyState.prototype.restoreState;
MyState.prototype.invalidate=function() {
	this.valid=false;
}
function myinit() {
	var s=new MyState(0,0);
	dojo.back.setInitialState(s);
	s.restoreState();
}
dojo.addOnLoad(myinit);

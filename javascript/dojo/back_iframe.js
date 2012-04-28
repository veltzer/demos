dojo.require("dijit.form.Button");
dojo.require("dijit.form.TextBox");
dojo.require("dojo.back");

var states=[];
var index=0;
var frm;
function MyState(p_url) {
	this.p_url=p_url;
	this.p_id=index;
	index++;
	states.push(this);
}
MyState.prototype.restoreState=function() {
	//alert("restoring "+this.p_url);
	var i=this.p_id;
	if(i>0) {
		//alert("restoring to "+i);
		i--;
		var s=states[i];
		//frm.src=s.p_url;
		frm.src="http://www.sourceforge.net";
	} else {
		frm.src=this.p_url;
	}
}
MyState.prototype.back=MyState.prototype.restoreState;
MyState.prototype.forward=MyState.prototype.restoreState;
// global to hold the iframe...
var mybool=false;
function myinit() {
	frm=document.createElement("iframe");
	var mydiv=document.getElementById("mydiv");
	mydiv.appendChild(frm);
	frm.onload=function() {
		alert("loaded "+frm.src);
		if(frm.src=="http://www.gnu.org/" && mybool) {
			frm.src="http://www.sourceforge.net";
			//window.location.href="http://www.sourceforge.net";
		}
		mybool=true;
	}
	var s=new MyState("http://www.gnu.org");
	dojo.back.setInitialState(s);
	s.restoreState();
}
function go() {
	var w_url = dijit.byId("id_url");
	var v_url=w_url.attr('value');
	//alert("going to "+v_url);
	var s=new MyState(v_url);
	dojo.back.addToHistory(s);
	frm.src=v_url;
}
dojo.addOnLoad(myinit);

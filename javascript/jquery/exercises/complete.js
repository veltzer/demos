/*
An example of auto complete...

	Mark Veltzer
*/
function min(a,b) {
	if(a<b) {
		return a;
	} else {
		return b;
	}
}
function gcd(t1,t2) {
	var len=min(t1.length,t2.length);
	for(var i=0;i<=len;i++) {
		if(t1.substring(0,i)!=t2.substring(0,i)) {
			var ret=t1.substring(0,i-1);
			return ret;
		}
	}
	var ret2=t1.substring(0,len);
	return ret2;
}
function complete(text, list) {
	// lets build a match list...
	var mlen=text.length;
	var matchlist=[];
	for(var i in list) {
		var val=list[i];
		if(val.substring(0,mlen)==text) {
			matchlist.push(val);
		}
	}
	if(matchlist.length===0) {
		return "";
	}
	var sgcd=matchlist[0];
	for(var i2 in matchlist) {
		var val2=matchlist[i2];
		sgcd=gcd(val2,sgcd);
	}
	return sgcd;
}

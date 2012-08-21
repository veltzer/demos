/*
 * A small helper to do ajax requests
 */
function jsonGet(url,callback) {
	var xhr=new XMLHttpRequest();
	xhr.onreadystatechange=function() {
		if(this.readyState==4 && this.status==200) {
			callback(JSON.parse(this.response));
		}
	}
	xhr.open('GET',url,true);
	xhr.send(null);
}

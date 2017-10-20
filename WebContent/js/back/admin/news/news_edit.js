window.onload = function() {
	listener("title","blur",validateTitle) ;
	listener("note","blur",validateNote) ;
	listener("pic","blur",validatePhoto) ;
	listener("newsForm","submit",function(e){
		if (validateTitle() & validateNote() & validatePhoto()) {
			this.submit() ;
		} else {
			e.preventDefault() ;
		}
	}) ;
}
function validateTitle() {
	return validateEmpty("title") ;
}
function validateNote() {
	return validateEmpty("note") ;
}
function validatePhoto() {
	return validateEmpty("pic") ;
}
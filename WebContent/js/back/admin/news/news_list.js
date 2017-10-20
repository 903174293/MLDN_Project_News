window.onload = function() {
	listener("selectall","click",function(){
		checkboxSelectAll("selectall","nid") ;
	}) ;
	listener("deleteBtn","click",function(){
		handleDelete("nid","pages/back/admin/news/NewsAction!delete.action") ;
	}) ;
} 
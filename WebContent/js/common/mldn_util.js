/**
 * 进行批量删除按钮的JS处理，在此函数之中会对是否选中数据进行判断
 * 如果数据被选中则会将数据重新组合“id|id|id”的形式传入到其它的页面，这个数据的参数名称固定为ids
 * @param eleId
 * @param delUrl
 * @returns
 */
function handleDelete(eleId,delUrl) {
	eleObject = document.all(eleId) ;	// 获取指定的组件信息
	idsValue = "" ;	// 初始化的字符串内容
	if (eleObject.length == undefined) {
		if (eleObject.checked) {	// 当前元素被选中
			idsValue += eleObject.value ;
		}
	} else {
		for (x = 0 ; x < eleObject.length ; x ++) {
			if (eleObject[x].checked) {
				idsValue += eleObject[x].value + "," ;
			}
		}
	}
	if (idsValue == "") {	// 如果现在数据没有改变，则表示没有内容被选中
		alert("对不起，您还未选择数据，请先选择！") ;
	} else {	// 跳转到删除页面
		if (window.confirm("您确定要执行此操作吗？")) {
			window.location = delUrl+"?ids=" + idsValue ;
		}
	}
}
/**
 * 实现复选框的全选操作控制的可重用函数
 * @param ctlId 发出全选指令的控制组件的名称
 * @param eleId 实现权限的控制操作
 * @returns
 */
function checkboxSelectAll(ctlId,eleId) {
	ctlObject = document.getElementById(ctlId) ;
	eleObject = document.all(eleId) ;	// 获取指定的组件信息
	if (eleObject.length == undefined) {
		eleObject.checked = ctlObject.checked ;
	} else {
		for (x = 0 ; x < eleObject.length ; x ++) {
			eleObject[x].checked = ctlObject.checked ;
		}
	}
}
function listener(eleId,eventType,fun) {
	document.getElementById(eleId).addEventListener(eventType,fun,false) ;
}
// 进行指定元素内容是否为空的判断
function validateEmpty(eleId) {
	divObject = document.getElementById(eleId + "Div") ;
	obj = document.getElementById(eleId) ;	
	if (obj != null) {
		if (obj.value != "") {
			divObject.className = "form-group has-success" ;
			return true ;
		} else {
			divObject.className = "form-group has-error" ;
			return false ;
		}
	}
}
function validateRegex(eleId,regex) {
	if (validateEmpty(eleId)) {	// 首先进行是否为空的验证
		obj = document.getElementById(eleId) ;
		divObject = document.getElementById(eleId + "Div") ;
		if (regex.test(obj.value)) {	// 正则验证
			divObject.className = "form-group has-success" ;
			return true ;
		} else {
			divObject.className = "form-group has-error" ;
			return false ;
		}
	}
}
function validateCheckbox(eleId) {	// 验证指定的复选框是否有被选中
	count = 0 ;	// 判断是否有选中的数据量
	obj = document.all(eleId) ;
	divObject = document.getElementById(eleId + "Div") ;
	if (obj.length == undefined) { 
		if (obj.checked) {
			count ++ ;
		}
	} else {
		for (x = 0 ; x < obj.length ; x ++) {
			if (obj[x].checked) {
				count ++ ;
			}
		}
	}
	if (count > 0) { 
		divObject.className = "form-group has-success" ;
		return true ; 
	} else {
		divObject.className = "form-group has-error" ;
		return false ;
	}
}
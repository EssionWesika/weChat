/***
 * 将对象打包成数组格式传入方法，若有一个为空则返回false
 * @param a
 * @returns {boolean}
 */
function isNull(a){
	for (var i = 0; i < a.length; i++) {
		if(a[i]==null||a[i]==""||a[i]==undefined){
			return true;
		}
	}
	return false;
}
/***
 * 解码器
 * @param data
 * @returns {string}
 */
function dc(data){
	return decodeURIComponent(data);
}
/***
 * 编码器
 * @param data
 * @returns {string}
 */
function ec(data){
	return encodeURIComponent(data);
}
/***
 * 小型 弹出警告框
 * @param data
 * @param fn
 */
function alert_sm(data,fn){
    bootbox.alert({
        size: "small",
        message: data,
        callback:fn
    });
}
/***
 * 模拟表单post请求
 * @param URL
 * @param PARAMS example:post('pages/statisticsJsp/excel.action', {html :prnhtml,cm1:'sdsddsd',cm2:'haha'});
 * @returns
 */
function post(URL, PARAMS) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "post";
    temp.style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}
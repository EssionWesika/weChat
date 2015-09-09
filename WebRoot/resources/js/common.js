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

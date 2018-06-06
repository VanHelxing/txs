/**
 * 添加
 * @param modal
 * @param url
 */
function createFunction(modal, url) {
    $(modal).modal({
        remote: url,
        buttons: [{
            title: "close"
        }]
    });
}

/**
 * 更新
 * @param modal
 * @param url
 * @param id
 */
function updateFunction(modal, url, id) {
    if (id.length == 0 || id.length > 1) {
        $.sobox.alert(strings['common.dialog.title'], strings['common.dialog.update.tip']);
        return;
    }
    url = url.concat("?id=").concat(id.valueOf().toString());

    $(modal).modal({
        remote: url,
        buttons: [{
            title: "close"
        }]
    });

}

/**
 * 删除
 * @param options
 */
function deleteFunction(modal,url,ids) {
    if (ids.length == 0) {
        $.sobox.alert(strings['common.dialog.title'], strings['common.dialog.select.tip']);
        return;
    }
    $.sobox.confirm(strings['common.dialog.title'], strings['common.dialog.delete.tip'], function () {
        $.post(url, {ids: ids.valueOf().toString()}, function (obj) { //回传函数
            var data = jQuery.parseJSON(obj);
            $.sobox.alert(strings['common.dialog.title'], data.message, function () {
                if (data.success) {
                    window.location.reload();
                }
            });

        });
    }, function () {
    });
}
/**
 * 表单异步提交方法
 */
function ajaxSave(options) {
    var save = $.extend({
        formId: "#data-form",
        modalId: "#modal",
    }, options);
    $(save.formId).ajaxSubmit(function (resultJson) {
        var data = JSON.parse(resultJson);
        modalToggle(save.modalId);
        message(data);
    });
}
/**
 * 模态框关闭方法
 * @param modalId
 */
function modalToggle(modalId) {
    if (modalId == null || modalId == "") {
        modalId = "#modal"
    }
    var modal = $(modalId);
    modal.modal("toggle");
}

/**
 * 弹出模态框的方法
 * @param options
 */
function buildModal(options) {
    var buildModal = $.extend({
        modal: "#modal",
        data: {
            success: false,
            message: ""
        }
    }, options);
    modal = $(buildModal.modal);
    modalContent = modal.find(".modal-content");
    modalContent.html("<div class='modal-body'><span class='control-label'>" + buildModal.data.message + "</span></div>");
}

/**
 * so box 显示提示信息
 * @param data
 * @param timeout
 */
function message(data) {
    if (data.success) {
        $.sobox.tip({
            posType: 'center',
            stayTime: 3000,
            content: data.message,
            showMask: true,
            closePop: function () {
                window.location.reload();
            }
        });
    } else {
        $.sobox.alert(strings['common.dialog.title'], data.message);
    }
}

/**
 * 条件查询
 * @param table
 */
function condQuery(table) {

    var param = {};
    var count = 0;
    //迭代查询条件
    $('.searchParam').each(function(){

        var str = $(this)[0].id;
        var forEachTmp = str.split('_');

        if ($(this).val()) {
            //组装查询对象
            var key1 = 'searchParams['+count+'].javaType';
            param[key1] = forEachTmp[0];
            var key2 = 'searchParams['+count+'].property';
            param[key2] = forEachTmp[1];
            var key3 = 'searchParams['+count+'].type';
            param[key3] = forEachTmp[2];
            if ('value' === forEachTmp[3]) {
                var key4 = 'searchParams['+count+'].value';
                param[key4] = $(this).val();
            }else if ('value1' === forEachTmp[3]) {
                var key5 = 'searchParams['+count+'].value1';
                param[key5] = $(this).val();
            }else {

            }

            count++;
        }
    });

    table.settings()[0].ajax.data = param;
    table.ajax.reload();
}




// 身份证号码验证
jQuery.validator.addMethod("isIdCardNo", function(value, element) {
    function IdentityCodeValid(code) {
        var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var tip = "";
        var pass= true;

        if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
            pass = false;
        }

        else if(!city[code.substr(0,2)]){
            pass = false;
        }
        else{
            //18位身份证需要验证最后一位校验位
            if(code.length == 18){
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = code[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != code[17]){
                    pass =false;
                }
            }
        }
        return pass;
    }
    return this.optional(element) || IdentityCodeValid(value);
}, "请输入正确的身份证号码。");

//通用验证方法（只需要传正则表达式）
jQuery.validator.addMethod("regex", //addMethod第1个参数:方法名称
    function(value,element,params){//addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数）
        var exp = new RegExp(params);//实例化正则对象，参数为传入的正则表达式
        return exp.test(value);         //测试是否匹配
    });//addMethod第3个参数:默认错误信息

//
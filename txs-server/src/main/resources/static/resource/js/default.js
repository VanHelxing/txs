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


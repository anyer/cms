/**自定义模块*/
layui.define(['layer'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer;
    var CmsCommon = {
        /**错误msg提示 */
        cmsLayErrorMsg:function (text) {
            top.layer.msg(text, {icon: 5});
        },
        /**成功 msg提示 */
        cmsLaySuccessMsg:function (text) {
            top.layer.msg(text, {icon: 6});
        },
        /**ajax Confirm 对话框*/
        ajaxCmsConfirm: function (title, text, url,param) {
            layer.confirm(text, {
                title: title,
                resize: false,
                btn: ['确定', '取消'],
                btnAlign: 'c',
                anim:1,
                icon: 3
            }, function () {
                $.ajax({
                    url : url,
                    type : 'post',
                    async: false,
                    data : param,
                    success : function(data) {
                        if(data.returnCode == "SUCCESS"){
                            top.common.cmsLaySuccessMsg(data.returnMessage);
                            location.reload();
                        }else{
                            top.common.cmsLayErrorMsg(data.returnMessage);
                        }
                    },error:function(data){
                        top.common.cmsLayErrorMsg(data.returnExpectionMsg);
                    }
                });
            });
        },
        /**弹出层*/
        cmsLayOpen:function (title,url,width,height) {

            var index = layui.layer.open({
                title : '<i class="larry-icon larry-bianji3"></i>'+title,
                type : 2,
                skin : 'layui-layer-molv',
                content : url,
                area: [width, height],
                resize:false,
                anim:1,
                success : function(layero, index){

                }
            });
        },
        /**弹出层-tip*/
        cmsLayOpenTip:function (title,url,width,height) {

            var index = layui.layer.open({
                title : '<i class="larry-icon larry-bianji3"></i>'+title,
                type : 2,
                skin : 'layui-layer-molv',
                content : url,
                area: [width, height],
                resize:false,
                anim:1,
                success : function(layero, index){
                    setTimeout(function(){
                        layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                            tips: [3, '#009f95']
                        });
                    },500)

                }
            });
        },
        /**退出*/
        logOut: function (title, text, url, type, dataType, data, callback) {
            parent.layer.confirm(text, {
                title: title,
                resize: false,
                btn: ['确定退出系统', '不，我点错了！'],
                btnAlign: 'c',
                icon: 3
            }, function () {
                location.href = url
            }, function () {
               
            })
        },
        /**重置表格宽度*/
        resizeGrid:function (){
            $(".layui-table-view .layui-table").css("width", "100%");
            $(window).resize(function(){
                $(".layui-table-view .layui-table").css("width", "100%");
            });
        },
        // 缓存临时数据
        putTempData: function (key, value) {
            if (value) {
                layui.sessionData('tempData', {key: key, value: value});
            } else {
                layui.sessionData('tempData', {key: key, remove: true});
            }
        },
        // 获取缓存临时数据
        getTempData: function (key) {
            return layui.sessionData('tempData')[key];
        }
    };

    // 所有lay-tips处理
    $('body').on('mouseenter', '*[lay-tips]', function () {
        var tipText = $(this).attr('lay-tips');
        var dt = $(this).attr('lay-direction');
        layer.tips(tipText, this, {tips: dt || 1, time: -1});
    }).on('mouseleave', '*[lay-tips]', function () {
        layer.closeAll('tips');
    });

    exports('common', CmsCommon)
})




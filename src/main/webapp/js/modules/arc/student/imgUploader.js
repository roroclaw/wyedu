/**
 * Created by dxz on 2017/8/8.
 */
$(function(){
    var uploader = WebUploader.create({
        auto:true,
        // swf文件路径
        swf:  BAS_URL+'plugins/webuploader/Uploader.swf',
        // 文件接收服务端。
        server: BAS_URL+'common/uploadImg.infc',
        formData: {accToken:getAccToken()},
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
            id: '#filePicker',
            label: '<i class="icon icon-upfile"></i>上传图片'
        },
        resize: false,
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/jpg,image/jpeg,image/png'
        }
    });

    uploader.onUploadSuccess = function (file, ret, hds) {
        var data = ret['data'];
        var status = ret['status'];
        if(data == undefined || data == null){
            if(status != 1){ //不成功
                $.alert_error(ret['describe']);
            }
        }
        if(status !== 0){ //成功
            var obj = ret['object'];
            $('#stuImg').attr('src',obj['fullDir']);
            $('#imgUrl').val(obj['shortDir']);
        }
        uploader.reset();
    };
});
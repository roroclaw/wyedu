<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${contextpath}/plugins/webuploader/webuploader.js?rand=${jsRand}"></script>
<!--上传组件 -->
<div class="message_bg importShowBox" style="display: none">
    <div class="message_box">
        <div class="message_title">${param.titleName}<i class="cancelBtn"></i></div>
        <div class="message_text placeholder">
             <div class="upbtn" ><div id="filePicker"></div></div>
        </div>
        <div class="info queueList" >
               <table class="fileListTable">
                   <tr class="title_tr"><td>文件名</td><td>文件大小</td><td>上传状态</td><td>操作</td></tr>
                   <%--<tr><td>1.jpg</td><td>234kb</td><td>上传成功</td><td><a href="#">删除</a></td></tr>--%>
                   <%--<tr><td>1.jpg</td><td>234kb</td><td>上传成功</td><td><a href="#">删除</a></td></tr>--%>
                   <%--<tr><td>1.jpg</td><td>234kb</td><td>上传成功</td><td><a href="#">删除</a></td></tr>--%>
                   <%--<tr><td>1.jpg</td><td>234kb</td><td>上传成功</td><td><a href="#">删除</a></td></tr>--%>
                   <%--<tr><td>1.jpg</td><td>234kb</td><td>上传成功</td><td><a href="#">删除</a></td></tr>--%>
                   <%--<tr><td>1.jpg</td><td>234kb</td><td>上传成功</td><td><a href="#">删除</a></td></tr>--%>
               </table>
           </div>
            <!--進度條-->
            <div class="statusBar">
                <div class="progress">
                    <span class="text">0%</span>
                    <span class="percentage"></span>
                </div>
                <div class="info"></div>
                <div id="filePicker2" style="float: right; margin-right: 160px;"></div>
                <div class="btns">
                    <div class="upbtn uploadBtn" style="float: left;">开始上传</div>
                </div>
            </div>
            <!--進度條-->
        </div>
    </div>
</div>

<script type="text/javascript">
//    function resetTable(){
//        $('.queueList .fileListTable').html();
//    }
    function createUploader(_callback, _formData,_fileNumLimit) {
        _formData = _formData != null && _formData != undefined ? _formData : {};
        var accToken = $.cookie('accToken');
//        alert(accToken);
        if(accToken == null || accToken == undefined){
            accToken = $("#basic_accToken").attr("val");
        }
        _formData['accToken'] = accToken;

        _fileNumLimit = _fileNumLimit != null && _fileNumLimit != undefined  ?_fileNumLimit : 1;
        var $wrap = $('.importShowBox');
        $('.uploader',$wrap).empty();
        $wrap.show();
        var _server = '${param.url}';
        // 图片容器
//        $wrap.find('.queueList table.table-upfile').remove();
        var $queue = $('.queueList .fileListTable'),

        // 状态栏，包括进度和控制按钮
                $statusBar = $wrap.find('.statusBar'),

        // 文件总体选择信息。
                $info = $statusBar.find('.info'),

        // 上传按钮
                $upload = $wrap.find('.uploadBtn'),

//        // 没选择文件之前的内容。
                $placeHolder = $wrap.find('.placeholder'),

                $progress = $statusBar.find('.progress').hide(),

        // 添加的文件数量
                fileCount = 0,

        // 已完成的文件数量
                complete = 0,

        // 添加的文件总大小
                fileSize = 0,

        // 优化retina, 在retina下这个值是2
                ratio = window.devicePixelRatio || 1,

        // 缩略图大小
                thumbnailWidth = 110 * ratio,
                thumbnailHeight = 110 * ratio,

        // 可能有pedding, ready, uploading, confirm, done.
                state = 'pedding',

        // 所有文件的进度信息，key为file id
                percentages = {},
        // 判断浏览器是否支持图片的base64
                isSupportBase64 = (function () {
                    var data = new Image();
                    var support = true;
                    data.onload = data.onerror = function () {
                        if (this.width != 1 || this.height != 1) {
                            support = false;
                        }
                    };
                    data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                    return support;
                })(),

        // 检测是否已经安装flash，检测flash的版本
                flashVersion = (function () {
                    var version;

                    try {
                        version = navigator.plugins['Shockwave Flash'];
                        version = version.description;
                    } catch (ex) {
                        try {
                            version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                                    .GetVariable('$version');
                        } catch (ex2) {
                            version = '0.0';
                        }
                    }
                    version = version.match(/\d+/g);
                    return parseFloat(version[0] + '.' + version[1], 10);
                })(),

                supportTransition = (function () {
                    var s = document.createElement('p').style,
                            r = 'transition' in s ||
                                    'WebkitTransition' in s ||
                                    'MozTransition' in s ||
                                    'msTransition' in s ||
                                    'OTransition' in s;
                    s = null;
                    return r;
                })(),

        // WebUploader实例
                uploader;

        if (!WebUploader.Uploader.support('flash') && WebUploader.browser.ie) {

            // flash 安装了但是版本过低。
            if (flashVersion) {
                (function (container) {
                    window['expressinstallcallback'] = function (state) {
                        switch (state) {
                            case 'Download.Cancelled':
                                alert('您取消了更新！');
                                break;

                            case 'Download.Failed':
                                alert('安装失败');
                                break;

                            default:
                                alert('安装已成功，请刷新！');
                                break;
                        }
                        delete window['expressinstallcallback'];
                    };

                    var swf = './expressInstall.swf';
                    // insert flash object
                    var html = '<object type="application/' +
                            'x-shockwave-flash" data="' + swf + '" ';

                    if (WebUploader.browser.ie) {
                        html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                    }

                    html += 'width="100%" height="100%" style="outline:0">' +
                    '<param name="movie" value="' + swf + '" />' +
                    '<param name="wmode" value="transparent" />' +
                    '<param name="allowscriptaccess" value="always" />' +
                    '</object>';

                    container.html(html);

                })($wrap);

                // 压根就没有安转。
            } else {
                $wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
            }

            return;
        } else if (!WebUploader.Uploader.support()) {
            alert('Web Uploader 不支持您的浏览器！');
            return;
        }

        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker',
                label: '<i class="icon icon-upfile"></i>${param.btnName}'
            },
            formData: _formData,
//            dnd: '#dndArea',
//            paste: '#uploader',
            swf: './js/datas/Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            server: _server,
            // runtimeOrder: 'flash',

            accept: {
//                 title: 'Images',
                 extensions: '${param.extensions}'
//                ,mimeTypes: 'image/*'
            },

            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: _fileNumLimit || 1,
            fileSizeLimit: 200 * 1024 * 1024, // 200 M
            fileSingleSizeLimit: 200 * 1024 * 1024 // 50 M
        });

        // 重置
        uploader.reset();
        $statusBar.hide();
        $placeHolder.show();
        $queue.hide();
        $queue.html('<tr class="title_tr"><td>文件名</td><td>文件大小</td><td>上传状态</td><td>操作</td></tr>');
        $wrap.find('.queueList').scrollTop(0);

        // 传参数
//        uploader.on('uploadBeforeSend', function (block, data) {
//            data.uid = $('select.fr.form-control').val();
//        });

        uploader.on('error', function (error_msg, max, file) {
            var msg;
            switch (error_msg) {
                case 'F_DUPLICATE':
                    msg = '此文件已存在';
                    break;
                case 'Q_TYPE_DENIED':
                    msg = '上传文件类型不正确';
                    break;
                case 'F_EXCEED_SIZE':
                    msg = file.name + ' 大小超出限制!';
                    break;
                case 'Q_EXCEED_NUM_LIMIT':
                    msg = "只能一次上传" + max + "个文件!";
                    break;
                case 'Q_EXCEED_SIZE_LIMIT':
                    msg = '文件总大小超出限制！';
                    break;
            }
            alert(msg);//layer.msg(msg);
            return false;
        });

        // 拖拽时不接受 js, txt 文件。
//        uploader.on('dndAccept', function (items) {
//            var denied = false,
//                    len = items.length,
//                    i = 0,
//            // 修改js类型
//                    unAllowed = 'text/plain;application/javascript ';
//
//            for (; i < len; i++) {
//                // 如果在列表里面
//                if (~unAllowed.indexOf(items[i].type)) {
//                    denied = true;
//                    break;
//                }
//            }
//
//            return !denied;
//        });
        /*中文提示*/
        uploader.on('error', function (error_msg, max, file) {
            var msg;
            switch (error_msg) {
                case 'F_DUPLICATE':
                    msg = '此文件已存在';
                    break;
                case 'Q_TYPE_DENIED':
                    msg = '上传文件类型不正确';
                    break;
                case 'F_EXCEED_SIZE':
                    msg = file.name + ' 大小超出限制!';
                    break;
                case 'Q_EXCEED_NUM_LIMIT':
                    msg = "只能一次上传" + max + "个文件!";
                    break;
                case 'Q_EXCEED_SIZE_LIMIT':
                    msg = '文件总大小超出限制！';
                    break;
            }
//            showmsg(msg);
            return false;
        });
        uploader.on('dialogOpen', function () {
            //console.log('dialogOpen');
        });

        // uploader.on('filesQueued', function() {
        //     uploader.sort(function( a, b ) {
        //         if ( a.name < b.name )
        //           return -1;
        //         if ( a.name > b.name )
        //           return 1;
        //         return 0;
        //     });
        // });

        // 添加“添加文件”的按钮，
        uploader.addButton({
            id: '#filePicker2',
            label: '继续添加'
        });

        uploader.on('ready', function () {
            window.uploader = uploader;
        });

//        function resetAddBtn(fileSize,limitSize){
//            if(fileSize >= limitSize){
//                $('#filePicker2').hide();
//            }else{
//                $('#filePicker2').show();
//            }
//        }

        // 当有文件添加进来时执行，负责view的创建
        function addFile(file) {
//            var $li = $('<tr id="' + file.id + '">' +
//            '<td>' + file.name + '</td>' +
//            '<td>' + WebUploader.formatSize(file.size) + '</td>' +
//            '<td>准备上传</td>' +
//            '</tr>');
            var $li = $('<tr id="' + file.id + '">' +
            '<td>' + file.name + '</td>' +
            '<td>' + WebUploader.formatSize(file.size) + '</td>' +
            '<td>准备上传</td></tr>');


//            $btns = $('<td class="file-panel">' +
//            '<span class="cancel">删除</span></td>').appendTo($li),
            $btns = $('<td class="file-panel"><a class="fileDelBtn cancel" href="###">删除</a></td>').appendTo($li),
            $prgress = $li.find('p.progress span'),
            $wrap = $li.find('p.imgWrap'),
            $info = $('<p class="error"></p>'),
            showError = function (code) {
                        switch (code) {
                            case 'exceed_size':
                                text = '文件大小超出';
                                break;

                            case 'interrupt':
                                text = '上传暂停';
                                break;

                            default:
                                text = '上传失败，请重试('+code+')';
                                break;
                        }
//                        $info.text(text).appendTo($li);
//                          $.alert_error(text);
                            $li.find('td.file-panel').remove();
                            $btns.appendTo($li);
                            alert(text);
                    };

            if (file.getStatus() === 'invalid') {
                showError(file.statusText);
            } else {
                // @todo lazyload
                $wrap.text('预览中');
                percentages[file.id] = [file.size, 0];
                file.rotation = 0;

            }

            file.on('statuschange', function (cur, prev) {
                if (prev === 'progress') {
                    $prgress.hide().width(0);
                } else if (prev === 'queued') {
                    $li.off('mouseenter mouseleave');
                    $btns.remove();
                }

                // 成功
                if (cur === 'error' || cur === 'invalid') {
                    //console.log(file.statusText);
                    showError(file.statusText);
                    percentages[file.id][1] = 1;
                } else if (cur === 'interrupt') {
                    showError('interrupt');
                } else if (cur === 'queued') {
                    $info.remove();
                    $prgress.css('display', 'block');
                    percentages[file.id][1] = 0;
                } else if (cur === 'progress') {
                    $info.remove();
                    $prgress.css('display', 'block');
                } else if (cur === 'complete') {
                    $prgress.hide().width(0);
                    $li.find('td').eq(2).html('已上传');
                    $li.find('td.file-panel').remove();
                    $li.append('<td class="file-panel" style="height: 29px; overflow: hidden;"><span class="success">上传成功</span></td>');
                }

                $li.removeClass('state-' + prev).addClass('state-' + cur);
            });

            $li.on('mouseenter', function () {
                $btns.stop().animate({height: 30});
            });

            $li.on('mouseleave', function () {
                $btns.stop().animate({height: 0});
            });

            $(document).on('click', '.fileDelBtn', function () {
                var index = $(this).index(),
                        deg;

                switch (index) {
                    case 0:
                        uploader.removeFile(file);
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if (supportTransition) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
                }


            });
            $li.appendTo($queue);
        }

        // 负责view的销毁
        function removeFile(file) {
            var $li = $('#' + file.id);

            delete percentages[file.id];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        function updateTotalProgress() {
            var loaded = 0,
                    total = 0,
                    spans = $progress.children(),
                    percent;

            $.each(percentages, function (k, v) {
                total += v[0];
                loaded += v[0] * v[1];
            });

            percent = total ? loaded / total : 0;


            spans.eq(0).text(Math.round(percent * 100) + '%');
            spans.eq(1).css('width', Math.round(percent * 100) + '%');
            updateStatus();
        }

        function updateStatus() {
            var text = '',
                    stats;

            if (state === 'ready') {
                text = (complete ? '已上传' + complete + '个文件, ' : '') + '待上传' + fileCount + '个文件，共' +
                WebUploader.formatSize(fileSize) + '。';
                if (_fileNumLimit == fileCount + complete) {
                    $('#filePicker2').hide();
                } else {
                    $('#filePicker2').show();
                }
            } else if (state === 'confirm') {
                stats = uploader.getStats();
                if (stats.uploadFailNum) {
                    text = '已成功上传' + stats.successNum + '个文件，' +
                    stats.uploadFailNum + '个文件上传失败，<a class="retry" href="#">重新上传</a>或<a class="ignore" href="#">忽略</a>'
                }
            } else {
                stats = uploader.getStats();
                text = '共' + (fileCount + complete) + '个文件（' +
                WebUploader.formatSize(fileSize) +
                '），已上传' + stats.successNum + '个文件';

                if (stats.uploadFailNum) {
                    text += '，失败' + stats.uploadFailNum + '个文件';
                }
                if (_fileNumLimit == fileCount + complete) {
                    $('#filePicker2').hide();
                    $upload.addClass('disabled');
                } else {
                    $('#filePicker2').show();
                    $upload.removeClass('disabled');
                }
            }
            //$info.html(text);
            $("#uploader .statusBar .info").html(text);

        }

        function setState(val) {
            var file, stats;

            if (val === state) {
                return;
            }

            $upload.removeClass('state-' + state);
            $upload.addClass('state-' + val);
            state = val;

            switch (state) {
                case 'pedding':
//                     $placeHolder.show();
//                     $queue.hide();
//                     $statusBar.hide();
//                     uploader.refresh();
                    break;

                case 'ready':
                    $placeHolder.hide();
                    $('#filePicker2').show();
                    $queue.show();
                    $statusBar.show();
                    uploader.refresh();
                    break;

                case 'uploading':
                    //$('#filePicker2').hide();
                    $progress.show();
                    $upload.text('上传中');
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text('上传中');
                    //$upload.stop();
                    break;

                case 'confirm':
                    $progress.hide();
                    $('#filePicker2').show();
                    $upload.text('开始上传');

                    stats = uploader.getStats();
                    if (stats.successNum && !stats.uploadFailNum) {
                        setState('finish');
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if (stats.successNum) {
                        /*
                         alert( '上传成功' );
                         $('#upfile_dialog').hide();
                         location.reload();
                         */
                        //$('.table-upfile tr.state-complete').remove();

                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }
            updateStatus();
        }

        // 单个上传成功处理
        uploader.onUploadSuccess = function (file, ret, hds) {
//            if (typeof _callback === 'function') {
//                _callback(file, ret, hds);
//            }
            var data = ret['data'];
            var status = ret['status'];
            if(data == undefined || data == null){
                if(status != 1){ //不成功
//                    file.trigger( 'statuschange', 'completeError', '' );
//                    status = file.getStatus();
//                    status.uploadFailNum = status.uploadFailNum + 1;
//                    status.successNum  = status.successNum - 1;
                    file.setStatus('error', ret['describe']);
                }
            }
            if(status !== 0){ //成功
                if (typeof _callback === 'function') {
                    _callback(file, ret, hds);
                }
            }
        };

        uploader.onUploadProgress = function (file, percentage) {
            var $li = $('#' + file.id),
                    $percent = $li.find('.progress span');

            $percent.css('width', percentage * 100 + '%');
            percentages[file.id][1] = percentage;
            updateTotalProgress();
        };

        uploader.onFileQueued = function (file) {
            complete = $queue.find('tr[class*="state-complete"]').length;
            //fileCount++;
            fileCount = $queue.find('tr').length - complete;
            fileSize += file.size;

            if (fileCount === 1) {
                $placeHolder.hide();
                $statusBar.show();
            }

            addFile(file);
            setState('ready');
            updateTotalProgress();
        };

        uploader.onFileDequeued = function (file) {
            fileCount--;
            fileSize -= file.size;

            if (!fileCount) {
                setState('pedding');
            }

            removeFile(file);
            updateTotalProgress();

        };

        uploader.on('all', function (type) {
            var stats;
            switch (type) {
                case 'uploadFinished':
                    setState('confirm');
                    break;

                case 'startUpload':
                    setState('uploading');
                    break;

                case 'stopUpload':
                    setState('paused');
                    break;

            }
        });

        uploader.onError = function (code) {
            alert('Eroor: ' + code);
        };

        $upload.off('click.zd').on('click.zd', function () {
            if ($(this).hasClass('disabled')) {
                return false;
            }

            if (state === 'ready') {
                uploader.upload();
            } else if (state === 'paused') {
                uploader.upload();
            } else if (state === 'uploading') {
                uploader.stop();
            }
        });

        $info.on('click', '.retry', function () {
            uploader.retry();
        });

        $info.on('click', '.ignore', function () {
            alert('todo');
        });

        $upload.addClass('state-' + state);
        updateTotalProgress();
    }


    var U = hash2Object(document.location.href),
            callback = U.fileCallback,
            p = parent,
            fileTypeExts = '*.avi; *.mov; *.mp4; *.mpeg; *.3gp; *.flv; *.asx; *.wmv;*.asf;*.mpg;*.rm;*.rmvb;*.wmv9;*.wmv3',
            queueSize = parseInt(U.maxQueueSize);

    function hash2Object(url) {
        var r = {};
        if (url.indexOf("?") > 0 || url.indexOf("&") > 0) {
            var h = url.split('?')[1].split('&'),
                    i = 0,
                    len = h.length,
                    item;
            for (; i < len; i++) {
                item = h[i].split('=');
                r[item[0]] = item[1];
            }
        }
        return r;
    }


</script>

/**
 * Created by dengxianzhi on 2017/1/18.
 */
$(function () {

    //组装菜单信息
    function renderMenus(data) {
        var $menuBox = $('.UI_menu_box');
        var dataList = data['object'];
        for (i = 0; i < dataList.length; i++) {
            var item = dataList[i];
            var name = item['name'];
            //var url = item['url'];
            //var id = item['id'];
            var menuHtml = '<div class="UI_menu_list">';

            //渲染二级菜单
            var subList = item['subMenus'];
            var sublen = subList.length;
            var isCur = '';
            //var curMenuid = $.cookie('cur_menu_id');
            var curMenuid = $('#cur_menu').val();
            //var subHtml = '<div class="list_text subMenuList " style="display: none">'
            var subHtml = '';
            if (subList != null && sublen > 0) {//存在二级菜单
                for (j = 0; j < sublen; j++) {
                    var subMenu = subList[j];
                    var subId = subMenu['id'];
                    var subUrl = subMenu['url'];
                    var subName = subMenu['name'];
                    if (subId == curMenuid) {//当前菜单
                        isCur = 'cur';
                        subHtml += '<a href="###" class="menu-item smenu '+isCur+'" data-id="' + subId + '" data-url="' + subUrl + '" title="' + subName + '">' + subName + '</a>';
                    }else{
                        subHtml += '<a href="###" class="menu-item smenu " data-id="' + subId + '" data-url="' + subUrl + '" title="' + subName + '">' + subName + '</a>';
                    }

                }
            }
            subHtml += '';
            if (isCur != '') {
                subHtml = '<div class="list_text subMenuList " >' + subHtml + '</div>';
            } else {
                subHtml = '<div class="list_text subMenuList " style="display: none" >' + subHtml + '</div>';
            }

            menuHtml += '<h4 class="menu-item fmenu ' + isCur + '" style="cursor:pointer">' + name + '</h4>';
            menuHtml += subHtml;
            menuHtml += '</div>';
            $menuBox.append(menuHtml);
        }

    }

    $.ajaxConnSend(this,'user/doGetMenusByUserId.infc', null, renderMenus);

    //一级菜单点击事件
    $(document).on('click', '.fmenu', function () {
        var $this = $(this);
        if ($this.hasClass('cur')) {
            $this.removeClass('cur');
            $this.next('.list_text').slideUp();
        } else {
            $('.fmenu').removeClass('cur');
            $this.addClass('cur');
            var id = $this.data('id');
            $('.list_text').slideUp();
            $this.next('.list_text').slideDown();
        }

    });

    //二级菜单点击事件
    $(document).on('click', '.smenu', function () {
        var $this = $(this);
        //var url = $this.data('url');
        var id = $this.data('id');
        //if(id == 'main'){
        //    window.location.href = $.customOpt.url + url;
        //}else{
            window.location.href = $.customOpt.url + 'goMenu.do?id='+id;
        //}
    });


});
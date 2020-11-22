/**
 * Created by dxzadmin on 2014/12/5.
 */
//全局系统配置常量
var BAS_URL = '/wyedu/';
$(function(){
    $('.cancelBtn','.message_box').click(function(){
        $(this).closest('.message_bg').hide();
    });
});

function getAccToken(){
    var accToken = $.cookie('accToken');
//        alert(accToken);
    if(accToken == null || accToken == undefined){
        accToken = $("#basic_accToken").attr("val");
    }
    return accToken;
}

var queryString=function(key){
    return (document.location.search.match(new RegExp("(?:^\\?|&)"+key+"=(.*?)(?=&|$)"))||['',null])[1];
};

function getDate(strDate) {
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
        function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
    return date;
}

function dateToEn(dt) {
    var m = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Spt", "Oct", "Nov", "Dec"];
    var w = ["Monday", "Tuseday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
    var d = ["st", "nd", "rd", "th"];
    mn = dt.getMonth();
    wn = dt.getDay();
    dn = dt.getDate();
    var dns;
    if (((dn) < 1) || ((dn) > 3)) {
        dns = d[3];
    }
    else {
        dns = d[(dn) - 1];
        if ((dn == 11) || (dn == 12)) {
            dns = d[3];
        }
    }
    return m[mn] + " " + dn + dns + " " + w[wn - 1] + " " + dt.getFullYear();
}

function gradeNumToGradeName(num) {
    var i=parseInt(num);
    var GradeName=["","一年级","二年级","三年级","四年级","五年级","六年级","初一年级","初二年级","初三年级","高一年级","高二年级","高三年级"];
    return GradeName[i];
}
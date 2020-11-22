/**
 * Created by dxzadmin on 2014/12/5.
 */
//$(function() {
//	/*============关闭弹出层==================*/
//	$(".cancel_btn,.closed_btn").click(function() {
//		$(".show_box").hide("slow");
//	});
//});



function changeDivShow(targetIDzip,totalNum) {
    for(i=1;i<(totalNum+1);i++){
        $("#divRowPart_"+i).hide();
    }
    $("#divRowPart"+targetIDzip).show();
	}
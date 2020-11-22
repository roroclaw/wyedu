$(function($){

  var defaultOption = {
    serviceUrl: null//获取数据的后台页面
    ,name:null
    ,type:'POST'
    ,onSelectedFun: null
  };

  /**
   * 构造下拉框
   *
   * @param options
   */
  $.fn.autoInput = function(options) {
    var opts = $.extend({}, defaultOption, options);
    var thisObj = $(this);
    var onSelectedFun = opts.onSelectedFun;
    opts.onSelect = onAutocompleteSelect;
    thisObj.autocomplete(opts);

    //添加隐藏提交值
    var hideObj = $('<input name="'+opts['name']+'" type="hidden"/>');
    thisObj.after(hideObj);

      //设置默认值
      var defaultText = thisObj.val();
      var defaultCode = thisObj.data('code');
      if(defaultText != null && defaultCode != null){
          hideObj.val(defaultCode);
          hideObj.data('text',defaultText);
      }

    function onAutocompleteSelect(item) {
      var value = item['value'];
      var data = item['data'];
      hideObj.val(data);
      hideObj.data('text',value);
      //执行自定义点击函数
      if(typeof onSelectedFun == 'function'){
          onSelectedFun(item);
      }
    }
      thisObj.blur(function(){
          //验证会否一致
          var showVal = thisObj.val();
          var hideText = hideObj.data('text');
          var hideVal = hideObj.val();

          if(showVal != hideText){
              if(hideVal != ''){
                  thisObj.val(hideText);
              }else{
                  thisObj.val('');
              }
          }
    });

  };


}(jQuery));
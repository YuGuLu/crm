<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>

//去后台验证登录相关的操作
        $.ajax({
            url : "",
            data : {

            },
            type : "",
            dataType : "json",
            success : function (data) {

                /*
                    data

                */

            }
        })

        //创建时间：当前系统时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人：当前登录用户
        String createBy = ((User)request.getSession().getAttribute("user")).getName();

        //时间控件
        $(".time").datetimepicker({
        minView: "month",
        language:  'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left"
        });

        //使用map打包数据
        Map<String,String> map = new HashMap<>();
        map.put("aname",aname);
        map.put("clueId",clueId);

        //自动补全插件
        $("#create-customerName").typeahead({
        source: function (query, process) {
        $.post(
        "workbench/transaction/getCustomerName.do",
        { "name" : query },
        function (data) {
        //alert(data);
        process(data);
        },
        "json"
        );
        },
        delay: 1500
        });
</body>
</html>

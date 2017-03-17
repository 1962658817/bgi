$(function(){  
    // 配置jqGrid组件
    $("#gridTable").jqGrid({  
        url: "jqGrid03.action",  
        datatype: "json",  
        mtype: "GET",  
        height: 350,  
        width: 600,  
        colModel: [  
              {name:"id",index:"id",label:"编码",width:40},    
              {name:"lastName",index:"lastName",label:"姓",width:80,sortable:false},  
              {name:"firstName",index:"firstName",label:"名",width:80,sortable:false},  
              {name:"email",index:"email",label:"电子邮箱",width:160,sortable:false},  
              {name:"telNo",index:"telNo",label:"电话",width:120,sortable:false}  
        ],  
        viewrecords: true,  
        rowNum: 15,  
        rowList: [15,50,100],  
        prmNames: {search: "search"},  
        jsonReader: {  
            root:"gridModel",  
            records: "record",  
            repeatitems : false  
        },  
        pager: "#gridPager",  
        caption: "联系人列表",  
        hidegrid: false,  
        shrikToFit: true  
    });  
      
    var alertText = "<div style="margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;" mce_style="margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;">请选择需要操作的数据行!</div>";  
      
    $("#gridTable").jqGrid("navGrid", "#gridPager", {  
        addfunc : openDialog4Adding,    // (1) 点击添加按钮，则调用openDialog4Adding方法
        editfunc : openDialog4Updating, // (2) 点击添加按钮，则调用openDialog4Updating方法
        delfunc : openDialog4Deleting,  // (3) 点击添加按钮，则调用openDialog4Deleting方法
        alerttext : alertText   // (4) 当未选中任何行而点击编辑、删除、查看按钮时，弹出的提示信息
    },{},{},{},{    // (5) 修改于查询相关的prmSearch参数
        caption: "查找",  
        Find: "Let's go!",  
        multipleSearch: true,  
        groupOps: [{ op: "AND", text: "全部" }],  
    },{});  
      
    // 配置对话框
    $("#consoleDlg").dialog({  
        autoOpen: false,      
        modal: true,    // 设置对话框为模态（modal）对话框
        resizable: true,      
        width: 480,  
        buttons: {  // 为对话框添加按钮
            "取消": function() {$("#consoleDlg").dialog("close")},  
            "创建": addContact,  
            "保存": updateContact,  
            "删除": deleteContact,  
        }
    });  
});  
var openDialog4Adding = function() {  
    var consoleDlg = $("#consoleDlg");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
    consoleDlg.find("input").removeAttr("disabled").val("");  
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('创建')").show();  
    consoleDlg.dialog("option", "title", "创建新联系人").dialog("open");  
};  
var openDialog4Updating = function(rowid) { // (6) 接受选中行的id为参数
    var consoleDlg = $("#consoleDlg");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
      
    consoleDlg.find("input").removeAttr("disabled");  
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('保存')").show();  
    consoleDlg.dialog("option", "title", "修改联系人信息");  
      
    loadSelectedRowData(rowid); // (7) 将选中行id传入loadSelectedRowData方法
}  
var openDialog4Deleting = function(rowid) { // (8) 接受选中行的id为参数
    var consoleDlg = $("#consoleDlg");  
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");  
      
    consoleDlg.find("input").attr("disabled", true);  
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();  
    dialogButtonPanel.find("button:contains('删除')").show();  
    consoleDlg.dialog("option", "title", "删除联系人");  
      
    loadSelectedRowData(rowid);  
}  
var loadSelectedRowData = function(selectedRowId) { // (9) 接受选中行的id为参数
    var params = {  
        "contact.id" : selectedRowId  
    };  
    var actionUrl = "viewContact.action";  
    // 从Server读取对应ID的JSON数据
    $.ajax( {  
        url : actionUrl,  
        data : params,  
        dataType : "json",  
        cache : false,  
        error : function(textStatus, errorThrown) {  
            alert("系统ajax交互错误: " + textStatus);  
        },  
        success : function(data, textStatus) {  
            // 如果读取结果成功，则将信息载入到对话框中
            var rowData = data.contact;  
            var consoleDlg = $("#consoleDlg");  
            consoleDlg.find("#selectId").val(rowData.id);  
            consoleDlg.find("#lastName").val(rowData.lastName);  
            consoleDlg.find("#firstName").val(rowData.firstName);  
            consoleDlg.find("#certificateNo").val(rowData.certificateNo);  
            consoleDlg.find("#email").val(rowData.email);  
            consoleDlg.find("#telNo").val(rowData.telNo);  
            consoleDlg.find("#address").val(rowData.address);  
            consoleDlg.find("#idCardNo").val(rowData.idCardNo);  
            consoleDlg.find("#nationality").val(rowData.nationality);  
              
            // 根据新载入的数据将表格中的对应数据行一并刷新一下
            var dataRow = {  
                    id : rowData.id,  
                    lastName : rowData.lastName,  
                    firstName : rowData.firstName,  
                    email : rowData.email,  
                    telNo : rowData.telNo  
                };  
              
            $("#gridTable").jqGrid("setRowData", data.contact.id, dataRow);  
                  
            // 打开对话框
            consoleDlg.dialog("open");  
        }  
    });  
};  
var addContact = function() {  
    var consoleDlg = $("#consoleDlg");  
          
    var pLastName = $.trim(consoleDlg.find("#lastName").val());  
    var pFirstName = $.trim(consoleDlg.find("#firstName").val());  
    var pEmail = $.trim(consoleDlg.find("#email").val());  
    var pTelNo = $.trim(consoleDlg.find("#telNo").val());  
    var pAddress = $.trim(consoleDlg.find("#address").val());  
    var pIdCardNo = $.trim(consoleDlg.find("#idCardNo").val());  
    var pNationality = $.trim(consoleDlg.find("#nationality").val());  
      
    var params = {  
        "contact.lastName" : pLastName,  
        "contact.firstName" : pFirstName,  
        "contact.email" : pEmail,  
        "contact.telNo" : pTelNo,  
        "contact.address" : pAddress,  
        "contact.idCardNo" : pIdCardNo,  
        "contact.nationality" : pNationality  
    };  
      
    var actionUrl = "createContact.action"  
      
    $.ajax( {  
        url : actionUrl,  
        data : params,  
        dataType : "json",  
        cache : false,  
        error : function(textStatus, errorThrown) {  
            alert("系统ajax交互错误: " + textStatus);  
        },  
        success : function(data, textStatus) {  
            if(data.ajaxResult == "success") {  
                var dataRow = {  
                    id : data.contact.id,   // 从Server端得到系统分配的id
                    lastName : pLastName,  
                    firstName : pFirstName,  
                    email : pEmail,  
                    telNo : pTelNo  
                };  
                  
                var srcrowid = $("#gridTable").jqGrid("getGridParam", "selrow");  
                  
                if(srcrowid) {  
                    $("#gridTable").jqGrid("addRowData", data.contact.id, dataRow, "before", srcrowid);  
                } else {  
                    $("#gridTable").jqGrid("addRowData", data.contact.id, dataRow, "first");  
                }  
                consoleDlg.dialog("close");  
                  
                alert("联系人添加操作成功!");  
                  
            } else {  
                alert("添加操作失败!");  
            }  
        }  
    });  
};  
var updateContact = function() {  
    var consoleDlg = $("#consoleDlg");  
      
    var pId = $.trim(consoleDlg.find("#selectId").val());  
    var pLastName = $.trim(consoleDlg.find("#lastName").val());  
    var pFirstName = $.trim(consoleDlg.find("#firstName").val());  
    var pEmail = $.trim(consoleDlg.find("#email").val());  
    var pTelNo = $.trim(consoleDlg.find("#telNo").val());  
    var pAddress = $.trim(consoleDlg.find("#address").val());  
    var pIdCardNo = $.trim(consoleDlg.find("#idCardNo").val());  
    var pNationality = $.trim(consoleDlg.find("#nationality").val());  
    var params = {  
        "contact.id" : pId,  
        "contact.lastName" : pLastName,  
        "contact.firstName" : pFirstName,  
        "contact.email" : pEmail,  
        "contact.telNo" : pTelNo,  
        "contact.address" : pAddress,  
        "contact.idCardNo" : pIdCardNo,  
        "contact.nationality" : pNationality  
    };  
    var actionUrl = "updateContact.action";  
    $.ajax( {  
        url : actionUrl,  
        data : params,  
        dataType : "json",  
        cache : false,  
        error : function(textStatus, errorThrown) {  
            alert("系统ajax交互错误: " + textStatus);  
        },  
        success : function(data, textStatus) {  
            if (data.ajaxResult == "success") {  
                var dataRow = {  
                    id : data.contact.id,  
                    lastName : pLastName,  
                    firstName : pFirstName,  
                    email : pEmail,  
                    telNo : pTelNo  
                };  
                $("#gridTable").jqGrid("setRowData", data.contact.id, dataRow, {color:"#FF0000"});  
                  
                alert("联系人信息更新成功!");  
                  
                consoleDlg.dialog("close");  
                  
            } else {  
                alert("修改操作失败!");  
            }  
        }  
    });  
};  
var deleteContact = function() {  
    var consoleDlg = $("#consoleDlg");  
      
    var pId = $.trim(consoleDlg.find("#selectId").val());  
    var params = {  
        "contact.id" : pId  
    };  
    var actionUrl = "deleteContact.action";  
    $.ajax({  
        url : actionUrl,  
        data : params,  
        dataType : "json",  
        cache : false,  
        error : function(textStatus, errorThrown) {  
            alert("系统ajax交互错误: " + textStatus);  
        },  
        success : function(data, textStatus) {  
            if (data.ajaxResult == "success") {  
                $("#gridTable").jqGrid("delRowData", pId);  
                  
                consoleDlg.dialog("close");  
                alert("联系人删除成功!");  
            } else {  
                alert("删除操作失败!");  
            }  
        }  
    });  
}; 












/*


<div id="consoleDlg">
<div id="formContainer">
	<form id="consoleForm">
	    <input type="hidden" id="selectId" />
		<table class="formTable">
			<tr>
				<th>
					角色名称：
				</th>
				<td>
					<input type="text" class="textField" id="name"
						name="name" />
				</td>
			</tr>
			<tr>
				<th >
					状态：
				</th>
				<td>
					<select class="textField" id="state" name="state"> 
						<option value="1">启用</option>
						<option value="0">禁用</option>
					</select>
				</td>
			</tr>
			
		</table>
	</form>
</div>
</div> */
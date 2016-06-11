<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link href="bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
	<script src="bootstrap3/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(function(){
		 $('#myModal').modal({
			 //初始化时不显示
			 show:false,
		    //背景不可点击
		     backdrop:false,
		     //handle: ".modal-header",   
		    // cursor: 'move',  
		   });
		 
		 $("#myModal").draggable({
			    handle: ".modal-header"
			});
		 
		//$("#city option:not(:first)").remove();
		//用来存放schools信息
		var jsonSchool=null;
		//获取下拉框中的city.刚进入页面就获取city信息.这样点下拉框就能显示出来
		var url="<%=basePath%>cityServlet";
		var args={"time":new Date(),"method":"getCity"};
		//返回的Json[{city_id=1, city_name=徐州}, {city_id=2, city_name=南京}, {city_id=3, city_name=北京}]
		$.getJSON(url,args,function(data){
			if(data.length==0){
				alert("无城市");
			}else{
				for(var i=0;i<data.length;i++){
					var cityId=data[i].cityId;
					var cityName=data[i].cityName;
					$("#city").append("<option value='"+ cityId+"'>"+ cityName+"</option>");
				}
			}
		});	
		//当city改变时.自动加载school下拉框信息
		$("#city").change(function(){
			//在上面添加节点时.把数据库中获取的id值作为value
			var cityId=$(this).val();
			$("#school option:not(:first)").remove();
			var url="<%=basePath%>cityServlet";
			var args={"time":new Date(),"method":"getSchool","cityId":cityId};
			$.getJSON(url,args,function(data){
				if(data.length==0)
					{alert("当前城市没有学校");}
				else
				{//转换为json对象
					//jsonSchool= eval("("+data+")");
					jsonSchool= data;
					//alert(data.schoolName);
					//alert(jsonSchool.schoolName);
					for(var i=0;i<data.length;i++){
						var schoolId=data[i].schoolId;
						var schoolName=data[i].schoolName;
						//alert(jsonSchool[i].schoolName);
						$("#school").append("<option value='"+schoolId+"'>"+schoolName+"</option>");
					}
				}
			});
		});
		
		//搜索
		$("#btn_searchAll").click(function(){
			
			if($("#city").val()==""){
				alert("请先选择具体城市再搜索");
			}
			else if($("#school").val()==""){
				alert("请先选择具体学校再搜索");
			}
			else{
				//每次搜索把不是标题的行清空
				$("tr:not(thead tr)").remove();
				var url="<%=basePath%>cityServlet";
				var cityId=$("#city").val();
				var schoolId=$("#school").val();
				var args={"time":new Date(),"method":"searchAll","cityId":cityId,"schoolId":schoolId};
				$.getJSON(url,args,function(data){
					//[{"personId":8,"personName":"qw","personAge":3,"schoolId":2},{"personId":9,"personName":"rtt","personAge":34,"schoolId":2},{"personId":10,"personName":"ada","personAge":21,"schoolId":2},{"personId":11,"personName":"wqe","personAge":12,"schoolId":2},{"personId":12,"personName":"werr","personAge":21,"schoolId":2}]
	
					for(var i=0;i<data.length;i++){
						var personId=data[i].personId;
						var personName=data[i].personName;
						var personAge=data[i].personAge;
						var schoolId=data[i].schoolId;
						var j=i+1;
						$("#stu").append("<tr id='hang"+personId+"'><td>"+j+"</td><td>"+personId+"</td><td>"+personName+"</td><td>"+personAge+"</td><td>"+schoolId +"<td></tr>");
						if(personId<=5){
							$("#hang"+personId).append("<td><button type='button' id='' class='btn btn-primary btn_tochange' data-toggle='modal' data-target='#myModal'>分配学校</button></td>");
						}else{
							$("#hang"+personId).append("<td><button type='button' class='btn btn-danger' data-toggle='modal' data-target='#myModal'>调换学校</button><button type='button' class='btn btn-success' data-toggle='modal' data-target='#myModal'>撤销学校</button></td>");
						}
					}
					
					//点按钮
					var id=-1;
					//弹出修改模态框的按钮
					$(".btn_tochange").click(function(){
						var parentTr=$(this).parent().parent();
						id=parentTr.find("td").eq(1).text();
						
						$(".modal-body").empty();
						$(".modal-body").append("请选择想要分配的学校:<br/>");
						$(".modal-body").append("<select id='choose_school' class='form-control'><option value=''>请选择...</option></select>");
						//模态框里的内容 不要用i,上面已经定义
						//alert(jsonSchool);
						for(var k=0;k<jsonSchool.length;k++){
							//alert(jsonSchool[k].schoolName);
							$("#choose_school").append("<option>"+jsonSchool[k].schoolName+"</option>");
						}
					});
					//模态框中提交修改
					$("#mySubmit").click(function(){
						var schoolId=-1;
						for(var k=0;k<jsonSchool.length;k++){
							if(jsonSchool[k].schoolName==$("#choose_school").val()){
								schoolId=jsonSchool[k].schoolId;
							}
						}

						var url="<%=basePath%>cityServlet";
						var args={"personId":id,"schoolId":schoolId,"method":"changeSchool","date":new Date()};
						//alert(args);
						$.getJSON(url,args,function(data){
							var result=data.result;
							if(result=="true"){
								$("#hang"+id).remove();
								$("tbody tr").each(function(i){								
									//alert($(this).html());
									$(this).find("td").eq(0).text(i+1);
								}); 
								alert("修改成功");	
							}else{
								alert("修改失败");
							};
						});
					});		
				});
			}
		});
	})
</script>
</head>
<body>
	<!-- 只要访问该页面.即会把请求forward到cityServlet.但是页面不 -->

	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="panel panel-primary">
				
				<!-- 模态框 -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="myModalLabel">标题</h4>
						      </div>
						      <div class="modal-body">
						        	内容
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
						        <button type="button" class="btn btn-primary" id="mySubmit">保存</button>
						      </div>
						    </div>
						  </div>
						</div>
				
				
				
					<div class="panel-heading">
						<div class="row" style="color: white">
							<!-- 下拉框 -->
							<div class="col-md-1" style="font-size: 20px">city:</div>
							<div class="col-md-3">

								<select id="city" class="form-control">
									<option value="">请选择...</option>
								</select>

							</div>
							<div class="col-md-1 " style="font-size: 20px">school:</div>
							<div class="col-md-3">

								<select id="school" class="form-control">
									<option value="">请选择...</option>
								</select>

							</div>
							<div class="col-md-4">
								<!-- 搜索 -->
								<button type="button" class="btn btn-success" id="btn_searchAll">搜索</button>

							</div>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-striped" id="stu">
							<thead><tr><td>序号</td><td>编号</td><td>姓名</td><td>年龄</td><td>学校编号</td><td>操作</td></tr></thead>
							<tr></tr>
						</table>
					</div>
				



				</div>



			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${detailVo.article.title}</title>
      <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/plugins/editer/styles/simditor.css">
    <link rel="stylesheet" href="/static/dist/css/style.css">
    <style>
        body {
            margin-top: 60px;
        }
        
    </style>
</head>
<body>
        <%@ include file="include/header.jsp" %>
              <!-- 导航栏结束 -->

              <!-- 文章列表开始 -->

              <div class="container">
                    <div class="box">
                            <ul class="breadcrumb" style="background-color: #fff;margin-bottom: 0px;">
                                <li><a href="/user/article/list">首页</a></li>
                                <li class="active">${detailVo.nodeName}</li>
                            </ul>
                            
                            <div class="topic-head">
                                <h3 class="title">${detailVo.article.title}</h3>
                                
                            </div>
                           
                            <div class="topic-body">
                                    ${detailVo.article.content}
                                </div>
                            <div class="topic-toolbar">
                                   
                                <ul class="list-inline text-muted">
                                    <li><i class="fa fa-eye"></i>  ${detailVo.article.scanNum}</li>
                                    <li><i class="fa fa-commenting"></i>  ${detailVo.article.replyNum}</li>
                                </ul>
                            </div>
                        </div>
                        <!--box end-->
                
                        <div class="box" style="margin-top:20px;">
                            <div class="talk-item muted" style="font-size: 12px">
                                ${detailVo.article.replyNum}个回复 | <c:choose>
                                	<c:when test="${detailVo.article.lastReplyTime == null || detailVo.article.replyNum == 0}">发表时间<span class="date">${detailVo.article.postTime}</span></c:when>
                                	<c:otherwise>直到<span id="lastReplyTime">${detailVo.article.lastReplyTime}</span></c:otherwise>
                                </c:choose>
                            </div>
                            <c:forEach items="${detailVo.replyList }" var="reply" varStatus="status">
	                            <c:if test="${reply.pid == 0}">
		                            <div class="talk-item">
		                                <table class="talk-table">
		                                    <tr>
		                                       
		                                        <td width="auto">
		                                            <a name="${reply.id}" style="font-size: 12px">${reply.userIp }</a> <span style="font-size: 12px" class="reply">${reply.createTime}</span>
		                                            <br>
		                                            <p style="font-size: 14px">${reply.content}</p>
		                                        </td>
		                                        <td width="70" align="right" style="font-size: 12px">
		                                            <a href="javascript:;" class="reReply" rel="" replyid="${reply.id}" title="回复"><i class="fa fa-reply"></i></a>&nbsp;
		                                            <span class="badge"></span>
		                                        </td>
		                                    </tr>
		                                </table>
		                                <div class="box" style="border-top:none">
											<c:forEach items="${detailVo.replyList }" var="reReply">
												<c:if test="${reReply.pid == reply.id}">
													<div class="talk-item">
														<table class="talk-table">
															<tr>
															   
																<td width="auto">
																	<a name="${reReply.id}" style="font-size: 12px">${reReply.userIp }</a> <span style="font-size: 12px" class="reply">${reReply.createTime}</span>
																	<br>
																	<p style="font-size: 14px">${reReply.content}</p>
																</td>
																
															</tr>
														</table>
													</div>
												</c:if>
											</c:forEach>
										</div>
		                            </div>
	                            </c:if>
                            </c:forEach>
                
                
                        </div>
                
                        <div class="box" style="margin:20px 0px;">
                            <a name="reply"></a>
                            <div class="talk-item muted" style="font-size: 12px"><i class="fa fa-plus"></i> 添加一条新回复</div>
                            <form action="/user/reply" method="post" id="replyForm" style="padding: 15px;margin-bottom:0px;">
                            	<input type="hidden" name="articleId" value="${detailVo.article.id}" />
                            	<input type="hidden" name="replyId" id="replyId" value=""/>
                                <textarea name="editor" id="editor"></textarea>
                            </form>
                            <div class="talk-item muted" style="text-align: right;font-size: 12px">
                                <span class="pull-left">请尽量让自己的回复能够对别人有帮助回复</span>
                                <button class="btn btn-primary" id="replyBtn">发布</button>
                            </div>
                        </div>
              </div>

             

              
    <!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
<script src="/static/dist/js/moment.js"></script>
<script src="/static/plugins/layer/layer.js"></script>   
<script src="/static/plugins/editer/scripts/module.min.js"></script>
<script src="/static/plugins/editer/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/editer/scripts/uploader.min.js"></script>
<script src="/static/plugins/editer/scripts/simditor.min.js"></script>
<script src="/static/dist/js/date.js"></script>
<script>
    $(function(){
        var editor = new Simditor({
            textarea: $('#editor'),
           	toolbar : ['bold', 'italic', 'underline', 'blockquote', 'code']
        });
        
        moment.locale("zh-cn");
        
        $("#lastReplyTime").text(moment($("#lastReplyTime").text()).format("YYYY年MM月DD日 HH时mm分ss秒"));
        
        $(".reply").text(function(){
        	return moment($(this).text()).fromNow();
        });
        
        $("#replyBtn").click(function(){
        	var content = editor.getValue();
        	if(content) {
	        	$("#replyForm").submit();
        		
        	} else {
        		layer.msg("请填写评论后提交");
        	}
        });
        
        $(".reReply").click(function(){
        	var replyId = $(this).attr("replyid");
        	var count = $(this).attr("rel");
        	$("#replyId").val(replyId);
        	window.location.href = "#reply";
        	editor.setValue("回复#" + count + ": &nbsp;");
        	editor.focus();
        });
        
        var badges = $(".badge");
        var reReply = $(".reReply");
        for(var i = 0; i < badges.length; i++) {
        	$(badges[i]).html(i + 1);
        	$(reReply[i]).attr("rel", i + 1);
        }
        
    });
</script>   
</body>
</html>
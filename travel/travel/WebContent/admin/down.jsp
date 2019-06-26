<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/admin/";%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="23" background="images/main_25.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="181" height="23" background="images/main_24.gif">&nbsp;</td>
      	<td><div align="right" class="STYLE1"><s:date name=""/><%=new Date()%></div></td>
        <td width="25"><img src="images/main_27.gif" width="25" height="23" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
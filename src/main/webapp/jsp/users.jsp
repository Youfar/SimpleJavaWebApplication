<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.*" %>
<%@ page import="java.util.List" %>

<% List<UserBean> users = (List<UserBean>) request.getAttribute("users"); %>

<!DOCTYPE html>
<html lang="ja">
<body>

<h2>Users</h2>
<p><a href="/add-user">Add New User</a></p>

<% for (UserBean u : users) {%>
id: <%=u.getId()%>
<br>
name: <%=u.getName()%>
<br><br>
<% } %>
</body>
</html>

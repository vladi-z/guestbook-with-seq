<%@ page import="ru.zmff.petprojects.guestbook.GuestBookMessage" %>
<%@ page import="java.util.LinkedList" %>
<%
    //noinspection unchecked
    LinkedList<GuestBookMessage> messages = (LinkedList<GuestBookMessage>) request.getAttribute("messages");
%>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%for (GuestBookMessage message: messages) {%>
<p>
    <b><%=message.getUsername()%>:</b>
    <%=message.getMessage()%>
</p>
<%}%>

<form method="post">
    <h1>Добавить запись</h1>
    <p>
        <label for="text">Сообщение</label>
    </p>
    <p>
        <textarea id="text" name="text"></textarea>
    </p>
    <p>
        <button type="submit">Сохранить</button>
    </p>
</form>

</body>
</html>

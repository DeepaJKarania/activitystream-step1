<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>
<%@ page session="false" isELIgnored="false"%>

<html>
<head>
<title>Activity Stream</title>


</head>
<body>

	<!-- create a form which will have textboxes for Sender Name and Message content along with a Send 
Submit button. Handle errors like empty fields -->

	<form action="sendMessage" method="post">
Sender Name : <input path="senderName" />
		<br>
Message : <input path="message" />
		<br>
		<button>Submit</button>



	</form>

	<!-- display all existing messages in a tabular structure with Sender Name, Posted Date and Message -->
MessageList
<c:if test="${not empty messageList}">

<table border="1">
  <thead>
    <tr>
      <th>Message Id</th>
      <th>Sender Name</th>
      <th>Message</th>
      <th>Posted Date</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${messageList}" var="msg"> 
<tr>
      
      <td>${msg.messageId}</td>
      <td>${msg.senderName}</td>
      <td>${msg.message}</td>
      <td>${msg.postedDate}</td>
    </tr>



</c:forEach>
</tbody>
</table>
</c:if>
 
 
</body>
</html>
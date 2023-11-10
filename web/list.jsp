<%@ page import="db.MemberService" %>
<%@ page import="java.util.List" %>
<%@ page import="db.Member" %><%--
  Created by IntelliJ IDEA.
  User: hagaj
  Date: 2023-11-09
  Time: 오후 7:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        table {
            width: 100%;
        }

        th, td {
            border: solid 1px #000;
        }
    </style>
</head>
<body>
<% MemberService memberService = new MemberService();
    List<Member> memberList = memberService.list();
%>
<h1> 회원 상세 </h1>
<table>
    <thead>
    <tr>
        <th>회원구분</th>
        <th>아이디</th>
        <th>비밀번호</th>
        <th>이름</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <%
            System.out.println("@@안!녕@@!!");

            for (Member member : memberList) {

        %>
        <td><%=member.getMemberType()%>
        </td>
        <td><a href="detail.jsp?memberType=<%=member.getMemberType() %>&userId=<%=member.getUserId()%>">
            <%=member.getUserId()%>
        </a>
        </td>
        <td><%=member.getPassword()%>
        </td>
        <td><%=member.getName()%>
        </td>
    </tr>
    <%
        }
    %>
    </tr>
    </tbody>
</table>
</body>
</html>

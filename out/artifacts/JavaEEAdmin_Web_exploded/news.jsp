<%@ page import="db.Items" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.News" %>
<html>
<head>
   <%@include file="head.jsp"%>
</head>
<body>
   <%@include file="navbar.jsp"%>
   <div class="container mt-5">
      <div class="row mt-5">
         <div class="col-12">
              <%
                  ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
                  if (news!=null){
                      for(News n : news){
              %>
             <div class="p-5 mb-4" style="background-color: #eaeeec;">
                 <h3><%=n.getTitle()%></h3>
                 <p><%=n.getContent()%></p>
                 <p>Posted by <strong><%=n.getUser().getFullName()%></strong>
                      At <strong><%=n.getPostDate()%></strong>
                 </p>
<%--                 <%--%>
<%--                     if (currentUser!=null--%>
<%--//                             && currentUser.getRole()==1--%>
<%--                     ){--%>
<%--                 %>--%>
                 <a href="/news-details?id=<%=n.getId()%>">
                     <h2>Details</h2>
                 </a>
<%--                 <%--%>
<%--                     }--%>
<%--                 %>--%>

             </div>
             <%
                      }
                  }
             %>
         </div>
      </div>
   </div>
</body>
</html>

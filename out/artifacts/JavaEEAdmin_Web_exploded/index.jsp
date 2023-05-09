<%@ page import="db.Items" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
   <%@include file="head.jsp"%>
</head>
<body>
   <%@include file="navbar.jsp"%>
   <div class="container mt-5">
      <div class="row mt-5">
         <div class="col-12 d-flex flex-row mb-3">
               <%
               ArrayList<Items> items = (ArrayList<Items>) request.getAttribute("items");
               if (items!=null){
               for (Items t : items){
               %>
                  <div class="card p-2 me-4" style="width: 18rem;">
                     <div class="card-body">
                        <h5 class="card-title"><%=t.getName()%></h5>
                        <p class="card-text"><%=t.getDescription()%></p>
                        <h5 class="card-title">$ <%=t.getPrice()%></h5>
                        <a class="btn btn-success btn-sm" href="#">Buy Now</a>
                     </div>
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

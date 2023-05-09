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
         <div class="col-6 mx-auto">
             <form action="/add-user" method="post">
                 <div class="row">
                     <div class="col-12">
                         <label>EMAIL: </label>
                     </div>
                 </div>
                 <div class="row mt-2">
                     <div class="col-12">
                         <input type="email" class="form-control" name="email" required placeholder="Insert email:">
                     </div>
                 </div>
                 <div class="row mt-3">
                     <div class="col-12">
                         <label>PASSWORD: </label>
                     </div>
                 </div>
                 <div class="row mt-2">
                     <div class="col-12">
                         <input type="password" class="form-control" name="password" required placeholder="Insert password:">
                     </div>
                 </div>
                 <div class="row mt-3">
                     <div class="col-12">
                         <label>FULL NAME: </label>
                     </div>
                 </div>
                 <div class="row mt-2">
                     <div class="col-12">
                         <input type="text" class="form-control" name="full_name" required placeholder="Insert full name:">
                     </div>
                 </div>
                 <div class="row mt-3">
                     <div class="col-12">
                         <label>ROLE: </label>
                     </div>
                 </div>
                 <div class="row mt-2">
                     <div class="col-12">
                         <input type="number" class="form-control" name="role_id" required placeholder="Insert role id:">
                     </div>
                 </div>
                 <div class="row mt-3">
                     <div class="col-12">
                         <button class="btn btn-success">REGISTER</button>
                     </div>
                 </div>
             </form>
         </div>
      </div>
   </div>
</body>
</html>

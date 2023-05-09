<%@ page import="db.Items" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
   <%@include file="head.jsp"%>
</head>
<body>
   <%@include file="navbar.jsp"%>
   <div class="container mt-3">
      <div class="row">
         <div class="col-12 mx-auto">
            <%
               User n = (User) request.getAttribute("user");
               if (n!=null){
            %>
            <div class="p-5 mb-4" style="background-color: #eaeeec;">
               <h3><%=n.getEmail()%></h3>
               <p><%=n.getFullName()%></p>
               <p><%=n.getPassword()%></p>
               <p><%=n.getRole()%></p>
               <p>
                  <!-- Button trigger modal -->
                  <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editUser">
                     EDIT
                  </button>

                  <!-- Modal -->
               <div class="modal fade" id="editUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div class="modal-dialog modal-lg">
                     <div class="modal-content">
                        <form action="/save-user" method="post">
                           <input type="hidden" name="id" value="<%=n.getId()%>">
                           <input type="hidden" name="id" value="<%=n.getEmail()%>">
                           <div class="modal-header">
                              <h1 class="modal-title fs-5" id="exampleModalLabel">Edit user</h1>
                              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                           </div>
                           <div class="modal-body">

                              <div class="row mt-3">
                                 <div class="col-12">
                                    <label>PASSWORD: </label>
                                 </div>
                              </div>
                              <div class="row mt-2">
                                 <div class="col-12">
                                    <input type="password" class="form-control" name="password" required placeholder="Insert password:" value="<%=n.getPassword()%>">
                                 </div>
                              </div>
                              <div class="row mt-3">
                                 <div class="col-12">
                                    <label>FULL NAME: </label>
                                 </div>
                              </div>
                              <div class="row mt-2">
                                 <div class="col-12">
                                    <input type="text" class="form-control" name="full_name" required placeholder="Insert full name:" value="<%=n.getFullName()%>">
                                 </div>
                              </div>
                              <div class="row mt-3">
                                 <div class="col-12">
                                    <label>ROLE: </label>
                                 </div>
                              </div>
                              <div class="row mt-2">
                                 <div class="col-12">
                                    <input type="number" class="form-control" name="role_id" required placeholder="Insert role:" value="<%=n.getRole()%>">
                                 </div>
                              </div>
                           </div>
                           <div class="modal-footer">
                              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                              <button class="btn btn-primary">Save changes</button>
                           </div>
                        </form>
                     </div>
                  </div>
               </div>
               </p>

            </div>
            <%
               }
            %>

         </div>
      </div>
   </div>
</body>
</html>

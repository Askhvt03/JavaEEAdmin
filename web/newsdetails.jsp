<%@ page import="db.Items" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.News" %>
<%@ page import="db.Comment" %>
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
                  News n = (News) request.getAttribute("news");

              %>
             <div class="p-5 mb-4" style="background-color: #eaeeec;">
                 <h3><%=n.getTitle()%></h3>
                 <p><%=n.getContent()%></p>
                 <p>Posted by <strong><%=n.getUser().getFullName()%></strong>
                      At <strong><%=n.getPostDate()%></strong>
                 </p>
                 <%
                     if (n!=null){
                 %>
                 <%
                     if (currentUser!=null){
                             if(currentUser.getRole()==1){
//                             && currentUser.getId()==n.getUser().getId()
                 %>
                 <p>
                     <!-- Button trigger modal -->
                     <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editNews">
                         EDIT
                     </button>
                     <!-- Button trigger modal -->
                     <button type="button" class="btn btn-danger btn-sm ms-2" data-bs-toggle="modal" data-bs-target="#deleteNews">
                         DELETE
                     </button>

                     <!-- Modal -->
                 <div class="modal fade" id="editNews" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                     <div class="modal-dialog modal-lg">
                         <div class="modal-content">
                             <form action="/save-news" method="post">
                                 <input type="hidden" name="id" value="<%=n.getId()%>">
                                 <div class="modal-header">
                                     <h1 class="modal-title fs-5" id="exampleModalLabel">Edit news</h1>
                                     <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                 </div>
                                 <div class="modal-body">
                                     <div class="row">
                                         <div class="col-12">
                                             <label>TITLE: </label>
                                         </div>
                                     </div>
                                     <div class="row mt-2">
                                         <div class="col-12">
                                             <input type="text" class="form-control" name="title" required placeholder="Insert title:" value="<%=n.getTitle()%>">
                                         </div>
                                     </div>
                                     <div class="row mt-3">
                                         <div class="col-12">
                                             <label>CONTENT: </label>
                                         </div>
                                     </div>
                                     <div class="row mt-2">
                                         <div class="col-12">
                                             <textarea class="form-control" name="content" placeholder="Insert content:" required rows="10 <%=n.getContent()%>"></textarea>
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

                     <!-- Modal -->
                 <div class="modal fade" id="deleteNews" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                     <div class="modal-dialog modal-lg">
                         <div class="modal-content">
                             <form action="/delete-news" method="post">
                                 <input type="hidden" name="id" value="<%=n.getId()%>">
                                 <div class="modal-header">
                                     <h1 class="modal-title fs-5" >Delete news</h1>
                                     <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                 </div>
                                 <div class="modal-body">
                                     <h5 class="text-center">Are you sure?</h5>
                                 </div>
                                 <div class="modal-footer">
                                     <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                     <button class="btn btn-danger">Yes</button>
                                 </div>
                             </form>
                         </div>
                     </div>
                 </div>
                 </p>


                 <%
                     }
                 %>
             </div>
             <div>
                 <form action="/add-comment" method="post">
                     <input type="hidden" name="news_id" value="<%=n.getId()%>">
                     <div class="row">
                        <textarea class="form-control" name="comment"></textarea>
                     </div>
                     <div class="row mt-2">
                         <div class="col-12">
                             <button class="btn btn-success btn-sm">ADD COMMENT</button>
                         </div>
                     </div>

                 </form>
                 <%
                     }
                 %>
             </div>
             <%
                  }
             %>
             <div class="row">
                 <div class="col-12">
                     <%
                         ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
                         if (comments != null){
                             for (Comment comment : comments){
                     %>
                     <div class="list-group mt-2">
                         <a href="JavaScript:void(0)" class="list-group-item list-group-item-action">
                             <div class="d-flex w-100 justify-content-between">
                                 <h5 class="mb-1"><%=comment.getUser().getFullName()%></h5>
                                 <small class="text-body-secondary"><%=comment.getPostDate()%></small>
                             </div>
                             <p class="mb-1"><%=comment.getComment()%></p>
                         </a>
                     </div>
                     <%
                             }
                         }
                     %>
                 </div>
             </div>
         </div>
      </div>
   </div>
</body>
</html>

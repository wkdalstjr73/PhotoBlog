<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, DBPKG.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Photo Blog</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
		crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
		crossorigin="anonymous">
</script>
<%-- <%
request.setCharacterEncoding("utf-8");

BoardDAO boardDAO = BoardDAO.getInstance();
List<BoardDTO> boardList = boardDAO.getTravelList();

%> --%>

<%
request.setCharacterEncoding("utf-8");

int paging = 1;
if(request.getParameter("page") != null){
    paging = Integer.parseInt(request.getParameter("page"));
}

int pageSize = 9; // 한 페이지 9장
int startRow = (paging - 1) * pageSize;

BoardDAO boardDAO = BoardDAO.getInstance();
List<BoardDTO> boardList = boardDAO.getTravelListPaging(startRow, pageSize);
%>
</head>
<body>
<div id="container">
	<%@include file="WEB-INF/include/header.jsp" %>
	<section>
		<div id="contents">
			<%@include file="WEB-INF/include/article.jspf" %>
			<%
				int totalCount = boardDAO.getTravelCount();
				int totalPage = (totalCount + pageSize - 1) / pageSize;
				
				int blockSize = 5; // 한 번에 보여줄 페이지 수
				
				int startPage = ((paging - 1) / blockSize) * blockSize + 1;
				int endPage = startPage + blockSize - 1;
				
				if(endPage > totalPage){
				    endPage = totalPage;
				}
			%>
			<div class="text-center mt-4">
				
				<!-- 첫 페이지 -->
				<a href="travel.jsp?page=<%=1 %>" class="btn btn-outline-dark m-1">◀◀</a>
				
				<%-- ◀ 이전 블록 --%>
				<% if(startPage > 1){ %>
				    <a href="travel.jsp?page=<%=startPage - 1%>" class="btn btn-outline-dark m-1">◀</a>
				<% } %>
				
				<%-- 페이지 번호들 --%>
				<%
				for(int i = startPage; i <= endPage; i++){
				%>
				    <a href="travel.jsp?page=<%=i%>" 
				       class="btn <%= (i == paging) ? "btn-dark" : "btn-outline-dark" %> m-1">
				       <%=i%>
				    </a>
				<%
				}
				%>
				
				<%-- ▶ 다음 블록 --%>
				<% if(endPage < totalPage){ %>
				    <a href="travel.jsp?page=<%=endPage + 1%>" class="btn btn-outline-dark m-1">▶</a>
				<% } %>
				
				<!-- 마지막 페이지 -->
				<a href="travel.jsp?page=<%=totalPage %>" class="btn btn-outline-dark m-1">▶▶</a>
				
			</div>
		</div>
	</section>
	<%@include file="WEB-INF/include/footer.jsp" %>
</div>
</body>
</html>
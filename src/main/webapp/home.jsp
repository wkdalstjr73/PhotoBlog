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
List<BoardDTO> boardList = boardDAO.getBoardList();

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
List<BoardDTO> boardList = boardDAO.getBoardListPaging(startRow, pageSize);
%>
</head>
<body>
<div id="container">
	<!-- WEB-INF/경로를 쓰는 이유는 사용자의 직접 접근을 방지하고 파일 노출이 방지가 되면서 보안이 좋기 때문 -->
	<%@ include file="WEB-INF/include/header.jsp" %>
	<section>
		<div id="contents">
			<!-- 파일 확장자명을 jspf로 하면
			이클립스 jsp 환경설정을 통해 독립적으로 브라우징 되지 않아
			뜨는 validation error를 끌 수 있음 -->
			<%@include file="WEB-INF/include/article.jspf" %>
			<%
				int totalCount = boardDAO.getBoardCount();
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
				<a href="home.jsp?page=<%=1 %>" class="btn btn-outline-dark m-1">◀◀</a>
				
				<%-- ◀ 이전 블록 --%>
				<% if(startPage > 1){ %>
				    <a href="home.jsp?page=<%=startPage - 1%>" class="btn btn-outline-dark m-1">◀</a>
				<% } %>
				
				<%-- 페이지 번호들 --%>
				<%
				for(int i = startPage; i <= endPage; i++){
				%>
				    <a href="home.jsp?page=<%=i%>" 
				       class="btn <%= (i == paging) ? "btn-dark" : "btn-outline-dark" %> m-1">
				       <%=i%>
				    </a>
				<%
				}
				%>
				
				<%-- ▶ 다음 블록 --%>
				<% if(endPage < totalPage){ %>
				    <a href="home.jsp?page=<%=endPage + 1%>" class="btn btn-outline-dark m-1">▶</a>
				<% } %>
				
				<!-- 마지막 페이지 -->
				<a href="home.jsp?page=<%=totalPage %>" class="btn btn-outline-dark m-1">▶▶</a>
				
			</div>
		</div>
	</section>
	<%@include file = "WEB-INF/include/footer.jsp" %>
</div>
</body>
</html>
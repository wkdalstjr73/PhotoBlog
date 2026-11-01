<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, DBPKG.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시글</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
		crossorigin="anonymous">
<style>
	body { margin: 0; padding: 0;}
	#container { width: 100%; margin: 0 auto;}
	/* header */
	header { width: 70%; height: 300px; background-color: black; 
			margin: 0 auto; border: 1px solid black;
	}
	header>h1 { color: white; line-height: 300px; text-align: center;
				font-style: italic; font-size: 3em; margin: 0;}
	/* nav */
	nav { width: 70%; height: 51px; margin: 0 auto; border-bottom: 1px solid black;}
	nav>ul { list-style-type: none; margin: 0; line-height: 51px; padding-left: 5%;}
	nav>ul>li { display: inline-block; width: 60px;}
	nav>ul>li>a { text-decoration: none; font-style: italic; color: black; }
	nav>ul>li>a:hover { text-decoration: underline; font-weight: bold;}
	/* section */
	section  { width: 70%; margin: 0 auto;}
	img {
		width: 20%;
		  -webkit-user-select: none;
		  -khtml-user-select: none;
		  -moz-user-select: none;
		  -o-user-select: none;
		  user-select: none;
		  -webkit-user-drag: none;
		  -khtml-user-drag: none;
		  -moz-user-drag: none;
		  -o-user-drag: none;
		  user-drag: none;
		}
	#contents { margin-top: 17px;}
	#contents a { text-decoration: none;}
	#contents a:hover { text-decoration: underline; font-weight: bold;}
	.card-img-top {
	  width: 100%;
	  height: 410px;   /* 원하는 고정 높이 */
	  object-fit: cover;  /* 비율 유지하면서 잘라냄 */
	}
	.card {
	  transition: transform 0.3s ease, box-shadow 0.3s ease;
	}
	
	.card:hover {
	  transform: scale(1.03); /* 1.03 = 3% 확대 */
	  box-shadow: 0 10px 20px rgba(0,0,0,0.2); /* 그림자 추가하면 더 자연스러움 */
	  z-index: 1; /* 겹칠 때 위로 올라오게 */
	}
	/* .card a {
	  text-decoration: none;
	  color: inherit;
	} */
	/* footer */
	footer  { width: 70%; height: 100px; margin: 17px auto; border-top: 1px solid black; clear: both;}
	footer>p { padding-left: 5%; padding-top: 17px;}
</style>
<style>
	#content { padding: 5%;}
	#content>h1  { }
	#content>div {margin-right: 5%; margin-bottom: 5%; width: 50%; border-right: 1px solid black; display: inline-block; float: left;}
	#content>div>img { width: 90%; height: auto; box-shadow: 0 0 15px rgba(0,0,0,0.5); border-radius: 5px;}
	#content>p { display:inline; font-size: larger; }
</style>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
		crossorigin="anonymous">
</script>
<%
request.setCharacterEncoding("utf-8");

// 메인페이지에서 데이터 받아오기(String 타입)
String p_no = request.getParameter("p_no");
BoardDAO boardDAO = BoardDAO.getInstance();
BoardDTO board = boardDAO.getBoard(p_no);

%>
</head>
<body>
<div id="container">
	<%@include file = "WEB-INF/include/header.jsp" %>
	<section>
		<div id="content">
			<div>
				<%
					/* 이미지 파일 확장자 관련 */
					String base = board.getImg();
					String[] exts = {
						".jpg", ".jpeg", ".png", ".JPG", ".JPEG", ".PNG",
						".webp", ".WEBP",
						".gif", ".GIF"
						};
					
					String filePath = "";
					for(String ext : exts){
	
					    String realPath = application.getRealPath("/photo/" + base + ext);
	
					    if(realPath != null){
					        java.io.File f = new java.io.File(realPath);
	
					        if(f.exists()){
					            filePath = "photo/" + base + ext;
					            break;
					        }
					    }
					}
				%>		
				<img src="<%=filePath %>" oncontextmenu="return false;">
			</div>
			<h1><%=board.getP_name() %></h1>
			<br>
			<p><%=board.getP_content() %></p>
		</div>
	</section>
	<%@include file="WEB-INF/include/footer.jsp" %>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글쓰기</title>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<!-- <link rel="stylesheet" href="/css/writeboard.css"> -->
</head>
<body>
<form name="writeForm" action="/board/show" method="post">
	<input type="hidden" name="userKey" value="<%=session.getAttribute("userKey")%>">
  <label>제목:<input style="min-width:150px" class="writeboardtitle" type="text" name="title"><br></label> 
  <textarea id="summernote" name="content"></textarea>
  <input type="submit" value="글등록">
</form>

<script type="text/javascript">
/* summernote에서 이미지 업로드시 실행할 함수 */
	function sendFile(file, editor, welEditable) {
    		 // 읽기
    	    var reader = new FileReader();
    	    reader.readAsDataURL(file);
    	    var dataURI ="";
    	    //로드 한 후
    	    reader.onload = function  () {
    	        var tempImage = new Image(); //drawImage 메서드에 넣기 위해 이미지 객체화
    	        tempImage.src = reader.result; //data-uri를 이미지 객체에 주입
    	        tempImage.onload = function() {
    	        	
    	            //리사이즈를 위해 캔버스 객체 생성
    	            var canvas = document.createElement('canvas');
    	            var canvasContext = canvas.getContext("2d");
    	            
    	            //캔버스 크기 설정
    	            canvas.width = 100; //가로 100px
    	            canvas.height = 100; //세로 100px
    	            
    	            //이미지를 캔버스에 그리기
    	            canvasContext.drawImage(this, 0, 0, 100, 100);
    	            //캔버스에 그린 이미지를 다시 data-uri 형태로 변환
    	            dataURI = canvas.toDataURL("image/jpeg");
    	            
    	            
    	            var ori = $('#summernote').val();
    	    	    ori+='<img src="'+dataURI+'">';    	    	   
    	    	   $('#summernote').summernote('code',ori);
    	    	    
    	        };
    	    }; 
} 	

	
	$(document).ready(function() {
        $('#summernote').summernote({ // summernote를 사용하기 위한 선언
            height: 400,
			callbacks: {
	        	onImageUpload: function(files, editor, welEditable) {
	        		for(var i = files.length -1; i>=0; i--) {
	        			sendFile(files[i], this, welEditable);
	        		}
	        	}
	        }
		});
	});

</script>

</body>
</html>
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
        $('input[class="writeboardtitle"]').keydown(function() {
        	  if (event.keyCode === 13) {
        	    event.preventDefault();
        	  };
        	});
       
        $('form').submit(function(){
        	if($('#summernote').val().trim()==""){
        		alert("내용을 작성해 주세요");
        		return false;        		
        	}
        	
        });
        document.querySelector(".gomypage").onclick = function(){
    		location.href="/member/mypageProc";
    	}
    	document.querySelector(".gologout").onclick = function(){
    		location.href="/member/logoutProc";
    	}

$(".writeboardtitle").keydown(function(){
	//console.dir($(this).val().length);
	if($(this).val().length>=40){
		alert("제목은 40자 를 초과해서 쓸수 없습니다");
	}
});



});

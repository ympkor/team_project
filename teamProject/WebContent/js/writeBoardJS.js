function sendFile(file, editor, welEditable) {
    		 // 읽기
    	    var reader = new FileReader();
    	    reader.readAsDataURL(file);
    	    var dataURI ="";
    	    //로드 한 후
    	    reader.onload = function(e) {
    	        var tempImage = new Image(); //drawImage 메서드에 넣기 위해 이미지 객체화
    	        tempImage.src = reader.result; //data-uri를 이미지 객체에 주입
    	        tempImage.onload = function() {
    	       
    	            //리사이즈를 위해 캔버스 객체 생성
    	            var canvas = document.createElement('canvas');
    	            var canvasContext = canvas.getContext("2d");
    	            
    	            var ori= e.target.result;
    	            var oriimage = new Image();
    	            oriimage.className ="imgitem";
    	            oriimage.src = ori;
    	            
    	            var max_size=500;
    	                	            
    	            var width = oriimage.width;
    	            var height = oriimage.height;
    	            if (width > height) {
    	                // 가로가 길 경우
    	                if (width > max_size) {
    	                  height *= max_size / width;
    	                  width = max_size;
    	                }
    	              } else {
    	                // 세로가 길 경우
    	                if (height > max_size) {
    	                  width *= max_size / height;
    	                  height = max_size;
    	                }
    	              }
    	              canvas.width = width;
    	              canvas.height = height;
    	            
    	            //이미지를 캔버스에 그리기
    	            canvasContext.drawImage(this, 0, 0, width, height);
    	            //캔버스에 그린 이미지를 다시 data-uri 형태로 변환
    	            dataURI = canvas.toDataURL("image/jpeg");
    	            
    	            
    	            var ori = $('#summernote').val();
    	    	    ori+='<img src="'+dataURI+'">';    	    	   
    	    	   $('#summernote').summernote('code',ori);
    	    	    
    	        };
    	    }; 
}//이미지리사이징
	
$(document).ready(function() {
        $('#summernote').summernote({ // summernote를 사용하기 위한 선언
        	toolbar: [
        		  ['style', ['style']],
        		  ['font', ['bold', 'underline', 'clear']],
        		  ['fontsize',['fontsize']],
        		  ['color', ['color']],
        		  ['para', ['ul', 'ol', 'paragraph']],
        		  ['insert', ['picture']],
        		],
        	height: 400,
			callbacks: {
	        	onImageUpload: function(files, editor, welEditable) {
	        		var contentval="'"+$('.note-editable').html()+"'";
	        		var imgtag='data:image/jpeg;base64';
	        		if(contentval.indexOf(imgtag)!= -1) {
	        			var results = contentval.match(/data:image/ig);
	        			if(results.length<=19){
	        				if(results.length+files.length>=21){
	        					alert("이미지는 20개까지 올릴수 있습니다.");	
	        				}else{
	        					for(var i = files.length -1; i>=0; i--) {
	        							sendFile(files[i], this, welEditable);
	        					}
	        				}
	        			}else{
	        				alert("이미지는 20개까지 올릴수 있습니다.");	        				
	        			}
	        		}else{
	        			if(files.length>=21){
	        				alert("이미지는 20개까지 올릴수 있습니다.");
	        			}else{
	        				for(var i = files.length -1; i>=0; i--) {
	        						sendFile(files[i], this, welEditable);
	        				}
	        			}
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
    		if($(this).val().length>=40){
    			alert("제목은 40자 를 초과해서 쓸수 없습니다");
    		}
    	});
});
function sendFile(file, editor, welEditable) {
    	   
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
    	            
    	            //업로드한 원본파일을 이미지 객체화 시킴
    	            var ori= e.target.result;
    	            var oriimage = new Image();
    	            oriimage.className ="imgitem";
    	            oriimage.src = ori;
    	            
    	            //원본파일의 가로 세로 길이를 변수에 저장
    	            var width = oriimage.width;
    	            var height = oriimage.height;
    	            
    	            //가로세로 최대 길이는 500
    	            var max_size=500;
    	            
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
    	            
    	            //리사이징될 이미지를 캔버스에 그리기
    	            canvasContext.drawImage(this, 0, 0, width, height);
    	            //캔버스에 그린 이미지를 다시 data-uri 형태로 변환
    	            dataURI = canvas.toDataURL("image/jpeg");
    	            
    	            //본문에 추가 시킴
    	            var ori = $('#summernote').val();
    	    	    ori+='<img src="'+dataURI+'">';    	    	   
    	    	   $('#summernote').summernote('code',ori);
    	    	    
    	        };
    	    }; 
}//이미지리사이징
	
$(document).ready(function() {
        $('#summernote').summernote({ // summernote를 사용하기 위한 선언
        	styleTags: [
        	    'h1', 'h2', 'h3', 'h4', 'h5', 'h6'
        		],
        	toolbar: [
        		  ['style', ['style']],
        		  ['font', ['bold', 'underline', 'clear']],
        		  ['fontsize',['fontsize']],
        		  ['color', ['color']],
        		  ['para', ['paragraph']],
        		  ['insert', ['picture']],
        		],
        	height: 400,
			callbacks: {
	        	onImageUpload: function(files, editor, welEditable) {
	        		var contentval="'"+$('.note-editable').html()+"'";
	        		var imgtag='data:image/jpeg;base64';
	        		if(contentval.indexOf(imgtag)!= -1) {//본문에 이미지가 이미 있을 경우 
	        			var results = contentval.match(/data:image/ig);
	        			if(results.length<=19){
	        				//원래있던 이미지와 업로드할 이미지가 합쳐서 21개가 되면 본문에 추가 안됨
	        				if(results.length+files.length>=21){
	        					alert("이미지는 20개까지 올릴수 있습니다.");	
	        				}else{
	        					for(var i = files.length -1; i>=0; i--) {
	        							sendFile(files[i], this, welEditable);
	        					}
	        				}
	        			}else{//본문에 이미지가 20개 있을 경우 그림을 더 추가 할 수 없음
	        				alert("이미지는 20개까지 올릴수 있습니다.");	        				
	        			}
	        		}else{//본문에 이미지가 없을 경우 
	        			if(files.length>=21){//한꺼번에 20개를 초과해서 올리면 본문에 추가할수 없음
	        				alert("이미지는 20개까지 올릴수 있습니다.");
	        			}else{//20개 이하의 이미지 업로드시 리사이징 시켜줌
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
        	if($('.writeboardtitle').val().trim()==""){
        		alert("제목을 작성해 주세요");
        		return false;        		
        	}
        	if($('.note-editable').text().trim()==""){
        		if($('.note-editable').html().indexOf('data:image/jpeg;base64')==-1){
        			alert("내용을 작성해 주세요");
        			return false;       		
        		}
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
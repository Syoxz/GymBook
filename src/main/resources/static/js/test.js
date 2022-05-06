	var i=0;
	$(document).ready(function(){
     $('#add_more').on('click', function(){
      var colorR = Math.floor((Math.random() * 256));
      var colorG = Math.floor((Math.random() * 256));
      var colorB = Math.floor((Math.random() * 256));
      i++;
      var html =
		  '<br/>'+
		  '<div id="append_no_'+i+'" class="animated bounceInLeft">'+
          '<div class="input-group mt-3">'+
		  '<div class="input-group-prepend">'+
		  '<span class="input-group-text br-15" style="color:rgb('+colorR+','+colorG+','+colorB+'">'+
		  '</span>'+
		  '</div>'+
		  '<input type="text" placeholder="Übung" th:field="*{uebung.name}" class="form-control"/>'+
		  '</div>'+
		  '<div class="input-group mt-3">'+
		  '<div class="input-group-prepend">'+
		  '<span class="input-group-text br-15" style="color:rgb('+colorR+','+colorG+','+colorB+'">'+
		  '</span>'+
		  '</div>'+
		  '<input type="number" step ="0.01" placeholder="Gewicht" th:field="*{uebung.gewicht}" class="form-control"/>'+
		  '</div>'+
		  '<div class="input-group mt-3">'+
		  '<div class="input-group-prepend">'+
		  '<span class="input-group-text br-15" style="color:rgb('+colorR+','+colorG+','+colorB+'">'+
		  '</span>'+
		  '</div>'+
		  '<input type="number" placeholder="Wiederholungen" th:field="*{uebung.wiederholung}" class="form-control"/>'+
		  '</div></div>';

	  $('#dynamic_container').append(html);
	  $('#remove_more').fadeIn(function(){
	  	 $(this).show();
	  });
     });

     $('#remove_more').on('click', function(){
         
         $('#append_no_'+i).removeClass('bounceInLeft').addClass('bounceOutRight')
            .fadeOut(function(){
            	$(this).remove();
            });
            i--;
            if(i==0){
            	$('#remove_more').fadeOut(function(){
            		$(this).hide()
            	});;
            }
   
     });
	});
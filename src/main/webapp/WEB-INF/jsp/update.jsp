<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<jsp:useBean id="random" class="java.util.Random" scope="application" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <title>Giphy - Manage your GIFs with ease</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,700" rel="stylesheet">

    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">

    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/ionicons.min.css">

    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">

    
    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
  </head>
  <body>

	<div id="colorlib-page">
		<a href="#" class="js-colorlib-nav-toggle colorlib-nav-toggle"><i></i></a>
		<aside id="colorlib-aside" role="complementary" class="js-fullheight text-center">
			<h1 id="colorlib-logo"><a href="index.html"><span class="flaticon-camera"></span>Giphy</a></h1>
			<nav id="colorlib-main-menu" role="navigation">
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="create">Upload</a></li>
				</ul>
			</nav>
		</aside> <!-- END COLORLIB-ASIDE -->
		<div id="colorlib-main">
			<section class="ftco-section bg-light ftco-bread">
				<div class="container">
					<div class="row no-gutters slider-text align-items-center">
	          <div class="col-md-12 ftco-animate">
	            <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span>Edit</span></p>
	            <h1 class="mb-3 bread">Update GIF Caption</h1>
	            <div class="container">
	            	<div class="row">
			            <div class="col-sm-6">
<div class="container">
  <form action="/updateCaptionForGif" method="post" enctype="multipart/form-data">
      <input type="hidden" id="gifId" name="gifId" value="${gif.gifId}" readonly>
    <div class="form-group">
      <label for="giphyId">Giphy Id:</label>
      <input type="text" class="form-control" id="giphyId" name="giphyId" value="${gif.giphyId}" readonly>
    </div>
    <div class="form-group">
      <label for="type">Type:</label>
      <input type="text" class="form-control" id="type" name="type"  value="${gif.type}" readonly>
    </div>
    <div class="form-group">
      <label for="gifUrl">Embed URL:</label>
      <input type="text" class="form-control" id="gifUrl" name="gifUrl"  value="${fn:escapeXml(gif.gifUrl)}" readonly>
    </div>
    <div class="form-group">
      <label for="gifDetails">URL:</label>
      <input type="text" class="form-control" id="gifDetails" name="gifDetails"  value="${gif.gifDetails}" readonly>
    </div>
    <div class="form-group">
      <label for="title">Title:</label>
      <input type="text" class="form-control" id="title"  value="${gif.title}" name="title" readonly>
    </div>
    <div class="form-group">
      <label for="caption">Caption:</label>
      <input type="text" class="form-control" id="caption"  value="${gif.caption}" name="caption" required>
    </div>
    <div class="form-group">
      <label for="userName">Username:</label>
      <input type="text" class="form-control" id="userName"  value="${gif.userName}" name="userName" readonly>
    </div>
    <div class="form-group">
      <label for="rating">Rating:</label>
      <input type="text" class="form-control" id="rating"  value="${gif.rating}" name="rating" readonly>
    </div>
    <div class="form-group">
      <label for="createdOn">Created on:</label>
      <input type="text" class="form-control" id="createdOn"  value="${gif.createdOn}" name="createdOn" readonly>
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
    <button type="button" class="btn btn-warning" onclick="window.location.href='delete?gifId=${gif.getGifId()}';">&nbsp;Delete&nbsp;</button>
  </form>
</div>
			            </div>
			            <div class="col-sm-6" ><img class="mt-4" src="${gif.gifDetails}"></div>
		            </div>
	            </div>
	          </div>
	        </div>
				</div>
			</section>
			<section class="ftco-section">
	    	<div class="container">
	    		<h2>Random Images</h2>
	    		<div class="row">
	    				<c:set var = "maxSize" scope = "session" value = "${gifs.size()-4 >= 0 ? gifs.size()-4 : 0}"/>
	    				<c:set var = "index" scope = "session" value = "${maxSize == 0 ? 0 : random.nextInt(maxSize)}"/>
    			        <c:forEach var = "i" begin = "0" end = "${gifs.size() >=4 ? 3 : gifs.size()-1 }">
    			        	<c:set var = "g" scope = "session" value = "${(index+i) < 0 ? 0 : gifs.get(index+i)}"/>
							<div class="col-md-6 col-lg-3 ftco-animate" onclick="event.preventDefault();window.location.href='update?gifId=${g.getGifId()}';" style="cursor:pointer">
								<div class="staff">
									<div class="img" style="background-image: url(${g.gifDetails});"></div>
									<div class="text pt-4">
										<h3><a href="#">${g.title}</a></h3>
										<span class="position mb-2">${g.userName}</span>
										<p>${g.caption}</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
	    	</div>
	    </section>
	    <footer class="ftco-footer ftco-bg-dark ftco-section">
	      <div class="container px-md-5">
	        <div class="row">
	          <div class="col-md-12">

	            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
	  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
	  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
	          </div>
	        </div>
	      </div>
	    </footer>
		</div><!-- END COLORLIB-MAIN -->
	</div><!-- END COLORLIB-PAGE -->

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/bootstrap-datepicker.js"></script>
  <script src="js/jquery.timepicker.min.js"></script>
  <script src="js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>
  <script src="js/main.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>

  <script>
  toastr.options = {
		  "closeButton": true,
		  "debug": false,
		  "newestOnTop": false,
		  "progressBar": false,
		  "positionClass": "toast-top-right",
		  "preventDuplicates": false,
		  "onclick": null,
		  "showDuration": "300",
		  "hideDuration": "1000",
		  "timeOut": "10000",
		  "extendedTimeOut": "1000",
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
	};
  </script>
    <c:choose>
	  <c:when test="${isUpdated == true}">
	     <script>
		     toastr["success"]("Caption of this GIF successfully updated", "Success")
		 </script>
	  </c:when>
	  <c:when test="${isUpdated == false}">
	     <script>
		     toastr["error"]("Problem occurred while updating GIF", "Error")
		 </script>
	  </c:when>
	</c:choose> 
    <c:choose>
	  <c:when test="${isCreated == true}">
	     <script>
		     toastr["success"]("GIF successfully created", "Success")
		 </script>
	  </c:when>
	  <c:when test="${isCreated == false}">
	     <script>
		     toastr["error"]("Problem encountered while saving GIF", "Error")
		 </script>
	  </c:when>
	</c:choose>    
  </body>
</html>
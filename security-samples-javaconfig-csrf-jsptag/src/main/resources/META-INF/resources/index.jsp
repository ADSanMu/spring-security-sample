<!DOCTYPE html>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>CSRF Protected JavaScript Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<sec:csrfMetaTags />
<script type="text/javascript" src="webjars/jquery/3.1.0/jquery.js"></script>
<script type="text/javascript">
   var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
   var csrfHeader = $("meta[name='_csrf_header']").attr("content");
   var csrfToken = $("meta[name='_csrf']").attr("content");
   console.log(csrfParameter);
   console.log(csrfHeader);
   console.log(csrfToken);
//    // using XMLHttpRequest directly to send an x-www-form-urlencoded request
//    var ajax = new XMLHttpRequest();
//    ajax.open("POST", "http://www.example.org/do/something", true);
//    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded data");
//    ajax.send(csrfParameter + "=" + csrfToken + "&name=John&...");
//    // using XMLHttpRequest directly to send a non-x-www-form-urlencoded request
//    var ajax = new XMLHttpRequest();
//    ajax.open("POST", "http://www.example.org/do/something", true);
//    ajax.setRequestHeader(csrfHeader, csrfToken);
//    ajax.send("...");
//    // using JQuery to send an x-www-form-urlencoded request
//    var data = {};
//    data[csrfParameter] = csrfToken;
//    data["name"] = "John";
// ... $.ajax({
//         url: "http://www.example.org/do/something",
//         type: "POST",
//         data: data,
//     });
//    // using JQuery to send a non-x-www-form-urlencoded request
//    var headers = {};
//    headers[csrfHeader] = csrfToken;
//    $.ajax({
//         url: "http://www.example.org/do/something",
//         type: "POST",
//         headers: headers,
//    });
  </script>
</head>
<body>
	hello! wait for 2 minutes and refresh the browser,you will still be here.
</body>
</html>

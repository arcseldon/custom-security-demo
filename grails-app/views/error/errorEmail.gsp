<%@ page contentType="text/html"%>
<html>
<head>
<title>Bid Query Error Notification</title>
<style type="text/css">
#A
body {
	font-family: "Trebuchet MS"
}
</style>
</head>
<body>
	<h4>Bid Query Error occurred at: ${datetime}</h4>
	<p>${message}</p>
	<p>Please see the Bid Query application log files for further details.</p>
	<p><small>*Please do not reply to this message, automatically generated from the Bid Query Application.</small></p>
</body>
</html>
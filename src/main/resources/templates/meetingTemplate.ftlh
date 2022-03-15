<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Spring Boot Email using FreeMarker</title>
</head>
<body>
	<p>${creator} ha <b>${azione}</b> un evento <b>${eventType}</b> </p>
	<p>Da <b>${dataInizio}</b> a <b>${dataFine}</b></p>
	<p> I partecipanti sono:</p>
	<ol>
		<#list partecipanti as partecipante>		
			<li>${partecipante}</li>			
		</#list>		
	</ol>	
	<p> <#if presenza == true>
		L'evento è programmato in presenza 
		<#else>
		L'evento sarà online
		</#if>
	</p>
	
	<img src= "https://media-exp1.licdn.com/dms/image/C4D0BAQEajLUmKqd6dQ/company-logo_200_200/0/1537444878799?e=1655337600&v=beta&t=61OF0UT25K5sV1FdKCIVQUTGV-23W0ZcYyQkbXFDemM" width="200px"/>
</body>
</html>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Spring Boot Email using FreeMarker</title>
</head>
<body>
	
	 <#if approved == true>
		<p> ${utente} <b>${azione}</b> la richiesta per l'approvazione di ferie/permessi. 
	<#else>
		<p>${utente} <b>${azione}</b> la richiesta per l'approvazione di ferie/permessi in quanto sono da rivedere le seguenti date: </p>
	
		<table>
			<tr>
				<th>Data</th>  <th>TipoEvento</th>
			</tr>
			<#list events as event>
			<tr>
				<td>${event.data}</td> <td>${event.tipo}</td>
			<tr>
			</#list>
		</table>
	 </#if>
		
	

	
</body>
</html>
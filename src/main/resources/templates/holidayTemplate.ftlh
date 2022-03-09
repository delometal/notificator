<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Spring Boot Email using FreeMarker</title>
</head>
<body>

	<p>${utente} ha <b>${azione}</b> una richiesta per l'approvazione di feie/permessi per le seguenti date: </p>
	
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
	
</body>
</html>
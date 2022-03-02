<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Spring Boot Email using FreeMarker</title>
</head>
<body>
	
	<p> Benvenuto in Perigea ${utente}, qui di seguito sono elencate le tue <b>credenziali iniziali</b> e il <b>token</b> di attivazione per la modifica della <b>password</b> </p>
	<ol>
		<li> Username: ${username} </li>
	 	<li> Password: ${password} </li>
	 	<li> Token attivazione: ${token} </li>
	</ol>
	
	<p> <b>ATTENZIONE</B> la password e il token forniti sono temporanei e sono validi fino alla seguente data: ${scadenza} </p>
	<p> Per l'attivazione delle credenziali definitive prosegui nel <b>link</b> che segue </p>
	<p> ${link} </p> 
	 	
	
</body>
</html>
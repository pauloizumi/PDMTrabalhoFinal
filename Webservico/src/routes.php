<?php
// Routes

$app->get('/colecao/list', function ($request, $response) {

	$db = $this->db;
	foreach($db->query('SELECT * FROM colecao') as $row){
		$return[] = $row;
	};

	return $response->withJson($return);
});


$app->get('/volume/list', function ($request, $response) {

	$db = $this->db;
	foreach($db->query('SELECT * FROM volume') as $row){
		$return[] = $row;
	};

	return $response->withJson($return);
});



$app->post('/volume/new', function ($request, $response) {

	$db = $this->db;
	$sth = $db->prepare("INSERT INTO volume (volume,id_colecao) VALUES (:volume, :id_colecao)");

	$dados = $request->getParsedBody();
	
	$insertVolume["volume"] = $dados["volume"];
	$insertVolume["id_colecao"] = $dados["id_colecao"];
	
	
	$sth->execute($insertVolume);
	
	return $response->withJson($insertVolume); 

});


$app->post('/colecao/new', function ($request, $response) {
	
	$base_photo_url = __DIR__ . DIRECTORY_SEPARATOR . '..' . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . 'images' . DIRECTORY_SEPARATOR;

	$dados = $request->getParsedBody();

	$foto = $dados['foto'];

	$db = $this->db;
	$sth = $db->prepare("INSERT INTO colecao(nome, categoria) VALUES (:nome, :categoria)");	

	$listColecao    = json_decode($dados['colecao'], true);

	$insertColecao["nome"]= $listColecao["nome"];
	$insertColecao["categoria"]= $listColecao["categoria"];
	
	$sth->execute($insertColecao);

	$lastInsertId = $db->lastInsertId('colecao_id_seq');
 	$insertColecao['id'] = $lastInsertId;
	 	
 	$imgFileName = "{$base_photo_url}{$lastInsertId}.png";
	
 	$file = fopen($imgFileName, "w");
 	fwrite($file, $foto);
	fclose($file);

 	return $response->withJson($insertColecao);
});

$app->get('/volume/list', function ($request, $response) {

	$db = $this->db;
	foreach($db->query('SELECT * FROM volume') as $row){
		$return[] = $row;
	};

	return $response->withJson($return);
});




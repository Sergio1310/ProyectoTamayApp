<?php


include 'conexion.php';

$stmt =$conexion->prepare("select id, nombre, descripcion, precio, imagen from productos where id_tipo = 2");
$stmt->execute();
$stmt->bind_result($id, $nombre, $descripcion, $precio, $imagen);
$comidas=array();
while($stmt->fetch()){
    $temp=array();
    $temp['id']=$id;
    $temp['nombre']=$nombre;
    $temp['descripcion']=$descripcion;
    $temp['precio']=$precio;
    $temp['imagen']=$imagen;
    array_push($comidas, $temp);
}
echo json_encode($comidas);

/*$consulta = "select nombre, descripcion, precio, imagen from productos";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
	$producto[] = array_map('utf8_encode', $fila);
}

echo json_encode($producto);
$resultado -> close();*/

?>
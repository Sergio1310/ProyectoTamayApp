<?php 
	
	include 'conexion.php';

	$stmt =$conexion->prepare("SELECT estatu.id, carrito.id_usuarios, usuarios.nombre, carrito.id_producto, productos.nombre, productos.precio, carrito.fecha FROM estatu INNER JOIN carrito ON estatu.id_carrito = carrito.id INNER JOIN productos ON carrito.id_producto = productos.id INNER JOIN usuarios ON carrito.id_usuarios = usuarios.id WHERE estatu.estado = 1");
	$stmt->execute();
	$stmt->bind_result($id_estatus, $id_usuario, $nombre_usuario, $id_producto, $nombre_producto, $precio_producto, $fecha_carrito);
	$pedido = array();
	while($stmt->fetch()){
	    $temp=array();
	    $temp['id_estatus'] = $id_estatus;
	    $temp['id_usuario'] = $id_usuario;
	    $temp['nombre_usuario'] = $nombre_usuario;
	    $temp['id_producto'] = $id_producto;
	    $temp['nombre_producto'] = $nombre_producto;
	    $temp['precio_producto'] = $precio_producto;
	    $temp['fecha_carrito'] = $fecha_carrito;
	    array_push($pedido, $temp);
	}
	echo json_encode($pedido);

?>

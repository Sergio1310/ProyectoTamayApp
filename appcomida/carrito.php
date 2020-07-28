<?php

include 'conexion.php';
$iddproducto=$_GET['producto'];
$idcliente=$_GET['cliente'];
$cantidad=$_GET['cantidad'];
$fecha=$_GET['fecha'];

$consulta="insert into carrito(id_usuarios,id_productos,cantidad,fecha) values('".$idcliente."','".$iddproducto."','".$cantidad."','".$fecha."')";
mysqli_query($conexion,$consulta) or die (mysqli_error($conexionn));
mysqli_close($conexion);

?>
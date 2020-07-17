<?php

include 'conexion.php';
$codigo=$_POST['codigo'];
$producto=$_POST['producto'];
$precio=$_POST['precio'];
$fabricante=$_POST['fabricante'];
$id_tipo=$_POST['id_tipo'];
$id_cliente=$_POST['id_cliente'];
$id_estado=$_POST['id_estado'];

$consulta="insert into producto values('".$codigo."','".$producto."','".$precio."','".$fabricante."','".$id_tipo."','".$id_cliente."','".$id_estado."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>
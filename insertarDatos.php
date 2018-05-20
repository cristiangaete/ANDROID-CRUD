<?php

$monto = $_REQUEST['monto'];
$descrip =$_REQUEST['descrip'];
$id_item = $_REQUEST['id_item'];
$fecha = $_REQUEST['fecha'];

$conexion = new mysqli("localhost","id3769535_fort","00000","id3769535_php");

$consulta = "insert into tbl_detalle(monto,id_item,descrip,fecha) values ($monto,$id_item,'$descrip','$fecha')";

$conexion ->query($consulta);
$resultado = $conexion->affected_rows;
return $resultado;
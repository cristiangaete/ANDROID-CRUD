<?php
$conexion = new mysqli("localhost","usuario","contraseña","nombre base de datos");
$consulta = "select id_detalle, monto, descrip, fecha, id_item from tbl_detalle";
$datos = array();
$res = $conexion->query($consulta);

while ($fila = $res->fetch_array()){
    $datos[] = $fila;
}
print json_encode($datos);



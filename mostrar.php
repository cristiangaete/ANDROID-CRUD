<?php
$conexion = new mysqli("localhost","id3769535_fort","00000","id3769535_php");
$consulta = "select id_detalle, monto, descrip, fecha, id_item from tbl_detalle";
$datos = array();
$res = $conexion->query($consulta);

while ($fila = $res->fetch_array()){
    $datos[] = $fila;
}
print json_encode($datos);



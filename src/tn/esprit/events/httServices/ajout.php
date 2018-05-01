<?php 

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "bonplan";
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$nom=$_GET['nom'];
$adresse=$_GET['adresse'];
$sql = "INSERT INTO evenements (nom,adresse)
VALUES ( '$nom','$adresse')" ;



?>

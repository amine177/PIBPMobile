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

$return_arr = array();
$sql = "SELECT * FROM evenements";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       $row_array['id'] = $row['id'];
       $row_array['nom'] = $row['nom'];
       $row_array['description'] = $row['description'];
	   $row_array['adresse'] = $row['adresse'];
    array_push($return_arr,$row_array);
    }
} 
echo json_encode($return_arr);





?>
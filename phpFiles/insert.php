<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "quizapp";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

if($_SERVER['REQUEST_METHOD']=='POST')
{

    $classification = $_POST['classification'];
 $grade = $_POST['grade'];
 
 

$sql = "INSERT INTO score_table (classification,grade) VALUES('$classification',$grade)";

if ($conn->query($sql) === TRUE) {
  echo "Socore has been recorded";
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
}
else {
    # code...
}

?>
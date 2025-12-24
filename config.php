<?php
// config.php
// Database configuration - change if needed

define('DB_HOST', '127.0.0.1');
define('DB_NAME', 'sss_db');
define('DB_USER', 'root');
define('DB_PASS', ''); // XAMPP default: empty

// Create mysqli connection
function db_connect() {
    $mysqli = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
    if ($mysqli->connect_errno) {
        die("DB Connection failed: " . $mysqli->connect_error);
    }
    // set charset
    $mysqli->set_charset("utf8mb4");
    return $mysqli;
}
?>
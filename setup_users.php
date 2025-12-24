<?php
// setup_users.php
// Run this once to create initial users with hashed passwords.
// After running, delete or protect this file.

require_once 'config.php';

$mysqli = db_connect();

// Helper to add user if not exists
function add_user($mysqli, $username, $password_plain, $role, $name = null, $flat_id = null) {
    $stmt = $mysqli->prepare("SELECT id FROM users WHERE username = ?");
    $stmt->bind_param('s', $username);
    $stmt->execute();
    $stmt->store_result();
    if ($stmt->num_rows > 0) {
        $stmt->close();
        return false;
    }
    $stmt->close();

    $hash = password_hash($password_plain, PASSWORD_DEFAULT);
    $ins = $mysqli->prepare("INSERT INTO users (username, password, role, name, flat_id) VALUES (?, ?, ?, ?, ?)");
    $ins->bind_param('ssssi', $username, $hash, $role, $name, $flat_id);
    $ins->execute();
    $ins->close();
    return true;
}

// Create admin, supervisor, resident
add_user($mysqli, 'admin', 'admin123', 'admin', 'Admin User', null);
add_user($mysqli, 'supervisor', 'super123', 'supervisor', 'Supervisor', null);

// find a flat to assign to resident
$resFlat = null;
$r = $mysqli->query("SELECT id FROM flats LIMIT 1");
if ($r && $row = $r->fetch_assoc()) $resFlat = $row['id'];

add_user($mysqli, 'resident1', 'res123', 'resident', 'Resident One', $resFlat);

echo "Setup complete. Created admin/supervisor/resident accounts (if they did not already exist).\n";
echo "Admin: admin / admin123\nSupervisor: supervisor / super123\nResident: resident1 / res123\n";
echo "Please delete or secure setup_users.php after use.\n";
?>
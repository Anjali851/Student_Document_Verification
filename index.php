<?php
// index.php - login page
require_once 'config.php';
require_once 'functions.php';

$mysqli = db_connect();
$error = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = trim($_POST['username'] ?? '');
    $password = $_POST['password'] ?? '';

    $stmt = $mysqli->prepare("SELECT id, username, password, role, name, flat_id FROM users WHERE username = ?");
    $stmt->bind_param('s', $username);
    $stmt->execute();
    $res = $stmt->get_result();
    if ($user = $res->fetch_assoc()) {
        if (password_verify($password, $user['password'])) {
            // set session
            $_SESSION['user'] = [
                'id' => $user['id'],
                'username' => $user['username'],
                'role' => $user['role'],
                'name' => $user['name'],
                'flat_id' => $user['flat_id']
            ];
            // redirect based on role
            if ($user['role'] === 'admin') header('Location: admin_dashboard.php');
            elseif ($user['role'] === 'supervisor') header('Location: supervisor_dashboard.php');
            else header('Location: resident_dashboard.php');
            exit();
        } else {
            $error = 'Invalid credentials';
        }
    } else {
        $error = 'Invalid credentials';
    }
    $stmt->close();
}
?>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>SSS - Login</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>
<div class="container">
    <h2>Society Security Solution - Login</h2>
    <?php if ($error): ?><div class="error"><?=htmlspecialchars($error)?></div><?php endif; ?>
    <?php if ($msg = flash_get()): ?><div class="success"><?=htmlspecialchars($msg)?></div><?php endif; ?>
    <form method="post" action="index.php">
        <label>Username</label>
        <input name="username" required>
        <label>Password</label>
        <input name="password" type="password" required>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>
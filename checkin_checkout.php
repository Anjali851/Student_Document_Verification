<?php
// checkin_checkout.php - check-in/out using security code (for regular visitors)
require_once 'functions.php';
require_login();
$mysqli = db_connect();

$action = $_POST['action'] ?? '';
$code = trim($_POST['security_code'] ?? '');

if ($code === '') {
    flash_set('Enter code');
    header('Location: resident_dashboard.php');
    exit();
}

// find the visitor
$stmt = $mysqli->prepare("SELECT id, flat_id FROM visitors WHERE security_code = ? AND type = 'regular' LIMIT 1");
$stmt->bind_param('s', $code);
$stmt->execute();
$res = $stmt->get_result();
if (!$visitor = $res->fetch_assoc()) {
    flash_set('Invalid code');
    header('Location: resident_dashboard.php');
    exit();
}
$stmt->close();

// only allow check-in/out for residents of assigned flat or supervisor/admin (we keep it simple)
$user = $_SESSION['user'];
if ($user['role'] === 'resident' && $user['flat_id'] != $visitor['flat_id']) {
    flash_set('You are not authorized for this code');
    header('Location: resident_dashboard.php');
    exit();
}

if ($action === 'checkin') {
    $stmt = $mysqli->prepare("UPDATE visitors SET checkin_time = NOW(), status='approved' WHERE id = ?");
    $stmt->bind_param('i', $visitor['id']);
    $stmt->execute();
    $stmt->close();
    flash_set('Check-in recorded');
} elseif ($action === 'checkout') {
    $stmt = $mysqli->prepare("UPDATE visitors SET checkout_time = NOW() WHERE id = ?");
    $stmt->bind_param('i', $visitor['id']);
    $stmt->execute();
    $stmt->close();
    flash_set('Check-out recorded');
}
header('Location: resident_dashboard.php');
exit();
?>
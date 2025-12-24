<?php
// staff.php - supervisor manages staff
require_once 'functions.php';
require_login();
if (!is_role('supervisor')) { flash_set('Access denied'); header('Location: index.php'); exit(); }

$mysqli = db_connect();
$action = $_POST['action'] ?? '';

if ($action === 'add') {
    $name = trim($_POST['name']);
    $role = trim($_POST['role']);
    $phone = trim($_POST['phone']);
    $stmt = $mysqli->prepare("INSERT INTO staff (name, role, phone) VALUES (?, ?, ?)");
    $stmt->bind_param('sss', $name, $role, $phone);
    $stmt->execute();
    $stmt->close();
    flash_set('Staff added');
    header('Location: supervisor_dashboard.php');
    exit();
}

header('Location: supervisor_dashboard.php');
exit();
?>
<?php
// approve_visitor.php - resident approves or rejects normal visitors
require_once 'functions.php';
require_login();
if (!is_role('resident')) { flash_set('Access denied'); header('Location: index.php'); exit(); }

$mysqli = db_connect();
$action = $_POST['action'] ?? '';
$visitor_id = intval($_POST['visitor_id'] ?? 0);

// verify visitor belongs to resident's flat
$flat_id = $_SESSION['user']['flat_id'];
$stmt = $mysqli->prepare("SELECT id FROM visitors WHERE id = ? AND flat_id = ?");
$stmt->bind_param('ii', $visitor_id, $flat_id);
$stmt->execute();
$stmt->store_result();
if ($stmt->num_rows === 0) {
    flash_set('Invalid visitor or access denied');
    header('Location: resident_dashboard.php');
    exit();
}
$stmt->close();

if ($action === 'approve') {
    $stmt = $mysqli->prepare("UPDATE visitors SET status='approved' WHERE id = ?");
    $stmt->bind_param('i', $visitor_id);
    $stmt->execute();
    $stmt->close();
    flash_set('Visitor approved');
} else {
    $stmt = $mysqli->prepare("UPDATE visitors SET status='rejected' WHERE id = ?");
    $stmt->bind_param('i', $visitor_id);
    $stmt->execute();
    $stmt->close();
    flash_set('Visitor rejected');
}

header('Location: resident_dashboard.php');
exit();
?>
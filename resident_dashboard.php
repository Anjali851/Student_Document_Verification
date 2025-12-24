<?php
// resident_dashboard.php
require_once 'functions.php';
require_login();
if (!is_role('resident')) {
    flash_set('Access denied: Residents only');
    header('Location: index.php');
    exit();
}
$mysqli = db_connect();
$uid = $_SESSION['user']['id'];
$flat_id = $_SESSION['user']['flat_id'];

// pending normal visitor requests for this flat
$stmt = $mysqli->prepare("SELECT * FROM visitors WHERE flat_id = ? AND type='normal' ORDER BY created_at DESC");
$stmt->bind_param('i', $flat_id);
$stmt->execute();
$pending = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);
$stmt->close();

// regular visitors for this flat
$stmt = $mysqli->prepare("SELECT * FROM visitors WHERE flat_id = ? AND type='regular' ORDER BY created_at DESC");
$stmt->bind_param('i', $flat_id);
$stmt->execute();
$regular = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);
$stmt->close();

// maintenance
$stmt = $mysqli->prepare("SELECT * FROM maintenance WHERE flat_id = ? ORDER BY created_at DESC");
$stmt->bind_param('i', $flat_id);
$stmt->execute();
$maintenance = $stmt->get_result()->fetch_all(MYSQLI_ASSOC);
$stmt->close();
?>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Resident Dashboard - SSS</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>
<div class="container">
    <h2>Resident Dashboard</h2>
    <p>Welcome, <?=htmlspecialchars($_SESSION['user']['name'] ?? $_SESSION['user']['username'])?> [resident] - <a href="logout.php">Logout</a></p>

    <section>
        <h3>Pending Visitor Requests</h3>
        <?php if (count($pending) === 0): ?><p>No pending requests.</p><?php endif; ?>
        <ul>
            <?php foreach ($pending as $p): ?>
            <li>
                <?=htmlspecialchars($p['name'])?> (<?=htmlspecialchars($p['phone'])?>) - <?=htmlspecialchars($p['created_at'])?>
                <form method="post" action="approve_visitor.php" style="display:inline">
                    <input type="hidden" name="visitor_id" value="<?=$p['id']?>">
                    <button name="action" value="approve">Approve</button>
                    <button name="action" value="reject">Reject</button>
                </form>
            </li>
            <?php endforeach; ?>
        </ul>
    </section>

    <section>
        <h3>Regular Visitors / Vendors (security codes shown)</h3>
        <ul>
            <?php foreach ($regular as $r): ?>
            <li><?=htmlspecialchars($r['name'])?> — Code: <strong><?=htmlspecialchars($r['security_code'])?></strong> — Checkin: <?=htmlspecialchars($r['checkin_time'])?> — Checkout: <?=htmlspecialchars($r['checkout_time'])?></li>
            <?php endforeach; ?>
        </ul>
    </section>

    <section>
        <h3>Maintenance</h3>
        <ul>
            <?php foreach ($maintenance as $m): ?>
            <li>Amount: <?=number_format($m['amount'],2)?> — Due: <?=($m['paid'] ? 'No' : 'Yes')?> — Due date: <?=htmlspecialchars($m['due_date'])?> — Mode: <?=htmlspecialchars($m['mode'])?> — Paid date: <?=htmlspecialchars($m['paid_date'])?></li>
            <?php endforeach; ?>
        </ul>
    </section>

    <section>
        <h3>Check-in / Check-out (for regular visitors)</h3>
        <form method="post" action="checkin_checkout.php">
            <label>Security code</label><input name="security_code" required>
            <button name="action" value="checkin">Check-in</button>
            <button name="action" value="checkout">Check-out</button>
        </form>
    </section>
</div>
</body>
</html>
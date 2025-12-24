<?php
// functions.php - utility functions used across pages
require_once 'config.php';
session_start();

function require_login() {
    if (!isset($_SESSION['user'])) {
        header('Location: index.php');
        exit();
    }
}

function current_user() {
    return isset($_SESSION['user']) ? $_SESSION['user'] : null;
}

function is_role($role) {
    $u = current_user();
    return $u && $u['role'] === $role;
}

function flash_set($msg) {
    $_SESSION['flash'] = $msg;
}

function flash_get() {
    if (isset($_SESSION['flash'])) {
        $m = $_SESSION['flash'];
        unset($_SESSION['flash']);
        return $m;
    }
    return null;
}
?>
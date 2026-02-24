package com.donaMaria_.demo.exceptions;

public class RoleInvalidaException extends RuntimeException {
    public RoleInvalidaException() {
        super("Role inválida");
    }
}
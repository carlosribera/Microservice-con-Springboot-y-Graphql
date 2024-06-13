package com.softand.demo.controller;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name","message", "status"})
public record EntityResponse(
    String name,
    String message,
    Boolean status) {
}

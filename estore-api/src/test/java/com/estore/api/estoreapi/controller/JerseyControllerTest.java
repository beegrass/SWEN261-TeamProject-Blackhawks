package com.estore.api.estoreapi.controller;

import com.estore.api.estoreapi.persistence.JerseyDAO;

@org.junit.jupiter.api.Tag("Controller-tier")
public class JerseyControllerTest {
    private JerseyController jerseycontroller;
    private JerseyDAO mockJerseyDAO;

    public JerseyControllerTest() { /* compiled code */ }

    @org.junit.jupiter.api.BeforeEach
    public void setupJerseyController() { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testGetJersey() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testGetJerseyNotFound() throws java.lang.Exception { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testGetJerseyHandleException() throws java.lang.Exception { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testCreateJersey() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testCreateJerseyFailed() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testCreateJerseyHandleException() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testUpdateJersey() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testUpdateJerseyFailed() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testUpdateJerseyHandleException() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testGetJerseys() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testGetJerseysHandleException() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testSearchJerseys() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testSearchJerseysHandleException() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testDeleteJerseys() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testDeleteJerseyNotFound() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testDeleteJerseyHandleException() throws java.io.IOException { /* compiled code */ }
}

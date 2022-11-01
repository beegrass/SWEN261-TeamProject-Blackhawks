package com.estore.api.estoreapi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-tier")
public class AdminTest {
    @Test
    public void testAdminConstructor(){
        Admin admin = new Admin();
        assertEquals(1, admin.getUserId());
        assertEquals("Admin", admin.getUsername());
        assertEquals(true, admin.getUserType());
    }
}

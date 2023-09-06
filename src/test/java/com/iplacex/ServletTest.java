package com.iplacex;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServletTest {
	
	public ServletTest() {}
	
	@BeforeClass
	public static void setUpClass() {}
	
	@AfterClass
	public static void tearDownClass() {}
	
	@Before
	public void setUp() {}
	
	@After
	public void tearDown() {}
	
	@Test
    public void testVerificarCredenciales() {
        
		try {
        Servlet instance = new Servlet();
        String usuario = "tuto";
        String contrasena = "123";
        if(usuario.equals("") || contrasena.equals("")) {
        	System.out.println("faltan datos");
        }
        boolean resultado = instance.verificarCredenciales(usuario,contrasena);
        if(resultado==true){
        	System.out.println("Credenciales correctas");
        }else {
        	System.out.println("Credenciales incorrectas");
        }
        assertEquals(false, resultado);
		}catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
	}
}	
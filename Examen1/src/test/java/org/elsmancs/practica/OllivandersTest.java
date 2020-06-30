
/**
 * Instrucciones:
 * 
 *  - Crea un repo privado compartido sólo con dfleta en GitHub.
 *  - Realiza un commit al pasar cada caso test.
 *  - Sin este commit tras cada caso, no corrijo el examen.
 */


package org.elsmancs.practica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.elsmancs.practica.controlador.Controlador;
import org.elsmancs.practica.dominio.Orden;
import org.elsmancs.practica.dominio.Usuaria;
import org.elsmancs.practica.dominio.NormalItem;
import org.elsmancs.practica.repositorio.NotEnoughProException;
import org.elsmancs.practica.repositorio.Repositorio;
import org.elsmancs.practica.servicio.Servicio;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Construye una aplicacion que maneja la base de datos
 * de la tienda de Ollivanders,
 * con las personas usuarias (users) de la tienda 
 * y los items disponibles (items), que son todos
 * del tipo NormalItem.
 * Las usuarias realizan pedidos (ordenes) al servicio
 * para comprar items. 
 */


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(statements = {
		"delete from t_ordenes",
		"delete from t_items",
		"delete from t_users",
		"insert into t_users (user_nom, user_prop) values ('Doobey', 15)",
		"insert into t_users (user_nom, user_prop) values ('Hermione', 100)",
		"insert into t_items (item_nom, item_prop, item_tipo) values ('+5 Dexterity Vest', 20, 'NormalItem')",
		"insert into t_items (item_nom, item_prop, item_tipo) values ('Elixir of the Mongoose', 7, 'NormalItem')",
		"insert into t_ordenes (ord_id, ord_user, ord_item) values (1,'Doobey','Elixir of the Mongoose')",
})
@AutoConfigureMockMvc

public class OllivandersTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired(required = false)
	Repositorio repo;
	
	@Autowired(required = false)
	Servicio servicio;

	@Autowired(required = false)
	private MockMvc mockMvc;

	@Autowired(required = false)
	Controlador controlador;

	/**
	 * Tests sobre los mappings
	 * 
	 * Observa el esquema de la base de datos que espera 
	 * la aplicacion en el fichero:
	 * src/main/resources/schema.sql
	 */
	
	// Completa la definicion y el mapping
	// de la clase NormalItem a la tabla t_items
	@Test
	public void test_mapping_normalItem() {
		NormalItem elixir = em.find(NormalItem.class, "Elixir of the Mongoose");
		assertNotNull(elixir);
		assertEquals("Elixir of the Mongoose", elixir.getNombre()); //item_nom
		assertEquals(7, elixir.getQuality(), 0);  //item_prop
		assertEquals("NormalItem", elixir.getTipo()); //item_tipo
	}
	

	// Completa la definicion y el mapping
	// de la clase Usuaria a la tabla t_users
	@Test
	public void test_mapping_user() {
		Usuaria elfo = em.find(Usuaria.class, "Doobey");
		assertNotNull(elfo);
		assertEquals("Doobey", elfo.getNombre());
		assertEquals(15, elfo.getDestreza(), 0); //user_prop
	}
	
	// Completa la definicion y el mapping
		// de la clase Orden a la tabla t_ordenes
		// El id de esta clase ha de seguir una estrategia
		// Identity
		@Test 
		public void test_mapping_orden() {
			Orden pedido = em.find(Orden.class, 1L);
			assertNotNull(pedido);
			assertEquals("Doobey", pedido.getUser().getNombre());
			assertEquals("Elixir of the Mongoose", pedido.getItem().getNombre());
			}
		
		/**
		 * Crea una clase llamada Repositorio e indica
		 * que es un repositorio o componente de Spring 
		 */
		@Test
		public void test_repositorio_es_componente() {
			assertNotNull(repo);
		}
		
		
		/**
		 * Implementa el metodo cargaUser del repositorio
		 * que devuelve el usuario/a con el nombre indicado
		 */
		@Test
		public void test_carga_user() {
			assertNotNull(repo);
			Usuaria elfo = repo.cargaUser("Doobey");
			assertNotNull(elfo);
			assertEquals("Doobey", elfo.getNombre());
			assertEquals(15, elfo.getDestreza());
		}
		
		/**
		 * Implementa el metodo cargaItem del repositorio
		 * que devuelve el item con el nombre indicado
		 */
		@Test
		public void test_carga_item() {
			assertNotNull(repo);
			NormalItem item = (NormalItem) repo.cargaItem("Elixir of the Mongoose");
			assertNotNull(item);
			assertEquals("Elixir of the Mongoose", item.getNombre());
			assertEquals(7, item.getQuality(), 0);
		}
		
		/**
	     * Implementa el metodo ordenar del repositorio
		 * que permite a un usuario/a pedir un item.
	     * El usuario/a y el item ya existen en la bbdd (NO has de crearlos).
		 * 
	     * El metodo devuelve la orden de tipo Orden creada.
		 * 
		 * Guarda la orden en la base de datos.
		 */
		@Test
		@Transactional
		public void test_ordenar_ok() throws NotEnoughProException {
			assertNotNull(repo);
			Orden orden = repo.ordenar("Hermione", "+5 Dexterity Vest");
			assertNotNull(orden);
			assertNotNull(orden.getId());
			assertEquals("Hermione", orden.getUser().getNombre());
			assertEquals("+5 Dexterity Vest", orden.getItem().getNombre());
		}
		

		/**
	     * Implementa el metodo ordenar del repositorio
		 * para que no permita generar ordenes de productos
		 * si no existe el usuario/a en la base de datos.
		 */
		@Test
		@Transactional
		public void test_ordenar_no_user() throws NotEnoughProException {
			assertNotNull(repo);
			Orden orden = repo.ordenar("Severus", "+5 Dexterity Vest");
			assertNull(orden);
		}
		
		/**
	     * Implementa el metodo ordenar del repositorio
		 * para que no permita generar ordenes de productos
		 * si no existe el item en la base de datos.
		 */
		@Test
		@Transactional
		public void test_ordenar_no_item() throws NotEnoughProException {
			assertNotNull(repo);
			Orden orden = repo.ordenar("Hermione", "Aged Brie");
			assertNull(orden);
		}




}

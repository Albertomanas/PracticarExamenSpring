
/**
 * Instrucciones:
 * 
 *  - Crea un repo privado compartido sólo con dfleta en GitHub.
 *  - Realiza un commit al pasar cada caso test.
 *  - Sin este commit tras cada caso, no corrijo el examen.
 */


package org.elsmancs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.elsmancs.practica.controlador.Controlador;
import org.elsmancs.practica.dominio.NormalItem;
import org.elsmancs.practica.dominio.Orden;
import org.elsmancs.practica.dominio.Usuaria;
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

	@Autowired
	Repositorio repo;
	
	@Autowired
	Servicio servicio;

	@Autowired
	private MockMvc mockMvc;
									   
	@Autowired
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
		assertEquals("Elixir of the Mongoose", elixir.getNombre());
		assertEquals(7, elixir.getQuality(), 0);  //item_prop
		assertEquals("NormalItem", elixir.getTipo());
	}

}

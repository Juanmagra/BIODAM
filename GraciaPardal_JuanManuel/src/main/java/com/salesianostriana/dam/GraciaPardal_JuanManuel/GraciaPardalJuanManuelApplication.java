package com.salesianostriana.dam.GraciaPardal_JuanManuel;

import com.salesianostriana.dam.GraciaPardal_JuanManuel.model.*;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.repository.*;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.PedidoServi;
import com.salesianostriana.dam.GraciaPardal_JuanManuel.service.UsuarioServi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootApplication()
public class GraciaPardalJuanManuelApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraciaPardalJuanManuelApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(LineaPedidoRepo repoLi, PedidoRepo repoPe, ProductoRepo repoPr, UsuarioRepo repoUs, CategoriaRepo repoCa, PedidoServi pedidoSer, UsuarioServi usuarioSer) {
		return args -> {
			List listaLinea = new ArrayList();
			List listaLinea2 = new ArrayList();
			List listaLinea3 = new ArrayList();
			List listaPedido = new ArrayList();
			List listaPedido2 = new ArrayList();
			Date fecha = new Date();


			Usuario user = new Usuario(100L, "Juan Manuel", "Gracia", "miEmail@Gmail.com", false, listaPedido);
			repoUs.save(user);
			user.setId(repoUs.findAll().get(0).getId());

			Usuario admin = new Usuario(100L, "Juan Manuel", "Gracia", "miEmail@Gmail.com", true, listaPedido);
			repoUs.save(admin);
			admin.setId(repoUs.findAll().get(1).getId());

			Categoria categoria = new Categoria(50L, "Categoria 1");
			repoCa.save(categoria);
			Categoria categoria2 = new Categoria(50L, "Categoria 2");
			repoCa.save(categoria2);
			Categoria categoria3 = new Categoria(50L, "Categoria 3");
			repoCa.save(categoria3);

			List <Categoria> categoriaLista = repoCa.findAll();
			categoria.setId(categoriaLista.get(0).getId());
			categoria2.setId(categoriaLista.get(1).getId());
			categoria3.setId(categoriaLista.get(2).getId());



			Producto producto = new Producto(56L, "Portatil", 500D, true, categoria);
			repoPr.save(producto);
			producto.setId(repoPr.findAll().get(0).getId());

			Producto producto2 = new Producto(56L, "Pantalla", 500D, true, categoria);
			repoPr.save(producto2);
			producto2.setId(repoPr.findAll().get(1).getId());

			Producto producto3 = new Producto(56L, "Raton", 500D, true, categoria2);
			repoPr.save(producto3);
			producto3.setId(repoPr.findAll().get(2).getId());


			Pedido pedido = new Pedido(90L,fecha,"Revision", user,listaLinea);
			repoPe.save(pedido);
			pedido.setId(repoPe.findAll().get(0).getId());

			Pedido pedido2 = new Pedido(90L,fecha,"Revision", user,listaLinea2);
			repoPe.save(pedido2);
			pedido2.setId(repoPe.findAll().get(1).getId());

			Pedido pedido3 = new Pedido(90L,fecha,"Revision", admin,listaLinea);
			repoPe.save(pedido3);
			pedido3.setId(repoPe.findAll().get(2).getId());

			//Pedido 1
			LineaPedido lineaPedido = new LineaPedido(80L, pedido, producto );
			repoLi.save(lineaPedido);
			lineaPedido.setId(repoLi.findAll().get(0).getId());

			LineaPedido lineaPedido2 = new LineaPedido(80L, pedido, producto );
			repoLi.save(lineaPedido2);
			lineaPedido2.setId(repoLi.findAll().get(1).getId());


			//Pedido 2
			LineaPedido lineaPedido3 = new LineaPedido(80L, pedido2, producto2);
			repoLi.save(lineaPedido3);
			lineaPedido3.setId(repoLi.findAll().get(0).getId());

			LineaPedido lineaPedido4 = new LineaPedido(80L, pedido2, producto );
			repoLi.save(lineaPedido4);
			lineaPedido4.setId(repoLi.findAll().get(0).getId());

			//Pedido 3
			LineaPedido lineaPedido5 = new LineaPedido(80L, pedido3, producto3 );
			repoLi.save(lineaPedido5);
			lineaPedido5.setId(repoLi.findAll().get(0).getId());

			listaLinea.add(lineaPedido);
			listaLinea.add(lineaPedido2);

			listaLinea2.add(lineaPedido3);
			listaLinea2.add(lineaPedido4);


			listaLinea3.add(lineaPedido5);


			listaPedido.add(pedido);
			listaPedido2.add(pedido2);
			listaPedido.add(pedido3);


			user.setPedidos(listaPedido);
			usuarioSer.edit(user);

			admin.setPedidos(listaPedido2);
			usuarioSer.edit(admin);


			pedido.setLineaPedido(listaLinea);
			pedidoSer.edit(pedido);

			pedido2.setLineaPedido(listaLinea2);
			pedidoSer.edit(pedido2);

			pedido3.setLineaPedido(listaLinea3);
			pedidoSer.edit(pedido3);









		};
	}
}

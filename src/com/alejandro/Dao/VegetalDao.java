package com.alejandro.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.alejandro.Model.*;

public class VegetalDao {
	
	JdbcTemplate template;
	
	public JdbcTemplate getTemplate() {
		return template;
	}
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public List<Vegetal> listarVegetales(){
		return template.query("select * from vegetales", new RowMapper<Vegetal>() {
			@Override
				public Vegetal mapRow(ResultSet rs, int row) throws SQLException
				{
					Vegetal v=new Vegetal();
					v.setId(rs.getInt(1));
					v.setNombre(rs.getString(2));
					v.setCategoria(rs.getString(3));
					v.setImagen(rs.getString(4));
					v.setProcedencia(rs.getString(5));
					v.setPrecio(rs.getFloat(6));
					return v;
				}
			}
		);
	}
		 
		 public List<Vegetal> listarVegetalesPorPaginas(int Nitem, int total){
				return template.query("select * from vegetales limit "+(Nitem-1)+","+total, new RowMapper<Vegetal>() {
					@Override
					public Vegetal mapRow(ResultSet rs, int row) throws SQLException
					{
						Vegetal v=new Vegetal();
						v.setId(rs.getInt(1));
						v.setNombre(rs.getString(2));
						v.setCategoria(rs.getString(3));
						v.setImagen(rs.getString(4));
						v.setProcedencia(rs.getString(5));
						v.setPrecio(rs.getFloat(6));
						return v;
					}
					
					
				}
				);
			}
		 public List<Vegetal> listarVegetalesCategoria(String categoria) {
				return template.query("select * from vegetales where categoria = '"+categoria+"'", new RowMapper<Vegetal>() {
					@Override
					public Vegetal mapRow(ResultSet rs, int row) throws SQLException
					{
						Vegetal v=new Vegetal();
						v.setId(rs.getInt(1));
						v.setNombre(rs.getString(2));
						v.setCategoria(rs.getString(3));
						v.setImagen(rs.getString(4));
						v.setProcedencia(rs.getString(5));
						v.setPrecio(rs.getFloat(6));
						return v;
					}
					
					
				}
				);
				
			}
		 public List<Slide> listarSlides() {
				return template.query("select * from carousel", new RowMapper<Slide>() {
					@Override
					public Slide mapRow(ResultSet rs, int row) throws SQLException
					{
						Slide s=new Slide();
						s.setId(rs.getInt(1));
						s.setTexto(rs.getString(2));
						s.setImagen(rs.getString(3));
						
						return s;
					}
					
					
				}
				);
				
			}
		/*
		 * gesti�n del back
		 */
		public int guardar(Vegetal v) {
			return  template.update("insert into vegetales"
					+ "(nombre,categoria,procedencia,imagen,precio) "
					+ "values (?,?,?,?,?)",
					v.getNombre(),
					v.getCategoria(),
					v.getProcedencia(),
					v.getImagen(),
					v.getPrecio()
			);
		}
		
		public int actualizar(Vegetal v) {
			return template.update("update vegetales set"
				+ " nombre=?,"
				+ " categoria=?,"
				+ " procedencia=?,"
				+ " imagen=?,"
				+ " precio=?"
				+ " where id=?",
				v.getNombre(),v.getCategoria(),v.getProcedencia(),v.getImagen(),v.getPrecio(),v.getId()
				);
			
		}

			 public int eliminar(int id) {
			  String sql = "delete from vegetales where id=" + id + "";
			  return template.update(sql);
			 }
		/*
		 * c�digo del mapper que se encarga de convertir un ResultSet en una lista de objetos, o uno solo
		 * si es un buscarporID
		 */
			 public Vegetal buscarporId(int id) {
			  String sql = "select * from vegetales where id=?";
			  return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Vegetal>(Vegetal.class));
			 }
			 
		public List<Usuario> usuarioRegistrado(String user,String password) {
			
			return template.query("select * from user where usuario = '"+user+"'and clave='"+password+"'", new RowMapper<Usuario>() {
				@Override
				public Usuario mapRow(ResultSet rs, int row) throws SQLException
				{
					Usuario u=new Usuario();
					u.setUsername(rs.getString(1));
					u.setPassword(rs.getString(2));
					u.setEmail(rs.getString(3));
					u.setName(rs.getString(4));
					u.setAge(rs.getInt(5));
					return u;
				}
				
				
			}
			);
		}

		
		public int crearUsuario(@Valid Usuario user) {
			return  template.update("insert into user"
					+ "(usuario,clave,mail,nombre,edad) "
					+ "values (?,?,?,?,?)",
					user.getUsername(),
					user.getPassword(),
					user.getEmail(),
					user.getName(),
					user.getAge()
				);
		}

		public int modificarUsuario(@Valid Usuario user) {
			return  template.update("update user set "
					+ "clave = ?,"
					+ "mail = ?,"
					+ "nombre = ?,"
					+ "edad = ? "
					+ "where usuario = ?",
					user.getPassword(),
					user.getEmail(),
					user.getName(),
					user.getAge(),
					user.getUsername()
				);
			
		}
	}



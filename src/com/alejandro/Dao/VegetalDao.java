package com.alejandro.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.alejandro.Model.*;

public class VegetalDao {

	
		 public JdbcTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
		JdbcTemplate template;
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
		public void aniadircarrito(int id) {
			
			
		}
		/*
		 * gestión del back
		 */
		 public int guardar(Vegetal v) {
		/*	  String sql = "insert into vegetales(nombre,salario,cargo) values('" + p.getNombre() + "'," + p.getSalario()
			    + ",'" + p.getCargo() + "')";*/
			    return  template.update("insert into vegetales(nombre,categoria,procedencia,precio) values (?,?,?,?)",v.getNombre(),v.getCategoria(),v.getProcedencia(),v.getPrecio());

			 // return template.update(sql);
			 }

			 public int actualizar(Vegetal v) {
			/*  String sql = "update vegetal set nombre='" + v.getNombre() + "', categoria=" + v.getCategoria() + ", procedencia='"
			    + v.getProcedencia() + "' where id=" + v.getId() + "";*/
			  return template.update("update vegetales set nombre=?, categoria=?, procedencia=?, precio=? where id=?",v.getNombre(),v.getCategoria(),v.getPrecio(),v.getId());
			//  return template.update(sql);
			 }

			 public int eliminar(int id) {
			  String sql = "delete from vegetales where id=" + id + "";
			  return template.update(sql);
			 }
		/*
		 * código del mapper que se encarga de convertir un ResultSet en una lista de objetos, o uno solo
		 * si es un buscarporID
		 */
			 public Vegetal buscarporId(int id) {
			  String sql = "select * from vegetales where id=?";
			  return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Vegetal>(Vegetal.class));
			 }
	}



package com.jvilchez.aplicacion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "enfermedad")
public class Enfermedad {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@NotBlank
	private String nombre;
	@Column
	@NotBlank
	private String medicamento_g;
	@Column
	@NotBlank
	private String medicamento_l;
	@Column
	@NotBlank
	private String medicamento_n;
	@Column
	@NotBlank
	private String sintomas;
	@Column
	@NotBlank
	private String apto;
	
	private String enfermedad_img;
    
	private String medi_g;  
	
	private String medi_l;
	
	private String medi_n;
	
	/*public Enfermedad() {
		super();
	}
	
	public Enfermedad(String nombre,String medicamento_g,String medicamento_l,String medicamento_n,String sintomas,String apto, String enfermedad_img, String medi_g, String medi_l, String medi_n) {
        this.nombre = nombre;
        this.medicamento_g = medicamento_g;
        this.medicamento_l = medicamento_l;
        this.medicamento_n = medicamento_n;
        this.sintomas = sintomas;
        this.apto = apto;
        this.enfermedad_img =enfermedad_img;
        this.medi_g = medi_g;
        this.medi_l = medi_l;
        this.medi_n = medi_n;
    }*/
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMedicamento_g() {
		return medicamento_g;
	}

	public void setMedicamento_g(String medicamento_g) {
		this.medicamento_g = medicamento_g;
	}

	public String getMedicamento_l() {
		return medicamento_l;
	}

	public void setMedicamento_l(String medicamento_l) {
		this.medicamento_l = medicamento_l;
	}

	public String getMedicamento_n() {
		return medicamento_n;
	}

	public void setMedicamento_n(String medicamento_n) {
		this.medicamento_n = medicamento_n;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getApto() {
		return apto;
	}

	public void setApto(String apto) {
		this.apto = apto;
	}
	
	public String getEnfermedad_img() {
		return enfermedad_img;
	}

	public void setEnfermedad_img(String enfermedad_img) {
		this.enfermedad_img = enfermedad_img;
	}
	
	public String getMedi_g() {
		return medi_g;
	}

	public void setMedi_g(String medi_g) {
		this.medi_g = medi_g;
	}
	
	public String getMedi_l() {
		return medi_l;
	}

	public void setMedi_l(String medi_l) {
		this.medi_l = medi_l;
	}
	
	public String getMedi_n() {
		return medi_n;
	}

	public void setMedi_n(String medi_n) {
		this.medi_n = medi_n;
	}
	
	@Override
	public String toString() {
		return "Enfermedad [id=" + id + ", nombre=" + nombre + ", apto=" + apto + ", sintomas="
				+ sintomas + ", enfermedad_img=" + enfermedad_img +  ", medi_g=" + medi_g + ", medi_l=" + medi_l + ", medi_n=" + medi_n + "]";
	}
	
}

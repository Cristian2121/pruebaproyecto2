/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Alumno;
import com.ipn.mx.modelo.entidades.Carrera;
import java.io.Serializable;

/**
 *
 * @author darkdestiny
 */
public class AlumnoDTO implements Serializable {

    private Alumno entidad;

    public AlumnoDTO() {
        entidad = new Alumno();
    }

    public Alumno getEntidad() {
        return entidad;
    }

    public void setEntidad(Alumno entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Clave : ").append(getEntidad().getIdAlumno()).append("\n");
        sb.append("Nombre : ").append(getEntidad().getNombreAlumno()).append("\n");
        sb.append("Paterno : ").append(getEntidad().getPaternoAlumno()).append("\n");
        sb.append("Materno : ").append(getEntidad().getMaternoAlumno()).append("\n");
        sb.append("Email : ").append(getEntidad().getEmailAlumno()).append("\n");
        sb.append("Clave Carrera  : ").append(getEntidad().getIdCarrera()).append("\n");
        //sb.append("Clave Carrera  : ").append(getEntidad().getIdCarrera().getIdCarrera).append("\n");
        
        return sb.toString();
    }
}













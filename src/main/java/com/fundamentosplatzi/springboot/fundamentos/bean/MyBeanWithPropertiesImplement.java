package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties {

    private String nombre;
    private String Apellido;

    public MyBeanWithPropertiesImplement(String nombre, String apellido) {
        this.nombre = nombre;
        Apellido = apellido;
    }

    @Override
    public String funtion() {
        return "nombre y apellido: " + nombre + "--" + Apellido;
    }
}

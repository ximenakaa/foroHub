package com.alura.foroHub.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Column(nullable = false)
    private String curso;

    // Getters y Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }

    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }

    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public StatusTopico getStatus() { return status; }

    public void setStatus(StatusTopico status) { this.status = status; }

    public Usuario getAutor() { return autor; }

    public void setAutor(Usuario autor) { this.autor = autor; }

    public String getCurso() { return curso; }

    public void setCurso(String curso) { this.curso = curso; }
}

package com.alura.foroHub.service;

import com.alura.foroHub.dto.request.DatosActualizacionTopico;
import com.alura.foroHub.dto.request.DatosRegistroTopico;
import com.alura.foroHub.dto.response.DatosRespuestaTopico;
import com.alura.foroHub.model.StatusTopico;
import com.alura.foroHub.model.Topico;
import com.alura.foroHub.model.Usuario;
import com.alura.foroHub.repository.TopicoRepository;
import com.alura.foroHub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public DatosRespuestaTopico guardarTopico(DatosRegistroTopico datos) {
        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setFechaCreacion(LocalDate.now());
        topico.setAutor(autor);
        topico.setCurso(datos.curso());
        topico.setStatus(StatusTopico.ACTIVO);

        Topico guardado = topicoRepository.save(topico);

        return new DatosRespuestaTopico(
                guardado.getId(),
                guardado.getTitulo(),
                guardado.getMensaje(),
                guardado.getFechaCreacion().toString(),
                guardado.getCurso(),
                guardado.getAutor().getNombre(),
                guardado.getStatus().name()
        );
    }

    public List<DatosRespuestaTopico> listarTodos() {
        return topicoRepository.findAll().stream()
                .map(t -> new DatosRespuestaTopico(
                        t.getId(),
                        t.getTitulo(),
                        t.getMensaje(),
                        t.getFechaCreacion().toString(),
                        t.getCurso(),
                        t.getAutor().getNombre(),
                        t.getStatus().name()
                )).toList();
    }

    @Transactional
    public DatosRespuestaTopico actualizarTopico(Long id, DatosActualizacionTopico datos) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setCurso(datos.curso());

        return new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().toString(),
                topico.getCurso(),
                topico.getAutor().getNombre(),
                topico.getStatus().name()
        );
    }

    @Transactional
    public void eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new RuntimeException("Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
    }
}

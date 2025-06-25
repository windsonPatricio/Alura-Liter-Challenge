package br.com.alura.AluraLiter.repository;

import br.com.alura.AluraLiter.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.nome LIKE %:nome%")
    Optional<Autor> findByName(@Param("nome") String nome);
    @Query("SELECT a FROM Autor a WHERE :ano BETWEEN CAST(a.dataNascimento AS integer) AND CAST(a.dataMorte AS integer)")
    List<Autor> findAuthorsAlive(@Param("ano") int ano);
}

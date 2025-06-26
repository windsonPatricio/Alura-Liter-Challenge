package br.com.alura.AluraLiter.repository;

import br.com.alura.AluraLiter.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {


    @Query("SELECT COUNT(l) > 0 FROM Livro l WHERE l.titulo LIKE %:titulo%")
    Boolean verificaLivroExiste(@Param("titulo") String Titulo);

    @Query(value = "SELECT * FROM livros WHERE :idioma = ANY (livros.idioma)", nativeQuery = true)
    List<Livro> findByLanguage(@Param("idioma") String idioma);
}

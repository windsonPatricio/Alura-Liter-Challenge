package br.com.alura.AluraLiter.repository;

import br.com.alura.AluraLiter.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivroRepository extends JpaRepository<Livro, Long> {


    @Query("SELECT COUNT(l) > 0 FROM Livro l WHERE l.titulo LIKE %:titulo%")
    Boolean verificaLivroExiste(@Param("titulo") String Titulo);
}

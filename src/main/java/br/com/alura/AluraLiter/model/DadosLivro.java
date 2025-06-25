package br.com.alura.AluraLiter.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String titulo,
                         @JsonAlias("download_count") int download,
                         @JsonAlias("authors") List<DadosAutor> autores,
                         @JsonAlias("languages")List<String> idioma
                        ) {
}

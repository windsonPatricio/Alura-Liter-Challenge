package br.com.alura.AluraLiter.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "livros")
public class Livro {

    public Livro(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(fetch = FetchType.EAGER)
    private Autor autor;
    private int download;
    private List<String> idioma = new ArrayList<>();



    public Livro(DadosLivro dadosLivro ){

        this.titulo = dadosLivro.titulo();
        this.autor = new Autor(dadosLivro.autores().get(0));
        this.download = dadosLivro.download();
        this.idioma = dadosLivro.idioma();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return
                "Titulo= " + titulo + '\n' +
                "Autores= " + autor.getNome() + '\n'+
                "Num Download= " + download + '\n'+
                "Idioma= " + idioma + '\n';

    }
}

package br.com.alura.AluraLiter.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dataNascimento;
    private String dataMorte;
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Livro> livro = new ArrayList<>();

    public Autor(){}


    public Autor(DadosAutor dadosAutor){

        this.nome = dadosAutor.nome();
        this.dataNascimento = dadosAutor.dataNascimento();
        this.dataMorte = dadosAutor.dataMorte();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(String dataMorte) {
        this.dataMorte = dataMorte;
    }

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro(List<Livro> livros) {
        this.livro = new ArrayList<>();
        livros.forEach(b -> {
            b.setAutor(this);
            this.livro.add(b);
        });
    }

    @Override
    public String toString() {
        List<String> livros = this.getLivro().stream().map(Livro::getTitulo).toList();
        return
                "Nome = " + nome + '\n' +
                "Data de Nacimento = " + dataNascimento + '\n' +
                "Data de Morte = " + dataMorte + '\n' +
                "Livro = " + livros + '\n';
    }
}

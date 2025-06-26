package br.com.alura.AluraLiter.principal;

import br.com.alura.AluraLiter.model.*;
import br.com.alura.AluraLiter.repository.AutorRepository;
import br.com.alura.AluraLiter.repository.LivroRepository;
import br.com.alura.AluraLiter.service.ConsumoApi;
import br.com.alura.AluraLiter.service.ConverterDados;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Principal {


    private LivroRepository livroRepository;
    private AutorRepository autorRepository;
    private Scanner leitura = new Scanner(System.in);
    private final String URL = "https://gutendex.com/books/?search=";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverterDados conversor = new ConverterDados();
    private List<DadosLivro> dadosLivro = new ArrayList<>();


    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }


    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar Livro
                    2- Lista de livros
                    3- Lista de autores
                    4- Lista de autores vivos por ano
                    5- Lista livros por idioma
                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    getLivro();
                    break;
                case 2:
                    listaLivros();
                    break;
                case 3:
                    listaAutores();
                    break;
                case 4:
                    listaAutoresVivosPorAno();
                    break;
                case 5:
                    listaLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }



    @Transactional
    private void getLivro() {
        System.out.println("Digite o nome do livro para Busca?");
        var titulo = leitura.nextLine();

        if(!titulo.isBlank()){
            var json  = consumo.obterDados(URL + titulo.replace(" ", "+"));
            var dados  = conversor.obterDados(json, DadosApi.class);

            Optional<DadosLivro> buscaLivro = dados.results().stream()
                    .filter(d -> d.titulo().toLowerCase().contains(titulo.toLowerCase()))
                    .findFirst();

            if(buscaLivro.isPresent()){
                DadosLivro dadosLivros = buscaLivro.get();

                if(!livroRepository.verificaLivroExiste(dadosLivros.titulo())){
                    Livro livro = new Livro(dadosLivros);
                    DadosAutor dadosAutor = dadosLivros.autores().get(0);
                    Optional<Autor> opcaoAutor = autorRepository.findByName(dadosAutor.nome());

                    if (opcaoAutor.isPresent()) {
                        Autor autorExiste = opcaoAutor.get();
                        livro.setAutor(autorExiste);
                        autorExiste.getLivro().add(livro);
                        autorRepository.save(autorExiste);
                    } else {
                        Autor novoAutor = new Autor(dadosAutor);
                        livro.setAutor(novoAutor);
                        novoAutor.getLivro().add(livro);
                        autorRepository.save(novoAutor);
                    }

                    livroRepository.save(livro);
                    System.out.println(livro);


                } else{
                    System.out.println("\n Livro já adicionado no Banco de Dados");
                }
            } else {
                System.out.println("\n Livro não encontrado!");
            }
        } else {
            System.out.println("\n valor digitado inválido!");
        }

    }

    private void listaLivros() {
        List<Livro> livro = livroRepository.findAll();

        if(!livro.isEmpty()) {
            System.out.println("============================");
            System.out.println("\n Livros Registrados !!!");
            System.out.println("============================ \n");
            livro.forEach(System.out::println);
        } else {
            System.out.println("============================");
            System.out.println("\n Livros não encontrados !!!");
            System.out.println("============================ \n");
        }
    }



    private void listaAutores() {
        List<Autor> autor = autorRepository.findAll();

        if(!autor.isEmpty()) {
            System.out.println("============================");
            System.out.println("  Autores Registrados !!!");
            System.out.println("============================ \n");
            autor.forEach(System.out::println);
        } else {
            System.out.println("============================");
            System.out.println(" Autor não encontrados !!!");
            System.out.println("============================ \n");
        }

    }

    private void listaAutoresVivosPorAno() {
        System.out.println("\n Digite um ano para consulta: ");

        if (leitura.hasNextInt()) {
            var ano = leitura.nextInt();
            List<Autor> autores = autorRepository.findAuthorsAlive(ano);

            if (!autores.isEmpty()) {
                System.out.println("============================");
                System.out.println("Autores vivos registrados apartir de  " + ano);
                System.out.println("============================ \n");
                autores.forEach(System.out::println);
            } else {
                System.out.println("============================");
                System.out.println("Nenhum autor encontrado!!");
                System.out.println("============================ \n");
            }

        } else {
            System.out.println("\n Valor digitado inválido!");
            leitura.next();
        }
    }


    private void listaLivrosPorIdioma() {
        var opcao = -1;
        String idioma = "";
            var idiomaMenu = """
                    1 - Inglês
                    2- Portugues
                    3- Espanhol
                    4- Francês
                                             
                    """;

            System.out.println(idiomaMenu);

            if(leitura.hasNextInt()) {

                opcao = leitura.nextInt();

                switch (opcao) {
                    case 1:
                        idioma = "en";
                        break;
                    case 2:
                        idioma = "pt";
                        break;
                    case 3:
                        idioma = "es";
                        break;
                    case 4:
                        idioma = "fr";
                        break;
                    default:
                        System.out.println("Opção inválida");
                }

                System.out.println("============================");
                System.out.println("Livros Registrados nesse idioma:");
                System.out.println("============================ \n");
                List<Livro> livro = livroRepository.findByLanguage(idioma);

                if(!livro.isEmpty()){
                    livro.forEach(System.out::println);
                }else {
                    System.out.println("============================");
                    System.out.println("\n Não existe livros nesse idioma!");
                    System.out.println("============================ \n");
                }

            } else{
                System.out.println("============================");
                System.out.println("Opção inválida!!!");
                System.out.println("============================ \n");
                leitura.next();
            }
    }

}

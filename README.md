
---

```markdown
# 📚 Projeto: Catálogo de Livros - Gutendex API + Spring Boot

Este projeto é uma aplicação desenvolvida com **Spring Boot** que roda via **terminal** e realiza o consumo da [API pública do Gutendex](https://gutendex.com/), permitindo buscar e armazenar informações de livros em um banco de dados **PostgreSQL**, utilizando **Spring Data JPA** para persistência.

---

## 🔧 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok
- API Gutendex (https://gutendex.com/)
- Maven

---

## 🚀 Funcionalidades

- ✅ Consumo da API do Gutendex para listar livros.
- ✅ Armazenamento de dados no banco PostgreSQL.
- ✅ Aplicação via terminal com interação simples e objetiva.
- ✅ Pesquisa por autor, título ou idioma.
- ✅ Listagem dos livros salvos no banco de dados.
- ✅ Boas práticas de estruturação de projeto com camadas (`controller`, `service`, `repository`, `model`, `dto`).

---

## ⚙️ Configuração do `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gutendexdb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
````

---

## 🧪 Como Executar

1. Clone este repositório:

   ```bash
   git clone https://github.com/seuusuario/seu-repositorio.git
   cd seu-repositorio
   ```

2. Configure o banco PostgreSQL com as credenciais no `application.properties`.

3. Execute a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

4. Interaja via terminal com os menus e opções disponíveis.

---

## 📦 Exemplos de Comandos

* Buscar livros por autor (ex: "Shakespeare")
* Salvar livros retornados da API no banco
* Listar todos os livros armazenados localmente

---

## 📝 Licença

Este projeto está sob a licença MIT. Sinta-se à vontade para usar, estudar e contribuir!

---

## 👨‍💻 Autor

Desenvolvido por \[Windson Patricio], durante estudos com Java e Spring Boot.

> Projeto realizado como parte de prática pessoal e aprendizado sobre consumo de APIs REST, persistência de dados e aplicação em linha de comando com Java.

```

SGCF – Sistema de Gestão e Controle Financeiro

Sistema desenvolvido em Java para gerenciamento de clientes, funcionários, reservas e controle financeiro.

O projeto foi construído com foco em boas práticas de desenvolvimento backend, organização em camadas e uso de ferramentas modernas de persistência e versionamento de banco de dados.

Tecnologias Utilizadas
Java
Swing (Interface gráfica)
PostgreSQL
Hibernate (ORM)
Flyway (Versionamento de banco)
BCrypt (Criptografia de senhas)
Maven (Gerenciamento de dependências)

 Funcionalidades
- Login de usuários com senha criptografada
- Recuperação de senha
- Cadastro de clientes
- Cadastro de funcionários
- Cadastro de passeios
- Controle de reservas
- Controle financeiro

 Arquitetura do Projeto

O sistema segue uma organização baseada em:

Camada Model (Entidades)
Camada Service (Regras de negócio)
Camada View (Interface Swing)
Persistência com Hibernate
Versionamento de banco com Flyway
 Como Executar o Projeto
1) Clone o repositório
git clone https://github.com/frantony07/SGCF.git
2) Configure o Banco de Dados
Instale o PostgreSQL
Crie um banco de dados
Configure as credenciais no arquivo:
src/main/resources/hibernate.cfg.xml
3) Execute as migrations

O Flyway criará automaticamente as tabelas ao iniciar o projeto.

4) Execute o projeto

Pela IDE (IntelliJ ou Eclipse)

Ou via Maven:

mvn clean install
mvn exec:java
 Autores
Kauan Alex Pereira
Frantony Alexander Nieves Torrealba
Camila Luiza Ronzzani da Silva
Alex Dias Mendoza
 Objetivo do Projeto

Projeto desenvolvido para fins acadêmicos e prática de:

Desenvolvimento Backend com Java
ORM com Hibernate
Versionamento de banco com Flyway
Criptografia de senhas
Organização de projeto em camadas

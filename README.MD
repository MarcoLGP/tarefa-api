# Trabalho — API de Tarefas

> API para gerenciar tarefas (CRUD) — projeto acadêmico feito com Spring Boot, Spring Data JPA e MySQL.

---

## Visão Geral

Este repositório contém uma API REST para gerenciar tarefas. A API expõe endpoints para criar, listar, atualizar, consultar por id e remover tarefas.

Cada `Tarefa` contém os seguintes campos:

* `id` (Long) — gerado automaticamente pelo banco
* `nome` (String) — título da tarefa
* `dataEntrega` (LocalDateTime) — data e hora de entrega (formato ISO 8601: `yyyy-MM-dd'T'HH:mm:ss`)
* `responsavel` (String) — nome do responsável

O controller principal está em `TarefaController` e é mapeado em `/tarefa`.

---

## Tecnologias

* Java 21
* Spring Boot 3.5.5
* Spring Data JPA
* MySQL (mysql-connector-j)
* Maven

---

## Endpoints

Base URL: `http://localhost:8080`

### Listar todas as tarefas

```
GET /tarefa
```

Resposta: `200 OK` com um array de tarefas.

### Criar nova tarefa

```
POST /tarefa
Content-Type: application/json
```

Exemplo de body:

```json
{
  "nome": "Revisão de código",
  "dataEntrega": "2025-10-01T18:30:00",
  "responsavel": "Mariana Silva"
}
```

Resposta: objeto `Tarefa` salvo (com `id` gerado).

### Atualizar tarefa (substituição completa)

```
PUT /tarefa/{id}
Content-Type: application/json
```

```json
{
  "id": 1,
  "nome": "Desenvolvimento da API - ajustes",
  "dataEntrega": "2025-12-12T00:00:00",
  "responsavel": "Marco RU999999"
}
```

Resposta:

* `200 OK` com a tarefa atualizada, ou
* `404 Not Found` se o `id` não existir.

### Buscar por id

```
GET /tarefa/{id}
```

Resposta:

* `200 OK` + tarefa, ou
* `404 Not Found` se não existir.

### Deletar por id

```
DELETE /tarefa/{id}
```

Resposta:

* `202 Accepted` em sucesso, ou
* `404 Not Found` se não existir.

---

## Como rodar localmente

### Pré-requisitos

* Java 21
* Maven 3.x
* MySQL (ou container com MySQL)

### application.properties

```properties
spring.application.name=trabalho-api-tarefas

spring.datasource.url=jdbc:mysql://localhost:3306/tarefas_db
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Criar banco (exemplo)

```sql
CREATE DATABASE tarefas_db;
```

---

## Exemplos `curl`

Criar:

```bash
curl -X POST "http://localhost:8080/tarefa" \
 -H "Content-Type: application/json" \
 -d '{
   "nome":"Desenvolvimento da API",
   "dataEntrega":"2025-12-12T00:00:00",
   "responsavel":"Marco RU4711565"
 }'
```

Listar:

```bash
curl http://localhost:8080/tarefa
```

Buscar por id:

```bash
curl http://localhost:8080/tarefa/1
```

Deletar:

```bash
curl -X DELETE http://localhost:8080/tarefa/1
```

## backup.sql

Abaixo está o dump do banco `tarefas_db` utilizado neste trabalho.

```sql
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: tarefas_db
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tarefa`
--

DROP TABLE IF EXISTS `tarefa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarefa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) COLLATE latin1_general_ci NOT NULL,
  `data_entrega` datetime NOT NULL,
  `responsavel` varchar(255) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarefa`
--

LOCK TABLES `tarefa` WRITE;
/*!40000 ALTER TABLE `tarefa` DISABLE KEYS */;
INSERT INTO `tarefa` VALUES (3,'Dcomentação do código','2025-10-05 18:00:00','Leandro'),(4,'Revisão do código','2025-11-15 09:00:00','Paulo André');
/*!40000 ALTER TABLE `tarefa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-08 10:21:56
```

**Restaurando o backup**

```bash
# no MySQL (linhas de comando):
mysql -u root -p
CREATE DATABASE tarefas_db;
USE tarefas_db;
SOURCE backup.sql;
```

## Estrutura do projeto

```
src/
  main/
    java/com/uninter/trabalho/tarefas/trabalho_api_tarefas/
      controller/
        TarefaController.java
      model/
        Tarefa.java
      repository/
        TarefaRepository.java
    resources/
      application.properties
```

## Autor

```
Autor: Marco Luca Giampauli Pedroza - Trabalho para matéria Desenvolvimento Web - Back End

```

---
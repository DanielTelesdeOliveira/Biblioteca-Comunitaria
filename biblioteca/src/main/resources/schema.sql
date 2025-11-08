create schema if not exists bibliotecaOnline;
use bibliotecaOnline;

CREATE TABLE Tipo_User (
    ID_Tipo INT AUTO_INCREMENT PRIMARY KEY,
    Nome_Tipo VARCHAR(100) NOT NULL
);
 
CREATE TABLE Genero (
    Id_Genero INT AUTO_INCREMENT PRIMARY KEY,
    Nome_Genero VARCHAR(100) NOT NULL
);
 
CREATE TABLE Editora (
    Id_Editora INT AUTO_INCREMENT PRIMARY KEY,
    Nome_Editora VARCHAR(255) NOT NULL
);
 
CREATE TABLE Autor (
    Id_Autor INT AUTO_INCREMENT PRIMARY KEY,
    Nome_Autor VARCHAR(255) NOT NULL
);
 
CREATE TABLE Usuario (
    ID_User INT AUTO_INCREMENT PRIMARY KEY,
    Email_User VARCHAR(255) NOT NULL UNIQUE,
    CPF_User VARCHAR(14) NOT NULL UNIQUE,
    Nome_User VARCHAR(255) NOT NULL,
    
    fk_Tipo_User_ID_Tipo INT,
    FOREIGN KEY (fk_Tipo_User_ID_Tipo) REFERENCES Tipo_User(ID_Tipo)
);
 
CREATE TABLE Livro (
    Id_Livro INT AUTO_INCREMENT PRIMARY KEY,
    Titulo_Livro VARCHAR(255) NOT NULL,
    Disponibilidade_Livro BOOLEAN DEFAULT TRUE,
    Qtd_Livro INT NOT NULL DEFAULT 1,
    
    Secao_Livro VARCHAR(100),
    Prateleira_Livro VARCHAR(100),
 
    fk_Genero_Id_Genero INT,
    fk_Editora_Id_Editora INT,
    fk_Autor_Id_Autor INT,
 
    FOREIGN KEY (fk_Genero_Id_Genero) REFERENCES Genero(Id_Genero),
    FOREIGN KEY (fk_Editora_Id_Editora) REFERENCES Editora(Id_Editora),
    FOREIGN KEY (fk_Autor_Id_Autor) REFERENCES Autor(Id_Autor)
);
 
CREATE TABLE Emprestimo (
    Id_Empre INT AUTO_INCREMENT PRIMARY KEY,
    Data_Empre DATE NOT NULL,
    Data_Previ_Devol DATE NOT NULL,
    Data_Devolucao DATE NULL,
    Multa_Empre DECIMAL(10, 2) DEFAULT 0.00,
    
    fk_Livro_Id_Livro INT,
    fk_Usuario_ID_User INT,
    
    FOREIGN KEY (fk_Livro_Id_Livro) REFERENCES Livro(Id_Livro),
    FOREIGN KEY (fk_Usuario_ID_User) REFERENCES Usuario(ID_User)
);
CREATE TABLE [pessoa] (
    id_pessoa integer NOT NULL IDENTITY(1,1),
    nome varchar(255) NOT NULL,
    endereco varchar(255) NOT NULL,
    cidade varchar(255) NOT NULL,
    estado char(2) NOT NULL,
    telefone varchar(11) NOT NULL,
    email varchar(255) NOT NULL,
    CONSTRAINT [PK_PESSOA] PRIMARY KEY CLUSTERED ([id_pessoa] ASC)
)
GO


CREATE TABLE [pessoa_fisica] (
    id_pessoa integer NOT NULL,
    cpf varchar(11) NOT NULL,
    CONSTRAINT [PK_PESSOA_FISICA] PRIMARY KEY CLUSTERED ([id_pessoa] ASC),
    CONSTRAINT [FK_PESSOA_FISICA_PESSOA] FOREIGN KEY ([id_pessoa]) REFERENCES [pessoa] ([id_pessoa])
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
GO


CREATE TABLE [pessoa_juridica] (
    id_pessoa integer NOT NULL,
    cnpj varchar(20) NOT NULL,
    CONSTRAINT [PK_PESSOA_JURIDICA] PRIMARY KEY CLUSTERED ([id_pessoa] ASC),
    CONSTRAINT [FK_PESSOA_JURIDICA_PESSOA] FOREIGN KEY ([id_pessoa]) REFERENCES [pessoa] ([id_pessoa])
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
GO


CREATE TABLE [produto] (
    id_produto integer NOT NULL IDENTITY(1,1),
    nome varchar(255) NOT NULL,
    quantidade integer NOT NULL,
    precoVenda numeric(10,2) NOT NULL,
    CONSTRAINT [PK_PRODUTO] PRIMARY KEY CLUSTERED ([id_produto] ASC)
)
GO


CREATE TABLE [usuario] (
    id_usuario integer NOT NULL IDENTITY(1,1),
    login varchar(25) NOT NULL,
    senha varchar(25) NOT NULL,
    CONSTRAINT [PK_USUARIO] PRIMARY KEY CLUSTERED ([id_usuario] ASC)
)
GO


CREATE TABLE [movimento] (
    id_movimento integer NOT NULL IDENTITY(1,1),
    id_pessoa integer NOT NULL,
    id_produto integer NOT NULL,
    id_usuario integer NOT NULL,
    quantidade integer NOT NULL,
    tipo char(1) NOT NULL,
    valor_unitario numeric(10,2) NOT NULL,
    CONSTRAINT [PK_MOVIMENTO] PRIMARY KEY CLUSTERED ([id_movimento] ASC)
)
GO

ALTER TABLE [movimento] WITH CHECK ADD CONSTRAINT [movimento_fk0] FOREIGN KEY ([id_pessoa])
REFERENCES [pessoa] ([id_pessoa])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [movimento] CHECK CONSTRAINT [movimento_fk0]
GO

ALTER TABLE [movimento] WITH CHECK ADD CONSTRAINT [movimento_fk1] FOREIGN KEY ([id_produto])
REFERENCES [produto] ([id_produto])
ON UPDATE CASCADE
GO
ALTER TABLE [movimento] CHECK CONSTRAINT [movimento_fk1]
GO

ALTER TABLE [movimento] WITH CHECK ADD CONSTRAINT [movimento_fk2] FOREIGN KEY ([id_usuario])
REFERENCES [usuario] ([id_usuario])
ON UPDATE CASCADE
GO
ALTER TABLE [movimento] CHECK CONSTRAINT [movimento_fk2]
GO

CREATE TABLE medico(
crm VARCHAR(20) NOT NULL,
nome VARCHAR(150) NOT NULL,
email VARCHAR(150) NOT NULL,
senha VARCHAR(50) NOT NULL,
CONSTRAINT pk_medico PRIMARY KEY(crm)
);

CREATE TABLE paciente(
id SERIAL NOT NULL,
nome VARCHAR(150) NOT NULL,
cpf VARCHAR(11),
rg VARCHAR(20),
foto VARCHAR(150),
chave VARCHAR(150),
sexo char NOT NULL,
grupoSanguineo varchar(3),
dataNascimento date NOT NULL,
login varchar(50) NOT NULL,
senha varchar(50) NOT NULL,
email varchar(150) NOT NULL,
CONSTRAINT pk_paciente PRIMARY KEY(id)
);


CREATE TABLE exame(
id SERIAL NOT NULL,
paciente INTEGER NOT NULL,
medico VARCHAR(20) NOT NULL,
nome VARCHAR(200) NOT NULL,
descricao VARCHAR(300),
data DATE NOT NULL,
exame VARCHAR(200),
CONSTRAINT pk_exame PRIMARY KEY(id),
CONSTRAINT fk_exame_paciente FOREIGN KEY(paciente) REFERENCES paciente(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT fk_exame_medico FOREIGN KEY(medico) REFERENCES medico(crm)
ON DELETE NO ACTION
ON UPDATE CASCADE
);

CREATE TABLE tipo(
id SERIAL NOT NULL,
descricao VARCHAR(200) NOT NULL,
CONSTRAINT pk_tipo PRIMARY KEY(id)
);

CREATE TABLE historico(
id SERIAL NOT NULL,
tipo integer NOT NULL,
descricao VARCHAR(300) NOT NULL,
observacao VARCHAR(300) NOT NULL,
dataDiagnostico DATE,
status char,
dataResolucao DATE,
CONSTRAINT pk_historico PRIMARY KEY(id),
CONSTRAINT fk_historico_tipo FOREIGN KEY(tipo) REFERENCES tipo(id)
ON DELETE NO ACTION
ON UPDATE CASCADE
);

CREATE TABLE historicopacientemedico (
medico varchar(20) NOT NULL,
paciente INTEGER NOT NULL,
historico INTEGER NOT NULL,
CONSTRAINT fk_historico_medico FOREIGN KEY(medico) REFERENCES medico(crm)
ON DELETE NO ACTION
ON UPDATE CASCADE,
CONSTRAINT fk_historico_paciente FOREIGN KEY(paciente) REFERENCES paciente(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT fk_historico_historico FOREIGN KEY(historico) REFERENCES historico(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE medicamento(
id SERIAL NOT NULL,
nome VARCHAR(300) NOT NULL,
dataInicio DATE,
dataFim DATE,
observacao VARCHAR(300),
CONSTRAINT pk_medicamento PRIMARY KEY(id)
);

CREATE TABLE medicamentopacientemedico (
medico varchar(20) NOT NULL,
paciente INTEGER NOT NULL,
medicamento INTEGER NOT NULL,
CONSTRAINT fk_medicamento_medico FOREIGN KEY(medico) REFERENCES medico(crm)
ON DELETE NO ACTION
ON UPDATE CASCADE,
CONSTRAINT fk_medicamento_paciente FOREIGN KEY(paciente) REFERENCES paciente(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT fk_medicamento_medicamento FOREIGN KEY(medicamento) REFERENCES medicamento(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE procedimento(
id SERIAL NOT NULL,
nome VARCHAR(300),
descricao VARCHAR(300) NOT NULL,
observacao VARCHAR(300),
data DATE,
CONSTRAINT pk_procedimento PRIMARY KEY(id)
);

CREATE TABLE procedimentopacientemedico (
medico varchar(20) NOT NULL,
paciente INTEGER NOT NULL,
procedimento INTEGER NOT NULL,
CONSTRAINT fk_procedimento_medico FOREIGN KEY(medico) REFERENCES medico(crm)
ON DELETE NO ACTION
ON UPDATE CASCADE,
CONSTRAINT fk_procedimento_paciente FOREIGN KEY(paciente) REFERENCES paciente(id)
ON DELETE CASCADE
ON UPDATE CASCADE,
CONSTRAINT fk_procedimento_procedimento FOREIGN KEY(procedimento) REFERENCES procedimento(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);





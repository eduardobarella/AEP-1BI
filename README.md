# Sistema de Solicitações Públicas

Sistema desenvolvido em Java para registro e acompanhamento de solicitações de serviços públicos via console (CLI).

## Visão geral

O sistema simula o fluxo básico entre cidadão e atendimento público.

O usuário pode registrar uma solicitação informando categoria, descrição, localização e prioridade, podendo optar por se identificar ou permanecer anônimo.  
Após o cadastro, é gerado um protocolo que permite acompanhar o andamento.

As solicitações passam por etapas de atendimento, e cada alteração de status é registrada com data e comentário, garantindo histórico e rastreabilidade.

## Funcionalidades

- Cadastro de solicitações
- Escolha de categoria e prioridade por menu
- Opção de anonimato
- Geração de protocolo automático
- Consulta de solicitação por protocolo
- Listagem geral de solicitações
- Atualização de status com comentário
- Registro de histórico com data
  
## Tecnologias utilizadas

- Java
- Programação Orientada a Objetos
- Interface via console (CLI)
- Estruturas em memória (ArrayList)

## Regras implementadas

### Categorias

Selecionadas por menu:

ILUMINACAO  
BURACO  
PODA  
SAUDE  
ZELADORIA  
DENUNCIA  

---

### Prioridade

Definida pelo usuário no cadastro:

ALTA  
MEDIA  
BAIXA  

---

### Status da solicitação

Controlado pelo sistema:

ABERTO  
TRIAGEM  
EM_EXECUCAO  
RESOLVIDO  
ENCERRADO  

---

### Histórico

Cada solicitação mantém um histórico de alterações contendo:

- data da atualização  
- status aplicado  
- comentário do responsável  

---

### Validações

- descrição obrigatória  
- seleção de opções via menu (evita entrada inválida)  
- uso de enums para garantir consistência dos dados  

## Estrutura do projeto

- `models` → entidades principais (Solicitacao, HistoricoStatus, enums)  
- `services` → regras de negócio (ServicoSolicitacoes)  
- `Main` → interface e fluxo do sistema  

## Observações

O sistema foi desenvolvido como versão inicial (beta), com foco em funcionamento, organização do código e aplicação de conceitos de POO.

Funcionalidades como persistência em banco de dados, controle de SLA e identificação de responsável não foram implementadas nesta versão, mas foram consideradas como possíveis evoluções do sistema.

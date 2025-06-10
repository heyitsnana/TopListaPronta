# TOP Lista Pronta

## Descrição
O projeto é um aplicativo para criar listas de compras de mercado e registrar as aquisições
por meio do escaneamento de notas/cupons fiscais (câmera ou leitura de QR code). Permite também
armazenar as informaçoes das listas e das auqisições, criação de listas com base no histórico
de compras, além de configurar alarmes que lembram a necessidade de compra de açlgum produto em
particular. O software ainda permite o compartilhamento de listas e sua sincronização, 
agrupamento de produtos por tipoe e marca, e a geração de relatórios tanto em dispositivos móveis e
desktop.

## Funcionalidades Principais
 - gerenciar usuários:
	- criar e excluir usuários (cada usuário com banco de dados próprio)
	- manter informações de compras de usuários (mesmo se usuário é excluído)
	- localizaçâo do usuário (identificar locais de compras para registro e futura informação)
 - ler notas/cupons fiscais:
	- escanear notas/cupons fiscais (uso da câmara do aparelho)
	- obter dados da nota/cupom fiscal (mercado,produto, quantidade, preço do produto, data)
 - gerenciar listas:
	- usuário elaborar listas
	- colocar/excluir itens de lista
	- sistema sugerir listas (em face da periodicidade de compras)
	- agrupar produtos por tipo e marca
 - sincronizar e compartilhar:
	- compartilhar listas em tempo real
	- compartilhar relatórios em tempo real
 - acessabilidade:
	- software estar disponível 24 horas / 365 dias por ano
	- acesso em aparelho celular e em desktop (relatórios)
 - relatórios:
	- analis de forma detalhada compras do usuário

## requisitos técnicos
 - sugestão de tecnologias:
	- linguagem de programação: Java (principal) e outras como auxiliares (PHP, Javascript,etc.)
	- banco de dados: PostgreSQL
	- OCR: biblioteca de leitura de notas/cupons fiscais (Google ML kit ou Tesseract)
	- hospedagem: github
	- CI/CD (entrega contínua/desenvolvimento contínuo): a ser definida (github actions, Jenkins)
	- qualidade do código: Java (a definir: provável sugestão: Checkstyle)
	- documentar a arquitetura: draw.io

 - estrutura do projeto: (VER)
	top-lista-pronta/
        ├── /.settinggs         # Inicializando projeto
        ├── /BancoDeDados       # INformações do Banco de Dados
        ├── /readme             # documentação do projeto, incluindo README.md
        ├── /bin                # Código-fonte (class)
        ├── /src                # Código-fonte (java)
        ├── .classpath          # classpath do projeto Java
        └── .project            # armazena metadados do projeto
         
- Ambiente de desenvolvimento: configuração:
	- ideal: utilizar plataforma Eclipse
	- alternativa: salvar código em projeto do Eclipse
	- instalar ferramente de OCR (caso necessário)
	- clonar repositório
	- instalar dependências (se necessário)
	- banco de dados: conectar banco de dados com ambiente de trabalho
	- testar configuração: executar programa básico para validar o ambiente
 - Pipeline de CI/CD (entrega contínua/desenvolvimento contínuo):
	- ferramenta: a ser definida (github actions, Jenkins)
	- configuração: configurar pipeline para rodar linters e testes automatizados em cada commit
	- qualidade: adicionar linters ao pipeline e configirar geraçao de relatórios
## licença
 - o repositório é privado, com acordo de confidencialidade

## contato
 - FULANA DE TAL (fulanadetal@fulanadetal.com.br)

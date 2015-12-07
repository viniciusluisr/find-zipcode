# find-zipcode

# Tecnologias:
- Spring boot com data-jpa starter para um serviço bootável que pode ser instalado e startado fácilmente, e o starter escolhido auxilia no uso do spring data para persistência
- Hsqldb para gerar um banco de dados relacional em memória
- Apache commons lang3 para a utilização do HashCodeBuilder e ToStringBuilder
- Lombok para a geração automática de getters e setters
- Log4j para geração de logs
- Google Guava para regexp
- Suite de testes:
    - Os testes unitários foram criados utilizando JUnit
    - Para a criação de objetos e cenários foi utilizado o Fixture Factory por sua facilidade na criação de objetos dinâmicos
    - Para criar objetos mockados foi utilizado o Mockito
    - Todas as classes de teste utilizam duas classes utilitárias: TestSupport que provê operações comuns a qualquer tipo de teste unitário e a FixtureTestSupport que provê o TestFixtureSupport para criação de objetos a partir de Templates definidas
    - Os testes de integração foram criados utilizando o starter test do spring boot que facilita bastante nas chamadas e tratamento dos endpoints
- maven para build da aplicação e gerenciamento de dependências
- Java 8

# Estratégias, design e arquitetura:
- A aplicação possui 3 camadas lógicas muito bem definidas e coesas: Repositories, Services e Resources. O Repository foi criado utilizando Spring Data que permite que seja criada apenas a Interface do repositório de dados e a implementação é gerada pelo Spring. Foi utilizado o JpaRepository que já possui operações básicas de CRUD. Os Services possuem uma interface de contrato que garante coesão e a documentação dos métodos que também garante organização para a leitura do código. O Services aplicam as regras de negócio e constraints, ou seja, toda a inteligência de manipulação de dados e lógica de programação são realizadas pelos Services, e estes injetam e acessam os Repositories para acesso a dados. Os Resources são responsáveis por atender a camada HTTP e definir os endpoints da aplicação, aplicando corretamente a utilização dos verbos HTTP, versionamento e URLs. Os Resources injetam e acessam os Services para consultar enviar informações vindas do mundo externo.
- Todos os parâmetros de métodos de interface pública são final para garantir que seu estado não mude. 
- Para que exista uma massa de dados inicial para testes, foi criada a classe Startup que cria alguns registros no banco Hsqldb no boot da aplicação

# Questão 1
- Para a busca de CEPs foi utilizado um Iterator que basicamente recebe o CEP a ser buscado e monta uma lista com todos os possíveis CEPs derivados deste, ou seja, aplicando a substituição por zero da direita para a esquerda. Em seguida, é realizada uma iteração sobre esses CEPs e a cada iteração uma busca é realizada no repositório de dados.

# Questão 2
- Utilizado Spring Data com JPA Repository para auxiliar no acesso a dados
- A estratégia utilizada é a mesma descrita no tópico (Estratégias, design e arquitetura), para cada operação existe um Endpoint em um Resource, que injeta e acessa um Service que por fim consulta um Repository.

#ENDPOINTS:
- CEP:
[GET] http://127.0.0.1:8080/v1/zipcodes/{cep} - Consultar um Endereço a partir de um CEP existente (deve conter exatamente 8 dígitos numéricos)
CEPs já existentes: 83838383, 98543012, 78543934, 64598884 e 50492398

- ENDEREÇO:
[POST] http://127.0.0.1:8080/v1/addresses - Cadastrar um novo Endereço (zipcode, number, street, neighborhood, city e state são campos obrigatórios)
[GET] http://127.0.0.1:8080/v1/addresses/{id} - Consultar um Endereço a partir de um Id único e inteiro
[PUT] http://127.0.0.1:8080/v1/addresses - Atualiza um endereço existente
[DELETE] http://127.0.0.1:8080/v1/addresses/{id} - Exclui um Endereço a partir de um Id único e inteiro

# HOW TO USE
- Para usar o projeto é necessário ter o Java 8 e maven instalados, com isso, basta ir até a raiz do projeto onde o pom está localizado e executar o comando mvn clean package spring-boot:run


# Questão 3
# Tecnologias:
- maven para build da aplicação e gerenciamento de dependências
- Java 8
- Junit e Hamcrest para testes unitários

# Estratégias, design e arquitetura:
- Foi utilizado um HashSet para controlar os caracteres não repetidos. Os caracteres que não se repetem vão sendo armazenados na iteração da String passada como parâmetro, a cada iteração é verificado se o mesmo já existe no HashSet characteres, caso não exista, este é armazenado no ArrayList unrepeated, e caso ele exista no HashSet, este caracter é removido do ArrayList unrepeated. No final, eu adiciono todos os caracteres do ArrayList unrepeated que são os que não se repetiram durante a iteração e adiciono em um iterator que é responsável pela implementação dos métodos next() e hasNext() do contrato Stream.

# Questão 4
- Quando qualquer requisição para uma URL da Netshoes (http://www.netshoes.com.br) for realizada, será primeiramente para um servidor DNS correspondente ao IP do servidor hospedeiro.
- Após o DNS realizar o discovery do IP do servidor hospedeiro, a requisição será direcionada para o servidor que hospeda o sistema da Netshoes a ser acessado. Essa requisição pode ser encaminhada ou direcionada para um servidor Apache/Express/NGINX onde essa requisição poderá ser tratada, filtrada, formatada, criptografada ou qualquer outro processo antes que esta chegue no real destino. A partir disso, a requisição pode passar inclusive por outros servidores os quais podem ser responsáveis por disponibilizar o conteúdo referente ao site da Netshoes.
- O site da Netshoes é construído por meio de conteúdos dinâmicos e estáticos. 
- O conteúdo estático pode ser cacheado em memória, arquivo ou engines de busca com o objetivo de optimizar o tempo de resposta ao usuário (estes conteúdos geralmente são pesados) e não causar garga-los na infraestrutura da Netshoes, ferramentas como Apache/Express/NGINX podem ajudar a implementar soluções de cache, outras ferramentas como Redis, Hazelcast e EhCache podem ser bastante úteis, não só para o conteúdo estático como para o dinâmico também.
- O conteúdo dinâmico pode ser escalado e acessível por meio de load balancing e apis distintas (por domínio, microserviços, soa etc) as quais também podem ser cacheadas.
- Com o conteúdo dinâmico e estático prontos, falta apenas rendenizar o HTML que deve ser retornado para o cliente. O browser irá rendenizar o HTML solicitando as imagens, javascripts, css e outros arquivos.
- Estes são possíveis passos entre a requisição de um cliente até a rendenização do site da Netshoes.

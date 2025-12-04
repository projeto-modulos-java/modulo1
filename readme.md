# Modulo 1 - API de cadastro de usuário

Nesse projeto temos uma api responsavel por manter o cadastro dos usuarios. Ela irá manter os dados dos usuarios e podera ser acessada pelas demais apis do projeto para que possam utilizar dados dos clientes. Para isso apenas necessitam enviar o id do usuario, que é compartilhado com todas as apis através do banco MySQL, na tabelas Users. 

Nesse projeto foi adotada uma arquitetura hexagonal, atraves de adaptadores para a comunicao entre as camadas da aplicao, os componentes estao separados nas 4 camadas: repositories, services, controllers e entities. A aplicacoa compoe uma arquitetura de microservicos e é responsavel apenas pelo cadastro dos usuarios.

é possivel também listar os usuarios atraves de paginas e sizes, usando, por exemplo, a api /user?page=1&size=5

Foi escolhida a arquitetura hexagonal para melhorar a divisao das camadas da aplicacao e evisdenciar as responsabilidades de cada componente, além de facilitar a alteracao de componentes, como repositorios ou repositorios, sem precisar alterar outras partes do codigo.

Obs: Para que se usar o graylog, é necessario ativa-lo através das configuracoes de inicialização, que sao mostradas no terminal ao iniciar o servidor, em que são mostradas a url do servidor e um usuario de admin e uma senha padrao , e configurar um input do tipo RawText TCP na porta 5555. 
# inovebd
Trabalho da Disciplina de Projeto de Bancos de Dados

# Setup
  Para poder fazer o setup primeiro deve-se baixar uma IDE para poder trabalhar no código, recomendo IntelliJ, com a conta de email do inf é possivel pegar a conta de estudante grátis, mas também existe um periodo de 30 dias no qual e possivel desfrutar dessa IDE sem problema.<br>
  Também se deve baixar o docker para poder rodar o banco de dados e o Tomcat para rodar o server. <br>
  Depois que baixar o docker rode os seguintes comandos
```
    sudo docker pull mysql
    sudo docker run --name i9db -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 -d mysql
    sudo docker start i9db

```
  Depois que fizer baixe o projeto pelo intellij. Para isso abra o intellij, vá em file->new->project from version controll->git e coloque https://github.com/Scheffel-V/inovebd.git na URL.<br>
  Após isso é necessario esperar o projeto abrir, quando ele abrir clique com o botão direito em cima da pasta i9, na guia que fica abaixo de Project no canto esquerdo da tela, irá abrir um menu clique em adicionar como um projeto maven. <br>
  Então vá em Run-> Edit Configurations clique no mais ache o Tomcat remote, onde diz application server clique em configure e adicione no Tomcat home o local onde extraiu o Tomcat.<br>
  
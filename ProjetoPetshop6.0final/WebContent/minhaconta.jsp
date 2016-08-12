<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          
         <script type="text/javascript" src="javascript.js"></script>
          <link rel="shortcut icon" href="favicon.png" />
          


	<title>Petshop CãoKilate</title>
	<style type="text/css">
a:link, a:visited {
	text-decoration: none
	}
a:hover {
	text-decoration: underline; 
	color: #f00
	}
a:active {
	text-decoration: none
	}
</style>
	<style type="text/css">
table {
	float: left;
}
</style>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="estilo.css">
	<link rel="stylesheet" type="text/css" href="css.css">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<link href="css/font-awesome.css" rel="stylesheet">
	<link href="css/bootstrap-social.css" rel="stylesheet" >
</head>
<body>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<!-- The mobile navbar-toggle button can be safely removed since you do not need it in a non-responsive implementation -->
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Menu</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		
		</div>
		<br>
		<img src="imagens/banner2.png" width="350">
		    
		
		
		<!-- Note that the .navbar-collapse and .collapse classes have been removed from the #navbar -->
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-center">
				<c:if test="${tipo != 'Administrador'}">
					<li class="verde"><a href="index.jsp">Home</a></li>
					<li class="separador"><a><span class="glyphicon glyphicon-minus glyphicon-separador"></span></a></li>
					<li class="verde"><a href="listaservicosdisponivel">Serviços</a></li>
					<li class="separador"><a><span class="glyphicon glyphicon-minus glyphicon-separador"></span></a></li>
					<li class="verde"><a href="buscaanimais">Consultas</a></li>
					<li class="separador"><a><span class="glyphicon glyphicon-minus glyphicon-separador"></span></a></li>
					<li class="verde"><a href="sobrenos.jsp">Sobre nós</a></li>
				</c:if> <!-- Fecha Menu do Cliente -->
				
				
				<c:if test="${tipo eq 'Administrador'}">
				<li class="verde"><a href="index.jsp">Home
				<li class="separador"><a><span class="glyphicon glyphicon-minus glyphicon-separador"></span></a></li>
				
				
				
				<li class="dropdown">
						<a href="#" class="nav-dropdown-tamanho dropdown-toggle js-activated">Gerenciar Serviços<span class="caret" /></a>
						<ul class="dropdown-menu">
							<li class="cadastrar"><a href="cadastroServ.jsp">Cadastrar<span class="glyphicon-cadastrar glyphicon glyphicon-plus-sign pull-right " /></a></li>
							<li class="listar"><a href="listaservicosadm">Listar<span class="glyphicon-listar glyphicon glyphicon-list pull-right" /></a></li>
						</ul>
					</li>
					<li class="separador"><a><span class="glyphicon glyphicon-minus glyphicon-separador"></span></a></li>
				
				<li class="dropdown">
						<a href="#" class="nav-dropdown-tamanho dropdown-toggle js-activated">Gerenciar Agenda<span class="caret" /></a>
						<ul class="dropdown-menu">
							<li class="cadastrar"><a href="listaragendaservicoadm">Serviços</a></li>
							<li class="listar"><a href="listar_agendaconsultaadm">Consultas</a></li>
						</ul>
					</li>
				
				
				
				
				
				</c:if>
			
			
			</ul>
			<ul class="nav navbar-nav navbar-right verde">
				<li>	
				<c:if test="${empty nomeUsuario}"> <!-- Aparecer logar apenas quando nao tiver logado -->
					<button type="button" class="botao" data-toggle="modal" data-target="#myModal">
						Logar &nbsp;
						<span class="glyphicon glyphicon-log-in"></span>
					</button>
				</c:if><!-- Apenas deslogado -->
				</li>
				
					<c:if test="${not empty nomeUsuario}"> <!-- botao sair apenas logado -->
						<a href="logoff"><button type="button" class="botao3">Sair &nbsp; <span class="glyphicon glyphicon-log-in"></span></button></a>
					</c:if><!-- botao sair apenas logado -->
				
				
				
				
				
				</ul>
					
					<c:if test="${tipo != 'Administrador'}"> <!-- Aparecer apenas quando for cleinte -->
						<c:if test="${not empty nomeUsuario}">
							<a href="minhaconta"><button type="button" class="botao2"><c:out value="${nomeUsuario}"></c:out> &nbsp; </button></a>
						</c:if>
					</c:if>	<!-- Aparecer apenas quando for cleinte -->			
				
				
				
			
				
				
				
			
		</div><!--/.nav-collapse -->
	</div>
</nav>

<!-- Modals -->
<div class="container">
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header modal-header-login" style="padding:35px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4><span class="glyphicon glyphicon-lock"></span> Alterar Senha</h4>
			</div>
			<div class="modal-body" style="padding:40px 50px;">
				<form action="alterasenha" method="post">
					<div class="form-group has-success">
						
						<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Senha Atual</label>
						<input type="password" name="atualsenha" class="form-control" id="psw" placeholder="******" style="width:500px;"><br>
						
								<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Nova Senha </label>
						<input type="password" name="novasenha" class="form-control" id="psw" placeholder="******" style="width:500px;"><br>
						
						
								<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Nova Senha </label>
						<input type="password" name="confnovasenha" class="form-control" id="psw" placeholder="******" style="width:500px;">
					</div>
					<table><tr><td><button type="submit" class="btn btn-success btn-block" style="width:100px;">Atualizar</button></td><td>&nbsp;&nbsp;</td></tr></table>
				</form>
			</div>
			<div class="modal-footer modal-footer-login" style=" text-align: center; background-color:#BFDAAF">
                            Copyright Controle Local &copy;2015 - Todos os direitos reservados.
                        </div>
		</div>
    </div>
</div> 
</div>


<div class="container">
<div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header modal-header-login" style="padding:35px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4><span class="glyphicon glyphicon-lock"></span> Deletar Conta</h4>
			</div>
			<div class="modal-body" style="padding:40px 50px;">
				<form action="excluiusuario" method="post">
					<div class="form-group has-success">
						<label for="usrname"><span class="glyphicon glyphicon-user"></span> Usuário</label>
						<input type="text" class="form-control" name="login" id="usrname" placeholder="ex: joao" style="width:500px;"><br>
					
						<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Senha</label>
						<input type="password" name="senha" class="form-control" id="psw" placeholder="******" style="width:500px;">
						<input type="hidden" name="tipoexclusao" value="1">
					</div>
					<table><tr><td><button type="submit" class="btn btn-success btn-block" style="width:100px;">Deletar</button></td><td>&nbsp;&nbsp;</td><td></td></tr></table>
				</form>
			</div>
			<div class="modal-footer modal-footer-login" style=" text-align: center; background-color:#BFDAAF">
                            Copyright Controle Local &copy;2015 - Todos os direitos reservados.
                        </div>
		</div>
    </div>
</div> 
</div>





<div class="container">
<div class="modal fade" id="orcaModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header modal-header-orcamento" style="padding:35px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4>Orçamento</h4>
			</div>
			<div class="modal-body" style="padding:20px 10px;">
			
				<form id="registrationForm" class="form-horizontal">

					<div class="form-group">
						<label class="col-xs-3 control-label">Nome</label>
						<div class="col-xs-5">
							<input type="text" class="form-control" name="nome" style="width:300px;"/>
						</div>
					</div>

					<div class="form-group">
						<label class="col-xs-3 control-label">Email</label>
						<div class="col-xs-5">
							<input type="email" class="form-control" name="email" style="width:300px;"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-3 control-label">Telefone</label>
						<div class="col-xs-5">
							<input type="text" class="form-control" name="telefone" style="width:300px;"/>
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-9 col-xs-offset-3">
							<button type="submit" class="btn btn-primary btn-lg" name="signup" value="Sign up">Enviar</button>
						</div>
					</div>
				</form>
				
			</div>
			<div class="modal-footer modal-footer-login">
				<p align="center">Copyright 2015</p>
			</div>
		</div>
    </div>
</div> 
</div>
	<br><br><br><br><br><br>
<div class="container">
<div class="col-xs-12 col-sm-9">

	<div id="minha conta" background-color = "#eee">	

			

				<c:if test="${empty loginUsuario}">

					<center>
						<font color="red"><h2>Você precisa estar logado para
								ter acesso a sua conta</h2></font>
					</center>

				</c:if>




				<c:if test="${not empty loginUsuario }">

					<c:if test="${not empty erroAtualizacao}">
						<ul>
							<c:forEach var="e" items="${erroAtualizacao}">
								<li><c:out value="${e}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
					
					<c:if test="${not empty sucess}">
						<script>
							 
       			//<!-- alert ("${sucess}");--!> 
       			 msg('${sucess}');  // esta é uma chamada para a função que deve ser definido na seção <head> ou outro arquivo JS. 
   			 
						</script>
					</c:if>
				
				
<h2><center>Minha Conta</center></h2>
							<form method="post" action="validacadastro" name="cadastro">

								<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dados Pessoais:
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Endereço:</h2>

								<table>

									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td><label>Nome:<p></label></td>
										<td><input type="text" class="form-control"  name="nome" size="40"
											value="${dadosUsuario.nome }"><br></td>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									</tr>

									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td>CPF:</td>
										<td><input type="text" class="form-control" name="cpf" size="25"
											value="${dadosUsuario.cpf }"
											onblur="ValidarCPF(cadastro.cpf);"
											onkeypress="MascaraCPF(cadastro.cpf);" maxlength="14"
											readonly="true"><br></td>
									</tr>

									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td>Telefone:</td>
										<td><input type="text" class="form-control" name="fone" size="25"
											value="${dadosUsuario.fone}"
											onblur="ValidaTelefone(cadastro.fone)"
											onkeypress="MascaraTelefone(cadastro.fone);" maxlength="15"><br></td>
									</tr>

									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td>E-mail:</td>
										<td><input type="text" class="form-control" name="email" size="40"
											value="${dadosUsuario.email}"><br></td>
									</tr>

									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td>Login:</td>
										<td><input type="text" class="form-control" name="login" size="24"
											value="${dadosUsuario.login}"><br></td>
									</tr>

									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td>Senha:</td>
										<td><input type="password" class="form-control"  name="senha" size="24"
											value="${dadosUsuario.senha}" readonly="true"><!-- <input type="submit" value="Alterar Senha" onclick="location.href='altsenha.jsp' "> 


<button><a href='altsenha.jsp'>Alterar Senha</a></button>  --><br></td>
									</tr>

									<tr>
										<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td><font color="red">
												Confirme:
										</font></td>
										<td><input type="password" class="form-control"  name="confirmaSenha" size="24"
											value="${param.confirmaSenha}"><br></td>
									</tr>

								</table>







								<table>

									<tr>
										<td>CEP:</td>
										<td><input type="text" class="form-control" name="cep" size="20"
											value="${dadosUsuario.endereco.cep}" id="cep"
											onblur="ValidaCep(cadastro.cep);"
											onkeypress="MascaraCep(cadastro.cep);" maxlength="10"><br></td>
									</tr>



									<tr>
										<td>Logradouro:</td>
										<td><input type="text" class="form-control" name="logradouro" size="25"
											id="rua" value="${dadosUsuario.endereco.logradouro}"><br></td>
									</tr>

									<tr>
										<td>Número:</td>
										<td><input type="text" class="form-control"  name="numero" size="4" id="numero"
											value="${dadosUsuario.endereco.numero}"><br></td>
									</tr>

									<tr>
										<td>Bairro:</td>
										<td><input type="text" class="form-control" name="bairro" size="17"
											id="bairro" value="${dadosUsuario.endereco.bairro}"><br>

										</td>
									</tr>

									<tr>
										<td>Cidade:</td>
										<td><input type="text" class="form-control"  name="cidade" size="16"
											id="cidade" value="${dadosUsuario.endereco.localidade}"><br></td>
									</tr>

									<tr>
										<td>Estado:</td>
										<td><input type="text" class="form-control" name="estado" size="4" id="uf"
											value="${dadosUsuario.endereco.uf}"><br></td>
									</tr>

									<tr>
										<td>Complemento:</td>
										<td><input type="text" class="form-control"  name="complemento" size="25"
											value="${dadosUsuario.endereco.complemento}"><br></td>
									</tr>

									<tr>
										<td><input type="hidden" name="tipo"
											value="${dadosUsuario.tipo.values}"><br></td>
									</tr>

									<tr>
										<td><input type="hidden" name="atualizar" value="1">
										</td>
									</tr>
									

								</table>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"
												data-toggle="modal" data-target="#myModal" value="Alterar Senha"></a>
								

								 
 								<br><br>
								<center>
									<input
										type="button" class="botao4" data-toggle="modal" data-target="#myModal2" value="Deletar Conta"></a> &nbsp;&nbsp;<input
										type="submit" class="botao4" value="Atualizar">
</form>
					
				<br><br>	<br>
					<div id="hr2"></div>
					

					<!-- APRESENTACAO ANIMAIS -->




					<br>
					<br>


					<c:if test="${tipo != 'Administrador'}">







							<c:if test="${not empty dadosAnimais }">

										



		


<div class="table-responsive">
		<h2 align="center">Animais</h2>
  		<table class="table table-bordered table-hover">
											<thead>

												<tr>

													<th>Nome</th>

													<th>Raça</th>

													<th>Tipo</th>

													<th>Porte</th>

													<th>Peso</th>

													<th>Alterar</th>

													<th>Excluir</th>


												</tr>

											</thead>

											<tbody>
												<c:forEach var="a" items="${dadosAnimais}">
													<tr>

														<td class="lalign"><c:out value="${a.nome}"></c:out></td>

														<td><c:out value="${a.raca}"></c:out></td>

														<td><c:out value="${a.tipo}"></c:out></td>

														<td><c:out value="${a.porte}"></c:out></td>

														<td><c:out value="${a.peso}"></c:out></td>

														<td><a href="alteraanimal?id=${a.codigo}">Alterar</a></td>

														<td><a href="deleteanimal?id=${a.codigo}">Excluir</a></td>

													</tr>
												</c:forEach>
											</tbody>



										</table>
									</div>

									<br> 

							</c:if>
<a href='adcanimal.jsp' class="pagina"><input type="button" class="botao5"
												value="Adicionar Animal"></a>



						
					</c:if>
 <br><br>
					<div id="hr2"></div>

					<!-- APRESENTACAO DAS CONSULTA -->


					<br>

				<c:if test="${tipo != 'Administrador'}">
				




							<c:if test="${empty dadosAgendamento}">
								<center>
									<font color="red">
										<h2>Não há nenhum agendamento</h2>
									</font>
								</center>

							</c:if>

							<c:if test="${not empty dadosAgendamento }">




<div class="table-responsive">
		<h2 align="center">Agenda</h2>
  		<table class="table table-bordered table-hover">








											<thead>

												<tr>

													<th><span>Data</span></th>

													<th><span>Hora</span></th>

													<th><span>Taxi</span></th>

													<th><span>Compromisso</span></th>

													<th><span>Animal</span></th>

													<th><span>Detalhar</span></th>


												</tr>

											</thead>

											<tbody>
												<c:forEach var="agenda" items="${dadosAgendamento}">
													<tr>

														<td class="lalign"><c:out
																value="${agenda.trataString()}"></c:out></td>

														<td><c:out value="${agenda.hora}:00"></c:out></td>

														<td><c:out value="${agenda.buscar ? 'Sim':'Não' }"></c:out></td>

														<td><c:out
																value="${agenda.consulta eq null ? 'Serviço':'Consulta' }"></c:out></td>

														<td><c:out value="${agenda.animal.nome}"></c:out></td>

														<td><a href="detalhamento?id_agenda=${agenda.codigo}">Detalhar</a></td>



													</tr>
												</c:forEach>
											</tbody>



										</table>
								</div>



	
							</c:if>
						
						</c:if>
				</c:if><br><br>	<br>
					<div id="hr2"></div><br><br></div>							
	</div><!-- Fecha o jumbotron -->


</div><!--/.col-xs-12.col-sm-9-->





</div>

<footer class="footer">
    <div class="container">
		<p class="text-footer">Copyright © 2015</p>
    </div>
</footer>

<script>
    var _gaq=[['_setAccount','UA-28756144-1'],['_trackPageview']];
      (function(d,t) {
          var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
          s.parentNode.insertBefore(g,s)
    }(document,'script'));      

    $(document).ready(function(){
      $("#myBtn").click(function(){
        $("#myModal").modal();
      });
  });

  $(document).ready(function(){
      $("#myBtn").click(function(){
          $("#myModal").modal();
      });
  });
</script>
<script src="js/hover-dropdown.js"></script>
<script>
    // very simple to use!
    $(document).ready(function() {
      $('.js-activated').dropdownHover().dropdown();
    });
  </script>

  <script>
      var _gaq=[['_setAccount','UA-28756144-1'],['_trackPageview']];
      (function(d,t) {
          var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
          s.parentNode.insertBefore(g,s)
      }(document,'script'));      
  </script>

</body>
</html>


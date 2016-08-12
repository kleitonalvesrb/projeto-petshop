<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          
         <script type="text/javascript" src="javascript.js"></script>
          <link rel="shortcut icon" href="favicon.png" />
          


	<title>Petshop CãoKilate</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="estilo.css">
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
				<h4><span class="glyphicon glyphicon-lock"></span> Entrar</h4>
			</div>
			<div class="modal-body" style="padding:40px 50px;">
				<form role="form" method="post" action="validalogin">
					<div class="form-group has-success">
						<label for="usrname"><span class="glyphicon glyphicon-user"></span> Usuário</label>
						<input type="text" class="form-control" name="login" id="usrname" placeholder="ex: joao" style="width:500px;">
					</div>
					<div class="form-group has-success">
						<label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Senha</label>
						<input type="password" name="senha" class="form-control" id="psw" placeholder="******" style="width:500px;">
					</div>
					<table><tr><td><button type="submit" class="btn btn-success btn-block" style="width:100px;">Login</button></td><td>&nbsp;&nbsp;</td><td>  <a href="cadastro.jsp"> <input type="button" class="btn btn-success btn-block" style="width:100px;" value="Cadastrar"></a></td></tr></table>
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
			<div class="modal-header modal-header-login" style="padding:35px 50px;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4> Fale Conosco</h4>
			</div>
			<div class="modal-body" style="padding:40px 50px;">
				<form action="contatocontroller" method="post">
		
							
				
				<table> <tr><td><label>Assunto:&nbsp;<p></label></td><td> <input type="text" class="form-control" name="assunto" size="40"><p></td></tr><p>
				 <tr><td><label>Email:<p></label> </td><td> <input type="text" class="form-control" name="email" size="40"> <p></td></tr>
				</table>
				<textarea name="comentario" class="form-control" rows="5" cols="50"></textarea>
				
				<br><button type="submit" class="btn btn-success btn-block" style="width:100px;">Login</button>
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

	<div class="jumbotron">
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
		<c:if test="${not empty agendaConsulta }">
		
		<div class="table-responsive">
		<h2 align="center">Lista de Consultas </h2>
  		<table class="table table-bordered table-hover">
    		
    		<thead>
    		

										<th>Data</th>

										<th>Horario</th>

										<th>Descrição</th>

										<th>Animal</th>
													
										<th>Dono</th>
													
										<th>Taxi</th>

										<th>Alterar</th>

										<th>Excluir</th>
    		</thead>
    		<tbody>
<c:forEach var="a" items="${agendaConsulta}">
													<tr>

														<td class="lalign"><c:out value="${a.trataString()}"></c:out></td>

														<td><c:out value="${a.hora}:00"></c:out></td>

														<td><c:out value="${a.consulta.resumoDescricao()}"></c:out></td>

														<td><c:out value="${a.animal.nome}"></c:out></td>
														
														<td><c:out value="${a.usuario.cpf eq '00000000000' ? 'Sem Dono' : a.usuario.primeiroNome}"></c:out></td>

														<td><c:out value="${a.buscar ? 'Sim':'Não' }"></c:out></td>
														<c:if test="${a.usuario.cpf != '00000000000'}">
														      <td><a href="alteraconsultaadm?codigoAgenda=${a.codigo}">Alterar</a></td>
														</c:if>
														<c:if test="${a.usuario.cpf eq '00000000000'}">
															<td> Alterar</td>
														</c:if>
														<td><a href="deleteconsultaadm?codigoAgenda=${a.codigo}">Excluir</a></td>

													</tr>
												</c:forEach>              </tbody> 
             
  		</table>
  		 </c:if>
							<c:if test="${empty agendaConsulta}">
							
								<center> <font color="red"> <h2>Não tem nenhuma consulta agendada<br><br><br><br></h2></font></center>
							</c:if>
	</div>
		
	</div><!-- Fecha o jumbotron -->


</div><!--/.col-xs-12.col-sm-9-->




<div class="row">
	<div class="col-xs-6 col-sm-3">
		<div class="container">
			<div class="panel panel-success text-center" style="width:320px; height:250px;">
				<div class="panel-heading">
					<strong>Contato</strong>
				</div>
					<a href="#orcaModal" data-toggle="modal">
					<br />
					</a><br>
					<span class="glyphicon glyphicon-earphone"> </span> (61) 3391-1990 <br>
					<br /><br />
					
					<button type="button" class="botaofale" data-toggle="modal" data-target="#orcaModal">Fale Conosco</button>
				</div>
			</div>
		</div>
	</div>
	
	
</div>

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


<link rel="stylesheet" type="text/css" href="css.css"> 
<div id="login">
	<center>
						<center>
					<div id="bordaModal">


<br><br>

						<fieldset style="border: 2px solid #2580a2; border-radius: 10px;">
							<br>
						<h1>Login</h1>
					<br>
					<form action="validalogin" method="post">
						<center>
							<img src='imagens/usuario.png' width="18" height="18" /> <input
								type="text" name="login" value=""><br> <img
								src='imagens/senha.png' width="18" height="18" /> <input
								type="password" name="senha" value=""><br>
							<br> 
							 <a href="cadastro.jsp"> <input type="button" value="Cadastrar">&nbsp;&nbsp;</a><input type="submit" value="Entrar">
						</center>
					</form>
					<br><br><br><br><br>
					</fieldset>
					
				
			
		</center>
	
</div>
<div id="pagina2">
	<div id="bordaModal">
		<center>
			<form action="contatocontroller" method="post">
			
<br><br>

						<fieldset style="border: 2px solid #2580a2; border-radius: 10px;">
							
						<h1>Contato</h1>
								
				<img src="imagens/telefone.png" width="20" height="20" /> (61) 3344-9988 <br>
				<br> Assunto: <input type="text" name="assunto" size="38"><br>
				<br> Email: <input type="text" name="email" size="40"><br>
				<br>
				<textarea name="comentario" rows="5" cols="50"></textarea>
				<br>
				<br> <input type="submit" name="enviar" value="Enviar"> <br>
</fieldset>			</form>
		</center>

	</div>
</div>
<div id="pagina3">
<div id="bordaModal">


<br><br>

						<fieldset style="border: 2px solid #2580a2; border-radius: 10px;">
							<br><center><br><br>
						<h1>Alterar Senha</h1>
    	<form action="alterasenha" method="post">
            Senha atual: <input type="password" name="atualsenha"><br>
            Nova senha: <input type="password" name="novasenha"><br>
            Nova senha: <input type="password" name="confnovasenha"><br><br>
            <input type='submit' value='Atualizar'><br><br><br>
        </form>
        </center>
        </div>
</div>

<div id="pagina4">
<div id="bordaModal">


<br><br>

						<fieldset style="border: 2px solid #2580a2; border-radius: 10px;">
							<br><center><br><br>
						<h1>Deletar Conta</h1>
    	<form action="excluiusuario" method="post">
            Login: <input type="text" name="login"><br>
            Senha: <input type="password" name="senha"><br>
            <input type='submit' value='Deletar'><br><br><br>
            <input type="hidden" name="tipoexclusao" value="1">
        </form>
        </center>
        </div>
</div>
<%@page import="Servers.Messaging"%>
<%@ page contentType='text/html; charset=utf-8'%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Электронная среда для тестирования обучающихся</title>
	</head>
	<body>
		<table width="1300"  border="1" align="center" cellspacing="0" cellpadding="10" bordercolor="darkblue">
        	<tr align = "center" style = "color: darkblue; font-size: 25px;" bgcolor="lightyellow">
            	<td colspan="2" height="100">
            		<p>Электронная информационно-образовательная среда</p>
            		<p>Тестирование обучающихся</p>
            	</td>
        	</tr>
        	<tr>
            	<td height="400">
            		<form action="AutoriziseServlet">
						<table align="center">
							<tr align="center">
								<td colspan="2" style = "font-size: 18px;">
									<p>Для входа в систему введите логин и пароль:</p>
								</td>
							</tr>
							<tr align="center">
								<td>Логин: </td>
								<td><input type="text" name="login" size = 25></td>
							</tr>
							<tr align="center">
								<td>Пароль: </td>
								<td><input type="password" name="password" size = 25></td>
							</tr>
							<tr align="center">
								<td colspan="2"><input type="submit" value="Войти"></td>
							</tr>
						</table>
					</form>
					<p align="center" colspan="2" style = "color: red;"> 
						<%= Messaging.ErrorMessage(request.getAttribute("errorLogin")) %>
					</p>
				</td>
        	</tr>
        	<tr bgcolor="lightyellow">
            	<td colspan="2" height="30">Разработал: студент группы ПО-51б Алексеев Владислав, 2018 год</td>
        	</tr>
    	</table>
	</body>
</html>
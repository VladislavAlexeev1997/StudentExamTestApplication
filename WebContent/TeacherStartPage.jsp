<%@ page contentType='text/html; charset=utf-8'%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Вход в личный кабинет преподавателя</title>
	</head>
	<body>
		<table align="center" width="1300"  border="1" cellspacing="0" cellpadding="10" bordercolor="darkblue">
        	<tr style = "color: darkblue; font-size: 25px;" bgcolor="lightyellow">
            	<td colspan="2" height="100" align="center" >
            		<p>Электронная информационно-образовательная среда</p>
            		<p>Тестирование обучающихся</p>
            	</td>
        	</tr>
        	<tr align="center">
        		<td width = "180" height="400">
            		<table>
						<tr align="center">
							<td>
								<input type="submit" size = "120" value="Просмотр предметов">
							</td>
						</tr>
						<tr align="center">
							<td>
								<input type="submit" value="Добавить новый тест">
							</td>
						</tr>
						<tr align="center">
							<td>
								<input type="submit" value="Привязать группу студентов">
							</td>
						</tr>
						<tr align="center">
							<td>
								<input type="submit" value="Редактировать тест">
							</td>
						</tr>
					</table>
				</td>
            	<td>
            		<h4>Здравствуйте, <%= request.getAttribute("fullName") %>! <input type="submit" value="Выход"></h4>
					<p>Вы зашли под профилем преподавателя</p>
				</td>
        	</tr>
    	</table>
	</body>
</html>
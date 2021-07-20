package Servers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClientClasses.User;

@WebServlet("/AutoriziseServlet")
public class AutoriziseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User nowUser = new User ();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (nowUser.getUserData(request.getParameter("login"), request.getParameter("password"))) {

			request.setAttribute("fullName", nowUser.getNames());
			if (nowUser.getRoleUser().equals("преподаватель"))
				request.getRequestDispatcher("TeacherStartPage.jsp").forward(request, response);
		}
		else {
			request.setAttribute("errorLogin", "¬ведены неверно логин или пароль!");
			request.getRequestDispatcher("Autorization.jsp").forward(request, response);
		}
		out.close();
	}

}

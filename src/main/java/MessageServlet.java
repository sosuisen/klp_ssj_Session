
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MessageBean;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet(urlPatterns = { "/message", "/clear" })
public class MessageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var session = request.getSession();

		if (session.getAttribute("history") == null) {
			session.setAttribute("history", new ArrayList<MessageBean>());
		}

		var path = request.getServletPath();
		if (path.equals("/clear")) {
			var list = (ArrayList<MessageBean>) session.getAttribute("history");
			list.clear();
		}

		request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		var mesBean = new MessageBean(request.getParameter("name"), request.getParameter("message"));
		var session = request.getSession();
		var list = (ArrayList<MessageBean>) session.getAttribute("history");
		list.add(mesBean);

		request.getRequestDispatcher(
				"/WEB-INF/message.jsp").forward(request, response);
	}
}

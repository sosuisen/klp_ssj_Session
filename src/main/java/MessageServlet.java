
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet(urlPatterns = { "/message", "/clear" })
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("history") == null) {
			session.setAttribute("history", new ArrayList<String>());
		}

		// 発展課題 4a
		// ArrayListのクリア
		String path = request.getServletPath();
		if (path.equals("/clear")) {
			ArrayList<String> list = (ArrayList<String>) session.getAttribute("history");
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

		String message = request.getParameter("message");
		HttpSession session = request.getSession();

		if (session.getAttribute("history") != null) {
			ArrayList<String> list = (ArrayList<String>) session.getAttribute("history");
			list.add(message);
		}
		request.getRequestDispatcher(
				"/WEB-INF/message.jsp").forward(request, response);
	}

}

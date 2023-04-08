
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MessageBean;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet(urlPatterns = { "/message", "/clear" })
public class MessageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("history") == null) {
			session.setAttribute("history", new ArrayList<MessageBean>());
		}

		String path = request.getServletPath();
		if (path.equals("/clear")) {
			ArrayList<MessageBean> list = (ArrayList<MessageBean>) session.getAttribute("history");
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
		String name = request.getParameter("name");
		String message = request.getParameter("message");

		MessageBean mesBean = new MessageBean();
		mesBean.setName(name);
		mesBean.setMessage(message);
		
		HttpSession session = request.getSession();
		ArrayList<MessageBean> list = (ArrayList<MessageBean>) session.getAttribute("history");
		list.add(mesBean);

		request.getRequestDispatcher(
				"/WEB-INF/message.jsp").forward(request, response);
	}

}


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Books;
import customTools.DBUtil;

/**
 * Servlet implementation class addTocart
 */
@WebServlet("/addTocart")
public class addTocart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addTocart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		HttpSession session = request.getSession(true);
		int book_id = Integer.parseInt(request.getParameter("bookid"));
		List<Books> shoopingCart;
		if (session.getAttribute("shoopingCart") == null) {
			shoopingCart = new ArrayList();
		} else {
			shoopingCart = (List<Books>) session.getAttribute("shoopingCart");
		}
		Books book = new Books();
		String q = "select b from Books b where b.id=" + book_id;
		TypedQuery<Books> aq = em.createQuery(q, Books.class);
		List<Books> list = aq.getResultList();

		try {
			book = list.get(0);
			shoopingCart.add(book);
			String alert = "Added to cart!";
			request.setAttribute("ProductList", alert);
			session.setAttribute("shoopingCart", shoopingCart);
				getServletContext().getRequestDispatcher("/index.jsp").forward(
					request, response);

		} catch (Exception e) {
			System.out.println("" + e.getStackTrace());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

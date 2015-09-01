

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Books;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		//String productid = request.getParameter("productid");
		List<Books> shoopingCart = null;
		String alert;
		String bookList = "";
		if(session.getAttribute("shoopingCart")==null){
			alert="cart is empty!";
			// Set response content type
			response.setContentType("text/html");

			request.setAttribute("ProductList", alert);
			
			getServletContext().getRequestDispatcher("/index.jsp")
					.include(request, response);
			alert = "";
		}else{
			shoopingCart=(List<Books>) session.getAttribute("shoopingCart");

		for(Books book: shoopingCart)
        {
			bookList+="<li class=\"list-group-item\"><img src=\""+book.getImage()
            		+"\" style=\"width:120px;height:120px\"> <a href=\"Details?bookid="
            		+book.getId()+"\">"+book.getTitle()+"</a><br> <b>Author: "+book.getAuthor()+"</b> "
            		+"<b>Price: $"+book.getPrice()+"</b><br>"
            		+"</li>";
        }
		
		
		// Set response content type
				response.setContentType("text/html");

				request.setAttribute("ProductList", bookList);
				
				getServletContext().getRequestDispatcher("/index.jsp")
						.forward(request, response);
				bookList = "";
		}
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

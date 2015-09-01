

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Books;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		List<Books>shoopingCart=(List<Books>) session.getAttribute("shoopingCart");
		String bookList="<div class=\"container\">";
		double sum =0, tax=0, total=0;
	
		for(Books book: shoopingCart)
        {
			bookList+="<li class=\"list-group-item\"><a href=\"Details?bookid="
            		+book.getId()+"\">"+book.getTitle()+"</a><br> <b>Author: "+book.getAuthor()+"</b> <br> "
            		+"<b>Price: $"+book.getPrice()+"</b></br>"
            		+"</li>";
			sum+=book.getPrice().doubleValue();
        }
		
		tax=Math.floor(sum*.19);
		System.out.println(tax );
		
		total= tax+sum;
		bookList+="<li class=\"list-group-item\"><br> <b>Sub Total: $"+sum+"</b><br> "
            		+"<b>Tax: $"+tax+"</b><br>"
            		+"<b>Total: $"+total+"</b><br>"
            		+"</li>";
				
		bookList+="</div>";
		// Set response content type
				response.setContentType("text/html");

				request.setAttribute("ProductList", bookList);
				
				getServletContext().getRequestDispatcher("/index.jsp")
						.forward(request, response);
				bookList = "";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

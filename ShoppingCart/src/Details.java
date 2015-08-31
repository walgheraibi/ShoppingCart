

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

import model.Books;
import customTools.DBUtil;

/**
 * Servlet implementation class Details
 */
@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Books book = new Books();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String isuppdateing = ""+request.getParameter("toUpdate");
		if(!isuppdateing.equals("Update"))
		{
			try
		
		{
			int book_id = Integer.parseInt(request.getParameter("bookid"));
			
	
		String message = "";
		String q="select b from Books b where b.id="+book_id;
		
		TypedQuery<Books>aq =em.createQuery(q,Books.class);
		System.out.println(""+aq);
		List<Books> list=aq.getResultList();
		System.out.println("query reult:"+aq.getResultList());
		message= " <div class=\"container\">";
		for(Books temp:list)
		{	
			System.out.println("In look: " +temp);
			System.out.println("In look: " +temp.getAuthor());
			book= temp;
			message+="<h4>"+
	                  temp.getTitle()+"</h4> <p>"+
                      "<b>Author: </b>"+
                      temp.getAuthor()+"</p>  <p><b>Price: $</b>"+
                      temp.getPrice()+"</p> <p> <b>Description: </b>"+
                      temp.getDescription()+"</p> "+
                     " <img src=\""+ temp.getImage()+"\" class=\"img-responsive\" alt=\"Cinque Terre\">"
      +            // "<a class=\"btn btn-primary\" onclick=\"<%Details.Update(request, response);%>\"> Add to Cart </a>";     
			
"<input type='button' class=\"btn btn-primary\" name=\"toUpdate\" value=\"Add to Cart\" onclick=\"location.href='/ShoppingCart/Details';\">"; 

		}
		// +
		//booklist.add(temp);
		
	message+="</div>";
	request.setAttribute("ProductList", message);

	getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

		}
		catch(Exception e)
		{
			System.out.println(""+e.getStackTrace());
			System.out.println("XXXXXXXx");
		}
		}
		else
		{
			ArrayList<Books> booklist = new ArrayList<Books>();
			booklist.add(book);
			System.out.print("it is update");
			System.out.print(booklist);
		}
	}

	protected void Update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Books> booklist = new ArrayList<Books>();
		booklist.add(book);
		System.out.print("in Update");
		System.out.print(booklist);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

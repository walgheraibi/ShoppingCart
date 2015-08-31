

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Books;
import customTools.DBUtil;

/**
 * Servlet implementation class Products
 */
@WebServlet("/Products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Products() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try
		{
	
		Books book = new Books();
	
		String message = "";
		String q="select b from Books b ";
		
		TypedQuery<Books>aq =em.createQuery(q,Books.class);
		System.out.println(""+aq);
		List<Books> list=aq.getResultList();
		System.out.println("query reult:"+aq.getResultList());
		message= " <div class=\"container\"><div class=\"row\">";
		for(Books temp:list)
		{	
			message+="<div class=\"item  col-sm-6 col-sm-6\"> <div class=\"thumbnail\"> <div class=\"caption\">"+
	                  " <h4>"+
	                  temp.getTitle()+"</h4> <p>"+
                      "<b>Author: </b>"+
                      temp.getAuthor()+"</p>  <p><b>Price: $</b>"+
                      temp.getPrice()+"</p> <p> <b>Description: </b>"+
                      temp.getDescription()+"</p> "+
                     " <img src=\""+ temp.getImage()+"\" class=\"img-responsive\" alt=\"Cinque Terre\" height=\"550\" width=\"250\">"+
                      "<a class=\"btn btn-primary\" href=\"Details?bookid="+temp.getId()+"\"> Detail </a>"+
                      "</div></div></div>";          

		}
 	   
	message+="</div>";
	request.setAttribute("ProductList", message);
	getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			System.out.println(""+e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

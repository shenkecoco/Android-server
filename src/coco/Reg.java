package coco;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reg
 */
@WebServlet("/Reg")
public class Reg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 新建服务对象
		String username = request.getParameter("newname");
		username = new String(username.getBytes(), "UTF-8");
		String password = request.getParameter("mima");
		System.out.println(username + "--" + password);

		// 新建服务对象
		Sql sql = new Sql();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean reged = sql.insertdata(username,password);
		System.out.println(reged);
		if (reged) {
			request.getSession().setAttribute("success", "regsuccess");
			out.print("regsuccess");
		} else {
			request.getSession().setAttribute("failed", "regfailed");
			System.out.println("Failed");
			out.print("regfailed");
		}
		out.flush();
		out.close();
	}

	/**
	 * Web程序在运行的时候,会给每一个新的访问者建立一个HttpSession,这个Session是用户身份的唯一表示。
session可以存放这个用户的一些经常被用到的信息,例如:用户名,权限
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

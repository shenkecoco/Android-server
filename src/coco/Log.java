package coco;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Log")
public class Log extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		username = new String(username.getBytes(), "UTF-8");
		String password = request.getParameter("password");
		System.out.println(username + "--" + password);

		// 新建服务对象
		Sql sql = new Sql();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 验证处理
		int loged = sql.login(username, password);
//		System.out.println(loged);
		if (loged==1) {
			System.out.println("Success");
			request.getSession().setAttribute("success", "loginsuccess");
			out.print("linksuccess");
		} else {
			if(loged==2) {
				request.getSession().setAttribute("failed1", "loginfailed1");
				System.out.println("Failed1");
				out.print("linkfailed1");
			}
			if(loged==3) {
				request.getSession().setAttribute("failed2", "loginfailed2");
				System.out.println("Failed2");
				out.print("linkfailed2");
			}
			
			//request.getSession().setAttribute("failed", "loginfailed");
		//	System.out.println("Failed");
		//	out.print("linkfailed");
		}
		 out.flush();
		 out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

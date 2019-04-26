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
		// �½��������
		String username = request.getParameter("newname");
		username = new String(username.getBytes(), "UTF-8");
		String password = request.getParameter("mima");
		System.out.println(username + "--" + password);

		// �½��������
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
	 * Web���������е�ʱ��,���ÿһ���µķ����߽���һ��HttpSession,���Session���û���ݵ�Ψһ��ʾ��
session���Դ������û���һЩ�������õ�����Ϣ,����:�û���,Ȩ��
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

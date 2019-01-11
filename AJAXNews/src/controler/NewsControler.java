package controler;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import entity.News;
import entity.Page;
import jdbc.NewsJDBC;
import util.JSONUtil;

/**
 * Servlet implementation class NewsControler
 */
@WebServlet("/NewsControler")
public class NewsControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsControler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("method") != null) {
			if ("doDelete".equals(request.getParameter("method"))) {
				doDelete(request, response);
			} else if("doUpdate".equals(request.getParameter("method"))){
				doUpdate(request, response);
			}else{
				doPaging(request, response);
			}
		} else {
			getFirstPage(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("doPut".equals(request.getParameter("method"))) {
			doPut(request, response);
		} else {
			NewsJDBC nc = new NewsJDBC();
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			News news = new News(id, title);
			int row = nc.updateNews(news);
			if (row > 0) {
				response.sendRedirect("updateNewsSuc.jsp");
			} else {
				response.sendRedirect("updateNewsFail.jsp");
			}
		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		NewsJDBC nc = new NewsJDBC();
		int row = nc.deleteNewsById(id);
		Object JSONObj =  JSONObject.toJSON(row);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(JSONObj.toString());
		out.close();
	}
	
	/*@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		NewsJDBC nc = new NewsJDBC();
		int row = nc.deleteNewsById(id);
		if (row > 0) {
			response.sendRedirect("deleteNewsSuc.jsp");
		} else {
			response.sendRedirect("deleteNewsFail.jsp");
		}
	}*/
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsJDBC nc = new NewsJDBC();
		String title = request.getParameter("title");
		News news = new News(title);
		int row = nc.addNews(news);
		if (row > 0) {
			response.sendRedirect("addNewsSuc.jsp");
		} else {
			response.sendRedirect("addNewsFail.jsp");
		}
	}

	protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewsJDBC nc = new NewsJDBC();
		int id = Integer.parseInt(request.getParameter("id"));
		News news = nc.getNewsById(id);
		request.setAttribute("news", news);
		request.getRequestDispatcher("updateNews.jsp").forward(request, response);
	}

	// 获取第一页的内容和总页数
	protected void getFirstPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNum=1;
		int pageSize = 5;
		NewsJDBC nc = new NewsJDBC();
		List<News> ls = nc.getPageNews(pageNum, pageSize);
		int totalRecords = nc.getTotalRecords();
		Page<News> pageList = new Page<News>(totalRecords, pageNum, pageSize, ls);
		request.setAttribute("pageList", pageList);
		request.setAttribute("kk", 1);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	// 分页
		protected void doPaging(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			int pageSize = 5;
			String requestCurrentPage = request.getParameter("pageNum");
			int pageNum = Integer.parseInt(requestCurrentPage);
			NewsJDBC nc = new NewsJDBC();
			List<News> ls = nc.getPageNews(pageNum, pageSize);
			int totalRecords = nc.getTotalRecords();
			Page<News> pageList = new Page<News>(totalRecords, pageNum, pageSize, ls);
			JSONUtil.writeJSON("page",pageList , response);
		}


}

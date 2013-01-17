package net.study.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import net.study.puzzle.Field;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.study.puzzle.Field;

public class Game extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private Field field;
       
    public Game() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(){
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		
		Field field = (Field)httpSession.getAttribute("field");
		if (field == null){
			field = new Field();
			httpSession.setMaxInactiveInterval(600);
			httpSession.setAttribute("field", field);
		}
		
		if("shuffle".equals(request.getParameter("action"))){
			field.shuffle();
		} else if ("move".equals(request.getParameter("action"))){
			int i = Integer.parseInt((String)request.getParameter("name"));
			field.move(i);
		}
		
		if(field.isVictory()){
			request.setAttribute("field", field);
			request.getRequestDispatcher("Victory.jsp").forward(request, response);
		} else {
			request.setAttribute("field", field);
			request.getRequestDispatcher("PuzzleViewer.jsp").forward(request, response);
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

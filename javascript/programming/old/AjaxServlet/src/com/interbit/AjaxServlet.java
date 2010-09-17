package com.interbit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxServlet extends HttpServlet {

	ArrayList<String> colors=new ArrayList<String>();
	ArrayList<String> animals=new ArrayList<String>();
	ArrayList<String> users=new ArrayList<String>();
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//super.doGet(req, resp);
		Map<String,String> map=req.getParameterMap();
		if(!map.containsKey("type")) {
			resp.getWriter().println("please provide type parameters");			
		}
		if(!map.containsKey("pref")) {
			resp.getWriter().println("please provide pref parameters");			
		}
		String type=req.getParameter("type");
		//resp.getWriter().println("type is "+type);
		String pref=req.getParameter("pref");
		//resp.getWriter().println("pref is "+pref);
		resp.getWriter().print("[ ");
		boolean first=true;
		for(int i=0;i<colors.size();i++) {
			String curr_color=colors.get(i);
			if(curr_color.startsWith(pref)) {
				if(!first) {
					resp.getWriter().print(", ");
				}
				resp.getWriter().print("\""+curr_color+"\"");
				first=false;
			}
		}
		resp.getWriter().println(" ]");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
	}
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		colors.add("red");
		colors.add("green");
		colors.add("blue");
		colors.add("purple");
		colors.add("yellow");
		colors.add("cyan");
		colors.add("pink");
		colors.add("grey");
		colors.add("black");
		colors.add("white");
		colors.add("orange");
		colors.add("magenta");
		colors.add("turquize");
		colors.add("crimson");
		colors.add("lemon");
		colors.add("cream");
		colors.add("amber");
		users.add("mark");
		users.add("doron");
		animals.add("horse");
		animals.add("fish");
		animals.add("whale");
		animals.add("pig");
		animals.add("goat");
		
	}

}

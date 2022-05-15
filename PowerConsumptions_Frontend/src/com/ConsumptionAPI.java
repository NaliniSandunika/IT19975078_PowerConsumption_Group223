package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConsumptionAPI")
public class ConsumptionAPI extends HttpServlet {
	
	Consumption consumptionObj = new Consumption();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = consumptionObj.insertConsumption(request.getParameter("accountNumber"),
		request.getParameter("premisesID"),
		request.getParameter("month"),
		request.getParameter("date"),
		request.getParameter("units"));
		response.getWriter().write(output);
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		String output = consumptionObj.updateConsumption(paras.get("hidConsumptionIDSave").toString(),
		paras.get("accountNumber").toString(),
		paras.get("premisesID").toString(),
		paras.get("month").toString(),
		paras.get("date").toString(),
		paras.get("units").toString());
		response.getWriter().write(output);
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		String output = consumptionObj.deleteConsumption(paras.get("consumptionID").toString());
		response.getWriter().write(output);
		
	}

}

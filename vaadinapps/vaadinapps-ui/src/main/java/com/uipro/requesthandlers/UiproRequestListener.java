package com.uipro.requesthandlers;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.uipro.dataservices.DataService;
import com.uipro.dataservices.UiproRequestDataService;
import com.uipro.entity.UiproRequest;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * Application Lifecycle Listener implementation class UiproRequestListener
 *
 */
@WebServlet(urlPatterns = "/mylistner", name = "UiproRequestListener", asyncSupported = true)
public class UiproRequestListener extends HttpServlet {

	private static final long serialVersionUID = -1571699010528307414L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// super.service(request, response);
		String webInfPath = getServletContext().getRealPath("/WEB-INF");
		String filePath = webInfPath + "\\rpHolder.txt";
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
			reader.close();
		} catch (Exception e) {

		}
		// String reqParams = request.getParameter("uid");
		// System.out.println(reqParams);
		JSONParser parser = new JSONParser();
		JSONObject reqParamsJson;
		// Creating file for the first time
		// myfile.createNewFile();
		try {
			reqParamsJson = (JSONObject) parser.parse(jb.toString());
			setReqParamsAsDataObject(reqParamsJson);
			// FileWriter file = new FileWriter(filePath);
			// file.write(reqP.toJSONString());
			System.out.println("Successfully converted to JSON object...");
			System.out.println("\nJSON Object: " + reqParamsJson.toJSONString());
			//ServletContext servletContext = VaadinServlet.getCurrent().getServletContext();
			//((MyUI) UI.getCurrent()).refreshPage();
			//Page.getCurrent().reload();
			// file.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setReqParamsAsDataObject(JSONObject reqParamsJson) {
		DataService.set(Integer.parseInt(reqParamsJson.get("uid").toString()), 
				Boolean.getBoolean(reqParamsJson.get("isNewPage").toString()), 
				reqParamsJson.get("template").toString(),
				reqParamsJson.get("element").toString(), 
				reqParamsJson.get("elementType").toString(), 
				reqParamsJson.get("elementName").toString(),
				reqParamsJson.get("elementId").toString(), 
				reqParamsJson.get("elementPosition").toString(), 
				reqParamsJson.get("elementColor").toString(),
				reqParamsJson.get("elementValue").toString());
	}
}

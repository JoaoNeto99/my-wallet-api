package carteiraJava.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import carteiraJava.controller.MontaDados;
import carteiraJava.model.Rendimento;

@WebServlet(urlPatterns = {"/periodo"}, name = "GetRendimentoPeriodoServlet", description = "GetRendimentoPeriodoServlet Returns json")
public class GetRendimentoPeriodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetRendimentoPeriodoServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//periodo?data1=2019-01-17&data2=2020-04-28
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		Rendimento rendi = new MontaDados().retornaRendimentoPeriodo(data1, data2);
		
		Gson gson = new Gson();
		String userJSON = gson.toJson(rendi);
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json ");
		response.setCharacterEncoding("UTF-8");
		pw.write(userJSON);
		pw.close();
	}
}

package carteiraJava.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import carteiraJava.controller.MontaDados;
import carteiraJava.model.Acoes;


@WebServlet(urlPatterns = {"/acoes"}, name = "GetAcoesServlet", description = "GetAcoesServlet Returns json")
public class GetAcoesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Acoes> acoes = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		acoes = new MontaDados().retornaAcoes();
		Gson gson = new Gson();
		String userJSON = gson.toJson(acoes);
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json ");
		response.setCharacterEncoding("UTF-8");
		pw.write(userJSON);
		pw.close();
		
		
	}

}

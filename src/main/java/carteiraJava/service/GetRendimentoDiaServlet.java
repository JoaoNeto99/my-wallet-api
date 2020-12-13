package carteiraJava.service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import carteiraJava.controller.MontaDados;
import carteiraJava.model.Rendimento;

@WebServlet(urlPatterns = {"/dia"}, name = "GetRendimentoDiaServlet", description = "GetRendimentoDiaServlet Returns json")
public class GetRendimentoDiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetRendimentoDiaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dia?acao=ITUB4F&data=2019-01-17
		String acao = request.getParameter("acao");
		String data = request.getParameter("data");
		
		Rendimento rendi = new MontaDados().retornaRendimentoDia(data, acao);
		Gson gson = new Gson();
		String userJSON = gson.toJson(rendi);
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json ");
		response.setCharacterEncoding("UTF-8");
		pw.write(userJSON);
		pw.close();
	}

}

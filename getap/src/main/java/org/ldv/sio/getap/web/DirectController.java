package org.ldv.sio.getap.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Exemple de controleur
 * 
 * ayant des actions à tester dans une interaction Ajax
 */
@Controller
public class DirectController {

	@RequestMapping(value = "/ws/test", method = RequestMethod.GET)
	public @ResponseBody
	void testAjax(HttpServletResponse response) {
		response.setContentType("application/pdf");
		try {
			OutputStream out = response.getOutputStream();
			out.write("<BingoAjax />".getBytes());
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		// return "<BingoAjax />";
	}
}

package org.ldv.sio.getap.web;

import org.ldv.sio.getap.app.FormAjoutUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Web controller for hotel related actions.
 */
@Controller
@RequestMapping("/admin/*")
public class AdminController {

	/**
	 * Default action, displays the use case page.
	 * 
	 * 
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void index() {

	}

	@RequestMapping(value = "ajoutUser", method = RequestMethod.GET)
	public String ajoutUser(FormAjoutUser formAjout, Model model) {

		return "admin/ajoutUser";
	}

}

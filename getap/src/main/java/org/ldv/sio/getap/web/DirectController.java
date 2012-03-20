package org.ldv.sio.getap.web;

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
	String testAjax() {
		return "Bingo Ajax !";
	}
}

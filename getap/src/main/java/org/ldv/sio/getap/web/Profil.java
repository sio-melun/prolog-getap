package org.ldv.sio.getap.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.ldv.sio.getap.app.FormEditProfil;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.ldv.sio.getap.utils.UtilSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Web controller for hotel related actions.
 */
@Controller
@RequestMapping("/profil/*")
public class Profil {

	@Autowired
	@Qualifier("DBServiceMangager")
	private IFManagerGeTAP manager;

	public void setManagerEleve(IFManagerGeTAP serviceManager) {
		this.manager = serviceManager;
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String editUserById(@RequestParam("id") String id,
			FormEditProfil formUser, Model model) {
		User me = UtilSession.getUserInSession();
		System.out.println("TEST id recu :" + me.getId());

		User currentUser = manager.getUser(Long.valueOf(id));

		System.out.println("User : " + currentUser);
		if (currentUser.getId().equals(UtilSession.getUserInSession().getId())) {
			formUser.setLogin(currentUser.getLogin());
			formUser.setMail(currentUser.getMail());
			// TODO as-t-on besoin de passer tout cela à la vue ?
			Long idUser = currentUser.getId();
			model.addAttribute("utilisateur", currentUser);
			model.addAttribute("sesDCTAPeleve",
					manager.getAllDCTAPByEleve(currentUser));
			model.addAttribute("etat0", manager.getAllDCTAPByEtat(0, idUser));
			model.addAttribute("etat1", manager.getAllDCTAPByEtat(1, idUser));
			model.addAttribute("etat2", manager.getAllDCTAPByEtat(2, idUser));
			model.addAttribute("etat4", manager.getAllDCTAPByEtat(4, idUser));
			model.addAttribute("etat8", manager.getAllDCTAPByEtat(8, idUser));
			model.addAttribute("etat32", manager.getAllDCTAPByEtat(32, idUser));
			model.addAttribute("etat64", manager.getAllDCTAPByEtat(64, idUser));
			model.addAttribute("etatsup1000",
					manager.getAllDCTAPModifByEtat(idUser));
		}
		return "profil/edit";
	}

	@RequestMapping(value = "doedit", method = RequestMethod.POST)
	public String doeditUserById(FormEditProfil formUser,
			BindingResult bindResult, Model model) {

		if (bindResult.hasErrors()) {
			return "profil/edit";
		} else {
			User me = UtilSession.getUserInSession();
			User user = manager.getUser(Long.valueOf(me.getId()));

			String oldHash = getEncodedPassword(formUser.getOldPass());
			if (oldHash.equals(user.getHash())
					&& formUser.getFirstPass().equals(formUser.getSecondPass())) {
				String hash = getEncodedPassword(formUser.getFirstPass());
				user.setHash(hash);
			} else {
				user.setHash(user.getHash());
			}
			if (formUser.getMail().equals(null)
					|| formUser.getMail().equals("")) {
				user.setMail(null);
			} else {
				user.setMail(formUser.getMail());
			}
			manager.updateProfil(user);

			return "redirect:/app/" + user.getRole() + "/index";
		}
	}

	public static String getEncodedPassword(String key) {
		byte[] uniqueKey = key.getBytes();
		byte[] hash = null;
		try {
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		StringBuffer hashString = new StringBuffer();
		for (int i = 0; i < hash.length; ++i) {
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			} else {
				hashString.append(hex.substring(hex.length() - 2));
			}
		}
		return hashString.toString();
	}

}

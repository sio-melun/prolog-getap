package org.ldv.sio.getap.web;

import org.ldv.sio.getap.app.AccPersonalise;
import org.ldv.sio.getap.app.DemandeConsoTempsAccPers;
import org.ldv.sio.getap.app.FormAjoutDctap;
import org.ldv.sio.getap.app.FormDemandeConsoTempsAccPers;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.ldv.sio.getap.utils.UtilSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Web controller for eleve related actions.
 */
@Controller
@RequestMapping("/eleve/*")
public class ElevesController {

	@Autowired
	@Qualifier("DBServiceMangager")
	private IFManagerGeTAP manager;

	public void setManagerEleve(IFManagerGeTAP serviceManager) {
		this.manager = serviceManager;
	}

	/**
	 * Default action, displays the use case page.
	 * 
	 * 
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void index() {

	}

	@RequestMapping(value = "mesdctap", method = RequestMethod.GET)
	public String mesdctap(Model model) {
		User me = UtilSession.getUserInSession();
		model.addAttribute("mesdctaps", manager.getAllDCTAPByEleve(me));
		Long id = me.getId();
		model.addAttribute("etat0", manager.getAllDCTAPByEtat(0, id));
		model.addAttribute("etat1", manager.getAllDCTAPByEtat(1, id));
		model.addAttribute("etat2", manager.getAllDCTAPByEtat(2, id));
		model.addAttribute("etat3", manager.getAllDCTAPByEtat(3, id));

		model.addAttribute("etat41", manager.getAllDCTAPByEtat(41, id));
		model.addAttribute("etat42", manager.getAllDCTAPByEtat(42, id));
		model.addAttribute("etat43", manager.getAllDCTAPByEtat(43, id));
		model.addAttribute("etat44", manager.getAllDCTAPByEtat(44, id));
		model.addAttribute("etat45", manager.getAllDCTAPByEtat(45, id));
		model.addAttribute("etat46", manager.getAllDCTAPByEtat(46, id));
		model.addAttribute("etat47", manager.getAllDCTAPByEtat(47, id));

		model.addAttribute("etat5", manager.getAllDCTAPByEtat(5, id));
		model.addAttribute("etat6", manager.getAllDCTAPByEtat(6, id));
		return "eleve/mesdctap";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deleteDCTAPById(@PathVariable String id, Model model) {
		DemandeConsoTempsAccPers currentDctap = manager.getDCTAPById(Long
				.valueOf(id));
		// Test que la DCTAP appartient à la bonne personne
		if (currentDctap.getEleve().equals(UtilSession.getUserInSession())) {
			if (manager.deleteDCTAPById(Long.valueOf(id))
					&& (currentDctap.getEtat() == 0 || currentDctap.getEtat() == 3)) {
				manager.deleteDCTAP(currentDctap);
				return "redirect:/app/eleve/mesdctap";
			}
		}

		return "redirect:/app/eleve/mesdctap";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String editDCTAPById(@RequestParam("id") String id,
			FormDemandeConsoTempsAccPers formDctap, Model model) {

		System.out.println("TEST id recu :" + formDctap.getId());

		DemandeConsoTempsAccPers currentDctap = manager.getDCTAPById(Long
				.valueOf(id));

		System.out.println("DCTAP : " + currentDctap);

		// valorise le bean de vue avec le dctap courant
		formDctap.setId(currentDctap.getId()); // en provenance d'un champ caché
		formDctap.setDateAction(currentDctap.getDateAction());
		formDctap.setProfId(currentDctap.getProf().getId());
		// formDctap.setProfNom(currentDctap.getProf().getNom());
		formDctap.setIdEleve(currentDctap.getEleve().getId());
		formDctap.setAccPersId(currentDctap.getAccPers().getId());
		formDctap.setMinutes(currentDctap.getMinutes());

		model.addAttribute("lesProfs", manager.getAllProf());
		model.addAttribute("etat", manager.getDCTAPById(formDctap.getId())
				.getEtat());
		model.addAttribute("lesAP", manager.getAllAP());
		return "eleve/edit";
	}

	@RequestMapping(value = "doedit", method = RequestMethod.POST)
	public String doeditDCTAPById(FormDemandeConsoTempsAccPers formDctap,
			BindingResult bindResult, Model model) {
		System.out.println("TEST :" + formDctap.getId());
		System.out.println("TEST id eleve :" + formDctap.getIdEleve());
		System.out.println("TEST :" + model);
		if (manager.getAPById(formDctap.getAccPersId()) != null) {
			System.out.println("TEST AP :"
					+ manager.getAPById(formDctap.getAccPersId()).getNom());
		} else {
			System.out.println("TEST AP : " + formDctap.getAccPersNom());
		}
		System.out.println("TEST minutes :" + formDctap.getMinutes());

		// java.sql.Date.valueOf(formDctap.getDateAction());
		User prof = manager.getUserById(formDctap.getProfId());
		if (prof == null)
			bindResult.rejectValue("profId", "required",
					"Erreur d'identifiant de professeur");

		if (bindResult.hasErrors()) {
			model.addAttribute("lesProfs", manager.getAllProf());
			return "eleve/edit";
		} else {
			User user = UtilSession.getUserInSession();
			DemandeConsoTempsAccPers dctapForUpdate = manager.getDCTAPById(Long
					.valueOf(formDctap.getId()));
			if (dctapForUpdate.getEtat() == 0 || dctapForUpdate.getEtat() == 3) {

				AccPersonalise acc = new AccPersonalise(null,
						formDctap.getAccPersNom(), 1, user.getId());
				if (manager.getAPById(formDctap.getAccPersId()) != null) {
					acc = manager.getAPById(formDctap.getAccPersId());
					dctapForUpdate.setAccPers(manager.getAPById(formDctap
							.getAccPersId()));
				} else {
					manager.addAP(acc);
					dctapForUpdate.setAccPers(manager.getAPByNom(formDctap
							.getAccPersNom()));
				}

				dctapForUpdate.setDateAction(formDctap.getDateAction());
				dctapForUpdate.setMinutes(formDctap.getMinutes());

				dctapForUpdate.setProf(manager.getUserById(formDctap
						.getProfId()));
				dctapForUpdate.setEtat(3);
				manager.updateDCTAP(dctapForUpdate);
			}

			return "redirect:/app/eleve/mesdctap";
		}
	}

	@RequestMapping(value = "ajoutdctap", method = RequestMethod.GET)
	public String ajoutUser(FormAjoutDctap formAjout, Model model) {

		model.addAttribute("lesProfs", manager.getAllProf());
		model.addAttribute("lesAP", manager.getAllAP());

		formAjout.setAnneeScolaire(UtilSession.getAnneeScolaireInSession());
		formAjout.setEleveId(UtilSession.getUserInSession().getId());
		formAjout.setEtat(0);

		return "eleve/ajoutdctap";
	}

	@RequestMapping(value = "doajout", method = RequestMethod.POST)
	public String doajoutUser(FormAjoutDctap formAjout,
			BindingResult bindResult, Model model) {
		model.addAttribute("lesProfs", manager.getAllProf());
		model.addAttribute("lesAP", manager.getAllAP());

		formAjout.setAnneeScolaire(UtilSession.getAnneeScolaireInSession());
		formAjout.setEleveId(UtilSession.getUserInSession().getId());
		formAjout.setEtat(0);

		System.out.println("TEST :" + formAjout.getId());
		System.out.println("TEST id eleve :" + formAjout.getEleveId());
		if (manager.getAPById(formAjout.getAccPersId()) != null) {
			System.out.println("TEST AP :"
					+ manager.getAPById(formAjout.getAccPersId()).getNom());
		} else {
			System.out.println("TEST AP : " + formAjout.getAccPersNom());
		}
		System.out.println("TEST :" + model);
		System.out.println("TEST annee scolaire : "
				+ manager.getCurrentAnneeScolaire());

		if (bindResult.hasErrors())
			return "eleve/ajoutdctap";
		else {
			AccPersonalise acc = new AccPersonalise(null,
					formAjout.getAccPersNom(), 1, formAjout.getEleveId());
			if (manager.getAPById(formAjout.getAccPersId()) != null) {
				acc = manager.getAPById(formAjout.getAccPersId());
			} else {
				manager.addAP(acc);
			}
			DemandeConsoTempsAccPers dctap = new DemandeConsoTempsAccPers(
					formAjout.getId(), manager.getCurrentAnneeScolaire(),
					formAjout.getDate(), formAjout.getMinutes(),
					manager.getUserById(formAjout.getProfId()), acc,
					manager.getUserById(formAjout.getEleveId()),
					formAjout.getEtat());

			manager.addDCTAP(dctap);

			return "redirect:/app/eleve/index";
		}
	}

	@RequestMapping(value = "refuse/{id}", method = RequestMethod.GET)
	public String refuseDCTAPById(@PathVariable String id, Model model) {
		DemandeConsoTempsAccPers dctap = manager.getDCTAPById(Long.valueOf(id));

		// Test que la DCTAP appartient à la bonne personne
		if (dctap.getEleve().equals(UtilSession.getUserInSession())
				&& dctap.getEtat() > 40) {
			dctap.setEtat(2);
			manager.updateDCTAP(dctap);
		}

		return "redirect:/app/eleve/mesdctap";
	}

	@RequestMapping(value = "valid/{id}", method = RequestMethod.GET)
	public String validDCTAPById(@PathVariable String id, Model model) {
		DemandeConsoTempsAccPers dctap = manager.getDCTAPById(Long.valueOf(id));

		// Test que la DCTAP appartient à la bonne personne
		if (dctap.getEleve().equals(UtilSession.getUserInSession())
				&& dctap.getEtat() > 40) {
			dctap.setEtat(1);
			manager.updateDCTAP(dctap);
		}

		return "redirect:/app/eleve/mesdctap";
	}
}
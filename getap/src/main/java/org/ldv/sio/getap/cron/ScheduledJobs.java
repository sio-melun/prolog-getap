package org.ldv.sio.getap.cron;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.ldv.sio.getap.app.DemandeValidationConsoTempsAccPers;
import org.ldv.sio.getap.app.ProfStats;
import org.ldv.sio.getap.app.User;
import org.ldv.sio.getap.app.service.IFManagerGeTAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ScheduledJobs {

	/*
	 * Documentation pour les CRON
	 * 
	 * Afin de faciliter le développement des tâches CRON, les différentes
	 * tâches seront centralisées dans ce fichier.
	 * 
	 * Comment procéder pour ajouter une tâche : ScheduledJobs.java - (Ajouter
	 * une méthode qui sera exécutée chaque x temps, en Autowired)
	 * web-application-config.xml - Trouver la ligne :
	 * 
	 * "<task:scheduled ref="
	 * ScheduledJobs" method="notifyMail" fixed-delay="2000" />"
	 * 
	 * Copier la ligne, la coller en dessous et la modifier (Modifier le
	 * paramètre method vers la méthode créer dans ScheduledJobs.java)
	 * 
	 * Il existe 3 types de paramètre pouvant déterminer l'interval d'exécution
	 * de la tâche cron :
	 * 
	 * 1. fixed-delay : La tâche s'exécutera toutes les x ms (2000 = 2
	 * secondes), a partir de la dernière exécution de cette même tâche CRON.
	 * Autrement dis, si la tâche "notifyMail", exécuté à 0.0 sc se termine à
	 * 0.5sc, alors la prochaine exécution de "notifyMail" sera exécutée à 2.5sc
	 * si fixed-delay = 2000.
	 * 
	 * 2. fixed-rate : A l'inverse de fixed-delay, fixed-rate permet de lancer
	 * une tâche CRON à interval régulier sans tenir compte de si son exécution
	 * a déjà été fini. Cela permet de lancer plusieurs fois une tâche CRON en
	 * même temps. Si la tâche "notifyMail" est lancée à 0.0 sc, et qu'elle se
	 * termine à 0.5sc, alors "notifyMail" sera relancée à 2.0 sc si fixed-rate
	 * = 2000.
	 * 
	 * 3. cron : C'est l'élément qui vous intéressera le plus. Il permet la
	 * plannification complète de l'exécution de votre tâche selon une syntaxe
	 * propre. Vous pouvez trouver un générateur de CRON + un descriptif au lien
	 * suivant : http://www.openjs.com/scripts/jslibrary/demos/crontab.php
	 * 
	 * Attention : Les tâches CRON sous Java Spring doivent prendre 6 arguments
	 * : le premier argument sera les secondes, le deuxième les minutes, le
	 * troisième les heures, etc..
	 * 
	 * Exemple : "0 * * * * *" se traduit par "Exécuter la tâche à chaque 0
	 * secondes de chaque minute, de chaque heure, de chaque jour, etc.." ce qui
	 * se traduit par : 11h00 et 00 secondes, 11h01 et 00 secondes, 11h02 et 00
	 * secondes, etc..
	 */

	private String destinataireMail;
	private String subjectMail;
	private String contentMail = "";
	public SimpleDateFormat dateFormatEnvoi = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	@Qualifier("DBServiceManager")
	private IFManagerGeTAP manager;

	public void notifyMail() throws ParseException {

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		int dateToday = (Calendar.DAY_OF_WEEK - 1);

		if (dateToday == Calendar.MONDAY || dateToday == Calendar.THURSDAY) {
			List<ProfStats> lesProfStats = manager.getAllStatsProfs(manager
					.getCurrentAnneeScolaire());

			if (dateToday == cal.get(Calendar.MONDAY)) {

			} else if (dateToday == Calendar.THURSDAY) {

				DateFormat dateFormatEnvoi = new SimpleDateFormat("dd/MM/yyyy");
				dateFormatEnvoi.setTimeZone(TimeZone
						.getTimeZone("Europe/Paris"));
				Date dateEnvoi = new Date();

				subjectMail = "[GeTAP] [" + dateFormatEnvoi.format(dateEnvoi)
						+ "] Récapitulatif et rappel de vos demandes";

				for (Object object : lesProfStats) {
					ProfStats statsProf = (ProfStats) object;

					if (statsProf.getDctapattente() > 10) {

						User professeur = manager.getUser((long) statsProf
								.getId());

						if (professeur.getMail() != null) {

							contentMail += "Bonjour " + statsProf.getNom()
									+ " " + statsProf.getPrenom()
									+ ",\n\nVoici vos demandes en attente :\n";

							List<DemandeValidationConsoTempsAccPers> lesDvctapProf = manager
									.getAllDVCTAPByProfPrinc(professeur,
											manager.getCurrentAnneeScolaire());

							for (int i = 0; i < lesDvctapProf.size(); i++) {
								int dvctapEtat = lesDvctapProf.get(i).getEtat();
								if (dvctapEtat == 0 || dvctapEtat == 4
										|| dvctapEtat > 1023) {
									contentMail += "- ["
											+ lesDvctapProf.get(i).getAccPers()
													.getNom()
											+ "] ["
											+ lesDvctapProf.get(i)
													.getDateAction()
											+ "] "
											+ lesDvctapProf.get(i).getEleve()
													.getPrenom()
											+ " "
											+ lesDvctapProf.get(i).getEleve()
													.getNom() + "\n";
								}
							}
							contentMail += "\nVous pouvez accéder à GeTAP par le lien suivant :\nhttp://getap.vinci-melun.org/getap/app/login/index\n\nBonne journée !";
							System.out.println(contentMail);
							contentMail = "";
						}
					}
				}
			}
		}
	}
}

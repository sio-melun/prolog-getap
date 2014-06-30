package org.ldv.sio.getap.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;

public class Initialisation implements ServletContextListener {

	@Autowired
	public void contextInitialized(ServletContextEvent servletcontextevent) {
		// Insérez ici le code à exécuter au démarrage du serveur.

	}

	public void contextDestroyed(ServletContextEvent servletcontextevent) {
		// Insérez ici le code à exécuter à l'arrêt manuel du serveur.

	}
}
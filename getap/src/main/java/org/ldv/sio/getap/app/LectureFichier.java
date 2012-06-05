package org.ldv.sio.getap.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectureFichier {
	private String fileCsv;
	private ArrayList<Utilisateur> listeUser;

	public LectureFichier(String fileCsv) {
		this.fileCsv = fileCsv;
	}

	public String getFileCsv() {
		return fileCsv;
	}

	public void setFileCsv(String fileCsv) {
		this.fileCsv = fileCsv;
	}

	public ArrayList<Utilisateur> getListeUser() {
		return listeUser;
	}

	public void setListeUser(ArrayList<Utilisateur> listeUser) {
		this.listeUser = listeUser;
	}

	/**
	 * 
	 * @param fileCsv
	 *            fichier .csv contenant la liste des utilisateurs
	 * @return ArrayList<User> liste de tous les utilisateurs extrait du fichier
	 *         .csv
	 */
	public ArrayList<Utilisateur> createListUser(String fileCsv) {
		try {
			String nomFichier = fileCsv;
			FileReader fr = new FileReader(nomFichier);

			BufferedReader buf = new BufferedReader(fr);

			String ligne = buf.readLine();

			ArrayList<Utilisateur> listUser = new ArrayList<Utilisateur>();

			// parcour toutes les lignes du .csv et cr�� les utilisateurs
			// correspondants
			while (ligne != null) {
				String[] params = ligne.split(";");
				if (params.length >= 2) {
					Utilisateur user = new Utilisateur(params[0], params[1],
							params[2], params[3]);
					listUser.add(user);
				} else {
					System.out
							.println("impossible d'instancier l'utilisateur : "
									+ params[0] + " !");
				}

				ligne = buf.readLine();
			}
			fr.close();
			return listUser;
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier est introuvable.");
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}
}

package org.ldv.sio.getap.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBC {

	public void feedBDD(String file) {
		/*
		 * String username = "prolog"; String password = "secret"; String driver
		 * = "com.mysql.jdbc.Driver"; String url =
		 * "jdbc:mysql://172.17.253.70/getap"; // d�finir la bonne IP
		 */String QUOTE = "\'";

		try { // chargement du pilote
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://172.17.253.143/getap", "test", "secret");

			// Insertion dans la base de donn�es d'une liste
			String lien = file;
			if (lien.contains('\\' + "")) {
				file = file.replace('\\', '/');
			}
			System.out.println(file);
			LectureFichier lf = new LectureFichier(file);
			ArrayList<Utilisateur> listUser = lf
					.createListUser(lf.getFileCsv());

			// parcour toute la liste d'utilisateur, les ins�re en base de
			// donn�es avec leur don�es respectives
			for (int i = 1; i < listUser.size(); i++) {
				Statement select = con.createStatement();
				ResultSet rs = select
						.executeQuery("SELECT libelle FROM classe WHERE libelle = "
								+ QUOTE + listUser.get(i).classe + QUOTE);
				rs.last();
				int count = rs.getRow();
				rs.beforeFirst();

				// Si la requete ne retourne aucun r�sultat, on insert la
				// nouvelle classe dans la base
				if (count == 0) {
					Statement st;
					String requete = "";
					st = con.createStatement();
					String classe = QUOTE + listUser.get(i).classe + QUOTE;
					requete = "INSERT INTO classe(libelle) VALUES(" + classe
							+ ");";
					st.executeUpdate(requete);
				}

				// Requete d'insertion de l'utilisateur dans la base
				PreparedStatement param = con
						.prepareStatement("INSERT INTO getap.user(nom, prenom, login, mdp, role, idClasse ) VALUES(?, ?, ?, ?, ?, ?);");
				param.setNString(1, listUser.get(i).nom);
				param.setNString(2, listUser.get(i).prenom);

				// d�finition de la base d'un login : 1ere lettre prenom + nom
				String baseLogin = listUser.get(i).prenom.charAt(0)
						+ listUser.get(i).nom; // correspond au login de
												// l'utilisateur

				// Requete permettant de retourner tous les login qui commence
				// par la 1ere lettre de son prenom + son nom
				Statement login = con.createStatement();
				ResultSet set = login
						.executeQuery("SELECT login FROM user WHERE login LIKE "
								+ QUOTE
								+ baseLogin
								+ "%"
								+ QUOTE
								+ "AND nom = "
								+ QUOTE
								+ listUser.get(i).nom
								+ QUOTE
								+ " AND prenom = "
								+ QUOTE
								+ listUser.get(i).prenom + QUOTE);
				set.last();
				count = set.getRow();
				set.beforeFirst();

				// Si aucun r�sultat n'est retourn�, le login n'existe pas , on
				// ins�re donc le login cr�� au moment de la lecture du fichier
				// .csv
				if (count == 0) // GOOD
					param.setNString(3, listUser.get(i).login);

				// Si un r�sultat est retourn�, le login existe d�j�, on cr�� un
				// login avec un chiffre en plus avec une incr�mentation
				else {
					int max = 1;
					while (set.next()) {
						String sLogin = set.getNString("login");
						int nbCarac = baseLogin.length();
						if (nbCarac != sLogin.length()) {
							String sNumber = sLogin.substring(nbCarac);
							System.out.println(sLogin + " => " + sNumber
									+ " => " + baseLogin);
							int number = Integer.parseInt(sNumber);
							if (max < number)
								max = number;
						}
					}
					max++;
					String sMax = String.valueOf(max);
					String log = baseLogin + sMax;
					param.setNString(3, log);
				}

				param.setNString(4, listUser.get(i).pass);
				// System.out.println("Chemin : " + file);
				String[] parties = file.split("/");
				String fichier = parties[parties.length - 1];
				// System.out.println("Fichier : " + fichier);

				// Si fichier.csv o� fichier commence par "eleve" -> role =
				// eleve
				if (fichier.startsWith("eleve"))
					param.setNString(5, "eleve");

				// Sinon si fichier.csv o� fichier commence par prof : 2 verif
				// suivant la classe -> si classe (prof principal) sinon (prof
				// intervenant)
				else if (fichier.startsWith("prof")) {
					if (listUser.get(i).classe == null)
						param.setNString(5, "prof-intervenant");
					else
						param.setNString(5, "prof-principal");
				}

				// Requete retournant l'id de la classe de l'utilisateur
				Statement classe = con.createStatement();
				String sql = "SELECT id FROM classe WHERE libelle = " + QUOTE
						+ listUser.get(i).classe + QUOTE;
				ResultSet result = classe.executeQuery(sql);
				if (result.next()) {
					int clas = result.getInt("id");
					param.setInt(6, clas);
				}

				// Execution de la requete !
				param.executeUpdate();

			}

			// fermeture
			con.close();
		} catch (SQLException ex) {
			System.err.println("==> SQLException: ");
			while (ex != null) {
				System.out.println("Message: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("ErrorCode: " + ex.getErrorCode());
				ex = ex.getNextException();
				System.out.println("");
			}
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

package org.ldv.sio.getap.app;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ServiceJDBC")
public class ImportFromSqlFile {
	private DataSource ds;

	public DataSource getDs() {
		return ds;
	}

	@Autowired
	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public void feedBDD(String file) {
		String QUOTE = "\'";

		try {
			Connection con = ds.getConnection();

			// Insertion dans la base de donnees d'une liste
			String lien = file;

			lien = File.pathSeparator + lien;

			// if (lien.contains('\\' + "")) {
			// file = file.replace('\\', );
			// }
			//
			System.out.println(file);
			LectureFichier lf = new LectureFichier(file);
			ArrayList<Utilisateur> listUser = lf
					.createListUser(lf.getFileCsv());

			// parcour toute la liste d'utilisateur, les insere en base de
			// donnees avec leur donees respectives
			for (int i = 1; i < listUser.size(); i++) {
				Statement select = con.createStatement();
				ResultSet rs = select
						.executeQuery("SELECT libelle FROM classe WHERE libelle = "
								+ QUOTE + listUser.get(i).classe + QUOTE);
				rs.last();
				int count = rs.getRow();
				rs.beforeFirst();

				// Si la requete ne retourne aucun resultat, on insert la
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
						.prepareStatement("INSERT INTO user(nom, prenom, idEtab, login, mdp, hash, role, idClasse ) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");

				param.setNString(1, listUser.get(i).nom);
				param.setNString(2, listUser.get(i).prenom);
				param.setNString(3, listUser.get(i).idEtab);

				// définition de la base d'un login : 1ere lettre prenom + nom
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

				// Si aucun résultat n'est retourné, le login n'existe pas , on
				// insere donc le login créé au moment de la lecture du fichier
				// .csv
				if (count == 0) // GOOD
					param.setNString(4, listUser.get(i).login);

				// Si un résultat est retourné, le login existe déjà, on créé un
				// login avec un chiffre en plus avec une incrémentation
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
					param.setNString(4, log);
				}

				param.setNString(5, listUser.get(i).pass);

				param.setNString(6, listUser.get(i).hashPass);

				String filename = file.substring(0, file.length() - 4);
				filename = filename.substring(5);
				System.out.println("Fichier : " + filename);
				param.setNString(7, filename);

				// Requete retournant l'id de la classe de l'utilisateur
				Statement classe = con.createStatement();
				String sql = "SELECT id FROM classe WHERE libelle = " + QUOTE
						+ listUser.get(i).classe + QUOTE;
				ResultSet result = classe.executeQuery(sql);
				if (result.next()) {
					int clas = result.getInt("id");
					param.setInt(8, clas);
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
		}

	}
}

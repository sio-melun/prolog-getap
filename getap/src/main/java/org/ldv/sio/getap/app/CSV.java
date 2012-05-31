package org.ldv.sio.getap.app;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

@Service("ServiceCSV")
public class CSV {

	private DataSource ds;

	public DataSource getDs() {
		return ds;
	}

	@Autowired
	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public void export() {

		try {
			Connection con = ds.getConnection();
			Statement select = con.createStatement();
			ResultSet rs = select
					.executeQuery("SELECT * FROM user, classe where user.idClasse = classe.id and role = 'eleve' order by classe.libelle, user.nom");

			Document document = new Document(PageSize.A4);

			try {
				FileWriter writer = new FileWriter("F:/test2.csv");

				writer.append("nom;prenom;INE;Division\n");
				while (rs.next()) {
					writer.append(rs.getString("nom"));
					writer.append(";");
					writer.append(rs.getString("prenom"));
					writer.append(";");
					writer.append(rs.getString("INE"));
					writer.append(";");
					writer.append(rs.getString("libelle"));
					writer.append("\n");
				}
				writer.flush();
				writer.close();

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

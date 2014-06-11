package org.ldv.sio.getap.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ServiceDemandesStatsProfesseurCSV")
public class StatsProfesseurCSV {

	private DataSource ds;

	public DataSource getDs() {
		return ds;
	}

	@Autowired
	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public void export(HttpServletResponse response,
			List<ProfStats> lesStatsProfesseur) {

		try {
			PrintWriter writer = response.getWriter();
			writer.println("NOM;PRENOM;AP_VALIDÉES;AP_EN_ATTENTE;AP_REFUSÉES;TOTAL");
			for (int i = 0; i < lesStatsProfesseur.size(); i++) {

				writer.append(lesStatsProfesseur.get(i).getNom() + ";");
				writer.append(lesStatsProfesseur.get(i).getPrenom() + ";");
				writer.append("" + lesStatsProfesseur.get(i).getDctapvalide()
						+ ";");
				writer.append("" + lesStatsProfesseur.get(i).getDctapattente()
						+ ";");
				writer.append("" + lesStatsProfesseur.get(i).getDctaprefuse()
						+ ";");
				writer.append("" + lesStatsProfesseur.get(i).getCountap() + ";");
				writer.println("");
			}
			writer.flush();
			writer.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
}

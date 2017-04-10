package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import metier.Client;

public class Dao implements IDao {

	@Override
	public void ajouterClient(Client c) {
		try {
			Connection conn = DaoConnexion.getConnexion();
			// 3- créer la requête
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO Client(nom, prenom, couleuryeux, age) VALUES (?, ?, ?, ?)");
			ps.setString(1, c.getNom());
			ps.setString(2, c.getPrenom());
			ps.setString(3, c.getCouleurYeux());
			ps.setInt(4, c.getAge());

			// 4- executer la requête
			ps.executeUpdate();
			// 5- présenter les résultats

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est executé quelque soit les étapes précédentes
			// 6- fermer la connexion
			DaoConnexion.closeConnexion();
		}

	}

	@Override
	public Collection<Client> listerClients() {

		Collection<Client> clients = new ArrayList<Client>();
		try {
			Connection conn = DaoConnexion.getConnexion();
			// 3- créer la requête
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Client");
			// 4- executer la requête
			ResultSet rs = ps.executeQuery();
			// 5- présenter les résultats
			while (rs.next()) {
				Client c = new Client();
				c.setId(rs.getInt("id"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setAge(rs.getInt("age"));
				c.setCouleurYeux(rs.getString("couleuryeux"));

				clients.add(c);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est executé quelque soit les étapes précédentes
			// 6- fermer la connexion
			DaoConnexion.closeConnexion();
		}
		return clients;
	}

	@Override
	public void modifierClient(int id, String nom, String prenom) {

		try {
			Connection conn = DaoConnexion.getConnexion();
			// 3- créer la requête
			PreparedStatement ps = conn.prepareStatement("UPDATE client SET nom = ? , prenom = ? WHERE id = ?");
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setInt(3, id);

			// 4- executer la requête
			ps.executeUpdate();
			// 5- présenter les résultats
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est executé quelque soit les étapes précédentes
			// 6- fermer la connexion
			DaoConnexion.closeConnexion();
		}
	}

	@Override
	public Client chercherClient(int id) {

		Client c = new Client();

		try {
			Connection conn = DaoConnexion.getConnexion();
			// 3- créer la requête
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Client WHERE id = ?");
			ps.setInt(1, id);
			// 4- executer la requête
			ResultSet rs = ps.executeQuery();
			// 5- présenter les résultats

			if (rs.next()) {

				c.setId(rs.getInt("id"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setAge(rs.getInt("age"));
				c.setCouleurYeux(rs.getString("couleuryeux"));
				conn.close();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est executé quelque soit les étapes précédentes
			// 6- fermer la connexion
			DaoConnexion.closeConnexion();
		}

		return c;
	}

	@Override
	public void supprimerClient(int id) {
		try {
			Connection conn = DaoConnexion.getConnexion();
			// 3- créer la requête
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Client WHERE id = " + id);
			// 4- executer la requête
			ps.executeUpdate();

			// 5- présenter les résultats
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est executé quelque soit les étapes précédentes
			// 6- fermer la connexion
			DaoConnexion.closeConnexion();
		}

	}

	@Override
	public Collection<Client> chercherParMC(String mc) {

		Collection<Client> clients = new ArrayList<Client>();
		try {
			Connection conn = DaoConnexion.getConnexion();
			// 3- créer la requête
			// PreparedStatement ps = conn.prepareStatement("SELECT * FROM
			// Client WHERE UPPER(nom) LIKE UPPER('%"+ mc +"%')");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Client WHERE UPPER(nom) LIKE ?");
			ps.setString(1, "%" + mc.toUpperCase() + "%");
			// 4- executer la requête
			ResultSet rs = ps.executeQuery();
			// 5- présenter les résultats
			while (rs.next()) {
				Client c = new Client();
				c.setId(rs.getInt("id"));
				c.setNom(rs.getString("nom"));
				c.setPrenom(rs.getString("prenom"));
				c.setCouleurYeux(rs.getString("couleuryeux"));
				c.setAge(rs.getInt("age"));

				clients.add(c);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// code qui est executé quelque soit les étapes précédentes
			// 6- fermer la connexion
			DaoConnexion.closeConnexion();
		}
		return clients;
	}

}

package service;

import java.util.Collection;

import metier.Client;

public interface IClientService {
	public void direBonjour();
	public void direAurevoir();
	//public void jesuisVip();
	public void ajouterClient(Client c);
	public void modifierClient(int id, String nom, String prenom);
	public void supprimerClient(int id);
	public Collection<Client> listerClients();
	public Collection<Client> chercherParMC(String mc);
	public Client chercherClient(int id);
}

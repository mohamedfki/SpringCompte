package tn.iit.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

@Entity
@Table(name = "t_compte")
public class Compte implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Include
	private Integer rib;
	private float solde;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_client")
	private Client client;
	//fetch: manière de chargement des données
	//EAGER : lorsque je charge un compte, l'attribut client se charge avec
	//LAZY: lorsque je charge un compte, l'attribut client ne se charge pas
	//LAZY suite: l'attribut client se charge, suite à l'appel de la méthode getClient()
//default fetch as for JPA
	// 1 --> EAGER
	// * --> LAZY
	
	public Compte(float solde, Client client) {
		super();
		this.solde = solde;
		this.client = client;
	}
	
	

}

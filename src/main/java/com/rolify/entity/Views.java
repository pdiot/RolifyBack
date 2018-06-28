package com.rolify.entity;

public interface Views {
	public static interface Common {}
	
	public static interface Partie extends Common {}
	public static interface PartieWithMJ extends Partie {}
	public static interface PartieWithJoueurs extends Partie {}
	public static interface PartieWithAssociations extends Partie{}
	public static interface PartieWithMessages extends Partie{}
	public static interface PartieWithAll extends PartieWithJoueurs, PartieWithMJ, PartieWithAssociations, PartieWithMessages {}
	
	public static interface Utilisateur extends Common {}
	public static interface UtilisateurWithAssociations extends Utilisateur {}
	public static interface UtilisateurWithPartiesMJ extends Utilisateur {}
	public static interface UtilisateurWithPartiesJoueur extends Utilisateur {}
	public static interface UtilisateurWithMessages extends Utilisateur {}
	public static interface UtilisateurWithGroupes extends Utilisateur {}
	public static interface UtilisateurWithAll extends UtilisateurWithAssociations, UtilisateurWithPartiesMJ, UtilisateurWithPartiesJoueur, UtilisateurWithMessages, UtilisateurWithGroupes {}
	
	public static interface Association extends Common {}
	public static interface AssociationWithUtilisateur extends Association {}
	public static interface AssociationWithPartie extends Association {}
	public static interface AssociationWithPersonnage extends Association {}
	public static interface AssociationWithAll extends AssociationWithUtilisateur, AssociationWithPartie, AssociationWithPersonnage {}
	
	public static interface Personnage extends Common {}
	public static interface PersonnageWithAssociation extends Personnage {}
	
	public static interface GroupeDiscussion extends Common {}
	public static interface GroupeDiscussionWithUsers extends Common {}
	public static interface GroupeDiscussionWithMessages extends Common {}
	public static interface GroupeDiscussionWithAll extends GroupeDiscussionWithUsers, GroupeDiscussionWithMessages {}
	
	public static interface Message extends Common {}
	public static interface MessageWithSource extends Message {}
	public static interface MessageWithPartie extends Message {}
	public static interface MessageWithGroupe extends Message {}
	public static interface MessageWithAll extends MessageWithSource, MessageWithPartie, MessageWithGroupe {}
	
}

export interface CommandModel {

  numeroCommande: number;
  nbCarte: number;
  tauxParticipation: number;
  dateCommande: Date | string; // Timestamps in TypeScript can be represented as Date objects or ISO string formats
  statut: string;
  //utilisateur: Employer; // to do : define interface
  //reclamations: Reclamation[]; // to do : define interface

}

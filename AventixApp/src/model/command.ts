export interface CommandModel {

  numeroCommande: number;
  nbCarte: number;
  tauxParticipation: number;
  dateCommande: Date | string; // Timestamps in TypeScript can be represented as Date objects or ISO string formats
  statut: string;
  utilisateur: {
    id: number;
  };
  reclamations: any[]; // to do : define Reclamation instead of any

}

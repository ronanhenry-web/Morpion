package org.example;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        play();
    }

    public static int[][] createPlateau(int nbRows, int nbColumns) {
        int[][] tab = new int[nbRows][nbColumns];

        return tab;
    }

    public static void displayPlateau(int[][] plateau) {
        System.out.print("  ");

        for(int cptColonne = 0; cptColonne < 3; cptColonne++) {
            System.out.print(cptColonne + " ");
        }
        System.out.println();

        for(int cptLigne = 0; cptLigne < plateau.length; cptLigne++) {
            System.out.print(cptLigne + " ");

            for(int cptColonne = 0 ; cptColonne < plateau[cptLigne].length; cptColonne++) {

                switch (plateau[cptLigne][cptColonne]) {
                    case 0:
                        System.out.print("- ");
                        break;
                    case 1:
                        System.out.print("x ");
                        break;
                    case 2:
                        System.out.print("o ");
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }
    }

    public static boolean plateauFull(int[][] plateau) {
        for(int cptLigne = 0; cptLigne < plateau.length; cptLigne++) {

            for (int cptColonne = 0; cptColonne < plateau[cptLigne].length; cptColonne++) {

                if(plateau[cptLigne][cptColonne] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkPlateau(int[][] plateau, int numLigne, int numColonne) {
        boolean check = false;

        if(numLigne >= 0 && numLigne < plateau.length && numColonne >= 0 && numColonne < plateau[numLigne].length) {
            if(plateau[numLigne][numColonne] == 0) {
                check = true;
            }
        }
        return check;
    }

    public static void fillCase(int[][] plateau, int numLigne, int numColonne, int numeroJoueur) {
        plateau[numLigne][numColonne] = numeroJoueur;
    }

    public static boolean equals(int entier1, int entier2, int entier3, int entier4) {
        boolean compare = false;

        if(entier1 == entier2 && entier2 == entier3 && entier3 == entier4) {
            compare = true;
        }

        return compare;
    }

    public static boolean win(int[][] plateau, int numJoueur) {
        // Ligne
        for(int cptLignes = 0; cptLignes < plateau.length; cptLignes++) {
            if(equals(plateau[cptLignes][0], plateau[cptLignes][1], plateau[cptLignes][2], numJoueur)) {
                return true;
            }
        }

        // Colonne
        for(int cptColonnes = 0; cptColonnes < plateau[0].length; cptColonnes++) {
            if(equals(plateau[0][cptColonnes], plateau[1][cptColonnes], plateau[2][cptColonnes], numJoueur)) {
                return true;
            }
        }

        // Diagonale
        if(equals(plateau[0][0], plateau[1][1], plateau[2][2], numJoueur) || equals(plateau[2][0], plateau[1][1], plateau[0][2], numJoueur)) {
            return true;
        }

        return false;
    }

    public static void play() {
        Scanner scan = new Scanner(System.in);

        int[][] plateau = createPlateau(3, 3);

        int currentNumJoueur = 1;

        boolean playing = true;

        while(playing) {
            System.out.println("\n********** Tour du joueur " + currentNumJoueur + "**********\n");

            int numligne = -1;
            int numColonne = -1;
            boolean caseValide = false;

            while(!caseValide) {
                displayPlateau(plateau);

                System.out.println();
                System.out.print("Le joueur " + currentNumJoueur + " choisit une ligne: ");
                numligne = scan.nextInt();

                System.out.print("Le joueur " + currentNumJoueur + " choisit une colonne: ");
                numColonne = scan.nextInt();
                System.out.println();

                caseValide = checkPlateau(plateau, numligne, numColonne);

                if(!caseValide) {
                    System.out.println("Case invalide");
                    System.out.println();
                }
            }

            fillCase(plateau, numligne, numColonne, currentNumJoueur);

            if(win(plateau, currentNumJoueur)) {
                displayPlateau(plateau);

                System.out.println("Le joueur " + currentNumJoueur + " a gagné");
                playing = false;
            } else if(plateauFull(plateau)) {
                displayPlateau(plateau);

                System.out.println("Égalité");
                playing = false;
            } else {
                if(currentNumJoueur == 1) {
                    currentNumJoueur = 2;
                } else {
                    currentNumJoueur = 1;
                }
            }
        }
    }
}
package aaBingo;

import java.util.Scanner;

public class Bingo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Missatges ja escrits per simplificar, que poden sortir més d'un cop
		String errorCartro = "ERROR \nRang del cartró incorrecte. (1-7)\n";
		String msgFileres = "Introdueix el nombre de fileres del cartró: ";
		String msgColumnes = "\nIntrodueix el nombre de columnes del cartró: ";
		String msgAcabar = "\nIntrodueix un número (-1 per acabar): ";
		String msgNumIntrNoValid = "ERROR \nIntrodueix un número vàlid (1-99): ";
		String msgCartro = "\nEl teu cartró: \n";
		String msgJocFinalitzat = "\nJoc finalitzat. Carregant els resultats...\n";

		// Variables per a contadors, mitjanes, etc.
		int contadorTatxats = 0;

		// Creació del cartró (demanar dimensions vàlides 1-7) i fer-lo matriu

		System.out.println("BINGO! | Borja Montseny DAW 1M\n");

		System.out.print(msgFileres);

		Scanner sc = new Scanner(System.in);

		// assignant fileres N

		int numN = sc.nextInt();

		// validador del rang de N
		boolean rangValid = numN > 0 && numN < 8;

		// bucle per introduïr N bé
		while (!rangValid) {
			System.out.print(errorCartro);
			System.out.print(msgFileres);
			numN = sc.nextInt();

			rangValid = numN > 0 && numN < 8;
		}

		// ara assignem columnes M
		System.out.print(msgColumnes);
		int numM = sc.nextInt();

		// reutilitzem el validador d'avans, pero validant M

		rangValid = numM > 0 && numM < 8;

		// bucle per introduïr M bé
		while (!rangValid) {
			System.out.print(errorCartro);
			System.out.print(msgColumnes);
			numM = sc.nextInt();

			rangValid = numM > 0 && numM < 8;
		}

		// creem el array amb N i M validats
		int matriuCartro[][] = new int[numN][numM];

		// omplim el array amb ints randoms del 1 al 99
		// i = files | j columnes
		for (int i = 0; i < matriuCartro.length; i++) {
			for (int j = 0; j < matriuCartro[0].length; j++) {
				matriuCartro[i][j] = (int) Math.floor(Math.random() * (99 - 1 + 1) + 1);
			}
		}

		// mostrem el cartró al jugador
		System.out.println("\nEl teu cartró:");

		for (int i = 0; i < matriuCartro.length; i++) {
			for (int j = 0; j < matriuCartro[0].length; j++) {
				System.out.print(matriuCartro[i][j] + " ");
			}
			System.out.println();
		}

		int numIntroduit = 0;

		// introduim nums, mostrem cartró a veure si ha tatxat algun
		while (numIntroduit != -1) {
			System.out.print(msgAcabar);
			numIntroduit = sc.nextInt();

			// reutilitzarem el validador, per validar numIntroduit 1-99 i tampoc -1
			rangValid = (numIntroduit >= 1 && numIntroduit <= 99) || (numIntroduit == -1);

			// extra. per a validar que el num es entre 1 i 99 i tampoc -1
			while (!rangValid) {
				System.out.print(msgNumIntrNoValid);
				numIntroduit = sc.nextInt();
				rangValid = numIntroduit >= 1 && numIntroduit <= 99 || (numIntroduit == -1);
			}

			System.out.println(msgCartro);

			// recorrem la matriu buscant la coincidència
			if (numIntroduit != -1) {
				for (int i = 0; i < matriuCartro.length; i++) {
					for (int j = 0; j < matriuCartro[0].length; j++) {

						if (matriuCartro[i][j] == numIntroduit) {
							matriuCartro[i][j] = -1;
							contadorTatxats++;
						}

					}
				}
			}

			// mostrem la matriu a veure si ha tatxat
			for (int i = 0; i < matriuCartro.length; i++) {
				for (int j = 0; j < matriuCartro[0].length; j++) {
					System.out.print(matriuCartro[i][j] + " ");
				}
				System.out.println();
			}

		}

		sc.close();

		// CÀLCULS PER ALS RESULTATS DE LA PARTIDA

		// percentatge de tatxats
		double percentatge = (contadorTatxats * 100) / (numN * numM);

		// 100 - percentatge tatxats per saber els que no em tatxat
		double percentatgeRestant = Math.round(100 - percentatge);

		// per la mitjana dels no tatxats
		double sumaNoTatxats = 0;
		double contadorNoTatxats = 0;

		// recorrem la matriu
		for (int i = 0; i < matriuCartro.length; i++) {
			for (int j = 0; j < matriuCartro[0].length; j++) {
				// si no està tatxat
				if (matriuCartro[i][j] != -1) {
					sumaNoTatxats += matriuCartro[i][j];
					contadorNoTatxats++;
				}
			}
		}

		double mitjanaNoTatxats = sumaNoTatxats / contadorNoTatxats;

		// ara, si hem introduit -1, finalitzem i mostrem els resultats
		if (numIntroduit == -1) {
			System.out.println(msgJocFinalitzat);
			System.out.println("Has pogut tatxar " + contadorTatxats + " números.");
			System.out.println("Falten el " + percentatgeRestant + "% de números per tatxar.");

			// si hem tatxat tots el nums del cartró comuniquem això
			if ((numN * numM) == contadorTatxats) {
				System.out.println("No hi ha números sense tatxar.");
			} else { // si no hem tatxat tot el cartró, mitjana del que ens queda per tatxar
				System.out.println("La mitjana dels números que has pogut tatxar és " + mitjanaNoTatxats + ".");
			}

		}

	}

}

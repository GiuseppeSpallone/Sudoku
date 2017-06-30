import java.io.File;
import java.util.Scanner;

public class Matrice {
    private static int DIMENSIONE = 9;

    public static int[][] caricaMatriceByFile(String path) {
        File file = new File(path);
        int righe = DIMENSIONE;
        int colonne = DIMENSIONE;
        int[][] matrice = new int[righe][colonne];

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextInt()) {
                for (int riga = 0; riga < righe; riga++) {
                    for (int colonna = 0; colonna < colonne; colonna++) {
                        matrice[riga][colonna] = scanner.nextInt();
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("File non trovato " + e.getMessage());
            return null;
        }


        return matrice;
    }


    public static void stampaMatrice(int matrice[][]) {
        if(matrice != null){
            int righe = 9;
            int colonne = 9;

            for (int riga = 0; riga < righe; riga++) {
                if (riga % 3 == 0)
                    System.out.println(" -----------------------");
                for (int colonna = 0; colonna < colonne; colonna++) {
                    if (colonna % 3 == 0)
                        System.out.print("| ");
                    System.out.print(matrice[riga][colonna]);
                    System.out.print(' ');
                }
                System.out.println("|");
            }
            System.out.println(" -----------------------");
        }else{
            System.out.println("Matrice vuota o non esistente");
        }

    }

}


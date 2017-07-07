import javax.swing.*;
import java.io.File;
import java.util.Scanner;

//RIF. INIZIALE: contiene metodi statici per fare operazione su matrici
//RIF. 1: prende in input un percorso dove è presente un file di testo che rappresenta una matrice e restutuisce la matrice
//RIF. 2: prende in input una matrice e stampa su console la matrice sotto forma di griglia Sudoku, sostituindo gli elementi con valori 0 con elementi con valori nulli
//RIF 3. permette di scegliere un file presente nel PC e restituisce il percorso del file

public class Matrice {
    private static int DIMENSIONE = 9;

    //RIF. 1
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

    //RIF. 2
    public static void stampaMatrice(int matrice[][]) {
        if (matrice != null) {
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
        } else {
            System.out.println("Matrice vuota o non esistente");
        }

    }

    //RIF. 3
    public static String apriMatrice() {
        JFileChooser jFileChooser = new JFileChooser();

        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            String path = file.getPath();
            return path;
        } else {
            return null;
        }
    }
}


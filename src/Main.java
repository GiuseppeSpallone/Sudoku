import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GUI gui = new GUI();

        gui.disegnaMenu();

        /*System.out.println("SCEGLI UNA MATRICE");
        Scanner scanner = new Scanner(System.in);
        int scelta =  scanner.nextInt();

        int matrice[][] = Matrice.caricaMatriceByFile("src/file/" + scelta);

        Risolutore risolutore = new Risolutore(matrice);
        boolean solved = risolutore.solve(new Risolutore.Cella(0, 0));

        if (!solved) {
            System.out.println("NON E' POSSIBILE RISOLVERE IL SUDOKU");
            return;

        } else {
            System.out.println("SOLUZIONE");
            Matrice.stampaMatrice(matrice);
        }*/
    }
}



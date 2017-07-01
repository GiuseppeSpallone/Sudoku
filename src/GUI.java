import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI {
    private JPanel jPanel;
    private JPanel jPanelMatrice;
    private JFrame jFrame;

    public static final Color OPEN_CELL_BGCOLOR = Color.WHITE;
    public static final Color CLOSED_CELL_BGCOLOR = Color.LIGHT_GRAY;
    public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

    public GUI() {

        jFrame = new JFrame("Sudoku");
        disegnaMenu();
        //disegnaMatrice(Matrice.caricaMatriceByFile("src/file/1"));

    }

    public void aggiungiPanel(JFrame jFrame, JPanel newJPanel) {
        jFrame.setContentPane(newJPanel);
        jFrame.setSize(500, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.invalidate();
        jFrame.repaint();
    }

    /*public void rimuoviPanel(JFrame jFrame, JPanel oldJPanel) {
        jFrame.removeAll();
    }*/

    public int[][] caricaMatrice(int scelta) {
        int matrice[][] = Matrice.caricaMatriceByFile("src/file/" + scelta);
        return matrice;
    }

    public void disegnaMatrice(int matrice[][]) {
        //rimuoviPanel(jFrame, jPanel);

        jPanelMatrice = new JPanel();
        jPanelMatrice.setLayout(new GridLayout(9, 9));

        JTextField[][] cella = new JTextField[9][9];

        for (int riga = 0; riga < 9; riga++) {
            for (int colonna = 0; colonna < 9; colonna++) {
                cella[riga][colonna] = new JTextField();
                cella[riga][colonna].setEditable(false);
                cella[riga][colonna].setHorizontalAlignment(JTextField.CENTER);
                cella[riga][colonna].setFont(FONT_NUMBERS);

                if (matrice[riga][colonna] == 0) {
                    cella[riga][colonna].setText("");
                    cella[riga][colonna].setBackground(OPEN_CELL_BGCOLOR);
                } else {
                    cella[riga][colonna].setText(matrice[riga][colonna] + "");
                    cella[riga][colonna].setBackground(CLOSED_CELL_BGCOLOR);
                }
                jPanelMatrice.add(cella[riga][colonna]);
            }
        }
        aggiungiPanel(jFrame, jPanelMatrice);
    }

    public void disegnaMenu() {
        jPanel = new JPanel();
        aggiungiPanel(jFrame, jPanel);

        int length = new File("src/file").listFiles().length;

        for (int i = 1; i <= length; i++) {
            int finalI = i;

            Button button = new Button("MATRICE " + i);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int matrice[][] = caricaMatrice(finalI);

                    if (matrice != null) {
                        disegnaMatrice(matrice);
                    } else {

                    }
                }
            });

            jPanel.add(button);
        }
    }
}

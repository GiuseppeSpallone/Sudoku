import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;

public class GUI {
    private JPanel jPanelMenu;
    private JPanel jPanelMatrice;
    private JFrame jFrame;

    public static final Color OPEN_CELL_BGCOLOR = Color.WHITE;
    public static final Color CLOSED_CELL_BGCOLOR = Color.LIGHT_GRAY;
    public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

    public GUI() {
        jFrame = new JFrame("Sudoku");
        disegnaMenu();
    }

    private void aggiungiMenuBar(JFrame jFrame, int matrice[][]) {
        JButton jButtonRisolvi = new JButton("RISOLVI");
        JButton jButtonEsci = new JButton("ESCI");

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(jButtonRisolvi);
        menuBar.add(jButtonEsci);
        jFrame.setJMenuBar(menuBar);

        jButtonEsci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setJMenuBar(null);
                disegnaMenu();
            }
        });

        jButtonRisolvi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                risolvi(matrice);
                disegnaMatrice(matrice);
            }
        });
    }

    private void aggiungiPanel(JFrame jFrame, JPanel newJPanel) {
        jFrame.setContentPane(newJPanel);
        jFrame.setSize(500, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.invalidate();
        jFrame.repaint();
    }

    private int[][] caricaMatrice(int scelta) {
        int matrice[][] = Matrice.caricaMatriceByFile("src/file/" + scelta);
        return matrice;
    }

    private void disegnaMatrice(int matrice[][]) {

        aggiungiMenuBar(jFrame, matrice);

        jPanelMatrice = new JPanel();
        jPanelMatrice.setLayout(new GridLayout(9, 9));
        jPanelMatrice.setFocusable(true);
        jPanelMatrice.requestFocusInWindow();

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

        Matrice.stampaMatrice(matrice);

    }

    private void disegnaMenu() {
        jPanelMenu = new JPanel();
        jPanelMenu.setBackground(Color.WHITE);

        int num_matrici = new File("src/file").listFiles().length;

        for (int i = 0; i < num_matrici; i++) {

            JButton jButton = new JButton("MATRICE " + (i + 1));

            int finalI = i + 1;

            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int matrice[][] = caricaMatrice(finalI);
                    disegnaMatrice(matrice);
                }
            });

            jPanelMenu.add(jButton);
        }

        ImageIcon imageIcon = new ImageIcon("src/altro/sudoku.png");
        JLabel label = new JLabel("", imageIcon, JLabel.CENTER);
        jPanelMenu.add(label);

        aggiungiPanel(jFrame, jPanelMenu);


    }

    private void risolvi(int matrice[][]) {
        Risolutore risolutore = new Risolutore(matrice);
        boolean solved = risolutore.solve(new Risolutore.Cella(0, 0));

        if (!solved) {
            JOptionPane.showMessageDialog(null, "Impossibile risolvere il Sudoku");
        } else {
            JOptionPane.showMessageDialog(null, "Risolto");
        }
    }

}

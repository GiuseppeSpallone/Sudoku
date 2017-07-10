//RIF. INIZIALE: contiene tutti i metodi che permettono di risolvere il problema Sudoku con una strategia di ricerca backtracking
//RIF. 1: per la rappresentazione di una cella della matrice che rappresenta la griglia Sudoku. Cell => (x, y)
//RIF. 2: per verificare se il valore assegnato alla cella è valido o meno
//RIF. 3: per passare alla cella successica a partire dalla cella corrente
//RIF. 4: assegna un valore alla cella corrente, verifica se è valido e se è valido passa alla cella successiva. Cicla finchè non abbiamo la soluzione al problema

public class Risolutore {

    private static int matrice[][];

    public Risolutore(int[][] matrice) {
        this.matrice = matrice;
    }

    //RIF. 1
    static class Cella {

        int riga, colonna;

        public Cella(int riga, int colonna) {
            super();
            this.riga = riga;
            this.colonna = colonna;
        }
    }

    //RIF. 2
    public boolean isValid(Cella cella, int valore) {

        if (matrice[cella.riga][cella.colonna] != 0) {
            throw new RuntimeException(
                    "La cella ha già un valore");
        }

        // se valore è presente nella riga, ritorna false
        for (int c = 0; c < 9; c++) {
            if (matrice[cella.riga][c] == valore)
                return false;
        }

        // se valore è presente nella colonna, ritorna false
        for (int r = 0; r < 9; r++) {
            if (matrice[r][cella.colonna] == valore)
                return false;
        }

        // per ottenere la griglia dovremmo calcolare(x1,y1) (x2,y2)
        int x1 = 3 * (cella.riga / 3);
        int y1 = 3 * (cella.colonna / 3);
        int x2 = x1 + 2;
        int y2 = y1 + 2;

        // se valore è presente nella griglia, ritorna false
        for (int x = x1; x <= x2; x++)
            for (int y = y1; y <= y2; y++)
                if (matrice[x][y] == valore)
                    return false;

        // se il valore non è presente nella riga, nella colonna e nella regione, return true
        return true;
    }

    //RIF. 3
    public Cella getNextCell(Cella cellaCorrente) {

        int riga = cellaCorrente.riga;
        int colonna = cellaCorrente.colonna;

        colonna++;

        if (colonna > 8) {
            // vado alla prossima riga
            colonna = 0;
            riga++;
        }

        //raggiunto la fine della matrice, return null
        if (riga > 8)
            return null; // fine

        Cella next = new Cella(riga, colonna);
        return next;
    }

    //RIF. 4
    public boolean solve(Cella cellaCorrente) {

        //se la cella è nulla abbiamo raggiunto la fine
        if (cellaCorrente == null)
            return true;

        //se grid[cur] già ha un valore, non c'è nulla da risolvere e si continua alla cella successiva
        if (matrice[cellaCorrente.riga][cellaCorrente.colonna] != 0) {

            //ritorna ciò che viene restituito da solve(next)
            //i.e lo stato della soluzione del Soduku non viene determinato da questa cella ma da altre celle
            return solve(getNextCell(cellaCorrente));
        }

        //qui è dove ogni possibile valore viene assegnato alla cella e controlla se è soluzione del problema
        //se grid[cur] non ha un valore, prova tutti i possibili valori
        for (int i = 1; i <= 9; i++) {
            //verifica se è valido, se lo è aggiorna.
            boolean valid = isValid(cellaCorrente, i);

            //valore di "i" non valido per questa cella, prova con un altro valore
            if (!valid)
                continue;

            //assegnazione
            matrice[cellaCorrente.riga][cellaCorrente.colonna] = i;

            //continua con la prossima cella
            boolean solved = solve(getNextCell(cellaCorrente));

            //se è risolto ritorna true, altrimenti prova con un altro valore
            if (solved)
                return true;
            else
                matrice[cellaCorrente.riga][cellaCorrente.colonna] = 0; // reset
            //continua con un altro possibile valore
        }

        //ritorna false se non esistono valori da 1 a 9 che risolvono questa cella
        return false;
    }
}

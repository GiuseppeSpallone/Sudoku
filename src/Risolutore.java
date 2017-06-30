public class Risolutore {

    private static int matrice[][];

    public Risolutore(int[][] matrice) {
        this.matrice = matrice;
    }

    static class Cella {

        int riga, colonna;

        public Cella(int riga, int colonna) {
            super();
            this.riga = riga;
            this.colonna = colonna;
        }
    };

    public boolean isValid(Cella cella, int valore) {

        if (matrice[cella.riga][cella.colonna] != 0) {
            throw new RuntimeException(
                    "La cella ha già un valore");
        }

        // if v present row, return false
        for (int c = 0; c < 9; c++) {
            if (matrice[cella.riga][c] == valore)
                return false;
        }

        // se v è presente nella colonna, return false
        for (int r = 0; r < 9; r++) {
            if (matrice[r][cella.colonna] == valore)
                return false;
        }

        // se v è presente nella griglia, return false

        // per ottenere la griglia dovremmo calcolare(x1,y1) (x2,y2)
        int x1 = 3 * (cella.riga / 3);
        int y1 = 3 * (cella.colonna / 3);
        int x2 = x1 + 2;
        int y2 = y1 + 2;

        for (int x = x1; x <= x2; x++)
            for (int y = y1; y <= y2; y++)
                if (matrice[x][y] == valore)
                    return false;

        // se il valore non è presente nella riga, nella colonna e nel rettangolo di selezione, return true
        return true;
    }

    // passiamo alla cella successiva
    public Cella getNextCell(Cella cellaCorrente) {

        int row = cellaCorrente.riga;
        int col = cellaCorrente.colonna;

        // next cell => col++
        col++;

        // if col > 8, then col = 0, row++
        // raggiunto la fine della riga, vado alla prossima riga
        if (col > 8) {
            // vado alla prossima riga
            col = 0;
            row++;
        }

        // raggiunto la fine della matrice, return null
        if (row > 8)
            return null; // fine

        Cella next = new Cella(row, col);
        return next;
    }

    // tutto è messo insieme qui

    // se il sudoku è risolto return true altrimenti return false
    public boolean solve(Cella cellaCorrente) {

        // se la cella è nulla abbiamo raggiunto la fine
        if (cellaCorrente == null)
            return true;

        // if grid[cur] già ha un valore , non c'è nulla per risolvere qui ,
        // continuo alla cella successiva
        if (matrice[cellaCorrente.riga][cellaCorrente.colonna] != 0) {

            // ritorno ciò che viene restituito da solve(next)
            // i.e lo stato della soluzione del Soduku non viene determinato da questa cella ma da altre celle
            return solve(getNextCell(cellaCorrente));
        }

        // Qui è dove ogni possibile valore viene assegnato alla cella e controlla se è soluzione del problema

        // if grid[cur] non ha un valore, prova tutti i possibili valori
        for (int i = 1; i <= 9; i++) {
            // verifica se è valido, se lo è aggiorna.
            boolean valid = isValid(cellaCorrente, i);

            if (!valid) // valore di "i" non valido per questa cella, prova con un altro valore
                continue;

            // assegnazione
            matrice[cellaCorrente.riga][cellaCorrente.colonna] = i;
            //Matrice.stampaMatrice(matrice);
            // continua con la prossima cella
            boolean solved = solve(getNextCell(cellaCorrente));
            // se è risolto return true, altrimenti prova con un altro valore
            if (solved)
                return true;
            else
                matrice[cellaCorrente.riga][cellaCorrente.colonna] = 0; // reset
            // continua con un altro possibile valore
        }

        // Se sei qui, non esistono valori da 1 - 9 per risolvere questa cella
        // return false
        return false;
    }
}

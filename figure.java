/** Twodimensional blocky figures
 *   changelog and agenda at bottom
 *  
 *  @author   Bjarni Jens Kristinsson
 *  @version  1.0
 */
public class figure {
    public final int v;
    public final figure[] variation;
    private final boolean[][] fig;
    // 'v' is the total number of different figures attained by rotations 
    //    (by 90°) and mirroring about a vertical line, but 'v'==0 if
    //    'this' is  transformation of another figure object.
    // 'variations' is an array where all the 'v' different transformations
    //    are stored. The rest (8-'v') references are null referencees.
    // 'fig' is a bivariate array with true and false values
    //    representing what squares the piece covers.
    
    // use: figure f = new figure(fig, makeVariants);
    // pre: 'fig' is a bivariate array with true and false values
    //         representing what squares the piece covers
    // post: 'f' is a new figure representing the piece
    public figure (boolean[][] fig, boolean makeVariants) {
        // 'makeVariants'==false prevents an infinite loop 
        // in method calcVariations()
        this.fig = fig;
        variation = new figure[8];
        if (makeVariants) this.v = this.calcVariations();
        else this.v = 0;
    }
        
    // snýr hlutnum 90° á móti klukkunni  
    // use: fig = fig.turn();
    // pre: (nothing)
    // post: 'fig' is a new figure attained by rotating 
    //          the old 'fig' 90° counterclockwise
    private figure turn () {
        boolean[][] B = new boolean[this.fig[0].length][this.fig.length];
        int d = this.fig[0].length;
        for (int i=0; i<this.fig.length; i++)
            for (int j=0; j<d; j++)
                B[(d-1)-j][i] = this.fig[i][j];
        return new figure(B,false);
    }
    
    // use: fig = fig.flip();
    // pre: (nothing)
    // post: 'fig' is a new figure attained by mirroring 
    //          the old 'fig' about a vertical line
    private figure flip () {
        boolean[][] B = new boolean[fig.length][fig[0].length];
        int d = this.fig[0].length;
        for (int i=0; i<this.fig.length; i++)
            for (int j=0; j<d; j++)
                B[i][j] = this.fig[i][(d-1)-j];
        return new figure(B,false);
    }
    
    // use: fig = fig.rotate(n);
    // pre: (nothing)
    // post: 'fig' is a new figure transformation of 'this', attained by any 
    //          number of rotations (by 90°) and mirroring about a vertical line.
    private figure rotate (int n) {
        switch (n) {
            case 1: return this.turn();
            case 2: return this.turn().turn();
            case 3: return this.turn().turn().turn();
            case 4: return this.flip();
            case 5: return this.flip().turn();
            case 6: return this.flip().turn().turn();
            case 7: return this.flip().turn().turn().turn();
            default: return this;
        }
    }

    // use: b = fig.equals(f);
    // pre: (nothing)
    // post: 'b' is true if 'fig' and 'f' represents the same piece
    //          free of variations and mirroring, but false otherwise
    private boolean equals (figure f) {
        if (this.fig.length != f.fig.length || this.fig[0].length != f.fig[0].length) return false;
        for (int i=0; i<this.fig.length; i++)
         for (int j=0; j<this.fig[0].length; j++)
          if (this.fig[i][j] != f.fig[i][j]) return false;
        return true;
    }
        
    // use: v = fig.calcVariations();
    // pre: 'fig' is a figure object, 'variations' is an array of null references
    // post: 'v' is the total number of different figures attained by
    //          rotations (by 90°) and mirroring 'fig' about a vertical line. 
    //          All thoose different variations has been stored in the the 
    //          'variation' array, with the rest of the references being null.
    private int calcVariations() {
        int count=0;
        variation[count++] = this;
        for (int i=1; i<8; i++) {
        boolean b = true;
            figure temp = this.rotate(i);
            for (int j=0; j<count; j++) {
                if (temp.equals(variation[j])) {
                    b = false;
                    break;
                }
            }
            if (b) variation[count++] = temp;
        }
        return count;
    }
    
    // use: b = fig.fits(board, I, J);
    // pre: 'board' must represent a square, that is
    //         board.length = board[i].length for every 0<=i<board.length
    // post: 'b' is true if and only if figure 'fig' may be laid on the board
    //          with top left corner (I,J), that is 'fig' does not try to occupy 
    //          any occupied squares and does not flow over outside the board.
    public boolean fits (boolean[][] board, int I, int J) {
        if (this.fig.length+I > board.length || this.fig[0].length+J > board[0].length) return false;
        for (int i=0; i<this.fig.length; i++)
            for (int j=0; j<this.fig[0].length; j++)
                if (this.fig[i][j] && board[I+i][J+j]) return false;
        return true;
    }
    
    // use: fig.add(board, I, J);
    // pre: fig.fits(board, I, J) == true
    // post: The figure 'fig' has been laid on the board with top left corner (I,J).
    public void add (boolean[][] board, int I, int J) {
        for (int i=0; i<this.fig.length; i++)
            for (int j=0; j<this.fig[0].length; j++)
                if (this.fig[i][j]) board[I+i][J+j] = true;
    }
    
    // use: fig.print();
    // pre: (nothing)
    // post: The figure 'fig' has been printed out on standard output with
    //          x-es representing the squares which the piece covers.
    public void print() {
        for (int i=0; i<fig.length; i++) {
           for (int j=0; j<fig[i].length; j++) {
                if (fig[i][j]) System.out.print("x ");
                else           System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}



/** changelog
 * 
 *      v1.0 [2012-12-23]:
 *          - represents all kinds of twodimensional blocky figures
 *               (meaning figures that are made of any number of identical squares)
 * 
 *   agenda
 *     - seperate 'board' functions (fits(), add()) into a new class
 */
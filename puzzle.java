/** Brute-force algorithm solving a puzzle (fitting the eigth figures in a 8x8 square)
 *   changelog and agenda at bottom
 *  
 *  @author   Bjarni Jens Kristinsson
 *  @version  1.0
 */
public class puzzle {
    
    /*   The figures in question.
     * 
     *      Figure 0        Figure 1        Figure 2
     *       x x             x x             x     x
     *       x x             x x x x         x     x
     *       x x x x         x x             x x x x
     * 
     *      Figure 3        Figure 4        Figure 5
     *       x x x             x x           x x
     *       x x x             x x           x x x x
     *       x x             x x x x             x x
     * 
     *      Figure 6        Figure 7
     *       x x x x         x x x x x
     *       x x x x         x x x
     */
    
    
    // use: board2 = clone(board);
    // pre: (nothing)
    // post: 'board2' and 'board' are references to two identical 
    //          twodimensional arrays - but not the same 
    public static boolean[][] clone (boolean[][] board) {
        boolean[][] b = new boolean[board[0].length][board.length];
        for (int i=0; i<board[0].length; i++)
         for (int j=0; j<board.length; j++)
          b[i][j] = board[i][j];
        return b;
    }
    
    // use: Board = makeIntBoard(b);
    // pre: 'b' must represent a cube, that is
    //         b[i].length = constant for every 0<=i<b.length and
    //         b[0][j].length = constant for every 0<=j<b[0].length.
    // post: 'Board' is a twodimensional array with Board[i][j] equal to the
    //          lowest integer k with the property that Board[k][i][j]==true.
    public static int[][] makeIntBoard(boolean[][][] b) {
        int[][] Board = new int[b[0].length][b[0][0].length];
        for (int i=0; i<b[0].length; i++) {
            for (int j=0; j<b[0][0].length; j++) {
                for (int layer=0; layer<b.length; layer++) {
                    if (b[layer][i][j]) {
                        Board[i][j] = layer;
                        break;
        }}}}
        return Board;
    }
    
    // use: drawSolution(Board, nr);
    // pre: StdDraw.java must be present
    // post: The solution to the puzzle, represented by 'Board', has
    //          been drawn on the screen in a new window and saved to the
    //          program's folder with a name unique to the integer 'nr'.
    public static void drawSolution (int[][] Board, int nr) {
        final int e = 50;
        StdDraw.setCanvasSize(10*e,10*e);
        StdDraw.setXscale(0,10*e);
        StdDraw.setYscale(0,10*e);
        StdDraw.clear();
        for (int i=0; i<Board.length; i++) {
        for (int j=0; j<Board[0].length; j++) {
            int x = 3*e/2 + i*e;
            int y = 3*e/2 + j*e;
            switch (Board[i][j]) {
                case 0: StdDraw.setPenColor(StdDraw.PINK);       break;
                case 1: StdDraw.setPenColor(StdDraw.CYAN);       break;
                case 2: StdDraw.setPenColor(StdDraw.YELLOW);     break;
                case 3: StdDraw.setPenColor(StdDraw.GRAY);       break;
                case 4: StdDraw.setPenColor(StdDraw.ORANGE);     break;
                case 5: StdDraw.setPenColor(StdDraw.BLUE);       break;
                case 6: StdDraw.setPenColor(StdDraw.LIGHT_GRAY); break;
                case 7: StdDraw.setPenColor(StdDraw.DARK_GRAY);  break;
                default: StdDraw.setPenColor(StdDraw.WHITE);     break;
            }
            StdDraw.filledSquare(x,y,e/2);
        }}
        StdDraw.save("solution_"+nr+".png");
    }
    
    // use: printSolution(Board, nr);
    // pre: (nothing)
    // post: The solution to the puzzle, represented by 'Board', has
    //          been written on standard output and labeled with a name
    //          unique to the integer 'nr'.
    public static void printSolution (int[][] Board, int nr) {
        System.out.println("Solution number "+nr+":");
        for (int i=0; i<Board.length; i++) {
            for (int j=0; j<Board[0].length; j++) {
                System.out.print(Board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void main (String[] args) {
        
        // Initializing variables.
        int count=0;
        final boolean t = true;
        figure temp0,temp1,temp2,temp3,temp4,temp5,temp6,temp7;
        
        // Creating the figures according to the pieces listed at the top.
        figure[] Figure = new figure[8];
        boolean b0[][] = { {true,true,false,false} , {true,true,false,false} , {true,true,true,true} };
         Figure[0] = new figure(b0,t);
        boolean b1[][] = { {true,true,false,false} , {true,true,true,true} , {true,true,false,false} };
         Figure[1] = new figure(b1,t);
        boolean b2[][] = { {true,false,false,true} , {true,false,false,true} , {true,true,true,true} };
         Figure[2] = new figure(b2,t);
        boolean b3[][] = { {true,true,true} , {true,true,true} , {true,true,false} };
         Figure[3] = new figure(b3,t);
        boolean b4[][] = { {false,true,true,false} , {false,true,true,false} , {true,true,true,true} };
         Figure[4] = new figure(b4,t);
        boolean b5[][] = { {true,true,false,false} , {true,true,true,true} , {false,false,true,true} };
         Figure[5] = new figure(b5,t);
        boolean b6[][] = { {true,true,true,true} , {true,true,true,true} };
         Figure[6] = new figure(b6,t);
        boolean b7[][] = { {true,true,true,true,true} , {true,true,true,false,false} };
         Figure[7] = new figure(b7,t);
        
        // board_i represents the board when i figures has been put on the board 
        boolean[][] board0 = new boolean[8][8];
        boolean[][] board1 = new boolean[8][8];
        boolean[][] board2 = new boolean[8][8];
        boolean[][] board3 = new boolean[8][8];
        boolean[][] board4 = new boolean[8][8];
        boolean[][] board5 = new boolean[8][8];
        boolean[][] board6 = new boolean[8][8];
        boolean[][] board7 = new boolean[8][8];
        boolean[][] board8 = new boolean[8][8];
        
        // 23-folded for-loop..  :/
        temp0 = Figure[0];
        for (int i1=0; i1<8; i1++) {
        for (int j1=0; j1<8; j1++) {
        if (temp0.fits(board0, i1, j1)) {
        temp0.add(board1, i1, j1);
        board2 = clone(board1);
        
        for (int v2=0; v2<Figure[1].v; v2++) {
        temp1 = Figure[1].variation[v2];
        for (int i2=0; i2<8; i2++) {
        for (int j2=0; j2<8; j2++) {
        if (temp1.fits(board1, i2, j2)) {
        temp1.add(board2, i2, j2);
        board3 = clone(board2);
        
        for (int v3=0; v3<Figure[2].v; v3++) {
        temp2 = Figure[2].variation[v3];
        for (int i3=0; i3<8; i3++) {
        for (int j3=0; j3<8; j3++) {
        if (temp2.fits(board2, i3, j3)) {
        temp2.add(board3, i3, j3);
        board4 = clone(board3);
        
        for (int v4=0; v4<Figure[3].v; v4++) {
        temp3 = Figure[3].variation[v4];
        for (int i4=0; i4<8; i4++) {
        for (int j4=0; j4<8; j4++) {
        if (temp3.fits(board3, i4, j4)) {
        temp3.add(board4, i4, j4);
        board5 = clone(board4);
        
        for (int v5=0; v5<Figure[4].v; v5++) {
        temp4 = Figure[4].variation[v5];
        for (int i5=0; i5<8; i5++) {
        for (int j5=0; j5<8; j5++) {
        if (temp4.fits(board4, i5, j5)) {
        temp4.add(board5, i5, j5);
        board6 = clone(board5);
        
        for (int v6=0; v6<Figure[5].v; v6++) {
        temp5 = Figure[5].variation[v6];
        for (int i6=0; i6<8; i6++) {
        for (int j6=0; j6<8; j6++) {
        if (temp5.fits(board5, i6, j6)) {
        temp5.add(board6, i6, j6);
        board7 = clone(board6);
        
        for (int v7=0; v7<Figure[6].v; v7++) {
        temp6 = Figure[6].variation[v7];
        for (int i7=0; i7<8; i7++) {
        for (int j7=0; j7<8; j7++) {
        if (temp6.fits(board6, i7, j7)) {
        temp6.add(board7, i7, j7);
        board8 = clone(board7);
        
        for (int v8=0; v8<Figure[7].v; v8++) {
        temp7 = Figure[7].variation[v8];
        for (int i8=0; i8<8; i8++) {
        for (int j8=0; j8<8; j8++) {
        if (temp7.fits(board7, i8, j8)) {
        temp7.add(board8, i8, j8);
        
            // This is the code that executes when the program finds a solution to the puzzle.
            count++;
            boolean[][][] b = { board1,board2,board3,board4,board5,board6,board7,board8 };
            int[][] B = makeIntBoard(b);
            printSolution(B,count);
            drawSolution(B,count);
        
        board8 = clone(board7);
        }}}} // ij8 ends
        
        board7 = clone(board6);
        }}}} // ij7 ends
        
        board6 = clone(board5);
        }}}} // ij6 ends
        
        board5 = clone(board4);
        }}}} // ij5 ends
        
        board4 = clone(board3);
        }}}} // ij4 ends
        
        board3 = clone(board2);
        }}}} // ij3 ends
        
        board2 = clone(board1);
        }}}} // ij2 ends
        
        board1 = clone(board0);
        }}} // ij1 ends
        
        System.out.println("Total number of unique solutions: "+count);
        System.exit(0);
    }
}



/** changelog
 * 
 *      v1.0 [2012-12-23]:
 *          - solves the puzzle using 'figure' class
 *          - finds 6 unique solutions
 *          - saves the solutions both in a text file and as images on hard drive
 *          - runs in about 30 seconds on a 1.6GHz Intel Dual Core processor
 * 
 *   agenda
 *     - stop using 'StdDraw' to draw the solutions, but make my own drawing class
 *     - generalise the code so it can be used to solve similar but larger puzzles
 *          (using a generalised 'board' class mentioned in figure.java's agenda)
 */
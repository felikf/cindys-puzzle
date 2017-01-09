package com.felix.cindyspuzzle;

public class Puzzle {
    private static final int BLACK = 1;
    private static final int WHITE = 2;

    private static final int EMPTY = 0;

    public static void main(String[] args) {
        int[] board = new int[] {BLACK, BLACK, EMPTY, WHITE, WHITE};

        Puzzle marble = new Puzzle();
        marble.printBoard1(board);

        marble.solvable(board);

        marble.printBoard1(board);
    }

    private boolean solvable(int[] board) {
        if (puzzleSolved(board)) {
            return true;
        }
        for (int position = 0; position < board.length; position++) {
            if (canMove(board, position)) {
                int[] newBoard = makeMove(board, position);

                if (solvable(newBoard)) {
                    printBoard1(newBoard);
                    return true;
                }

//                System.out.println("NOT SOLVABLE: ");
//                System.out.println("Position: " + position);
//                printBoard1(board);
            }
        }
        return false;
    }

    private void printBoard1(int[] newBoard) {
        for (int i : newBoard) {
            if (i == BLACK) {
                System.out.print("BLACK ");
            } else if(i == WHITE) {
                System.out.print("WHITE ");
            } else {
                System.out.print("___ ");
            }
        }

        System.out.println();
    }

    private boolean canMove(int[] board, int position) {
        if (board[position] == EMPTY) {
            return false;
        }

        if (board[position] == BLACK) {
            if ((position + 1) < board.length  &&  board[position + 1] == EMPTY) {
                return true;
            }

            if ((position + 2) < board.length  &&  board[position + 2] == EMPTY   &&  board[position + 1] == WHITE) {
                return true;
            }
        }

        if (board[position] == WHITE) {
            if ((position - 1) >= 0  &&  board[position - 1] == EMPTY) {
                return true;
            }

            if ((position - 2) >= 0  &&  board[position - 2] == EMPTY  &&  board[position - 1] == BLACK) {
                return true;
            }
        }

        return false;
    }

    private int[] makeMove(int[] board, int position) {
        int[] newBoard = new int[board.length];

        System.arraycopy(board, 0, newBoard, 0, board.length);


        if (newBoard[position] == EMPTY) {
            throw new IllegalStateException();
        }

        if (newBoard[position] == BLACK) {
            if (newBoard[position + 1] == EMPTY) {
                newBoard[position] = EMPTY;
                newBoard[position + 1] = BLACK;
                return newBoard;
            }

            if (newBoard[position + 2] == EMPTY  &&  newBoard[position + 1] == WHITE) {
                newBoard[position] = EMPTY;
                newBoard[position + 2] = BLACK;
                return newBoard;
            }
        }

        if (newBoard[position] == WHITE) {
            if (newBoard[position - 1] == EMPTY) {
                newBoard[position] = EMPTY;
                newBoard[position - 1] = WHITE;
                return newBoard;
            }

            if (newBoard[position - 2] == EMPTY  &&  newBoard[position - 1] == BLACK) {
                newBoard[position] = EMPTY;
                newBoard[position - 2] = WHITE;
                return newBoard;
            }
        }

        return newBoard;
    }

    private boolean puzzleSolved(int[] board) {
        int half = board.length / 2;

        for (int i = 0; i < half; i++) {
            if (board[i] != WHITE) {
                return false;
            }
        }

        for (int i = half + 1; i < board.length; i++) {
            if (board[i] != BLACK) {
                return false;
            }
        }

        return true;
    }

}

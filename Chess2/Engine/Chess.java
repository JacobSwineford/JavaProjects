package Misc.CustomClasses.Chess2.Engine;

import javafx.scene.paint.Color;

import java.util.LinkedList;

import static Misc.CustomClasses.Chess2.Engine.Chess.Direction.*;

/**
 * Engine for the game of chess.
 *
 * @author Jacob Swineford
 */
public class Chess {
    private Piece[][] board;
    static Color[] colors = {Color.BLACK, Color.WHITE};

    public Chess() {
        board = new Piece[8][8];
        initialPieceSetup();
    }

    public void move(Piece piece, int x, int y) {
        int[] pos = positionOf(piece); // old position
        board[y][x] = piece;
        board[pos[1]][pos[0]] = null;
        piece.incrementMoves();
    }

    public Piece[][] getBoard() {return board;}

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] instanceof Pawn) sb.append("P ");
                else if (board[i][j] instanceof Knight) sb.append("k ");
                else if (board[i][j] instanceof Queen) sb.append("Q ");
                else if (board[i][j] instanceof King) sb.append("K ");
                else if (board[i][j] instanceof Bishop) sb.append("B ");
                else if (board[i][j] instanceof Rook) sb.append("R ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    enum Direction {
        upward,
        downward,
        left,
        right,
        upward_left,
        upward_right,
        downward_left,
        downward_right
    }

    class Pawn extends Piece {

        // used to calculate movements and attacks. note that for the
        // pawn this is mainly used for attacking, and moving is
        // tested for separately
        LinkedList<int[]> moveBias = new LinkedList<>();

        Pawn(Color color) {
            super(color);
            if (color == colors[0]) {
                moveBias.add(new int[] {-1, 1});
                moveBias.add(new int[] {1, 1});
            } else {
                moveBias.add(new int[] {-1, -1});
                moveBias.add(new int[] {1, -1});
            }
        }

        @Override
        public LinkedList<LinkedList<int[]>> getActions() {
            LinkedList<LinkedList<int[]>> actions = biasedActions(this, moveBias);
            actions.get(0).clear();
            actions.get(0).addAll(moves());
            return actions;
        }

        private LinkedList<int[]> moves() {
            LinkedList<int[]> moves = new LinkedList<>();
            int[] pos = positionOf(this);
            try {
                if (getColor() == colors[0]) {
                    if (board[pos[1] + 1][pos[0]] == null)
                        moves.add(new int[] {pos[0], pos[1] + 1});
                    if (getMoves() == 0 && board[pos[1] + 2][pos[0]] == null)
                        moves.add(new int[] {pos[0], pos[1] + 2});
                } else {
                    if (board[pos[1] - 1][pos[0]] == null)
                        moves.add(new int[] {pos[0], pos[1] - 1});
                    if (getMoves() == 0 && board[pos[1] - 2][pos[0]] == null)
                        moves.add(new int[] {pos[0], pos[1] - 2});
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                //
            }
            return moves;
        }
    }

    class Knight extends Piece {

        // used to calculate movements and attacks
        LinkedList<int[]> moveBias = new LinkedList<>();

        Knight(Color color) {
            super(color);
            moveBias.add(new int[] {-1, -2});
            moveBias.add(new int[] {-2, -1});
            moveBias.add(new int[] {-2, 1});
            moveBias.add(new int[] {-1, 2});
            moveBias.add(new int[] {1, 2});
            moveBias.add(new int[] {2, 1});
            moveBias.add(new int[] {2, -1});
            moveBias.add(new int[] {1, -2});
        }

        @Override
        public LinkedList<LinkedList<int[]>> getActions() {
            return biasedActions(this, moveBias);
        }
    }

    class Queen extends Piece {

        Queen(Color color) {
            super(color);
        }

        @Override
        public LinkedList<LinkedList<int[]>> getActions() {
            LinkedList<LinkedList<int[]>> actions = directionalActions(this, upward);
            LinkedList<int[]> moves = actions.get(0);
            LinkedList<int[]> attacks = actions.get(1);

            LinkedList<LinkedList<int[]>> ur = directionalActions(this, upward_right);
            LinkedList<LinkedList<int[]>> ul = directionalActions(this, upward_left);
            LinkedList<LinkedList<int[]>> dr = directionalActions(this, downward_right);
            LinkedList<LinkedList<int[]>> dl = directionalActions(this, downward_left);
            moves.addAll(ur.get(0)); attacks.addAll(ur.get(1));
            moves.addAll(ul.get(0)); attacks.addAll(ul.get(1));
            moves.addAll(dr.get(0)); attacks.addAll(dr.get(1));
            moves.addAll(dl.get(0)); attacks.addAll(dl.get(1));

            LinkedList<LinkedList<int[]>> d = directionalActions(this, downward);
            LinkedList<LinkedList<int[]>> r = directionalActions(this, right);
            LinkedList<LinkedList<int[]>> l = directionalActions(this, left);
            moves.addAll(d.get(0)); attacks.addAll(d.get(1));
            moves.addAll(r.get(0)); attacks.addAll(r.get(1));
            moves.addAll(l.get(0)); attacks.addAll(l.get(1));

            return actions;
        }
    }

    class King extends Piece {

        // used to calculate movements and attacks
        LinkedList<int[]> moveBias = new LinkedList<>();

        King(Color color) {
            super(color);
            moveBias.add(new int[] {-1, 0});
            moveBias.add(new int[] {-1, -1});
            moveBias.add(new int[] {0, -1});
            moveBias.add(new int[] {1, -1});
            moveBias.add(new int[] {1, 0});
            moveBias.add(new int[] {1, 1});
            moveBias.add(new int[] {0, 1});
            moveBias.add(new int[] {-1, 1});
        }

        @Override
        public LinkedList<LinkedList<int[]>> getActions() {
            return biasedActions(this, moveBias);
        }
    }

    class Bishop extends Piece {
        Bishop(Color color) {
            super(color);
        }

        @Override
        public LinkedList<LinkedList<int[]>> getActions() {
            LinkedList<LinkedList<int[]>> actions = new LinkedList<>(directionalActions(this, upward_left));
            LinkedList<int[]> moves = actions.get(0);
            LinkedList<int[]> attacks = actions.get(1);

            LinkedList<LinkedList<int[]>> ur = directionalActions(this, upward_right);
            LinkedList<LinkedList<int[]>> dr = directionalActions(this, downward_right);
            LinkedList<LinkedList<int[]>> dl = directionalActions(this, downward_left);
            moves.addAll(ur.get(0)); attacks.addAll(ur.get(1));
            moves.addAll(dr.get(0)); attacks.addAll(dr.get(1));
            moves.addAll(dl.get(0)); attacks.addAll(dl.get(1));

            return actions;
        }
    }

    class Rook extends Piece {

        Rook(Color color) {
            super(color);
        }

        @Override
        public LinkedList<LinkedList<int[]>> getActions() {
            LinkedList<LinkedList<int[]>> actions = new LinkedList<>(directionalActions(this, upward));
            LinkedList<int[]> moves = actions.get(0);
            LinkedList<int[]> attacks = actions.get(1);

            LinkedList<LinkedList<int[]>> d = directionalActions(this, downward);
            LinkedList<LinkedList<int[]>> r = directionalActions(this, right);
            LinkedList<LinkedList<int[]>> l = directionalActions(this, left);
            moves.addAll(d.get(0)); attacks.addAll(d.get(1));
            moves.addAll(r.get(0)); attacks.addAll(r.get(1));
            moves.addAll(l.get(0)); attacks.addAll(l.get(1));

            return actions;
        }
    }

    private void initialPieceSetup() {
        for (int i = 0; i < 8; i++) board[1][i] = new Pawn(colors[0]);
        for (int i = 0; i < 8; i++) board[6][i] = new Pawn(colors[1]);
        board[0][0] = new Rook(colors[0]);
        board[0][7] = new Rook(colors[0]);
        board[7][0] = new Rook(colors[1]);
        board[7][7] = new Rook(colors[1]);

        board[0][1] = new Knight(colors[0]);
        board[0][6] = new Knight(colors[0]);
        board[7][1] = new Knight(colors[1]);
        board[7][6] = new Knight(colors[1]);

        board[0][2] = new Bishop(colors[0]);
        board[0][5] = new Bishop(colors[0]);
        board[7][2] = new Bishop(colors[1]);
        board[7][5] = new Bishop(colors[1]);

        board[0][3] = new Queen(colors[0]);
        board[7][3] = new Queen(colors[1]);
        board[0][4] = new King(colors[0]);
        board[7][4] = new King(colors[1]);
    }

    /**
     * Gets the actions that the given piece can take on the current board,
     * using their move bias as a guide.
     *
     * @param piece given piece
     * @param moveBias given piece's move bias
     *
     * @return a list of actions that this piece can take based on the current
     *         status of the board
     */
    private LinkedList<LinkedList<int[]>> biasedActions(Piece piece, LinkedList<int[]> moveBias) {
        LinkedList<LinkedList<int[]>> actions = new LinkedList<>();
        LinkedList<int[]> moves = new LinkedList<>();
        LinkedList<int[]> attacks = new LinkedList<>();
        int[] pos = positionOf(piece);
        for (int[] arr : moveBias) {
            int x = pos[0] + arr[0];
            int y = pos[1] + arr[1];
            try {
                if (board[y][x] == null) moves.add(new int[] {x, y});
                else if (board[y][x].getColor() != piece.getColor())
                    attacks.add(new int[] {x, y});
            } catch (ArrayIndexOutOfBoundsException e) {
                //
            }
        }
        actions.add(moves);
        actions.add(attacks);
        return actions;
    }

    private LinkedList<LinkedList<int[]>> directionalActions(Piece piece, Direction direction) {
        LinkedList<LinkedList<int[]>> actions = new LinkedList<>();
        LinkedList<int[]> moves = new LinkedList<>();
        LinkedList<int[]> attacks = new LinkedList<>();
        int[] pos = positionOf(piece);
        int x = pos[0];
        int y = pos[1];
        while (true) {
            switch (direction) {
                case left: x -= 1; break;
                case right: x += 1; break;
                case upward: y -= 1; break;
                case downward: y += 1; break;
                case upward_left: {x -= 1; y -= 1; break;}
                case upward_right: {x += 1; y -= 1; break;}
                case downward_left: {x -= 1; y += 1; break;}
                case downward_right: {x += 1; y += 1;}
            }
            try {
                if (board[y][x] == null) moves.add(new int[] {x, y});
                else if (board[y][x].getColor() != piece.getColor()) {
                    attacks.add(new int[] {x, y});
                    break;
                } else {
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        actions.add(moves);
        actions.add(attacks);
        return actions;
    }

    private int[] positionOf(Piece piece) {
        int[] ret = {-1, -1};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == piece) {
                    ret[0] = j;
                    ret[1] = i;
                }
            }
        }
        return ret;
    }
}



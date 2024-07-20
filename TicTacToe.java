import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private char[][] board;
    private char currentPlayerMark;
    private JLabel messageLabel;
    
    public TicTacToe() {
        buttons = new JButton[3][3];
        board = new char[3][3];
        currentPlayerMark = 'X';
        
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(this);
                buttonPanel.add(buttons[i][j]);
                board[i][j] = '-';
            }
        }
        
        messageLabel = new JLabel("Player " + currentPlayerMark + "'s turn", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        
        add(buttonPanel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);
        
        setSize(400, 400);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        int row = -1, col = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clickedButton == buttons[i][j]) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        if (row == -1 || col == -1) {
            return;
        }
        if (board[row][col] != '-') {
            messageLabel.setText("Invalid move!");
            return;
        }
        board[row][col] = currentPlayerMark;
        clickedButton.setText(String.valueOf(currentPlayerMark));
        if (checkForWin()) {
            messageLabel.setText("Player " + currentPlayerMark + " wins!");
            disableButtons();
            return;
        } else if (checkForTie()) {
            messageLabel.setText("It's a tie!");
            disableButtons();
            return;
        }
        switchPlayer();
        messageLabel.setText("Player " + currentPlayerMark + "'s turn");
    }
    
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }
    
    public boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkDiagonalsForWin() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]));
    }
    
    public boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 == c2) && (c2 == c3) && (c1 != '-'));
    }

    public boolean checkForTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    
    public void switchPlayer() {
        if (currentPlayerMark == 'X') {
            currentPlayerMark = 'O';
        } else {
            currentPlayerMark = 'X';
        }
    }
    
    public static void main(String[] args) {
        new TicTacToe();
    }

}
    

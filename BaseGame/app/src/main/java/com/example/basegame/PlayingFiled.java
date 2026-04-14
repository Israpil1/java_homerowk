package com.example.basegame;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayingFiled extends AppCompatActivity {

    private int[] boardState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private ImageView[] boardButtons = new ImageView[9];

    private boolean isPlayerOneTurn = true;
    private boolean isVsComputer = false;
    private String difficulty = "Easy";
    private boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_field);

        isVsComputer = getIntent().getBooleanExtra("isVsComputer", false);
        difficulty = getIntent().getStringExtra("difficulty");
        String p1Name = getIntent().getStringExtra("playerOne");
        String p2Name = getIntent().getStringExtra("playerTwo");

        TextView playerOneTitle = findViewById(R.id.playerOneName);
        TextView playerTwoTitle = findViewById(R.id.playerTwoName);
        if(playerOneTitle != null) playerOneTitle.setText(p1Name);
        if(playerTwoTitle != null) playerTwoTitle.setText(p2Name);

        for (int i = 0; i < 9; i++) {
            String buttonID = "image" + (i + 1);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            boardButtons[i] = findViewById(resID);

            final int index = i;
            boardButtons[i].setOnClickListener(v -> handleBoxClick(index));
        }
    }

    private void handleBoxClick(int index) {
        if (boardState[index] == 0 && gameActive) {
            performMove(index);

            if (gameActive && isVsComputer && !isPlayerOneTurn) {
                setBoardEnabled(false);
                makeComputerMove();
            }
        }
    }

    private void performMove(int index) {
        if (isPlayerOneTurn) {
            boardState[index] = 1;
            boardButtons[index].setImageResource(R.drawable.ic_xicon);
        } else {
            boardState[index] = 2;
            boardButtons[index].setImageResource(R.drawable.ic_oicon);
        }

        if (checkWin()) {
            String winner = isPlayerOneTurn ? "Player One Wins!" : "Player Two Wins!";
            Toast.makeText(this, winner, Toast.LENGTH_LONG).show();
            gameActive = false;
        } else if (isBoardFull()) {
            Toast.makeText(this, "It's a Draw!", Toast.LENGTH_LONG).show();
            gameActive = false;
        } else {
            isPlayerOneTurn = !isPlayerOneTurn;
        }
    }

    private void makeComputerMove() {
        new Handler().postDelayed(() -> {
            int move = -1;

            if ("Hard".equals(difficulty)) {
                move = getBestMove(); // Теперь здесь вызывается Minimax
            } else if ("Medium".equals(difficulty)) {
                move = getMediumMove();
            } else {
                move = getRandomMove();
            }

            if (move != -1) {
                performMove(move);
            }
            setBoardEnabled(true);
        }, 600);
    }

    private int getRandomMove() {
        List<Integer> available = new ArrayList<>();
        for (int i = 0; i < 9; i++) if (boardState[i] == 0) available.add(i);
        return available.isEmpty() ? -1 : available.get(new Random().nextInt(available.size()));
    }

    private int getMediumMove() {
        int move = findWinningMove(2);
        if (move != -1) return move;
        move = findWinningMove(1);
        if (move != -1) return move;
        return getRandomMove();
    }

    private int findWinningMove(int player) {
        for (int i = 0; i < 9; i++) {
            if (boardState[i] == 0) {
                boardState[i] = player;
                if (checkWin()) {
                    boardState[i] = 0;
                    return i;
                }
                boardState[i] = 0;
            }
        }
        return -1;
    }

    private int getBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for (int i = 0; i < 9; i++) {
            if (boardState[i] == 0) {
                boardState[i] = 2; // Пробуем ход компьютера
                int score = minimax(boardState, 0, false);
                boardState[i] = 0; // Отмена хода
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        return move;
    }

    private int minimax(int[] board, int depth, boolean isMaximizing) {
        if (checkWinForMinimax(2)) return 10 - depth;
        if (checkWinForMinimax(1)) return depth - 10;
        if (isBoardFull()) return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == 0) {
                    board[i] = 2;
                    int score = minimax(board, depth + 1, false);
                    board[i] = 0;
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == 0) {
                    board[i] = 1;
                    int score = minimax(board, depth + 1, true);
                    board[i] = 0;
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    private boolean checkWinForMinimax(int p) {
        int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
        for (int[] pos : winPositions) {
            if (boardState[pos[0]] == p && boardState[pos[1]] == p && boardState[pos[2]] == p) return true;
        }
        return false;
    }

    private boolean checkWin() {
        int[][] winPositions = {
                {0,1,2}, {3,4,5}, {6,7,8},
                {0,3,6}, {1,4,7}, {2,5,8},
                {0,4,8}, {2,4,6}
        };
        for (int[] pos : winPositions) {
            if (boardState[pos[0]] != 0 &&
                    boardState[pos[0]] == boardState[pos[1]] &&
                    boardState[pos[1]] == boardState[pos[2]]) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int state : boardState) if (state == 0) return false;
        return true;
    }

    private void setBoardEnabled(boolean enabled) {
        for (ImageView btn : boardButtons) btn.setEnabled(enabled);
    }
    public void restartMatch() {
        boardState = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        gameActive = true;
        isPlayerOneTurn = true;

        for (ImageView button : boardButtons) {
            button.setImageResource(0); // Удаляет изображение
            button.setEnabled(true);    // Включает кнопку обратно
        }
    }
}
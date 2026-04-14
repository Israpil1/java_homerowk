package com.example.basegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText playerOne = findViewById(R.id.playerOne);
        EditText playerTwo = findViewById(R.id.playerTwo);
        RadioGroup gameModeGroup = findViewById(R.id.gameModeGroup);
        Spinner difficultySpinner = findViewById(R.id.difficultySpinner);
        Button startGameButton = findViewById(R.id.startGameButton);

        String[] difficulties = {"Easy", "Medium", "Hard"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficulties);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        difficultySpinner.setVisibility(View.GONE);

        gameModeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.pve) {
                playerTwo.setText("Computer");
                playerTwo.setEnabled(false);
                difficultySpinner.setVisibility(View.VISIBLE);
            } else {
                playerTwo.setText("");
                playerTwo.setEnabled(true);
                difficultySpinner.setVisibility(View.GONE);
            }
        });

        startGameButton.setOnClickListener(v -> {
            String getPlayerOneName = playerOne.getText().toString().trim();
            String getPlayerTwoName = playerTwo.getText().toString().trim();

            boolean isVsComputer = (gameModeGroup.getCheckedRadioButtonId() == R.id.pve);

            String difficulty = difficultySpinner.getSelectedItem().toString();

            if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter names for both players", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, PlayingFiled.class);
                intent.putExtra("playerOne", getPlayerOneName);
                intent.putExtra("playerTwo", getPlayerTwoName);
                intent.putExtra("isVsComputer", isVsComputer);
                intent.putExtra("difficulty", difficulty);
                startActivity(intent);
            }
        });
    }
}
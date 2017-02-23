package com.example.android.soccerchance;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int playerScore = 0;
    int aiScore = 0;
    int turnCount = 0;

    Button highleftButton;
    Button highRightButton;
    Button lowleftButton ;
    Button lowRightButton;
    TextView resultTextView;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        highleftButton = (Button) findViewById(R.id.kick_high_left);
        highRightButton = (Button) findViewById(R.id.kick_high_right);
        lowleftButton = (Button) findViewById(R.id.kick_low_left);
        lowRightButton = (Button) findViewById(R.id.kick_low_right);
        resultTextView = (TextView) findViewById(R.id.result_text_view);

        whosTurn(turnCount);
    }

    public void shoot(View view) {
        switch (view.getId()) {
            case R.id.kick_high_left:
                if (highleftButton.getText().toString() == "Kick High Left") {
                    checkAIBlock(1);
                } else {
                    aiShot(1);
                }
                break;
            case R.id.kick_high_right:
                if (highRightButton.getText().toString() == "Kick High Right") {
                    checkAIBlock(2);
                } else {
                    aiShot(2);
                }
                break;
            case R.id.kick_low_left:
                if (lowleftButton.getText().toString() == "Kick Low Left") {
                    checkAIBlock(3);
                } else {
                    aiShot(3);
                }
                break;
            case R.id.kick_low_right:
                if (lowRightButton.getText().toString() == "Kick Low Right") {
                    checkAIBlock(4);
                } else {
                    aiShot(4);
                }
                break;
        }
    }

    private void checkAIBlock(int count) {
        int aiBlock = rand.nextInt(4) + 1;
        System.out.println("Player: Shoots " + count + "\nAI blocks with " + aiBlock);
        if (aiBlock == count) {
            resultText(false, 1);
        } else {
            playerScore += 1;
            updatePlayerScore();
            resultText(true, 1);
        }
    }

    private void aiShot(int count) {
        int aiShoot = rand.nextInt(4) + 1;
        System.out.println("AI: Shoots " + aiShoot + "\nPlayer blocks with " + count);
        if (aiShoot == count) {
            resultText(false, 0);
        } else {
            aiScore += 1;
            updateAiScore();
            resultText(true, 0);
        }
    }

    private void updatePlayerScore() {
        TextView playerScoreText = (TextView) findViewById(R.id.player_score);
        playerScoreText.setText(Integer.toString(playerScore));
    }

    private void updateAiScore() {
        TextView aiScoreText = (TextView) findViewById(R.id.ai_score);
        aiScoreText.setText(Integer.toString(aiScore));
    }

    private void changeButtonTitle(boolean aiTurn) {
        if (aiTurn) {
            highleftButton.setText("Block High Left");
            highRightButton.setText("Block High Right");
            lowleftButton.setText("Block Low Left");
            lowRightButton.setText("Block Low Right");
        } else {
            highleftButton.setText("Kick High Left");
            highRightButton.setText("KickHigh Right");
            lowleftButton.setText("Kick Low Left");
            lowRightButton.setText("Kick Low Right");
        }

    }

    private void whosTurn(int count) {
        TextView turnTextView = (TextView) findViewById(R.id.whos_turn);
        if (count == 0) {
            turnTextView.setText("Player");
            changeButtonTitle(false);
        } else {
            turnTextView.setText("CPU");
            changeButtonTitle(true);
        }
    }

    private void resultText(boolean scored, int whoTurn) {
        resultTextView.setTextColor(Color.BLACK);
        turnCount = whoTurn;

        if (scored) {
            resultTextView.setText("Goal!");
            resultTextView.setTextColor(Color.TRANSPARENT);
            whosTurn(turnCount);
        } else {
            resultTextView.setText("Blocked!");
            resultTextView.setTextColor(Color.TRANSPARENT);
            whosTurn(turnCount);
        }

    }

    private void stallTime(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}

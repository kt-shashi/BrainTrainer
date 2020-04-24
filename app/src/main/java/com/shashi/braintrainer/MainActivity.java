package com.shashi.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView resultTextView;
    TextView scoreTextView;
    private int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView sumTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView timerTextView;
    ConstraintLayout gameLayout;

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct Answer :)");
            score++;
            numberOfQuestions++;
        } else {
            resultTextView.setText("Wrong Answer :(");
            numberOfQuestions++;
        }
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void newQuestion() {
        Random rand = new Random();
        int choose = rand.nextInt(3);
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        switch (choose) {
            case 0:
                answers.clear();

                sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

                locationOfCorrectAnswer = rand.nextInt(4);
                for (int i = 0; i < 4; i++) {
                    if (i == locationOfCorrectAnswer) {
                        answers.add(a + b);
                    } else {
                        int wrongAnswer = rand.nextInt(41);
                        while (wrongAnswer == (a + b)) {
                            wrongAnswer = rand.nextInt(41);
                        }
                        answers.add(wrongAnswer);
                    }
                }

                button0.setText(Integer.toString(answers.get(0)));
                button1.setText(Integer.toString(answers.get(1)));
                button2.setText(Integer.toString(answers.get(2)));
                button3.setText(Integer.toString(answers.get(3)));
                break;
            case 1:
                answers.clear();

                sumTextView.setText(Integer.toString(a) + " - " + Integer.toString(b));

                locationOfCorrectAnswer = rand.nextInt(4);
                for (int i = 0; i < 4; i++) {
                    if (i == locationOfCorrectAnswer) {
                        answers.add(a - b);
                    } else {
                        int wrongAnswer = rand.nextInt(41);
                        while (wrongAnswer == (a - b)) {
                            wrongAnswer = rand.nextInt(41);
                        }
                        answers.add(wrongAnswer);
                    }
                }

                button0.setText(Integer.toString(answers.get(0)));
                button1.setText(Integer.toString(answers.get(1)));
                button2.setText(Integer.toString(answers.get(2)));
                button3.setText(Integer.toString(answers.get(3)));
                break;
            case 2:
                answers.clear();
                a = rand.nextInt(11);
                b = rand.nextInt(11);

                sumTextView.setText(Integer.toString(a) + " * " + Integer.toString(b));

                locationOfCorrectAnswer = rand.nextInt(4);
                for (int i = 0; i < 4; i++) {
                    if (i == locationOfCorrectAnswer) {
                        answers.add(a * b);
                    } else {
                        int wrongAnswer = rand.nextInt(101);
                        while (wrongAnswer == (a * b)) {
                            wrongAnswer = rand.nextInt(101);
                        }
                        answers.add(wrongAnswer);
                    }
                }

                button0.setText(Integer.toString(answers.get(0)));
                button1.setText(Integer.toString(answers.get(1)));
                button2.setText(Integer.toString(answers.get(2)));
                button3.setText(Integer.toString(answers.get(3)));
                break;
        }
    }

    public void playAgain(View view) {
        playAgainButton.setVisibility(View.INVISIBLE);
        score = 0;
        numberOfQuestions = 0;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        timerTextView.setText("30");
        resultTextView.setText("");

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        newQuestion();

        new CountDownTimer(30100, 1000) {

            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }

            public void onFinish() {
                resultTextView.setText("!-Over-!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();

    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(timerTextView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        goButton = (Button) findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgain);
        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}

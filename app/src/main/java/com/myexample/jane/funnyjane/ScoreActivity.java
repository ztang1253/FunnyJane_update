package com.myexample.jane.funnyjane;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ScoreActivity extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_score);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_score = (TextView) findViewById(R.id.tv_score);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final PersonEntity person = (PersonEntity) bundle.getSerializable("content");
        int score;
        int sex = person.getSex();
        switch (sex) {
            case Sex.MALE:
                score = new Random().nextInt(sex * 400);
                break;
            case Sex.FEMALE:
                score = new Random().nextInt(sex * 200);
                break;
            case Sex.UNKNOWN:
                score = new Random().nextInt(sex * 133);
                break;
            case Sex.UNDIFFERENTIATED:
            default:
                score = new Random().nextInt(sex * 100);
                break;
        }

        person.setScore(score);
        tv_name.setText(person.toString());
        tv_score.setText("Your funny score is " + score);

        ArrayList<PersonEntity> list = (ArrayList<PersonEntity>)ObjectIOUtils.read(ScoreActivity.this, MainActivity.FILE_PATH);
        if (list == null)
            list = new ArrayList<>();

        list.add(person);
        Collections.sort(list, new Comparator<PersonEntity>() {
            @Override
            public int compare(PersonEntity personEntity, PersonEntity t1) {
                return t1.getScore() - personEntity.getScore();
            }
        });

        if (list.size() > MainActivity.RECORD_MAX)
            list.remove(MainActivity.RECORD_MAX);

        ObjectIOUtils.write(ScoreActivity.this, list, MainActivity.FILE_PATH);
    }
}

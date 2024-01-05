package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvScores implements Scores {

    private final List<Score> scores = new ArrayList<>();

    @Override
    public void load() {

        ClassLoader loader = CsvScores.class.getClassLoader();

        try (FileInputStream file = new FileInputStream(loader.getResource("data/score.csv").getFile());
             BufferedReader br = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8))) {

            String line = "";
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                int seq = Integer.parseInt(split[0]);
                int score = Integer.parseInt(split[1]);

                Score studentScore = new Score(seq, score);
                scores.add(studentScore);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Score> findAll() {
        return scores;
    }
}
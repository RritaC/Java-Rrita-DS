package com.example;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreAnnotations;

import java.util.Properties;

public class MoodPredictor {
    private final StanfordCoreNLP pipeline;

    public MoodPredictor() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,parse,sentiment");
        pipeline = new StanfordCoreNLP(props);
    }

    public String predictMood(String text) {
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);

        String mood = "Neutral"; // Default mood
        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            String sentiment = sentence.get(CoreAnnotations.SentimentClass.class);
            switch (sentiment) {
                case "Very positive":
                case "Positive":
                    mood = "Positive 🙂";
                    break;
                case "Negative":
                case "Very negative":
                    mood = "Negative 🙁";
                    break;
                case "Neutral":
                    mood = "Neutral 😐";
                    break;
            }
        }
        return mood;
    }
}

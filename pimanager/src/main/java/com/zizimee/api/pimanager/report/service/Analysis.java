package com.zizimee.api.pimanager.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class Analysis {
    public Map<String, Integer> analyzeWords(List<String> comments) {
        Map<String, Integer> wordList = new HashMap<>();
        for(String comment : comments) {
            String[] words = comment.split("[ \n]");
            for(int i=0; i<words.length; i++) {
                Integer freq = wordList.get(words[i]);
                wordList.put(words[i], (freq==null) ? 1 : freq+1);
            }
        }
        return wordList;
    }
}

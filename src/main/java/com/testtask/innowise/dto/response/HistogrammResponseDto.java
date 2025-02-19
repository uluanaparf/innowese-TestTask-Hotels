package com.testtask.innowise.dto.response;


import java.util.Map;

public class HistogrammResponseDto {
    private Map<String, Integer> histogram;

    public Map<String, Integer> getHistogram() {
        return histogram;
    }

    public void setHistogram(Map<String, Integer> histogram) {
        this.histogram = histogram;
    }

    public HistogrammResponseDto(Map<String, Integer> histogram) {
        this.histogram = histogram;
    }
}

package com.siad.project.model;

public class Recommendation {
    private int id;
    private String region;
    private String recommendation;
    private String reason;
    private String priority; // HIGH, MEDIUM, LOW
    private double impactScore;

    public Recommendation() {}

    public Recommendation(String region, String recommendation, String reason, String priority, double impactScore) {
        this.region = region;
        this.recommendation = recommendation;
        this.reason = reason;
        this.priority = priority;
        this.impactScore = impactScore;
    }

    public Recommendation(int id, String region, String recommendation, String reason, String priority, double impactScore) {
        this.id = id;
        this.region = region;
        this.recommendation = recommendation;
        this.reason = reason;
        this.priority = priority;
        this.impactScore = impactScore;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public double getImpactScore() {
        return impactScore;
    }

    public void setImpactScore(double impactScore) {
        this.impactScore = impactScore;
    }
}

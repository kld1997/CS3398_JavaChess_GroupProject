package Profiles;

import java.io.Serializable;

public class Profile implements Serializable {
    private String username;
    private int score;
    private int rank;

    public Profile(String username) {
        this.username = username;
        score = 0;
        rank = 0;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUsername() { return username;}

    public int getScore() { return score;}

    public int getRank() { return rank; }

    public void checkRank() {
        if (score >= 1200) {
            rank = 1;
        } else if (score >= 2400) {
            rank = 2;
        } else if (score >= 3600) {
            rank = 3;
        } else if (score >= 4800) {
            rank = 4;
        } else if (score >= 6000) {
            rank = 5;
        } else if (score >= 7200) {
            rank = 6;
        } else if (score >= 8400) {
            rank = 7;
        } else if (score >= 9600) {
            rank = 8;
        } else if (score >= 10800) {
            rank = 9;
        } else if (score >= 12000) {
            rank = 10;
        }
    }
}

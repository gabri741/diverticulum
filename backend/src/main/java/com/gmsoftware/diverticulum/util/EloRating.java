package com.gmsoftware.diverticulum.util;

public abstract class EloRating {
    
    private static final int K = 32; // Fator de ajuste

    // Método para calcular a expectativa de vitória
    public static double calculateExpectedScore(long ratingA, long ratingB) {
        return 1.0 / (1.0 + Math.pow(10, (ratingB - ratingA) / 400.0));
    }

    // Método para atualizar o rating de um jogador
    public static long updateRating(long currentRating, double expectedScore, double actualScore) {
        return (long) (currentRating + K * (actualScore - expectedScore));
    }
    
}

package fr.lernejo.guessgame;

import fr.lernejo.logger.Logger;
import fr.lernejo.logger.LoggerFactory;

public class Simulation {

    private final Logger logger = LoggerFactory.getLogger("simulation");
    private final Player player;
    private long numberToGuess;
    public Simulation(Player player) {
        this.player = player;
    }

    public void initialize(long numberToGuess) {
        this.numberToGuess = numberToGuess;
    }
    private boolean nextRound() {
        long guess = player.askNextGuess();
        if (guess == numberToGuess) {
            logger.log("Congratulations! You guessed the correct number.");
            return true;
        }
        else if (guess > numberToGuess)
        {
            player.respond(true);
        }
        else
        {
            player.respond(false);
        }
        return false;
    }

    public void loopUntilPlayerSucceed(long maxIterations) {
        long startTime = System.currentTimeMillis();
        long iterations = 0;
        boolean success;

        do {
            success = nextRound();
            iterations++;
        } while (!success && iterations < maxIterations);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        if (success) {
            logger.log("You won! It took " + iterations + " iterations in " + formatDuration(duration));
        } else {
            logger.log("You lost! The maximum number of iterations (" + maxIterations + ") was reached.");
        }
    }

    private String formatDuration(long durationMillis) {
        long minutes = durationMillis / 60000;
        long seconds = (durationMillis % 60000) / 1000;
        long milliseconds = durationMillis % 1000;
        return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds);

    }
}
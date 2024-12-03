package fr.lernejo.guessgame;

import fr.lernejo.logger.Logger;
import fr.lernejo.logger.LoggerFactory;

public class ComputerPlayer implements Player {

    private final Logger logger = LoggerFactory.getLogger("player");
    private long lowerBound = 0;
    private long upperBound = Long.MAX_VALUE;

    @Override
    public long askNextGuess() {
        long guess = (lowerBound + upperBound) / 2;
        logger.log("Computer guesses: " + guess);
        return guess;
    }

    @Override
    public void respond(boolean lowerOrGreater) {
        if (lowerOrGreater) {
            upperBound = (lowerBound + upperBound) / 2;
        } else {
            lowerBound = (lowerBound + upperBound) / 2 + 1;
        }
    }
}
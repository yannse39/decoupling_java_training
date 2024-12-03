package fr.lernejo.guessgame;

import fr.lernejo.logger.Logger;
import fr.lernejo.logger.LoggerFactory;

import java.security.SecureRandom;

public class Launcher {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("launcher");

        if (args.length == 1 && args[0].equals("-interactive")) {
            logger.log("Starting interactive mode...");
            HumanPlayer humanPlayer = new HumanPlayer();
            Simulation simulation = new Simulation(humanPlayer);

            SecureRandom random = new SecureRandom();
            long randomNumber = random.nextInt(100);
            simulation.initialize(randomNumber);

            simulation.loopUntilPlayerSucceed(Long.MAX_VALUE);
        } else if (args.length == 2 && args[0].equals("-auto")) {
            try {
                long numberToGuess = Long.parseLong(args[1]);
                logger.log("Starting auto mode with number: " + numberToGuess);

                ComputerPlayer computerPlayer = new ComputerPlayer();
                Simulation simulation = new Simulation(computerPlayer);

                simulation.initialize(numberToGuess);
                simulation.loopUntilPlayerSucceed(1000);
            } catch (NumberFormatException e) {
                logger.log("Invalid number format for auto mode. Please provide a valid number.");
            }
        } else {
            logger.log("Usage:");
            logger.log("  -interactive: Play the game interactively with a human player");
            logger.log("  -auto <number>: Play the game automatically with the given number to guess");
        }
    }
}
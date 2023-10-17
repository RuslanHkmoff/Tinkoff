package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameRunner {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String INVALID_INPUT = "Invalid input, please try again";
    private final Word word;
    private final GameProvider gameProvider;
    private Session session;

    public GameRunner() {
        Dictionary dictionary = new Dictionary();
        this.word = new Word(dictionary.getRandomWord());
        this.gameProvider = new GameProvider();
    }

    public void run() {
        LOGGER.info("Started game");
        LOGGER.info("Choose the number of mistakes you can make: ");
        int maxAttempts = getMaxAttempts();
        session = new Session(word, maxAttempts);
        GuessResult currResult;
        do {
            LOGGER.info("The word: " + word.getHiddenWord());
            currResult = makeMove();
            printState(currResult);
        } while (!session.isGameOver());
    }

    private int getMaxAttempts() {
        while (true) {
            try {
                int numberFromUser = Integer.parseInt(gameProvider.getNumberOfMistakes());
                return numberFromUser + word.getLength();
            } catch (NumberFormatException e) {
                LOGGER.info(INVALID_INPUT);
            }
        }
    }

    private GuessResult makeMove() {
        String input = gameProvider.getUserInput();
        return session.guess(input);
    }

    private void printState(GuessResult guess) {
        LOGGER.info(guess.message());
    }
}

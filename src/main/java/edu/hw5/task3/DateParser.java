package edu.hw5.task3;


import edu.hw5.task3.handler.DateParserHandler;
import edu.hw5.task3.handler.DateParserHandler1;
import edu.hw5.task3.handler.DateParserHandler2;
import edu.hw5.task3.handler.DateParserHandler3;
import edu.hw5.task3.handler.DateParserHandler4;
import edu.hw5.task3.handler.DateParserHandler5;
import edu.hw5.task3.handler.DateParserHandler6;
import java.time.LocalDate;
import java.util.Optional;

public class DateParser {

    private DateParser() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        DateParserHandler chain = new DateParserHandler1(
            new DateParserHandler2(
                new DateParserHandler3(
                    new DateParserHandler4(
                        new DateParserHandler5(
                            new DateParserHandler6(null)
                        )
                    )
                )
            )
        );

        return chain.handleRequest(string);
    }
}


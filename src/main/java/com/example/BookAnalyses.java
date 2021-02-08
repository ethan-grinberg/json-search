package com.example;

import com.example.models.Book;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public final class BookAnalyses {
    public static double averageAuthorPolarity(final List<Book> bookList, final String author) {
        if (bookList == null || author == null) {
            throw new IllegalArgumentException();
        }
        List<Book> booksByAuthor = BookFilters.filterByAuthorName(bookList, author);
        double polaritySum = 0;
        for (Book book : booksByAuthor) {
            double polarity = book.getMetrics().getSentiments().getPolarity();
            polaritySum += polarity;
        }
        double averagePolarity = polaritySum / booksByAuthor.size();
        //The following code comes from:
        //https://www.programiz.com/java-programming/examples/round-number-decimal
        DecimalFormat dFormat = new DecimalFormat("#.######");
        dFormat.setRoundingMode(RoundingMode.CEILING);
        return Double.parseDouble(dFormat.format(averagePolarity));
    }
}

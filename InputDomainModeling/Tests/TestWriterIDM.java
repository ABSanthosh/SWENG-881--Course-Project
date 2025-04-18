
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

import de.siegmar.fastcsv.writer.CsvWriter;
import de.siegmar.fastcsv.writer.QuoteStrategies;
import de.siegmar.fastcsv.writer.LineDelimiter;

public class TestWriterIDM {

    @Test
    public void test() throws IOException {

        /**
         * ************************************************************************************************************
         * Test: idm-w-01 Description: Baseline test for normal values with all
         * default settings.
         ************************************************************************************************************
         */
        StringWriter sw1 = new StringWriter();
        CsvWriter writer1 = CsvWriter.builder().build(sw1);
        writer1.writeRecord("hello", "world");
        writer1.close();
        assertEquals("hello,world\r\n", sw1.toString());

        /**
         * ************************************************************************************************************
         * Test: idm-w-02 Description: Verify handling of null values with a
         * strategy that forces quoting.
         ************************************************************************************************************
         */
        StringWriter sw2 = new StringWriter();
        CsvWriter writer2 = CsvWriter.builder()
                .quoteStrategy(QuoteStrategies.ALWAYS)
                .build(sw2);
        writer2.writeRecord(null, null);
        writer2.close();
        assertEquals("\"\"",
        \"\"\r\n", sw2.toString()
        );

        /**************************************************************************************************************
         * Test:        idm-w-03
         * Description: Ensure empty strings are correctly quoted under a forced quote strategy.
         *************************************************************************************************************/
        StringWriter sw3 = new StringWriter();
        CsvWriter writer3 = CsvWriter.builder()
                .quoteStrategy(QuoteStrategies.ALWAYS)
                .build(sw3);
        writer3.writeRecord("");
        writer3.close();
        assertEquals("\"\"\r\n", sw3.toString());

        /**
         * ************************************************************************************************************
         * Test: idm-w-04 Description: Validate escaping of special characters
         * like comma, quotes, and newlines.
         ************************************************************************************************************
         */
        StringWriter sw4 = new StringWriter();
        CsvWriter writer4 = CsvWriter.builder().build(sw4);
        writer4.writeRecord("comma, separated", "\"quoted\"");
        writer4.close();
        assertEquals("\"comma, separated\",\"\"quoted\"\"\r\n", sw4.toString());

        /**
         * ************************************************************************************************************
         * Test: idm-w-05 Description: Test alternate field separator (`;`) with
         * normal string values.
         ************************************************************************************************************
         */
        StringWriter sw5 = new StringWriter();
        CsvWriter writer5 = CsvWriter.builder()
                .fieldSeparator(';')
                .build(sw5);
        writer5.writeRecord("a", "b");
        writer5.close();
        assertEquals("a;b\r\n", sw5.toString());

        /**
         * ************************************************************************************************************
         * Test: idm-w-06 Description: Use a non-default quote character (`'`)
         * and ensure output is consistent.
         ************************************************************************************************************
         */
        StringWriter sw6 = new StringWriter();
        CsvWriter writer6 = CsvWriter.builder()
                .quoteCharacter('\'')
                .build(sw6);
        writer6.writeRecord("x", "y");
        writer6.close();
        assertEquals("x,y\r\n", sw6.toString());

        /**
         * ************************************************************************************************************
         * Test: idm-w-07 Description: Apply a quoting strategy that forces
         * quotes on non-empty fields.
         ************************************************************************************************************
         */
        StringWriter sw7 = new StringWriter();
        CsvWriter writer7 = CsvWriter.builder()
                .quoteStrategy(QuoteStrategies.ALWAYS)
                .build(sw7);
        writer7.writeRecord("quoted");
        writer7.close();
        assertEquals("\"quoted\"\r\n", sw7.toString());

        /**
         * ************************************************************************************************************
         * Test: idm-w-08 Description: Change line delimiter to LF and test
         * output of a value with an embedded newline character.
         ************************************************************************************************************
         */
        StringWriter sw8 = new StringWriter();
        CsvWriter writer8 = CsvWriter.builder()
                .lineDelimiter(LineDelimiter.LF)
                .build(sw8);
        writer8.writeRecord("newline\ntext");
        writer8.close();
        assertEquals("\"newline\ntext\"\n", sw8.toString());
    }
}

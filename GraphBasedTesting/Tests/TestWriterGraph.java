package org.fastcsv;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import de.siegmar.fastcsv.writer.CsvWriter;
import de.siegmar.fastcsv.writer.QuoteStrategies;

public class TestWriterGraph {

    @Test
    public void test() throws IOException {

        /**************************************************************************************************************
         * Test: g-w-01 Description: Path 1→2→3→4→5→6→7→14→15→16 — null value, quoteNull
         * = true
         *************************************************************************************************************/
        StringWriter sw1 = new StringWriter();
        CsvWriter writer1 = CsvWriter.builder().quoteStrategy(QuoteStrategies.ALWAYS).build(sw1);
        writer1.writeRecord((String) null);
        writer1.close();
        assertEquals("\"\"\r\n", sw1.toString());
        /**************************************************************************************************************
         * Test: g-w-02 Description: Path 1→2→3→4→6→8→9→14→15→16 — empty string,
         * quoteEmpty = true
         *************************************************************************************************************/
        StringWriter sw2 = new StringWriter();
        CsvWriter writer2 = CsvWriter.builder().quoteStrategy(QuoteStrategies.ALWAYS).build(sw2);
        writer2.writeRecord("");
        writer2.close();
        assertEquals("\"\"\r\n", sw2.toString());

        /**************************************************************************************************************
         * Test: g-w-03 Description: Path 1→2→3→4→6→7→14→3→4→6→7→14→15→16 — two null
         * values
         *************************************************************************************************************/
        StringWriter sw3 = new StringWriter();
        CsvWriter writer3 = CsvWriter.builder().quoteStrategy(QuoteStrategies.ALWAYS).build(sw3);
        writer3.writeRecord(null, null);
        writer3.close();
        assertEquals("\"\",\"\"\r\n", sw3.toString());

        /**************************************************************************************************************
         * Test: g-w-04 Description: Path 1→2→3→4→6→8→10→12→13→14→15→16 — quoted
         * unescaped value
         *************************************************************************************************************/
        StringWriter sw4 = new StringWriter();
        CsvWriter writer4 = CsvWriter.builder().quoteStrategy(QuoteStrategies.ALWAYS).build(sw4);
        writer4.writeRecord("value");
        writer4.close();
        assertEquals("\"value\"\r\n", sw4.toString());

        /**************************************************************************************************************
         * Test: g-w-05 Description: Path 1→2→3→4→6→8→10→11→13→14→15→16 — quoted and
         * escaped value
         ************************************************************************************************************
         */
        StringWriter sw5 = new StringWriter();
        CsvWriter writer5 = CsvWriter.builder().quoteStrategy(QuoteStrategies.ALWAYS).build(sw5);
        writer5.writeRecord("esca\"ped");
        writer5.close();
        assertEquals("\"esca\"\"ped\"\r\n", sw5.toString());
    }
}

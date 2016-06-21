package com.galvanize.csvToMarkdown;

import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Created by gschool on 6/12/16.
 */
public class CSVToMarkdown {
    private String[][] rows;
    final static Logger logger = Logger.getLogger(CSVToMarkdown.class);

    public CSVToMarkdown(String csv){
        this.rows = getRows(csv.split("\n"));
        for (String[] row : rows) {
        }
    }

    public String toMarkdownTable(){
        int[] longests = longestElements();
        return formatTableWithLengths(longests);
    }


    private String joinWithString(String[] toJoin, String delim){
        String joined = "";
        for(int i = 0; i < toJoin.length; i++){
            if(i != 0) joined += delim;
            joined += toJoin[i];
        }
        return joined;
    }

    private int[] longestElements(){
        String[][] rows = this.rows;
        int[] largests = new int[this.rows[0].length];
        String element = "";
        String[] row = null;

        for(int i = 0; i < rows.length; i++){
            row = rows[i];
            for(int j = 0; j < row.length; j++){
                element = row[j];
                if (largests[j] < element.length()) largests[j] = element.length();
            }
        }

        return largests;
    }

    private String[][] getRows(String[] lines){
        String[][] rows = new String[lines.length][];
        for(int i = 0; i < lines.length; i++){
            rows[i] = lines[i].split(",");
        }
        return rows;
    }

    private String formatTableWithLengths(int[] longests) {
        String[] formattedRows = new String[this.rows.length + 1];

        int rowIndex = 0;
        for (int formattedIndex = 0; formattedIndex < formattedRows.length; formattedIndex++) {
            String[] row = this.rows[rowIndex];
            if(formattedIndex == 1){
                formattedRows[1] = "| " + padRowWithChar(longests, new String[rows[0].length], '-') + " |";
            }else{
                formattedRows[formattedIndex] = "| " + padRowWithChar(longests, row, ' ') + " |";
                rowIndex++;
            }
        }
        return joinWithString(formattedRows, "\n");
    }

    private String padRowWithChar(int[] longests, String[] row, char character) {
        int paddingLength = 0;
        for (int i = 0; i < row.length; i++) {
            String element = row[i];
            if (element == null) {
                paddingLength = longests[i];
                row[i] = "";
            }
            else paddingLength = longests[i] - element.length();
            row[i] += padWithChar(paddingLength, character);
        }
        return joinWithString(row, " | ");
    }

    private String padWithChar(int length, char character){
        char[] padding = new char[length];
        Arrays.fill(padding, character);
        return new String(padding);
    }
}

package com.galvanize.csvToMarkdown

import spock.lang.Specification

/**
 * Created by gschool on 6/12/16.
 */
class CSVToMarkdownTest extends Specification {
    def "#generateMarkdown successfully transforms csv input string to markdown table."(String csv, String markdownTable) {
        expect:
        new CSVToMarkdown(csv).toMarkdownTable() == markdownTable

        where:
        csv << [['First,Last,Address',
                 'Annamarie,Romaguera,9446 Schroeder Squares',
                 'Sebastian,Hessel,114 Frederic Centers',
                 'Marlon,Hahn,307 Bradtke Grove',
                 'Duane,Schowalter,10771 Keaton Knoll',
                 'Laurence,Schuster,610 Odell Point'
                ].join("\n") as String,
                ['A,C',
                 'this,and',
                 'is,lots',
                 'a,of',
                 'string,characters'
                ].join("\n") as String]

        markdownTable << [['| First     | Last       | Address                |',
                           '| --------- | ---------- | ---------------------- |',
                           '| Annamarie | Romaguera  | 9446 Schroeder Squares |',
                           '| Sebastian | Hessel     | 114 Frederic Centers   |',
                           '| Marlon    | Hahn       | 307 Bradtke Grove      |',
                           '| Duane     | Schowalter | 10771 Keaton Knoll     |',
                           '| Laurence  | Schuster   | 610 Odell Point        |'
                          ].join("\n") as String,
                          ['| A      | C          |',
                           '| ------ | ---------- |',
                           '| this   | and        |',
                           '| is     | lots       |',
                           '| a      | of         |',
                           '| string | characters |'
                          ].join("\n") as String]
    }
}

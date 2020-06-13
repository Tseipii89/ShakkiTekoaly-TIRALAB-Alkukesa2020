#  reporting

## Week 1

Date       | time | target |
-----------|------|--------|
2020-05-10 | 1.5h | Project creation on git and writing of Design documentation |
2020-05-11 | 1.5h | Getting familiar with the chess library and making the first package diagram version |
2020-05-13 | 1.5h | Started TDD coding for TiraBot and some neede chess elements |
2020-05-13 | 1h | Started Rank, File and Tile class |
2020-05-14 | 3h | Continued to code different classes and unit -tests for those |
2020-05-15 | 1h | Start of Pawn movement generator |

## Week 2

Date       | time | target |
-----------|------|--------|
2020-05-17 | 2.5h | Pawn movement rules start and continuous integration |
2020-05-18 | 2h | Finish Pawn movement rules and correcting checkstyle |
2020-05-19 | 2.5h | Jacoco exclude extra classes and refactor movement and knight movement rules. Checkstyle to 107 errors |
2020-05-21 | 2h | Rook and Bishop movement rules. Checkstyle down to 94 errors |
2020-05-23 | 1.5h | Queen and King movement rules. Checkstyle down to 93 errors |

## Week 3

Date       | time | target |
-----------|------|--------|
2020-05-24 | 3h | TiraBot takes the movement String and updates the gameBoard |
2020-05-25 | 3h | TiraBot returns a random move. Lichess works with the bot (although bot doesn't yet know that you shouldn't sacrifice your king) |
2020-05-26 | 2h | Added different values to the pieces. The bot prefers eating opponent pieces now. Still might accidentally sacrifice king. Checkstyle down to 80 errors. |
2020-05-27 | 4h | King will not go in to checked position. Other pieces will not move to a position where king is checked |
2020-05-28 | 1h | Refactoring |
2020-05-29 | 3h | Fixing the King not in check method. There was a fool bug that was hard to recognize since my debugging doesn't work. Refactoring the code. |

## Week 4

Date       | time | target |
-----------|------|--------|
2020-05-30 | 4.5h | Making of minimax algo, it doesn't work at the moment. Have to check it later |
2020-05-31 | 5h | Making of minimax algo ready. The bot stills makes stupid moves though.|
2020-06-01 | 2.5h | There was a bug in Min-Max. Found it and fixed it.|
2020-06-02 | 1h | Making code to have less checkstyle errors. |
2020-06-03 | 1h | Start to write test document and code the performance test |
2020-06-04 | 1.5h | Performance test done, there is an issue with TiraBot's usage of Min-Max algo |
2020-06-05 | 1.5h | Writing of implementation document, and update class diagram |

## Week 5

Date       | time | target |
-----------|------|--------|
2020-06-06 | 4h | Found the problem with MIN-MAX. It wasn't in the bot. It was the performance test. Made corrections. |
2020-06-07 | 1h | Refactoring and checkstyle down to 0 errors. |
2020-06-08 | 4h | Alpha-beta pruning implemented. Tests written and again found that performancetest class was the problem. The alpha-beta works again. Writing of testing document. |
2020-06-09 | 1h | Writing of implementation document |
2020-06-10 | 2h | Writing of issue for my fellow student. Removed unnecessary code in kingcheckclass. Still the test fails. |
2020-06-10 | 1.5h | Corrected the test failures. Tested the bot again. Now the moves are correct, but still bot makes stupid moves. It has to be something to do with the board evaluation. Also the checkstyle had ignored the datastructure -package. I added it into checkstyle. Checkstyle errors are again in 108, yay. |

## Week 6

Date       | time | target |
-----------|------|--------|
2020-06-13 | 1.5h | Created min, max and abs methods. Simplified code. Corrected Checkstyle down to 56 errors. Corrected the bug that errored Travis build (at least I think so) |

## Totals

 Week   | Time     |
--------|----------|
 1      | 9.5h    |
 2      | 10.5h    |
 3      | 16h    |
 4      | 17h    |
 5      | 13.5h    |
 6      | 1h    |
**Tot** | **67.5h** |

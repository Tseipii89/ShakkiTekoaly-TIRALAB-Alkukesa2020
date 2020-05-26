# Week 3

[Hour reporting](/documentation/Hour_reporting.md)

## What has happened during week 3
* Bot takes and returns a random move, that lichess knows how to read
* Bot prefers eating opponents now


## Problems
* I had made the bot so that Files are with capital letters. Unfortunately Lichess only takes lowercase letters :(
* If you play on black on Lichess, the board is upside down (your black pawns are on the rank 2, not on rank 7). This madness messes my bot if you play on black. The bot only works when you play on white (because I thought that black pawns should be on the ranks 7 and 8 in the beginning, like in any normal chess they would)


## Questions
* What would be best way to handle situation where my move exposes the king? Should I just make test and find best move for my opponent after my move to see if my king is in danger or some other method?

## Next week



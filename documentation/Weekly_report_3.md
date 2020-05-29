# Week 3

[Hour reporting](/documentation/Hour_reporting.md)

## What has happened during week 3
* Bot takes and returns a random move, that lichess knows how to read
* Bot prefers eating opponents now
* Bot doesn't go into checked state


## Problems
* I had made the bot so that Files are with capital letters. Unfortunately Lichess only takes lowercase letters :(
* If you play on black on Lichess, the board is upside down (your black pawns are on the rank 2, not on rank 7). This madness messes my bot if you play on black. The bot only works when you play on white (because I thought that black pawns should be on the ranks 7 and 8 in the beginning, like in any normal chess they would)
* The objects I used for Board seems not be optimal forthe MIN-MAX. I have to see what performance tests say.
* I had to think a lot about how to implement the functionality that the pieces don't leave King in checked position. For some reason I first tried to make a deep copy of the Board on every time, and use that Board as a place for checking the King's vulnerability. Luckily I then thought that I can just use shallow copies and return the situation to normal without filling the stack. The same proble will arise with MIN-MAX, but now I know how to solve it.
    * There was a silly mistake where I first took a random move and later checked that if the next move will be more valuable. The problem was that the next move might not be more valuable, but it might leave the king unchecked, and so give trendemous value to the opponent. Fix was to filter all moves from possible moves before taking a random move from the move list.


## Questions



## Next week
* MIN-MAX algorithm implementation
* Refactoring and optimizing (this will take a lot of time)
* Performance testing after MIN-MAX has been applied


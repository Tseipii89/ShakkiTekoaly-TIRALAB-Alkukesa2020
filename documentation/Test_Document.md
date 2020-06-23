# Test Document

Testing document presents the results of the time the algorithm takes to count the best move.

As the code improvements are done, so will the test document also be updated.

**NB** Also one should note that there is one checkstyle error caused by PerformanceTest -class. This is regarded to the AbstractionCoupling that shouldn't higher than 7. However because the PerformanceTest is unique in it's own style, I haven't fixed this problem. It doesn't affect the performance of the program or any other class.

# Testing
Test have been run 20 times. There is an initial run that is not counted to the , to "wake up" the Java compiler.

To measure the algorithm runtime, the average and standard deviation is calculated. 

## Results with Min-Max algorithm (the version 1 of chess AI)
In the code the Min-Max algorithm is set to check only two steps down the min-max tree. The issue was that third level made the algorithm very very slow. Below are the results of the performance of the Min-Max algorithm with 0, 1, 2 and 3 steps down the min-max tree. The TiraBot does an initial move suggestion and after that the Min-Max values are calculated. The 0 step basically counts the board value after the bot move has been done.

**NB! FOR SOME REASON THE TIRABOT DOESN'T WORK WITH MIN-MAX ALGO. I FOUND THIS DURING PERFORMANCE TESTING. THE RESULTS BELOW ARE FOR THE MIN-MAX ALGO, NOT FOR THE BOT**

Results for MIN-Max performance test. As can be suspected, the run time grows exponentially (Min-Max complexity is O (n ^ m)). The standard deviation also grows, but the percent value of std from average actually decreases.

| Steps down the min-max tree | Average run time (ns) | Standard deviation (ns) |
|-----------------------------|-----------------------|-------------------------|
| 0                           | 1 327 080             | 489 168                 |
| 1                           | 20 670 095            | 467 657                 |
| 2                           | 451 430 220           | 2 580 760               |
| 3                           | 10 772 454 800        | 179 152 089             |

### Update 6.6.2020

I got the TiraBot to work, but for some reason it takes a much more time to do the calculations, and it is only possible to calculate the moves down two steps (even that takes very long time).

The step starts at 1, because the tirabot always tries a move and then checks if it was good. This results in a situations where the min-max tree will always be at least 1 step deep. The results are pretty much the same as in MIN-MAX algo.

| Steps down the TiraBot | Average run time (ns) | Standard deviation (ns) |
|-----------------------------|-----------------------|-------------------------|
| 1                           | 18 991 050             | 189 664                |
| 2                           | 426 067 820            | 4 517 328                 |
| 3                           | 11 001 773 225          | 394 481 457               |

## Results with Alpha-Beta algorithm (the version 2 of chess AI)

I tried first to see the difference in results when the chess-board was in intial situation. The alpha-beta pruning gave the results below.

| Steps down the TiraBot | Average run time (ns) | Standard deviation (ns) |
|-----------------------------|-----------------------|-------------------------|
| 1                           | 23 961 665             | 6 009 945               |
| 2                           | 462 129 370           | 6 206 256                 |
| 3                           | 1 406 786 380         | 11 142 290              |
| 4                           | 14 783 292 950        | 203 886 874              |

There is noticable change in run time, when there are more depth to be checked. Basically alpha-beta does depth 4 check with same cost as min-max does 3.

Also the standard deviation changes a lot. I don't know what causes this. It might be that the randomly choosen first move of bot is to be blamed, but I really don't know.

### Second test

I tried to make the difference between min-max and alpha-beta even more clear. I choose the board situation given in alpha-beta section of page <https://www.freecodecamp.org/news/simple-chess-ai-step-by-step-1d55a9266977/> and ran the min-max and alpha-beta to see the differences. According to the web site, alpha-beta checks "only" 61 721 postions where min-max checks 879 750 positions.

The difference was clear.

| Steps down the TiraBot | Average run time min-max (ns) | Average run time alpha-beta (ns) | Standard deviation min-max (ns) | Standard deviation alpha-beta (ns) |
|-----------------------------|-----------------------|-------------------------|-------------------------|-------------------------|
| 1                           | 25 488 350           | 20 921 330              | 14 526 599              | 747 431            |
| 2                           | 449 757 665           | 466 062 805                 | 3 408 302             | 17 609 794             |
| 3                           | 10 266 832 135         | 1 380 264 290             | 36 659 406              | 26 217 170              |
| 4                           | 257 812 308 655       | 14 157 014 330              | 8 004 264 980              | 125 214 300             |

The run time is clearly better with alpha-beta pruning.

Again I have no idea what is happening with the standard deviation.

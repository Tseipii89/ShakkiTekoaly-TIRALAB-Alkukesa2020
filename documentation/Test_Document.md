# Test Document

Testing document presents the results of the time the algorithm takes to count the best move.

As the code improvements are done, so will the test document also be updated.

# Testing
As explained in <https://github.com/TiraLabra/Testing-and-rmq/tree/master/src/main/java/rmq/util> test have been run 20 times, and first run is ignored.

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


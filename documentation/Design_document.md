# Design document

In this project, the chess AI is built on top of the finished chess [template](https://github.com/TiraLabra/chess).

The work is part of Data Structures and Algorithms for Intermediate Studies in the University of Helsinki computer science Bachelor degree.

## What algorithms and data structures are implemented in this project

Lauri Hartikainen has in [FreeCodeCamp](https://www.freecodecamp.org/news/simple-chess-ai-step-by-step-1d55a9266977/) an interesting solution for building simple chess artificial intelligence.

This solution utilizes a search tree data structure, as well as an alpha-beta pruning algorithm. The alpha-beta pruning compares possible moves x moves down the search tree, and selects the best move from these.

## What problem is the project  solving and why did you choose these algorithms / data structures

I use the selected solution because chess is a zero-sum game in which the search tree and minimax algorithm are really effective. The problem with the MiniMax algorithm, however, is that the number of its inputs and comparable situations can quickly grow large.
This problem is addressed by an algorithm called alpha-beta pruning.

## What inputs the program receives and how it uses them

The program receives the last move played by the opponent and counts certain number of moves from the search tree. From these results, the algorithm selects the best according to predetermined rules that are also provided in the program.

## Target time requirement

The time requirement of the algorithm is O (n ^ m), where n indicates the number of possible moves in typical situation, and m indicates the number of nodes that are searched .

In the best case, alpha-beta pruning can be used to drop half of the possible moves in which case the time requirement of the algorithm drops to O (n ^ 0.5m)[More can be found in Alpha-beta pruning wikipedia page](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning).

Below is an example of an example provided by the  [Chessprogramming wiki](https://www.chessprogramming.org/Alpha-Beta) of how alpha-beta pruning reduces the number of nodes checked in the optimal situation compared to minimax (meaning all nodes are checked).

The depth is denoted by the letter n and assumed possible white movements are b = 40. As can be seen from the table, at best, alpha-beta pruning saves considerable resources (in this case, time).

depth n | b^n | b^⌈n/2⌉ + b^⌈n/2⌋ - 1
------------ | ------------- | -------------
0 | 1 | 1
1 | 40 | 40
2 | 1,600 | 79
3 | 64,000 | 1,639
4 | 2,560,000 | 3,199
5 | 102,400,000 | 65,569
6 | 4,096,000,000 | 127,999
7 | 163,840,000,000 | 2,623,999
8 | 6,553,600,000,000 | 5,119,999

## Sources

[Lauri Hartikainen FreeCodeCamp](https://www.freecodecamp.org/news/simple-chess-ai-step-by-step-1d55a9266977/)

[Chessprogramming wiki](https://www.chessprogramming.org/Alpha-Beta)

[Alphja-beta pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning)


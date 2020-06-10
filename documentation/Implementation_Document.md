# Implementation Document

The realised architecture of the program is shown below (I didn't realise the classes create such a mess). The ready given classes from Tira chess -library are not included in the architecture picture.

![Chess AI realised architecture diagram](/documentation/images/chess-ai-realised-structure.png)

## The O -analyses

The program was tested with two different algorithms. The first one was basic [Minimax](https://en.wikipedia.org/wiki/Minimax) algorithm and the second was the [Alpha-beta pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning) improvement done on the Minimax.

### Minimax

The pseudocode is taken from the [Minimax](https://en.wikipedia.org/wiki/Minimax) wikipedia page. 

```
function minimax(node, depth, maximizingPlayer) is
    if depth = 0 or node is a terminal node then
        return the heuristic value of node
    if maximizingPlayer then
        value := −∞
        for each child of node do
            value := max(value, minimax(child, depth − 1, FALSE))
        return value
    else (* minimizing player *)
        value := +∞
        for each child of node do
            value := min(value, minimax(child, depth − 1, TRUE))
        return value
```
```
(* Initial call *)
minimax(origin, depth, TRUE)
```

The theoretical O -time complexity of this algorithm is O(n^m) where n indicates possible moves in given situation and m indicates the nodes searched. 

### Alpha-beta pruning

The pseudocode is taken from the [Alpha-beta pruning](https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning) wikipedia page. 

```
function alphabeta(node, depth, α, β, maximizingPlayer) is
    if depth = 0 or node is a terminal node then
        return the heuristic value of node
    if maximizingPlayer then
        value := −∞
        for each child of node do
            value := max(value, alphabeta(child, depth − 1, α, β, FALSE))
            α := max(α, value)
            if α ≥ β then
                break (* β cut-off *)
        return value
    else
        value := +∞
        for each child of node do
            value := min(value, alphabeta(child, depth − 1, α, β, TRUE))
            β := min(β, value)
            if β ≤ α then
                break (* α cut-off *)
        return value
```
```
(* Initial call *)
alphabeta(origin, depth, −∞, +∞, TRUE)
```

In the best situation the theoretical O -time complexity of this algorithm is O(n^0.5 * m) where n indicates possible moves in given situation and m indicates the nodes searched. 

Alpha-beta pruning can in best situations drop half of the nodes needed to be investigated. This has a huge impact on the code performance as we will see next.

## The tested performance

The test are run in a very spesific board situation to bring focus on the difference of the run times of the different algorithms. The board layout is told on [Alpha-beta pruning section](https://www.freecodecamp.org/news/simple-chess-ai-step-by-step-1d55a9266977).

As one can see there is significant improvement with alpha-beta pruning. The O(n^m) means that dropping half of the exponent "m" causes significant reduce in the nodes that are checked.

| Steps down the TiraBot | Average run time min-max (ns) | Average run time alpha-beta (ns) | Standard deviation min-max (ns) | Standard deviation alpha-beta (ns) |
|-----------------------------|-----------------------|-------------------------|-------------------------|-------------------------|
| 1                           | 25 488 350           | 20 921 330              | 14 526 599              | 747 431            |
| 2                           | 449 757 665           | 466 062 805                 | 3 408 302             | 17 609 794             |
| 3                           | 10 266 832 135         | 1 380 264 290             | 36 659 406              | 26 217 170              |
| 4                           | 257 812 308 655       | 14 157 014 330              | 8 004 264 980              | 125 214 300             |

## Future improvements and shortages

The code was written fully with objects. Now after the implementation of the code, I think it might have been easier to do for example the game board with just matrix table, and not board -object.

### Known problems/updates

* Bishop went in the same tile with Pawn. So again there is something wrong with the movement rules. It has something to do with KingInCheck -class, but the KingInCheck -class works on some instances. These are just super annoying. When I add something, something breaks somewhere else.
    * Probably because some issue with movement rules, some tests aren't passed.
    * I just have to make class KingCheckedCounter simpler
* I haven't implemented the castling for the bot.
* I haven't added enpassant for my bot.
* I have to create my own random class.
* I have to create my own Math.min and Math.max methods. These are super easy to do, though.
* I have to move the moverules -class's method createMovementString into a different class. Now moverules does two things.
* In Min-Max I don't have to keep the boolean maximizing player info at parameters. Side info is enough.
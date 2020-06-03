# Test Document

Testing document presents the results of the time test the algorithm takes to count the best move.

As the code improvements are done, so will the test document also be updated.

# Testing
As explained in <https://github.com/TiraLabra/Testing-and-rmq/tree/master/src/main/java/rmq/util> test have been run 20 times, and first run is ignored.

To measure the algorithm runtime, the average and standard deviation is calculated. 

## Results with Min-Max algorithm (the version 1 of chess AI)
In the code the Min-Max algorithm is set to check only two steps down the min-max tree. The issue was that third level made the algorithm very very slow. Below are the results of initialization and performance for the Min-Max algorithm with 0, 1, 2 and 3 steps down the min-max tree.


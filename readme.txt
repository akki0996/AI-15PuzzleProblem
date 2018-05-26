I had difficulity generating test cases for DLS and DFS as system weren't able to calculate. I waited for more than 10 minutes, but no result. 
For example, one of the test cases for AStar ended up creating 46 million nodes. 
My computer was able to handle it, but when I tested it on my friends computer, their system crashed.

java FifteenProblem "123456789AB DEFC" BFS
Depth:1  num_created: 4  num_expanded: 1         max_fringe: 3

java FifteenProblem "123456789A BDEFC" BFS
Depth:2  num_created: 19         num_expanded: 5         max_fringe: 14

java FifteenProblem "123456789A BDEFC" DFS
Depth:2  num_created: 8  num_expanded: 2         max_fringe: 6

java FifteenProblem "123456789AB DEFC" DFS
Depth:1  num_created: 4  num_expanded: 1         max_fringe: 3

java FifteenProblem "1E3456789ABD2FC " GBFS h1
Depth:286        num_created: 27777      num_expanded: 8811      max_fringe: 9740

java FifteenProblem "123456789A BDEFC" GBFS h1
Depth:2  num_created: 8  num_expanded: 2         max_fringe: 6

java FifteenProblem "1E3456789ABD2FC " GBFS h2
Depth:148        num_created: 3909       num_expanded: 1261      max_fringe: 1392

java FifteenProblem "B6 8F4C759321EAD" GBFS h2
Depth:210        num_created: 4576       num_expanded: 1497      max_fringe: 1656

java FifteenProblem "234C1587 BEA69DF" GBFS h2
Depth:36         num_created: 203        num_expanded: 65        max_fringe: 104

java FifteenProblem "1E3456789ABD2FC " AStar h1
Depth:34         num_created: 46309866   num_expanded: 14922713  max_fringe: 20039778

java FifteenProblem "1E3456789ABD2FC " AStar h2
Depth:34         num_created: 324739     num_expanded: 106790    max_fringe: 134030

java FifteenProblem "51246A379 C8DEBF" DLS 13
Depth:11         num_created: 30422      num_expanded: 9716      max_fringe: 25


Currently, BFS, DFS and DLS works with both goal states, however, AStar and GBFS only works with the primary goal state. I was going to implement the second goal state for AStar and GBFS also, but I realized then I will be calculating heuristics of each successor nodes for both goal nodes, then the time complexity will rise, therefore, I decided not to implement the goal states for AStar and GBFS
The average branching factor for 8 puzzle problem was 2.67. More specifically, we have 4 moves if the blank was in the center. 2 movies if the blank was in the corner, total of 8 movies for four corners. 3 movies from center of each side, total of 12 movies
In the case of 15 puzzle, we have 4 movies 4 times, 3 moves 8 times, 2 movies 4 times, which resulted in to average branching factor of 3 (48/16).

BFS
The overall time complexity of BFS is O(b ^ d), where b is the branching factor and d is the depth of the tree. However, for every state, we are performing number of operations, for example. 
2d array to string – n^2 – for comparison purposes – explored states and goal states
generating Successors nodes – O( <= 4 )
check if node exists – O(1)
check if matches goal node – O(<=2)
I am performing around 40 operations for each vertex, but overall the time complexity of these operations stays constant. There, time complexity of BFS O(b ^ d)

DFS
The overall time complexity of BFS is O(b ^ d), where b is the branching factor and d is the depth of the tree. However, for every state, we are performing number of operations, for example. 
2d array to string – n^2 – for comparison purposes – explored states and goal states
generating Successors nodes – O( <= 4 )
check if node exists – O(1)
check if matches goal node – O(<=2)
I am performing around 40 operations for each vertex, but overall the time complexity of these operations stays constant. There, time complexity of BFS O(b ^ d)

DLS
The overall time complexity of BFS is O(b ^ L), where b is the branching factor and L is the depth limit of the tree. However, for every state, we are performing number of operations, for example. 
2d array to string – n^2 – for comparison purposes – explored states and goal states
generating Successors nodes – O( <= 4 )
check if node exists – O(1)
check if matches goal node – O(<=2)
I am performing around 40 operations for each vertex, but overall the time complexity of these operations stays constant. There, time complexity of BFS O(b ^ L). DLS works similar to DFS, but we have added a constraint of depth. In other words, how deep each branch can go for.

Greedy Best-First Search
The overall time complexity of GBFS is O(b ^ m), where m is the depth of the search space. There are also number of operations that are getting performed for each state. I am calculating heuristics for each state to decide the priority inside the priority queue. Furthermore, furthermore, priority queue is also performing operations in sorting the spaces as per the heurisitcs.
2d array to string – n^2 – for comparison purposes – explored states and goal states
generating Successors nodes – O(4 * n^2) – cost of calculating heuristics for each child.
check if node exists – O(1)
check if matches goal node – O(<=2)
I am performing around 100 operations for each search space which does add a up a lot from the perspective of time complexity, however, as for Big-0h, it is a constant number. 

AStar
The time complexity of AStar highly depends on the type of heuristics, but in the worst case, it can O(b ^ d) – d is the depth.
2d array to string – n^2 – for comparison purposes – explored states and goal states
generating Successors nodes – O(4 * n^2) – cost of calculating heuristics for each child.
check if node exists – O(1)
check if matches goal node – O(<=2)
I am performing around 100 operations for each search space which does add a up a lot from the perspective of time complexity, however, as for Big-0h, it is a constant number. The main difference between GBFS and A Start is the calculation of the total path. In other words, one of them doesn’t consider the total cost from getting the source to goal.
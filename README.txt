Class Test runs the main method


Class Map
Represents Canvas that draws out the map and shortest path and etc.
Methods: 
painComponent() outputs lines representing dijkstra’s, prim’s algorithm and the initial map, if the line is too small - doesn’t draw it to cut down on time, however if the line’s length is less than 1px - does not represent it to cut down on time expenses
setCoord() scales coordinates to the size of window
componentResized() updates the size 




Class SetUp
Stands behind scanning file,adding elements to map and behind algorithms
Methods:
scan() adds input roads to arrayList of edges inside class point, adds intersections to map of points
printMap() prints out the map:points and edges connecting them
fillqueue () - fills priority queue based on myComparator(): points with smallest distances are added to beginning of queue, with biggest distances - at the end of the queue
dijkstra() - calculates shortest path between two points, by updating distance for adjacent edge and picking the closest neighbor point. Method stops running when reached destination point. It prints out total distance and path between points by following the parents of points.
prims() - calculates MSWT and prints out the roads. Works similarly to dijkstra() - updates distances at all neighboring points (but doesn’t sum the previous distance and the new distance) and picks the closest one
Note: prims() doesn’t work properly - it doesn’t output the tree that includes all the points

MyComparator - compares distance between two points. If first point’s current distance < second, returns -1. If first point’s current distance > second, returns 1


Point - represents intersection. Contains coordinates, distance that is updated during prim’s and dijkstra’s, parent point, list of beginEdges and allEdges. Reason why there are two lists of edges - so that when you call paintComponent, you didn’t draw the lines twice (from both beginning and end points of the edge).


Edge - represents roads, contains two points (beginning and end), road’s name and its length. Calculates distance between two points, by solving it based on latitude and longitude.

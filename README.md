# AvlTree
Implementation of AVL tree in java with ut in jUnit4. Made for training purposes.

According to wiki: In computer science, an AVL tree (named after inventors Adelson-Velsky and Landis) is a self-balancing binary search tree. In an AVL tree, the heights of the two child subtrees of any node differ by at most one; if at any time they differ by more than one, rebalancing is done to restore this property.
https://en.wikipedia.org/wiki/AVL_tree

AvlTree interface is operating on IPayload objects. User need to provide own class extending IPayload, which means that it should implement comparable interface. 

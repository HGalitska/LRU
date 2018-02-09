# Queue-based LRU page replacement algorithm 

### LRU concept:
In case of page fault, page that hasn't been referenced for longest time is replaced by requested one.

### My queue based implementation:
  - mapped pages are added to the queue
  - queue is represented by doubly-linked list
  - whenever any page is referenced:
     - if it is in memory (in queue) -> bring it to front
     - if a page fault is occured -> last page is removed from the queue and requested one is added to front

>So that way, the most recently referenced pages are in the front of the queue and the least
recently referenced ones are in the rear end of it.

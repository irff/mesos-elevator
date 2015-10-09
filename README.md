# Elevator Control System

By Tri Ahmad Irfan (triahmadirfan@gmail.com)

In this elevator control system I implemented a two classes:

### class Elevator with attributes:

* id: Int // the elevator id
* position: Int // the floor where the elevator is currently on
* dest: Set[Int] // set of floor destinations of the elevator
* direction: Int // the direction of elevator (positive for up, negative for down). the default is zero, and is only computed when picking up people & stepping forward in simulation

All attributes are immutable.

The class Elevator also has method named **status** that returns the tuple (id, position, dest).

## class ElevatorSystem with attributes:

* elevators: Seq[Elevator] = // list of elevators the system handles
* currentElevatorId: Int // the current elevator that the system currently handles (only used when stepping forward in simulation)
* minFloor: Int // The lowest possible floor level
* maxFloor: Int // The highest possible floor level

The class Elevator system also has the following methods:

`def status(): Seq[(Int, Int, Set[Int])]`
This method returns the current state of all elevators.

`def update(id: Int, position: Int, dest: Set[Int]): Unit`
This method updates the elevator with that id into a new position & destinations.

`def pickup(floor: Int, direction: Int): Unit`
This method picks a specific person on `floor` with `direction`. Because FCFS (first-come, first-served) is not good enough, I decided to implement an algorithm to better handle incoming pickups. My algorithm is:

1. select nearest empty elevator
2. select nearest elevator in the same direction
3. select any nearest elevator with the least people in it

`def step(): Unit`
This method moves forward one elevator at a time periodically, each step will move the elevator up or down, remove the current floor from the destination set, or change direction. It can be improved so that each move will check if the elevator reached the lowest or highest floor and make it smarter, but I didn't have the chance to implement it.
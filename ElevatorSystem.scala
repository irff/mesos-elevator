package mesoselevator

class ElevatorSystem {
    var elevators: Seq[Elevator] = Seq()
    var currentElevatorId: Int = 0
    var minFloor: Int = -1;
    var maxFloor: Int = 100;

    def status(): Seq[(Int, Int, Set[Int])] = {
        elevators map { elevator =>
            elevator.status
        }
    }
    
    def update(id: Int, position: Int, dest: Set[Int]): Unit = {
        elevators = elevators map { elevator =>
            if(elevator.id == id) {
                new Elevator(id, position, dest, 0)
            } else {
                elevator
            }
        }
    }

    def pickup(floor: Int, direction: Int): Unit = {
        // scheduling algorithm
        // 1. select nearest empty elevator
        // 2. select nearest elevator in the same direction
        // 3. select any nearest elevator with the least people in it

        val nearestEmptyElevators = elevators filter { elevator =>
            elevator.dest.isEmpty
        } sortBy (elevator => Math.abs(elevator.position - floor))

        val nearestSameDirectionElevators =
            elevators filter { elevator =>
                elevator.dest.nonEmpty
            } filter { elevator =>
                // positive/zero = up, negative = down
                if(direction >= 0) {
                    // find nearest elevator (mostly) going up
                    elevator.dest.count(
                        destinationFloor => destinationFloor - elevator.position >= 0
                    ) > elevator.dest.count(
                        destinationFloor => destinationFloor - elevator.position <= 0
                    )
                } else {
                    // find nearest elevator (mostly) going down
                    elevator.dest.count(
                        destinationFloor => destinationFloor - elevator.position >= 0
                    ) < elevator.dest.count(
                        destinationFloor => destinationFloor - elevator.position <= 0
                    )
                }
            } filter { elevator =>
                // only select if they pass the desired floor
                if(direction >= 0) {
                    elevator.position <= floor
                } else {
                    elevator.position >= floor
                }
            } sortBy (elevator => Math.abs(elevator.position - floor))

        val anyNearestElevators =
            elevators.sortBy(
                elevator => {
                    val differenceFloor = Math.abs(elevator.position - floor)
                    if(differenceFloor == 0) {
                        -elevator.dest.size
                    } else {
                        differenceFloor
                    }
                }
            )

        val newElevator =
            if(nearestEmptyElevators.nonEmpty) nearestEmptyElevators.head
            else if(nearestSameDirectionElevators.nonEmpty) nearestSameDirectionElevators.head
            else anyNearestElevators.head

        elevators = elevators map { elevator =>
            if(elevator.id == newElevator.id) new Elevator(elevator.id, floor, elevator.dest, elevator.direction)
            else elevator
        }
    }

    def step(): Unit = {
        var currentElevator = elevators(currentElevatorId)

        // compute the elevator's direction
        val newDirection =
            if(currentElevator.direction == 0) {
                if(currentElevator.dest.count(
                    destinationFloor => destinationFloor - currentElevator.position >= 0
                ) >= (currentElevator.dest.count(
                    destinationFloor => destinationFloor - currentElevator.position <= 0))
                ) {
                    1
                } else {
                    -1
                }
            } else {
                currentElevator.direction
            }

        // move one floor up or down
        val newPosition =
            if(newDirection > 0) {
                currentElevator.position + 1
            } else {
                currentElevator.position - 1
            }

        // when reaching a specific floor, remove that from destination
        val newDest: Set[Int] = currentElevator.dest - currentElevator.position

        elevators = elevators map { elevator =>
            if(currentElevatorId == elevator.id) {
                new Elevator(currentElevatorId, newPosition, newDest, newDirection)
            } else {
                elevator
            }
        }

        currentElevatorId = (currentElevatorId + 1) % elevators.size
    }
}
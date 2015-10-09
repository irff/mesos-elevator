package mesoselevator

class ElevatorSystem {
    var elevators: Seq[Elevator] = Seq()

    def status(): Seq[(Int, Int, Set[Int])] = {
        elevators map { elevator =>
            elevator.status
        }
    }
    
    def update(id: Int, position: Int, dest: Set[Int]): Unit = {
        elevators = elevators map { elevator =>
            if(elevator.id == id) {
                new Elevator(id, position, dest)
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
                    ) >= (elevator.dest.size / 2)
                } else {
                    // find nearest elevator (mostly) going down
                    elevator.dest.count(
                        destinationFloor => destinationFloor - elevator.position <= 0
                    ) >= (elevator.dest.size / 2)
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
            if(elevator.id == newElevator.id) new Elevator(elevator.id, floor, elevator.dest)
            else elevator
        }
    }

    def step(): Unit = {
        
    }
}
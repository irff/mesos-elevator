package mesoselevator

class ElevatorSystem {
    var elevators: Seq[Elevator] = Seq(new Elevator(0, 0, Set(0)))

    def status(): Seq[(Int, Int, Set[Int])] = {
        elevators map { elevator =>
            (elevator.id, elevator.position, elevator.dest)
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

    def pickup(floorDest: Int, direction: Int): Unit = {
        // scheduling algorithm

    }

    def step(): Unit = {
        elevators = elevators map { elevator =>

        }
    }
}
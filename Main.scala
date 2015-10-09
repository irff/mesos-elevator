package mesoselevator

object Main {
    def main(args: Array[String]) = {
        val elevatorSystem = new ElevatorSystem()

        // Sample movement
        elevatorSystem.elevators = Seq(
            new Elevator(0, 0, Set(), 0),
            new Elevator(1, 0, Set(), 0),
            new Elevator(2, 0, Set(), 0),
            new Elevator(3, 0, Set(), 0)
        )

        println(elevatorSystem.status)

        elevatorSystem.update(0, 1, Set(10, 9))
        elevatorSystem.update(1, 4, Set(9, 8, 5, 0))
        elevatorSystem.update(2, 0, Set(5))

        println(elevatorSystem.status)

        elevatorSystem.pickup(3, -1)
        elevatorSystem.update(3, 3, Set(1))

        println(elevatorSystem.status)
        
        elevatorSystem.pickup(5, 1)
        
        println(elevatorSystem.status)

        elevatorSystem.step()
        println(elevatorSystem.status)
        elevatorSystem.step()
        println(elevatorSystem.status)
        elevatorSystem.step()
        println(elevatorSystem.status)
        elevatorSystem.step()
        println(elevatorSystem.status)

        elevatorSystem.pickup(10, -1)
    }
}
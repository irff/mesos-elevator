package mesoselevator

object Main {
    def main(args: Array[String]) = {
        val elevatorSystem = new ElevatorSystem()

        elevatorSystem.elevators = Seq(
            new Elevator(0, 0, Set()),
            new Elevator(1, 0, Set()),
            new Elevator(2, 0, Set()),
            new Elevator(3, 5, Set())
        )

        println(elevatorSystem.status)

        elevatorSystem.update(1, 4, Set(9, 8, 5, 0))
        elevatorSystem.update(0, 1, Set(10, 9))

        println(elevatorSystem.status)

        elevatorSystem.pickup(10, -1)

        println(elevatorSystem.status)
    }
}
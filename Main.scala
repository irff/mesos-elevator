package mesoselevator

object Main {
    def main(args: Array[String]) = {
        val elevatorSystem = new ElevatorSystem()
        elevatorSystem.elevators = Seq(new Elevator(0, 0, Set()), new Elevator(1, 0, Set()))
        println(elevatorSystem.status)
        elevatorSystem.update(1, 2, Set(5))
        println(elevatorSystem.status)
    }
}
package mesoselevator

class Elevator(paramId: Int, paramPosition: Int, paramDest:  Set[Int])  {
    val id: Int = paramId
    val position: Int = paramPosition
    val dest: Set[Int] = paramDest

    def status: (Int, Int, Set[Int]) = (id, position, dest)
}
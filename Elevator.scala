package mesoselevator

class Elevator(paramId: Int, paramPosition: Int, paramDest:  Set[Int], paramDirection: Int)  {
    val id: Int = paramId
    val position: Int = paramPosition
    val dest: Set[Int] = paramDest
    val direction: Int = paramDirection

    def status: (Int, Int, Set[Int]) = (id, position, dest)
}
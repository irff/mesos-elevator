package mesoselevator

class Elevator(paramId: Int, paramPosition: Int, paramDest:  Set[Int])  {
    val id: Int = paramId
    var position: Int = paramPosition
    var dest: Set[Int] = paramDest

    def direction: Int = {
        1
    }

    def addDest(floor: Int) = {
        dest = dest + floor
    }

    def removeDest(floor: Int) = {
        dest = dest - floor
    }
}
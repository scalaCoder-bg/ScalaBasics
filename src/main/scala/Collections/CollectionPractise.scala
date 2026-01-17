package Collections

object CollectionPractise extends App{

  //Print all combinations between two Lists

  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c','d')

  println(chars.flatMap(char => (numbers.map(num => s"$char$num"))))

  //Seq

  val aSequence = Seq(4,3,6,1,2)

  println(aSequence)
  println(aSequence.sorted)
  println(aSequence.sortBy(x => -x))

  val myRange = 1 until 10

  val myList = List(2,3,4,5,6)

  val prepended = myList.+:(42)

  println(prepended)

}

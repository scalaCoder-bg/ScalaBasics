package LeetCodeProblems

object FoldLeft extends App{

  //Checking weather List is in sequence
  val list = List(1,2,3,4,5,7)

  val (prev, isValid) = list.tail.foldLeft((list.head, true)){
    case ((acc,true), n) => (acc+1, acc+1 == n)
    case (acc, n) => acc
  }

  println(s"These are the final values $prev")
  println(isValid)

}

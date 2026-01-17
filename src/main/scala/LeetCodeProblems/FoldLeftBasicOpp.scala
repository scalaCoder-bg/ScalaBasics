package LeetCodeProblems

object FoldLeftBasicOpp extends App {

  // Summing a list
  val list = List(2,3,4,1,2,5,6)

  val sumOfList = list.tail.foldLeft(list.head){(acc,n) => acc + n}
  println("Sum of List")
  println(sumOfList)
  
  
  // Building a String
  val list1 = List("b","h","a","r","g","a","v","a")
  
  val resultString = list1.tail.foldLeft(list1.head){(acc, letter) => acc+letter}

  println("Sum of List")
  println(sumOfList)

  // Reversing a list without using reverse

  val list2 = List(1,2,3,4,6,7)

  val reverseList = list2.tail.foldLeft(List(list2.head)){(acc,n) => n :: acc}

  println("Reversed List")
  println(reverseList)

  // Counting the number of elements in a string

  val testString = "bhargavagopalg"

  val countOfLetters = testString.foldLeft(Map.empty[Char,Int]){(acc,n) => acc + (n -> (acc.getOrElse(n,0) + 1))}
  println("Count of elements")
  countOfLetters.foreach{case(char, noOfTimes) =>println(s"The character $char is there $noOfTimes times")}
  

}

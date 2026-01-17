package practise

import scala.util.{Try, Success, Failure}

object Implicitss extends App{



  case class Employee(
                     name: String,
                     employeeId: Int,
                     interviewStatus: InterviewPrepStatus
                     )

  def declare(name:String)(using declaration:String):String ={
    declaration + name
  }

  given defDeclare:String = "The Notorious "

//  println(declare("Conor Mcgregor "))

  given Conversion[String,Int] with
    def apply(x:String): Int = {
      x match {
        case "Conor" => 1
        case _ => 2
      }
    }

  val myInt: Int = "bg"
  println(myInt)


  def myMult(firstNumber: Int)(using secondNumber: Int) = {
    firstNumber * secondNumber
  }

  given secNo: Int = 10

  println(myMult(20))

  extension (employee: Employee){
    def greetEmployee: String = "Hello Champ " + employee.name
  }

  val bg = Employee("Bhargava", 12098, Cleared)

  println(bg.greetEmployee)

  def add(a:Int, b:Int,c:Int) = a+b+c

  val add5 = add(5,_: Int, _:Int)

  println("This is add")
  println(add5(2,2))

  val doubler = {
    (x: Int, y:Int) => x + y
  }

  println("Doubler")
  println(doubler(29,1))

  def doubleMultplyier(f:Int => Int, x: Int) = {
    f(f(x))
  }

  println(doubleMultplyier((x:Int) => x*10, 2))

  val list = List(1,2,3,6,5,6)
  val (_,isValid) = list.tail.foldLeft((list.head, true)){
    case ((prevNum, true), num) => (num, prevNum + 1 == num)
    case (acc, num) => acc
  }

  println(s"This is is valid $isValid")

  val evenList = list.collect{case num:Int => num*10}

  def rec(n: Int, acc:Int = 1): Int = {

      if (n == 1) {
        acc
      } else {
        rec(n - 1, n * acc)
      }

  }

  println(rec(5))





}

package HigherOrderFunctions

object HigherOrderFuncs extends App {

  /*Higher order functions are those which take functions as their parameter or
   returns function as a result oy both
   */

  //Function which accepts function as a parameter
  def applyTwice(f:Int => Int, elem: Int) = {
    f(f(elem))
  }

  val result = applyTwice((x => x * 5), 10)

  println(result)

  // Function which returns function as a parameter

  def multiplier(factor: Int): Int => Int = {
    (x: Int) => x*factor
  }

  val sixMul = multiplier(6)

  println("Function returning function as a result")
  println(sixMul(7))

  // Commonly used Higher order funcs

  val nums = List(1, 2, 3, 4, 5)

  println("Common higher order funcs in scala")

  println(nums.map(_ * 2)) // => List(2, 4, 6, 8, 10)
  println(nums.filter(_ % 2 == 0)) // => List(2, 4)
  println(nums.foldLeft(0)(_ + _)) // => 15
  nums.foreach(println) // prints each element


  implicit val defaultName: String = "Bhargava";

  def pritnsmYname(greeting: String)(implicit name:String) ={
    println(s"$greeting $name")
  }

  pritnsmYname("Hello")
  pritnsmYname("Hello")("Soundarya")
}

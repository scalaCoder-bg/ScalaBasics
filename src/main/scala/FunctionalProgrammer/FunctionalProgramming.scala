package FunctionalProgrammer

object FunctionalProgramming extends App {

  trait MyFunc[A,B] {
    def apply(elem:A):B
  }

  val doubler = new MyFunc[Int, Int] {
    override def apply(elem:Int):Int = {
      elem * 2
    }
  }

  val adder = new Function2[Int,Int,Int] {
    override def apply(elem1:Int, elem2:Int) = {
      elem1 + elem2
    }
  }

  val myFunc = new Function[Int, Int] {
    override def apply(elem: Int): Int = elem
  }

  val functionReturner: Function1[Int, Function1[Int,Int]] =  new Function1[Int, Function1[Int,Int]] {
    override def apply(elem:Int):Function1[Int, Int] = new Function1[Int,Int] {
        override def apply(elem2:Int) = elem + elem2
      }
  }

  println(functionReturner(2)(3))

  val functionReturnerWithAnonymus: Int => Int => Int = number => number2 => number + number2
  
  



  println(adder(4,2))

  println(doubler(5))

  val lambdaDoubler: Int => Int = _ + 2

  println(lambdaDoubler(2))
  
  println(functionReturnerWithAnonymus(10)(12))

}

package FunctionalProgrammer


object HofAndCurries extends App{

//  val superFunction: (Int, (String, Int => Int))=> Int => (String => Int) => (Int => Int) = ???

  def nTimesRec[A](f:A => A, n:Int):A => A= {
    if (n<1){
      (x:A) => x
    } else {
      (x:A) => nTimesRec(f,n-1)(f(x))
    }
  }

  val recFunc =  nTimesRec[Int](_ * 2, 4)

  println(recFunc(3))

  def curryFunc[A](f:A => A)(num:A):A = {
    f(num)
  }

  val squared = curryFunc((x:Int) => x*x)
  val multiply2 = curryFunc((x:Int) => x * 2)
  val conCatHi = curryFunc((str:String) => "Hi " + str)

  println(squared(10))
  println(multiply2(10))
  println(conCatHi("Goat"))


  def curriedFormatter(formatter: String)(value: Double): String = formatter.format(value)

  val point2Formatter = curriedFormatter("%4.4f")

  println(point2Formatter(Math.PI))

  def toCurry[A](func: (A, A) => A) = {
    (x:A) => (y:A) => func(x,y)
  }

  def fromCurry[A](func: A => A => A) = {
    (x:A, y: A) => func(x)(y)
  }

  def compose[A](f: A => A, g:A => A, x:A) = {
    f(g(x))
  }

  def andThen[A](f: A => A, g: A => A, x: A) = {
    g(f(x))
  }



}

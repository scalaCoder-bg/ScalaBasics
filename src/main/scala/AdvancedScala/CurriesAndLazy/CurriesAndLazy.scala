package AdvancedScala.CurriesAndLazy

object CurriesAndLazy extends App{

  def curriedAdder(x:Int)(y:Int):Int = x + y

  val add5 = curriedAdder(5)
  println(add5(10))

  val simpleAdder = (x:Int, y:Int) => x + y
  def simpleAdder2(x:Int, y:Int) = x + y
  def curriedAdder2(x:Int)(y:Int) = x + y

  val add7 = simpleAdder(7,_)
  val add77 = simpleAdder2(7,_)
  val add777 = curriedAdder2(7) _
  val add7777 = (y:Int) => simpleAdder2(7,y)

  val lazyValue: Int = {
    println("Computing lazy value")
    42
  }
  println("Before accessing lazy value")
  println(s"Lazy value: $lazyValue")
  println(s"Lazy value again: $lazyValue")

  def formatter (form: String)(number: Double) = form.format(number)

  val Fourformatter = formatter("%4.2f") _
  val Eightformatter = formatter("%8.6f") _
  val fourteenFormatter = formatter("%14.12f") _
  val listOfDoubles = List(Math.PI, Math.E, 1, 9.8, 37, 100)

  listOfDoubles.foreach(elem => println(Fourformatter(elem)))
  listOfDoubles.foreach(elem => println(Eightformatter(elem)))
  listOfDoubles.foreach(elem => println(fourteenFormatter(elem)))

  def byName(n: => Int): Int = n + 10
  def byMethod(f: () => Int): Int = f() + 10

  def method: Int = 42
  def parenMethod(): Int = 42

  println(byName(method))
  println(parenMethod())
  println(byName(10))

  val myMapelements = listOfDoubles.map{
    case(elem) if(elem % 2 == 0) => "Hello I am an even number " + elem
    case(elem) if(elem % 2 != 0) => 0
  }

  lazy val adder: Int => Int => Int = x => y => x + y

  println(adder(4)(6))

  println(adder(11)(22))

}

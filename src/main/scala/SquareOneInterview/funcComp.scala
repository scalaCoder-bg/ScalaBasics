package SquareOneInterview

object funcComp extends App{
  
  def func1(n: Option[Int]):Option[Int] = n.map(_ + 1)

  def func2(n: Option[Int]):Option[Int] = n.map(_ * 10)
  
  val fucComp= func1.andThen(func2)
  
  println(fucComp(Some(5)))

}

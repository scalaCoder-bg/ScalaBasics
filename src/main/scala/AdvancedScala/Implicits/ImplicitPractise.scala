package AdvancedScala.Implicits

object ImplicitPractise extends App{

  implicit val defaultOrdering: Ordering[Int] = Ordering.fromLessThan((x,y) => x > y)
  println(List(3,4,7,8,1,2).sorted)

  implicit object Converter {
    implicit val reverseOrdering: Ordering[Int] = Ordering.fromLessThan(_ < _)
  }

  case class Purchase(numberOfUnits:Int, price:Double)

  object Purchase {
    implicit val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((x, y) => x.price > y.price)
  }

  object UnitCountOrdering {
    implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan((x, y) => x.numberOfUnits < y.numberOfUnits)
  }

  object UnitPriceOrering {
    implicit val unitPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((x, y) => x.price/x.numberOfUnits < y.price/y.numberOfUnits)
  }

}

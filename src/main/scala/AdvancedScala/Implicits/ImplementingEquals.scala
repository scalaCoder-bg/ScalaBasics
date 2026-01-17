package AdvancedScala.Implicits

object ImplementingEquals extends App{

  case class Person(name:String, age: Int)

  trait Equal[T] {
    def equals(x:T, y:T):Boolean
  }

  implicit object NameComparator extends Equal[Person] {
    override def equals(x: Person, y: Person): Boolean = x.name == y.name
  }

  implicit class EnrichEquals[T](value: T) {
    def === (anotherValue: T)(implicit equal: Equal[T]) = equal.equals(value, anotherValue)
    def !== (anotherValue: T)(implicit equal: Equal[T]) = if(equal.equals(value, anotherValue)) false else true
  }

  println(Person("bhargava",25) !== Person("Adithya", 26))

}

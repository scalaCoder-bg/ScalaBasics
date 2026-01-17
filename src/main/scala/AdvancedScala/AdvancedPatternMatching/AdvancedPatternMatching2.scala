package AdvancedScala.AdvancedPatternMatching

object AdvancedPatternMatching2 extends App {

  val list = List(3).flatMap(x => List(x*2))

  List(1,2,3).flatMap(x => List())

}

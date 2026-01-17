package AdvancedScala.AdvancedPatternMatching

object AdvPatternMatching extends App{

  object EvaluateInteger {
    def unapply(num: Int):Option[String] = {
      num match {
        case x if(x<10) => Some("Single digit")
        case x if(x%2 == 0) => Some("Even")
        case _ => Some("Other NUmber")
      }
    }
  }

  val result = 24 match {
    case EvaluateInteger(value) => value
  }

  println(result)

}

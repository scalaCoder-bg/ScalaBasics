package ObjectOrientedScala

class EvenPredicate extends MyPredicate[Int]{

  override def test(elem: Int): Boolean = elem % 2 == 0
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  def transform(elem : String): Int = elem.toInt
}



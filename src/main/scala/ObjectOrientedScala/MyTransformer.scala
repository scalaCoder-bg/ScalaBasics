package ObjectOrientedScala

import ObjectOrientedScala.Generics.MyList

trait MyTransformer[-A, B] {

  def transform(elem : A): B

}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyFlatMapTransformer[-A,B] {
  def transform(elem: A): MyList[B]
}

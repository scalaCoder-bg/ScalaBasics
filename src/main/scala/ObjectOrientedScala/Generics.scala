package ObjectOrientedScala

import scala.language.postfixOps

object Generics extends App {

  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](elem: B): MyList[B]
    def printElements: String
    override def toString: String = "[" + printElements + "]"
    def map[B](transformer: MyTransformer[A,B]): MyList[B]
    def filter(myPredicate: MyPredicate[A]): MyList[A]
    def ++[B >: A](list: MyList[B]):MyList[B]
    def flatMap[B](myFlatMapTransformer: MyFlatMapTransformer[A, B]): MyList[B]
  }

  class Animal
  class Dog extends Animal
  class Cat extends Animal

  class Cons[+A](hd: A, tl: MyList[A]) extends MyList[A] {
    def head: A = hd

    def tail: MyList[A] = tl

    def isEmpty: Boolean = false

    def add[B >: A](elem: B): MyList[B] = new Cons(elem, this)

    override def printElements: String = {
      if(tl.isEmpty) "" + hd
      else "" + hd + "," + tl.printElements
    }

    def map[B](transformer: MyTransformer[A, B]): MyList[B] = new Cons[B](transformer.transform(hd), tl.map(transformer))

    def filter(myPredicate: MyPredicate[A]): MyList[A] = {
      if(myPredicate.test(hd)) new Cons(hd, tl.filter(myPredicate))
      else tl.filter(myPredicate)
    }

    def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(hd, tl.++(list))

    def flatMap[B](myFlatMapTransformer: MyFlatMapTransformer[A, B]): MyList[B] = myFlatMapTransformer.transform(hd) ++ tl.flatMap(myFlatMapTransformer)
  }

  object EmptyList extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException

    def tail: MyList[Nothing] = throw new NoSuchElementException

    def isEmpty: Boolean = true

    def add[B >: Nothing](elem: B): MyList[B] = new Cons(elem, this)

    override def printElements: String = ""

    def map[B](transformer: MyTransformer[Nothing, B]): MyList[B]= EmptyList

    def filter(myPredicate: MyPredicate[Nothing]): MyList[Nothing] = EmptyList

    def ++[B >: Nothing](list: MyList[B]):MyList[B] = list

    def flatMap[B](myFlatMapTransformer: MyFlatMapTransformer[Nothing, B]): MyList[B] = EmptyList
  }

  val listOfInt = new Cons[Int](1, new Cons[Int](2, new Cons[Int](3, EmptyList)))
  val listOfString = new Cons[String]("one", new Cons[String]("two", EmptyList))
  val anotherLstOfInt = new Cons(4, new Cons(5, EmptyList))

  println(listOfInt.toString)
  println(listOfString.toString)



  println(listOfInt.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println(listOfInt.map(new MyTransformer[Int, String] {
    override def transform(elem: Int): String = elem match {
      case n if(n==1)=> "one"
      case n if(n==2)=> "two"
      case n if(n==3)=> "three"
      case _ => "infinite"
    }
  }).toString)

  println(listOfInt ++ anotherLstOfInt)

  println(listOfInt.flatMap(new MyFlatMapTransformer[Int, Int] {
    override def transform(elem: Int): MyList[Int] = new Cons[Int](elem, new Cons(elem + 1, EmptyList))
  }))

}

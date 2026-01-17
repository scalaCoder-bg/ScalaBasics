package FunctionalProgrammer

import scala.runtime.Nothing$

object FunctionalMyList extends App{

  abstract class MyList[+A] {
    def head: A

    def tail: MyList[A]

    def isEmpty: Boolean

    def add[B >: A](elem: B): MyList[B]

    def printElements: String

    override def toString: String = "[" + printElements + "]"

    def map[B](transformer: A => B): MyList[B]

    def filter(myPredicate: A => Boolean): MyList[A]

    def ++[B >: A](list: MyList[B]): MyList[B]

    def flatMap[B](myFlatMapTransformer: A => MyList[B]): MyList[B]

    def foreach(f:A => Unit): Unit

    def sort(f:(A,A) => Int):MyList[A]

    def zipWith[B >: A](list: MyList[B], f:(A,B) => B):MyList[B]

    def fold[B](x:B)(f: (B, A)=> B): B

  }

  class Cons[+A](hd: A, tl: MyList[A]) extends MyList[A] {
    def head: A = hd

    def tail: MyList[A] = tl

    def isEmpty: Boolean = false

    def add[B >: A](elem: B): MyList[B] = new Cons(elem, this)

    override def printElements: String = {
      if (tl.isEmpty) "" + hd
      else "" + hd + "," + tl.printElements
    }

    def map[B](transformer: A => B): MyList[B] = new Cons[B](transformer(hd), tl.map(transformer))

    def filter(myPredicate: A => Boolean): MyList[A] = {
      if (myPredicate(hd)) new Cons(hd, tl.filter(myPredicate))
      else tl.filter(myPredicate)
    }

    def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(hd, tl.++(list))

    def flatMap[B](myFlatMapTransformer: A => MyList[B]): MyList[B] = myFlatMapTransformer(hd).++(tl.flatMap(myFlatMapTransformer))

    def foreach(f: A => Unit): Unit = {
      f(hd)
      tl.foreach(f)
    }

    def sort(f: (A, A) => Int): MyList[A] = {
      def insert(x: A, sortedLst: MyList[A]): MyList[A] = {
        if(sortedLst.isEmpty) {
          new Cons(x, EmptyList)
        } else if(f(x, sortedLst.head)  <= 0) {
          new Cons(x, sortedLst)
        } else {
          new Cons(sortedLst.head, insert(x, sortedLst.tail))
        }
      }

      val sortedList = tl.sort(f)
      insert(hd, sortedList)
    }

    def zipWith[B >: A](list: MyList[B], f: (A, B) => B): MyList[B] = {
      new Cons(f(hd,list.head), tl.zipWith(list.tail,f))
    }

    def fold[B](x:B)(f: (B, A)=> B): B = {
      tl.fold(f(x,hd))(f)
    }
  }

  object Cons {
    def apply[A](hd: A, lst: MyList[A]) = {
      new Cons(hd, lst)
    }
  }

  object EmptyList extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException

    def tail: MyList[Nothing] = throw new NoSuchElementException

    def isEmpty: Boolean = true

    def add[B >: Nothing](elem: B): MyList[B] = new Cons(elem, this)

    override def printElements: String = ""

    def map[B](transformer: Nothing => B): MyList[B] = EmptyList

    def filter(myPredicate: Nothing => Boolean): MyList[Nothing] = EmptyList

    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

    def flatMap[B](myFlatMapTransformer: Nothing => MyList[B]): MyList[B] = EmptyList

    def foreach(f: Nothing => Unit): Unit= ()

    def sort(f: (Nothing, Nothing) => Int): MyList[Nothing] = EmptyList

    def zipWith[B >: Nothing](list: MyList[B], f: (Nothing, B) => B): MyList[B] = EmptyList

    def fold[B](x:B)(f: (B, Nothing)=> B): B = x
  }

  val listOfInt = new Cons[Int](1, new Cons[Int](2, new Cons[Int](3, EmptyList)))
  val listOfString = new Cons[String]("one", new Cons[String]("two", EmptyList))
  val anotherLstOfInt = new Cons(4, new Cons(5, new Cons(6, EmptyList)))

  println(listOfInt.toString)
  println(listOfString.toString)


  println(listOfInt.filter((elem: Int)=> elem % 2 == 0).toString)

  println(listOfInt.map((elem: Int) => elem match {
    case n if (n == 1) => "one"
    case n if (n == 2) => "two"
    case n if (n == 3) => "three"
    case _ => "infinite"
  }).toString)

  println(listOfInt ++ anotherLstOfInt)

  println(listOfInt.flatMap((elem: Int) => new Cons[Int](elem, new Cons(elem + 1, EmptyList))))

  println(listOfInt.foreach(elem => println(elem)))

  println(listOfInt.zipWith(anotherLstOfInt, (x,y) =>x * y))

  val toBeSortedList = Cons(3, Cons(1, Cons(9, Cons(2, Cons(10, EmptyList)))))

  println(toBeSortedList.sort((x,y) => x - y))

  val numbers = Cons(1, Cons(2, Cons(3, EmptyList)))
  val chars = Cons('a', Cons('b', Cons('c', EmptyList)))
  val colors = Cons("black", Cons("blue", Cons("white", EmptyList)))

  val combiList = for {
    num <- numbers
    char <- chars
    color <- colors
  } yield ("" + num + char + "-" + color)

  println(combiList)

}

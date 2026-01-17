package AdvancedScala.CurriesAndLazy

object ImplementingStreams extends App{

  trait MyStream[+A] {
    def isEmpty: Boolean
    def head: A
    def tail: MyStream[A]
    def #::[B >: A](elem: B): MyStream[B]
    def ++[B >: A](stream: MyStream[B]):MyStream[B]
    def foreach(f: A => Unit):Unit
    def map[B](f: A => B): MyStream[B]
    def flatMap[B](f: A => MyStream[B]):MyStream[B]
    def filter(predicate: A => Boolean):MyStream[A]
    def take(n:Int): MyStream[A]
  }

  object MyStream {
    def from[A](start:A)(generator: A => A): MyStream[A] = {
      new Cons[A](start, from(generator(start))(generator))
    }

    def apply[A](hd: A, x: =>MyStream[A]) = {
      new Cons[A](hd, x)
    }
  }

  object EmptyStream extends MyStream[Nothing]{
    def isEmpty: Boolean = true

    def head: Nothing = throw new RuntimeException

    def tail: MyStream[Nothing] = throw new RuntimeException

    def #::[B >: Nothing](elem: B): MyStream[B] = new Cons[B](elem, EmptyStream)

    def ++[B >: Nothing](stream: MyStream[B]): MyStream[B] = stream

    def foreach(f: Nothing => Unit): Unit = ()

    def map[B](f: Nothing => B): MyStream[B] = EmptyStream

    def flatMap[B](f: Nothing => MyStream[B]): MyStream[B] = EmptyStream

    def filter(predicate: Nothing => Boolean): MyStream[Nothing] = EmptyStream

    def take(n: Int): MyStream[Nothing] = throw new RuntimeException()

  }

  class Cons[A](hd: A, tailReturner: =>MyStream[A]) extends MyStream[A] {

    def isEmpty: Boolean = false

    override val head = hd

    override lazy val  tail: MyStream[A] = tailReturner

    def #::[B >: A](elem: B): MyStream[B] = new Cons[B](elem, this)

    def ++[B >: A](stream: MyStream[B]): MyStream[B] = new Cons[B](hd, tail ++ stream)

    def foreach(f: A => Unit): Unit = {
      f(hd)
      tail.foreach(f)
    }

    def map[B](f: A => B): MyStream[B] = new Cons[B](f(hd), tail.map(f))

    def flatMap[B](f: A => MyStream[B]): MyStream[B] = f(hd) ++ tail.flatMap(f)

    def filter(predicate: A => Boolean): MyStream[A] = {
      if (predicate(hd)) {
        new Cons(hd, tail)
      } else {
        tail.filter(predicate)
      }
    }

    def take(n: Int): MyStream[A] ={
      if(n < 1) {
        EmptyStream
      } else {
        new Cons(hd, tail.take(n - 1))
      }
    }

  }


  val stream = Stream.iterate(1)(_ + 1)

  val stream10 = stream.take(10)

  stream10.foreach(println)

  val myNewStream = MyStream.from(10)(_ * 5)

  println(myNewStream.take(5))

  myNewStream.take(5).foreach(println)

  val newList = List(2,3,4,5,6,7,8,9,10,11,12,13)

  def erotesthenous(list:List[Int], acc:List[Int] = List.empty[Int]): List[Int] = {
    if(list.isEmpty) acc
    else {
      erotesthenous(list.tail.filter(_ % list.head != 0), list.head :: acc)
    }
  }


}

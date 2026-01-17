package Collections

import scala.runtime.Nothing$

object MaybeImplementation extends App {

  abstract class Maybe[+A] {
    def map[B](transformer: A=> B):Maybe[B]
    def flatMap[B](transformer: A => Maybe[B]): Maybe[B]
    def filter(checkFunc: A => Boolean): Maybe[A]
  }

  object Empty extends Maybe[Nothing] {
    def map[B](transformer: Nothing => B): Maybe[B] = Empty

    def flatMap[B](transformer: Nothing => Maybe[B]): Maybe[B] = Empty

    def filter(checkFunc: Nothing => Boolean): Maybe[Nothing] = Empty
  }

  class Elem[+A](value: A) extends Maybe[A] {
    def map[B](transformer: A => B): Maybe[B] = {
      Elem(transformer(value))
    }

    def flatMap[B](transformer: A => Maybe[B]): Maybe[B] = transformer(value)

    def filter(checkFunc: A => Boolean): Maybe[A] = {
      checkFunc(value) match {
        case true => this
        case false => Empty
      }
    }
  }

  object Elem {
    def apply[A](value: A) = new Elem(value)
  }







}

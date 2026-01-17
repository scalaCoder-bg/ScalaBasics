package ObjectOrientedScala

import com.sun.source.doctree.UnknownInlineTagTree

import scala.runtime.Nothing$

object InheritanceAndStuff extends App {

  abstract class MyList {
    def head: Int
    def tail: MyList
    def isEmpty: Boolean
    def add(elem: Int): MyList
    def pop: MyList
    def printlnElements: String
    override def toString: String = "[" + printlnElements + "]"
  }

  object EmptyList extends MyList {
    def head: Int = throw new NoSuchElementException("EmptyList.head")

    def tail: MyList = throw new NoSuchElementException("EmptyList.head")

    def isEmpty: Boolean = true

    def add(elem: Int): MyList = new NonEmptyList(elem, EmptyList)

    def pop: MyList = throw new NoSuchElementException("EmptyList.head")

    def printlnElements: String = ""

    override def toString: String = "[" + printlnElements + "]"
  }

  class NonEmptyList(hd: Int,tl: MyList ) extends MyList {
    def head: Int = hd

    def tail: MyList = tl

    def isEmpty: Boolean = false

    def add(elem: Int): MyList = new NonEmptyList(elem, this)

    def pop: MyList = tl

    def printlnElements: String = {
      if(tl.isEmpty) "" + hd
      else hd + tl.printlnElements
    }

    override def toString: String = "[" + printlnElements + "]"
  }


  val myList = EmptyList
  val newList = EmptyList.add(2)
  val secondAddedList = newList.add(3)

  println(newList.head)
  println(secondAddedList.head)

  println(newList.toString)
  println(secondAddedList.toString)

}

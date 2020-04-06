package funsets

// 协变
trait List[+T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]

  def nth(n: Int): T

  def prepend[U >: T](elem: U) : List[U]  = new Cons[U](elem, this)

 //  def prepend2(elem: T) : List[T]  = new Cons[T](elem, this)
}

class Nil[T] extends List[T] {
  override def isEmpty: Boolean = true

  override def head: T = throw new NoSuchElementException("Nil.head")

  override def tail: List[T] = throw new NoSuchElementException("Nil.tail")

  override def nth(n: Int): T = throw new NoSuchElementException("Nil.nth")
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty: Boolean = false

  override def nth(n: Int): T = if (n == 1) head else tail.nth(n - 1)
}


object List {

  type A = IntSet => NonEmpty
  type B = NonEmpty => IntSet

  // List()
  def apply[T](): List[T] = new Nil[T]

  // List(1,2)
  def apply[T](x1: T, x2: T): List[T] = new Cons[T](x1, new Cons[T](x2, new Nil[T]))

  // 可以确定返回类型
  def assertPos[S <: IntSet](r : S) : S = {
    r
  }

  def fun(f: B) = {}

  def main(args: Array[String]): Unit = {
    val empty: Empty.type = assertPos(Empty)
    val nonEmpty : NonEmpty = assertPos(new NonEmpty(1, Empty, Empty))
    fun(IntSet => new NonEmpty(1, Empty, Empty))
  }
}

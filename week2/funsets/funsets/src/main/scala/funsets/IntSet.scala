package funsets

/**
 * 构造一个IntSet集合
 */
abstract class IntSet {
  def contains(x: Int): Boolean

  def incl(x: Int): IntSet

  def union(other: IntSet): IntSet
}

/**
 * 空集合 EmptySet
 */
object Empty extends IntSet {
  override def contains(x: Int): Boolean = false

  override def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)

  override def union(other: IntSet): IntSet = other

  override def toString: String = "."
}


/**
 * 构造非空集合
 */
class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  override def contains(x: Int): Boolean =
    if (x < elem)
      left contains x
    else if (x > elem)
      right contains x
    else
      true

  override def incl(x: Int): IntSet =
    if (x < elem)
      new NonEmpty(elem, left incl x, right)
    else if (x > elem)
      new NonEmpty(elem, left, right incl x)
    else
      this

  // 比较抽象的递归方法调用
  override def union(other: IntSet): IntSet = ((left union other) union right) incl elem

  override def toString: String = "{" + left + right + "." + elem + "}"
}


object IntSet {
  def main(args: Array[String]): Unit = {
    val a: scala.List[NonEmpty] = scala.List[NonEmpty](new NonEmpty(1, Empty, Empty))
    val b: scala.List[IntSet] = a
    // b(0) = Empty
    val s: NonEmpty = b.head.asInstanceOf[NonEmpty]
    print(s)

    //    NonEmpty[] a = new NonEmpty[] {
    //      new NonEmpty(1, Empty, Empty)
    //    }
    //    IntSet[] b = a
    //    b[0] = Empty
    //    NonEmpty s = a[0]
  }
}
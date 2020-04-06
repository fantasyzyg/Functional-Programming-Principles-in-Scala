package objsets

/**
 *      Peano numbers
 * 实现自然数，但是不使用标准库中的 numerical class
 */
abstract class Nat {
  def isZero: Boolean

  def predecessor: Nat

  // 没有用到数字，而是利用了一个更加抽象的概念(在scala实现中是对象的构造)
  def successor: Nat = new Succ(this)

  def +(that: Nat): Nat

  def -(that: Nat): Nat

}


/**
 *   需要定义一个边界 Zero , 对于边界的数字需要做好特别处理
 */
object Zero extends Nat {
  override def isZero: Boolean = true

  override def predecessor: Nat = throw new Error("0.predecessor")

  override def +(that: Nat): Nat = that

  override def -(that: Nat): Nat = if (that.isZero) that else throw new Error("negative number.")
}


case class Succ(n: Nat) extends Nat {
  override def isZero: Boolean = false

  override def predecessor: Nat = n

  // 一层层的对象构造
  override def +(that: Nat): Nat = new Succ(n + that)

  override def -(that: Nat): Nat = if (that.isZero) this else n - that.predecessor
}
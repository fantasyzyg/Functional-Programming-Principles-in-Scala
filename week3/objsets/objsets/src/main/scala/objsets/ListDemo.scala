package objsets

object ListDemo {

  def removeAt[T](n: Int, xs: List[T]): List[T] = {
    if (n == 0)
      xs match {
        case List() => throw new Error("index out of range!")
        case _ :: ys => ys
      }
    else
      xs match {
        case List() => throw new Error("index out of range!")
        case x :: ys => x :: removeAt(n - 1, ys)
      }
  }

  def removeAt1[T](n: Int, xs: List[T]): List[T] = (xs take n - 1) ++ (xs drop n)

  def flatten[T](xs: List[T]): List[T] = {
    xs match {
      case List() => List()
      case x :: ys => x match {
        case list: List[T] => flatten(list) ++ flatten(ys)
        case _ => x :: flatten(ys)
      }
    }
  }

  def concat[T](xs: List[T], ys: List[T]): List[T] =
    xs match {
      case List() => ys
      case x :: zs => x :: concat(zs, ys)
    }


  def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0)
      xs
    else {
      def merge(xs: List[T], ys : List[T]): List[T] = {
        (xs, ys) match {
          case (List(), List()) => List()
          case (List(), y) => y
          case (x, List()) => x
          case (x::x1, y::y1) => if (ord.lt(x, y)) x :: merge(x1, ys) else y :: merge(xs, y1)
        }
      }

      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd))
    }
  }

  def pack[T](xs : List[T]): List[List[T]] = xs match {
    case List() => List()
    case x :: _ => {
      val (first, rest) = xs span (y => y == x)
      first :: pack(rest)
    }
  }

  def encode[T](xs : List[T]) : List[(T, Int)] = pack(xs).map(x => (x.head, x.length))

  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5)
    println(list.init)

    println(removeAt1(1, list))
    println(flatten(List(List(1, 1), 2, List(3, List(5, 8)))))

    val value: List[B] = List[B]()

    // 参数是逆变的
    // val value1: List[A] = value.updated(1, new A())


    println(msort(List(2,3,1,4,5)))
    println(msort(List("b", "a", "c")))
  }
}

class A {}

class B extends A

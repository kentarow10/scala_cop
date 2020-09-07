package example

import scala.collection.mutable

class ChecksumAccumulator {
    private var sum = 0
    def add (b: Byte): Unit = { sum += b}
    // 256との論理積をとってビット反転して1を足すことの意味？
    def checksum (): Int = ~(sum & 0xFF) + 1
}

// コンパニオンオブジェクト。
// 互いに非公開のメンバにアクセスできる
object ChecksumAccumulator {
    private val cache = mutable.Map.empty[String, Int]
    def calculate(s: String): Int =
        if (cache.contains(s))
            cache(s)
        else{
            val acc = new ChecksumAccumulator
            for (c <- s)
                acc.add(c.toByte)
            val cs = acc.checksum()
            cache += (s -> cs)
            cs
        }
}
package example

object Hello extends App {
  NextStep.run
}

object FirstStep {
  def run () = {
    def greet () = println("hello,world")

    greet
  }
}

object NextStep {
  def run () = {
    val big = new java.math.BigInteger("12345")
    println(big)

    val greetStrings = new Array[String](3)
    // val だが変数が参照するオブジェクトは変わりうるという例
    greetStrings(0) = "a"
    greetStrings(1) = "b"
    greetStrings(2) = "c"

    greetStrings(0) = "x"
    // for ( i <- 0 to 2) println(greetStrings(i))
    for ( i <- 0 to 2) Console println greetStrings(i)

    // applyメソッドが呼び出されている(配列に限らず、OBJECT(i)というものは全て)
    for ( i <- 0 to 2) Console println greetStrings.apply(i)
    // 同様に代入はupdateメソッドが呼び出されている
    greetStrings.update(0,"a")
    for ( i <- 0 to 2) Console println greetStrings(i)

    // 全ての表現はオブジェクトがもつメソッドと捉えられる。(javaではプリミティブかラッパーかという判断があった)

    val numNames = Array("zero","one","two")
    // Arrayのコンパニオンオブジェクトにapplyが用意されている
    // newとかく必要がないのはこの為。
    val numNames2 = Array.apply("zero","one","two")
    for ( num <- numNames2) println(num)

    // scala array : mutable
    // java list : mutable
    // scala list : immutable
    val li = List(2,3)
    val lii = 1 :: li
    println(lii)
    // 末尾がコロンのメソッドは右側が呼び出し。
    val liii = li.::(1)
    println(liii)

    // append(:+)はlistの長さに比例する計算量となる。
    // prepend(::)した後にreverseする
    // 追加操作をもつListBufferを利用するなどして、出来るだけ避けたほうが良い。

    // タプル

    // 集合とマップ
    // HashSet(デフォルトでimmutable)
    // 変数はvarである必要あり
    var jetSet = Set("a","b")
    // 要素にa,b,cccをもつ新しいimmuな集合がjetSetに代入されるイメージ
    jetSet += "ccc"
    println(jetSet.contains("ccc"))

    import scala.collection.mutable
    val aaa = mutable.Set("a")
    aaa += "bbb"
    println(aaa.contains("bbb"))

    // map

    import scala.io.Source
    def widthOfLength(s: String) = s.length.toString.length

    val lines = Source.fromFile("/Users/kentarowaki/Desktop/scala_cop/training/src/main/scala/example/Hello.scala").getLines().toList
    val longestLine = lines.reduceLeft((a,b) => if (a.length > b.length) a else b)
    val maxWidth = widthOfLength(longestLine)

    for (line <- lines){
      val numSpace = maxWidth - widthOfLength(line)
      val padding = " " * numSpace
      println(padding + line.length + " | " + line)
    }
  }
}

// trait Greeting {
//   lazy val greeting: String = "hello"
// }

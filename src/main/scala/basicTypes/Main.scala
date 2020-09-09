package basicTypes

object Main extends App {
    // 補数：対象の数に補数を足せば対象の数の桁が一つ上がる最小の数になる
    // 例：123 => 877

    // 補数でマイナスを表すことができる
    // 例：555 - 123 => 555 + (1000 - 123) = 1432
    // 一番大きい桁を最後に排除する
    // 全てのn進数で言える

    // 基本型Byte
    // 2の補数表現の8ビット符号付整数

    // 1の補数：7桁の場合、足して1111111になるような数なので、ビットを反転させたものになる
    // 2の補数：7桁の場合、足して10000000になるような数なので、1の補数に1足したものである

    // Float：32ビットのIEEE754単精度浮動小数点数
    // Double：64ビットのIEEE754倍精度浮動小数点数
    // TODO 後から調べる

    // 文字列の扱い
    println("""| aaaaa   bbbbbbb cc
    | ddddd eeeeefff   ffff |ggg""".stripMargin)

    // 文字列補間子
    // s,raw,f

    // Richラッパーというものが各基本型に備わっていていろんな機能が暗黙的に使える

    // 基本的なクラスの作ってみた
    val a = new Rational(3,4)
    val b = new Rational(9,4)

    println(a + b)

    val c = new Rational(1324564,3232)
    println(c)

    // BuiltInFuncTraining.printFilesHere
    // BuiltInFuncTraining.printFilesHereOnlyMdFile
    // BuiltInFuncTraining.grep("*an*")
    println(BuiltInFuncTraining.kukuTable)
}

object BuiltInFuncTraining {

    def printFilesHere() = {
        val files = (new java.io.File(".")).listFiles
        for (f <- files) println(f)
    }

    def printFilesHereOnlyMdFile() = {
        val files = (new java.io.File(".")).listFiles
        for (f <- files
            if f.isFile
            if f.getName.endsWith(".md")
        ) println(f)
    }

    def grep(pattern: String) = {
        for {
            f <- (new java.io.File("./")).listFiles
            if f.isFile()
            // 文字コードの問題でexceptionが起きる
            // line <- fileLines(f)
            // if line.trim.matches(pattern)
        }println(f)
    }

    private def fileLines(file: java.io.File) = scala.io.Source.fromFile(file).getLines().toList

    // 九九を表示
    private def makeRowSeq(row: Int) = for (col <- 1 to 9) yield (" " * (4 - ((row * col).toString).length)) + (row * col).toString

    private def makeRow(row: Int) = makeRowSeq(row).mkString

    def kukuTable() = {
        val tableSeq = for (row <- 1 to 9) yield makeRow(row)
        tableSeq.mkString("\n")
    }
}
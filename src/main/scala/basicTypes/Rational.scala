package basicTypes

// immutable前提の設計
class Rational(n: Int, d: Int) {
    // コンストラクタの制約
    // requireメソッドはPredefに定義されていて無条件にimportされている
    require(d != 0)

    private val g = gcd(n.abs, d.abs)
    val bnshi = n / g
    val bnbo = d / g

    // 引数一つで来た時の補助コンストラクタ
    // 名前はthisと決められている。
    def this(n: Int) = this(n, 1)

    def + (that: Rational): Rational = 
        new Rational(bnshi * that.bnbo + that.bnshi * bnbo, bnbo * that.bnbo)
    def + (i: Int): Rational = new Rational(bnshi + i*bnbo, bnbo)

    def - (that: Rational): Rational =
        new Rational(bnshi * that.bnbo - that.bnshi * bnbo, bnbo * that.bnbo)
    def - (i: Int): Rational = new Rational(bnshi - i*bnbo, bnbo)

    def * (that: Rational): Rational =
        new Rational(bnshi * that.bnshi, bnbo * that.bnbo)
    def * (i: Int): Rational = new Rational(bnshi * i, bnbo)
    
    def / (that: Rational): Rational =
        new Rational(bnshi * that.bnbo, bnbo * that.bnshi)
    def / (i: Int): Rational = new Rational(bnshi, bnbo * i)

    override def toString = if(bnbo == 1) bnshi.toString else bnshi + "/" + bnbo
    private def gcd(a: Int, b: Int): Int = if(b == 0) a else gcd(b, a%b)
}

// implicitなIntToRationalは後ほど学ぶ
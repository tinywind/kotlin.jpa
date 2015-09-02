package jpa.study

fun main(args: Array<String>) {
    jpa.study.ch03.test()
}

fun foo2() {
    fun gcd(x: Int, y: Int): Int {
        var a = x;
        var b = y;

        while (b != 0) {
            var temp = a % b;
            a = b;
            b = temp;
        }
        return Math.abs(a);
    }

    loop@ for (i in 1..10) {
        loop2@ for (j in 1..10) {
            if (gcd(i, j) == 1) {
                println("$i , $j's GCD is 1")
                //                break@loop
                continue@loop
            }
        }
    }

    println(foo())
}

fun foo() {
    var ints = Array(5, { i -> ((i) * (i)) })

    ints.forEach(fun(value: Int) {
        if (value == 0) return@foo
        print(value)
    })
}
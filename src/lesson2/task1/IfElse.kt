@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.*

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    return when {
        age / 10 % 10 == 1 -> "$age лет"
        age % 10 in 2..4 -> "$age года"
        age % 10 == 1 -> "$age год"
        else -> "$age лет"
    }
}


    /**
     * Простая
     *
     * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
     * и t3 часов — со скоростью v3 км/час.
     * Определить, за какое время он одолел первую половину пути?
     */
    fun timeForHalfWay(t1: Double, v1: Double,
                       t2: Double, v2: Double,
                       t3: Double, v3: Double): Double {
        val firstPart = t1 * v1
        val secondPart = t2 * v2
        val thirdPart = t3 * v3
        val route = firstPart + secondPart + thirdPart
        return when {
            (firstPart >= (route / 2.0)) -> t1 - (firstPart - route / 2) / v1
            ((firstPart + secondPart) < (route / 2.0)) -> (thirdPart - route / 2.0) / v3 + t1 + t2
            else -> (route / 2.0 - firstPart) / v2 + t1
        }
    }

    /**
     * Простая
     *
     * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
     * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
     * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
     * и 3, если угроза от обеих ладей.
     * Считать, что ладьи не могут загораживать друг друга
     */
    fun whichRookThreatens(kingX: Int, kingY: Int,
                           rookX1: Int, rookY1: Int,
                           rookX2: Int, rookY2: Int): Int {
        val rookRouteX1 = (kingX == rookX1)
        val rookRouteX2 = (kingX == rookX2)
        val rookRouteY1 = (kingY == rookY1)
        val rookRouteY2 = (kingY == rookY2)
        return when {
            ((!rookRouteX1 && !rookRouteY1) && (!rookRouteX2 && !rookRouteY2)) -> 0
            ((rookRouteX1 || rookRouteY1) && (!rookRouteX2 && !rookRouteY2)) -> 1
            ((!rookRouteX1 && !rookRouteY1) && (rookRouteX2 || rookRouteY2)) -> 2
            else -> 3
        }
    }

    /**
     * Простая
     *
     * На шахматной доске стоят черный король и белые ладья и слон
     * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
     * Проверить, есть ли угроза королю и если есть, то от кого именно.
     * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
     * и 3, если угроза есть и от ладьи и от слона.
     * Считать, что ладья и слон не могут загораживать друг друга.
     */
    fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                              rookX: Int, rookY: Int,
                              bishopX: Int, bishopY: Int): Int {
        val bishopRouteX = (Math.abs(kingX - bishopX))
        val bishopRouteY = (Math.abs(kingY - bishopY))
        val rookRouteX = (kingX == rookX)
        val rookRouteY = (kingY == rookY)
        return when {
            ((rookRouteX || (kingY == rookY)) && (bishopRouteX == bishopRouteY)) -> 3
            ((!rookRouteX && !rookRouteY) && (bishopRouteX == bishopRouteY)) -> 2
            ((rookRouteX || rookRouteY) && (bishopRouteX != bishopRouteY)) -> 1
            else -> 0
        }
    }

    /**
     * Простая
     *
     * Треугольник задан длинами своих сторон a, b, c.
     * Проверить, является ли данный треугольник остроугольным (вернуть 0),
     * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
     * Если такой треугольник не существует, вернуть -1.
     */
    fun triangleKind(a: Double, b: Double, c: Double): Int {
        val a2 = pow(a, 2.0)
        val b2 = pow(b, 2.0)
        val c2 = pow(c, 2.0)
        return when {
            ((a + b <= c) || (a + c <= b) || (b + c <= a)) -> -1 //Проверяем существование треугольника
            ((a2 + b2 == c2) || (a2 + c2 == b2) || (c2 + b2 == a2)) -> 1 //Проверяем, является ли треугольник прямоугольным
            ((a2 + b2 > c2) && (a2 + c2 > b2) && (c2 + b2 > a2)) -> 0 // Проверяем, является ли треугольник остроугольным
            else -> 2 //Проверяем, является ли треугольник тупоугольным
        }
    }

    /**
     * Средняя
     *
     * Даны четыре точки на одной прямой: A, B, C и D.
     * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
     * Найти длину пересечения отрезков AB и CD.
     * Если пересечения нет, вернуть -1.
     */
    fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
        ((b >= d) && (c <= a) && (d >= a)) -> (d - a) //Случай, при котором отрезок DC пересекается с отрезком АВ
        ((d >= b) && (b >= c) && (a <= c)) -> (b - c) //Случай, при котором отрезок AB пересекается с отрезком DC
        ((d >= b) && (a >= c)) -> (b - a) //Случай, при котором отрезок AB входит в отрезок DC
        ((b >= d) && (c >= a)) -> (d - c) //Случай, при котором отрезок DC входит в отрезрок АВ
        else -> -1
    }
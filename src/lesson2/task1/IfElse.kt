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
    if (age / 10 % 10 == 1) return age.toString() + " лет"
    else if ((age % 10 >= 2) && (age % 10 <= 4)) return age.toString() + " года"
    else if (age % 10 == 1) return age.toString() + " год"
    return age.toString() + " лет"
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
    val firstpart = t1 * v1
    val secondpart = t2 * v2
    val thirdpart = t3 * v3
    val route = firstpart + secondpart + thirdpart
    if (firstpart >= (route / 2.0)) return t1 - (firstpart - route / 2) / v1
    else if ((firstpart + secondpart) < (route / 2.0)) return (thirdpart - route / 2.0) / v3 + t1 + t2
    else return (route / 2.0 - firstpart) / v2 + t1
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
    val rookrouteX1 = (kingX == rookX1)
    val rookrouteX2 = (kingX == rookX2)
    val rookrouteY1 = (kingY == rookY1)
    val rookrouteY2 = (kingY == rookY2)
    if ((!rookrouteX1 && !rookrouteY1) && (!rookrouteX2 && !rookrouteY2)) return 0
    else if ((rookrouteX1 || rookrouteY1) && (!rookrouteX2 && !rookrouteY2)) return 1
    else if ((!rookrouteX1 && !rookrouteY1) && (rookrouteX2 || rookrouteY2)) return 2
    else
        return 3

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
    val bishoprouteX = (Math.abs(kingX - bishopX))
    val bishoprouteY = (Math.abs(kingY - bishopY))
    val rookrouteX = (kingX == rookX)
    val rookrouteY = (kingY == rookY)
    if ((rookrouteX || (kingY == rookY)) && (bishoprouteX == bishoprouteY)) return 3
    else if ((!rookrouteX && !rookrouteY) && (bishoprouteX == bishoprouteY)) return 2
    else if ((rookrouteX || rookrouteY) && (bishoprouteX != bishoprouteY)) return 1
    else return 0
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
    if ((a + b <= c) || (a + c <= b) || (b + c <= a)) return -1 //Проверяем существование треугольника
    else if ((a2 + b2 == c2) || (a2 + c2 == b2) || (c2 + b2 == a2)) return 1 //Проверяем, является ли треугольник прямоугольным
    else if ((a2 + b2 > c2) && (a2 + c2 > b2) && (c2 + b2 > a2)) return 0 // Проверяем, является ли треугольник остроугольным
    return 2 //Проверяем, является ли треугольник тупоугольным
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {

    return when { ((b > d) && (c < a) && (d > a)) -> (d - a) //Случай, при котором отрезок DC пересекается с отрезком АВ
        ((d > b) && (b > c) && (a < c)) -> (b - c) //Случай, при котором отрезок AB пересекается с отрезком DC
        ((d > b) && (a > c)) -> (b - a) //Случай, при котором отрезок AB входит в отрезок DC
        ((b > d) && (c > a)) -> (d - c) //Случай, при котором отрезок DC входит в отрезрок АВ
        ((b == c) || (d == a)) -> 0 //Случай, при котором отрезки равны и лежат друг на друге
        else -> -1
    }
}
package me.darksidecode.bfuandroidlabs

import java.util.concurrent.ThreadLocalRandom
import kotlin.math.pow

fun main() {
    runTask(1, ::task1)
    runTask(2, ::task2)
    runTask(3, ::task3)
    runTask(4, ::task4)
    runTask(5, ::task5)
}

private fun runTask(number: Int, task: () -> Unit) {
    println("${"=".repeat(10)} Task #${number} ${"=".repeat(10)}")
    task()
    println()
}

private fun task1() {
    val n = readln().toInt()
    val numbers = (1..n).asSequence().map { readln().toInt() }
    val result = numbers.foldIndexed(0) { i, acc, next ->
        acc + next * (-1.0).pow(i).toInt() }
    println(result)
}

private fun task2() {
    val numOfRoads = readln().toInt()
    val tunnelHeightsOnRoads = (1..numOfRoads).asSequence().map {
        val numOfTunnelsOnRoad = readln().toInt()
        (1..numOfTunnelsOnRoad).asSequence().map { readln().toInt() }
    }
    val maxAllowedHeightOnRoads = tunnelHeightsOnRoads.map { it.min() }
    val (bestRoad, maxHeight) = maxAllowedHeightOnRoads.withIndex().maxBy { it.value }
    println("${bestRoad + 1} $maxHeight")
}

private fun task3() {
    val digits = readln().asSequence().map { it.digitToInt() }
    val digitsSum = digits.sum()
    val digitsProduct = digits.fold(1) { acc, next -> acc * next }
    val isTwiceEven = (digitsSum % 2) == 0 && (digitsProduct % 2) == 0
    println(isTwiceEven)
}

private fun task4() {
    val input = readln()

    val windowSeenChars = mutableSetOf<Char>()
    var windowStart = 0
    var windowStop = 0
    var bestWindowStart = 0
    var bestWindowStop = 0

    while (windowStart < input.length) {
        if (windowStop < input.length) {
            windowStop++
        }

        val windowRightmostChar = input[windowStop - 1]

        while (windowRightmostChar in windowSeenChars) {
            windowSeenChars -= input[windowStart++]
        }

        windowSeenChars += windowRightmostChar

        if (windowStop - windowStart > bestWindowStop - bestWindowStart) {
            bestWindowStart = windowStart
            bestWindowStop = windowStop
        }

        if (input.length - windowStart < bestWindowStop - bestWindowStart) {
            break
        }
    }

    println("Best substring: ${input.substring(bestWindowStart, bestWindowStop)}")
    println("Location: from char #${bestWindowStart + 1} to char #$bestWindowStop")
    println("Length: ${bestWindowStop - bestWindowStart}")
}

private fun task5() {
    val numOfRows = readln().toInt()
    val numOfCols = readln().toInt()

    val rng = ThreadLocalRandom.current()
    val matrix = Array(numOfRows) { IntArray(numOfCols) { rng.nextInt(1, 10) } }
    val maxs = matrix.map { it.max() }

    println("Matrix: ${matrix.contentDeepToString()}")
    println("Maxs: $maxs")
}

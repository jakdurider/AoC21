package day02

import java.io.File

fun main() {
    val testInput = File("src/day02/Day02.txt").readLines()
    fun part1(): Int {

        val result = testInput.fold(Pair(0,0)) {
            acc, cmd ->
            val each_cmd = cmd.split(" ")
            val num = each_cmd[1].toInt()
            val new_pair =
                when(each_cmd[0]) {
                    "forward" -> Pair(acc.first + num, acc.second)
                    "down" -> Pair(acc.first, acc.second + num)
                    "up" -> Pair(acc.first, acc.second - num)
                    else -> Pair(acc.first, acc.second)
                }
            new_pair
        }
        return result.first * result.second
    }

    fun part2(): Int {
        val result = testInput.fold(Triple(0,0, 0)) {
                acc, cmd ->
            val each_cmd = cmd.split(" ")
            val num = each_cmd[1].toInt()
            val new_triple =
                when(each_cmd[0]) {
                    "forward" -> Triple(acc.first + num, acc.second + acc.third * num, acc.third)
                    "down" -> Triple(acc.first, acc.second, acc.third + num)
                    "up" -> Triple(acc.first, acc.second, acc.third - num)
                    else -> Triple(acc.first, acc.second, acc.third)
                }
            new_triple
        }
        return result.first * result.second
    }

    println(part1())
    println(part2())
}

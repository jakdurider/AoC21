package day11

import java.io.File

class octopus(
    var num: Int,
    var flashed: Boolean
)
fun main() {
    val octopuses = File("src/day11/day11.txt").readLines().asSequence().map{it.toCharArray().asSequence().map{it2->octopus(Character.getNumericValue(it2), false)}.toMutableList()}.toList()
    val pairs = listOf(Pair(0,1), Pair(0,-1), Pair(1,0), Pair(-1,0), Pair(1,1), Pair(1,-1), Pair(-1,1), Pair(-1,-1))
    var total = 0
    fun flash(i: Int, j: Int){
        if(i in 0..9 && j in 0..9){
            ++octopuses[i][j].num
            if(octopuses[i][j].num >= 10 && !octopuses[i][j].flashed){
                ++total
                octopuses[i][j].flashed = true
                for(pair in pairs) flash(i+pair.first, j+pair.second)
            }
        }
    }
    fun reset(): Unit{
        for(i in 0..octopuses.lastIndex){
            for(j in 0..octopuses[i].lastIndex){
                if(octopuses[i][j].num >= 10){
                    octopuses[i][j].num = 0
                    octopuses[i][j].flashed = false
                }
            }
        }
    }
    fun part1(): Int {
        val steps = 100
        for(step in 0 until steps){
            for(i in 0..octopuses.lastIndex){
                for(j in 0..octopuses[i].lastIndex){
                    flash(i,j)
                }
            }
            reset()
        }
        return total
    }

    fun part2(): Int {
        var step = 0
        while(true){
            ++step
            for(i in 0..octopuses.lastIndex){
                for(j in 0..octopuses[i].lastIndex){
                    flash(i,j)
                }
            }
            if(octopuses.all{it.all{it2->it2.flashed}}) break
            reset()
        }
        return step
    }

    println(part1())
    println(part2())
}

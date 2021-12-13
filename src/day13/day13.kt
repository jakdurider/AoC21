package day13

import java.io.File


fun main() {
    val input = File("src/day13/day13.txt").readLines()
    var dots = mutableListOf<Pair<Int,Int>>()
    var folds = mutableListOf<Pair<Char,Int>>()
    for(str in input){
        if(str.isEmpty()) break
        val pair = str.split(",")
        dots += Pair(pair[0].toInt(),pair[1].toInt())
    }
    for(str in input){
        if(str.isNotEmpty() && str[0]=='f'){
            val split = str.split("=")
            folds += Pair(split[0][11], split[1].toInt())
        }
    }
    fun fold(line: Pair<Char, Int>){
        if(line.first == 'x'){
            for(i in 0..dots.lastIndex){
                if(dots[i].first > line.second) dots[i] = dots[i].copy(2*line.second - dots[i].first, dots[i].second)
            }
        }
        else if(line.first == 'y'){
            for(i in 0..dots.lastIndex){
                if(dots[i].second > line.second) dots[i] = dots[i].copy(dots[i].first, 2*line.second - dots[i].second)
            }
        }
        dots = dots.toSet().toMutableList()
    }
    fun part1(): Int {
        fold(folds[0])
        return dots.size
    }

    fun part2(): Int {
        for(inst in folds){
            fold(inst)
        }
        var matrix = mutableListOf<MutableList<Char>>()
        for(i in 0..49){
            matrix += MutableList<Char>(50,{'.'})
        }
        for(dot in dots){
            matrix[dot.second][dot.first] = '0'
        }
        for(i in 0..49){
            println(matrix[i])
        }
        return 1
    }

    println(part1())
    println(part2())
}

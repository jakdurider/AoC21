package day10

import java.io.File
import java.util.*

fun main() {
    val testInput = File("src/day10/day10.txt").readLines()
    fun part1(): Int {
        val result = testInput.fold(0){ acc, line->
            val stack = Stack<Char>()
            var total = 0
            for(i in 0..line.lastIndex){
                when(line[i]){
                    '(','{','[','<' -> stack.push(line[i])
                    ')' ->  {
                        if(stack.pop() != '('){
                            total = 3
                            break
                        }
                    }
                    ']' -> {
                        if(stack.pop() != '['){
                            total = 57
                            break
                        }
                    }
                    '}' -> {
                        if(stack.pop() != '{'){
                            total = 1197
                            break
                        }
                    }
                    '>' -> {
                        if(stack.pop() != '<'){
                            total = 25137
                            break
                        }
                    }
                }
            }
            acc + total
        }
        return result
    }

    fun part2(): Long {
        val list = mutableListOf<Long>()
        for(line in testInput){
            val stack = Stack<Char>()
            var total : Long = 0
            var incomplete = true
            for(i in 0..line.lastIndex){
                when(line[i]){
                    '(','{','[','<' -> stack.push(line[i])
                    ')' -> if(stack.pop() != '('){
                        incomplete = false
                        break
                    }
                    ']' -> if(stack.pop() != '[') {
                        incomplete = false
                        break
                    }
                    '}' -> if(stack.pop() != '{'){
                        incomplete = false
                        break
                    }
                    '>' -> if(stack.pop() != '<'){
                        incomplete = false
                        break
                    }
                }
            }
            if(incomplete){
                while(!stack.isEmpty()){
                    total *= 5
                    val char = stack.pop()
                    if(char == '(') total += 1
                    if(char == '[') total += 2
                    if(char == '{') total += 3
                    if(char == '<') total += 4
                }
                list += total
            }
        }
        return list.sorted()[(list.size)/2]
    }

    println(part1())
    println(part2())
}
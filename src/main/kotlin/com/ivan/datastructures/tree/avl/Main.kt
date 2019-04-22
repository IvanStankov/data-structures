package com.ivan.datastructures.tree.avl

import com.ivan.datastructures.tree.DigitsConsoleTreePrinter
import com.ivan.datastructures.tree.Tree
import com.ivan.datastructures.tree.TreePrinter

fun main(args: Array<String>) {

    val treePrinter: TreePrinter = DigitsConsoleTreePrinter()

    val avl: Tree<String, Int> = AvlTree()
    avl.insert("1", 1)
    avl.insert("2", 2)
    avl.insert("3", 3)
    treePrinter.print(avl)
    avl.insert("4", 4)
    treePrinter.print(avl)
    avl.insert("5", 5)
    avl.insert("6", 6)
//    avl.insert("7", 7)
//    avl.insert("9", 9)
//    avl.insert("8", 8)
//    avl.insert("99", 99)
//    avl.insert("88", 88)

    treePrinter.print(avl)
//    avl.remove("7")
//    avl.remove("3")
//    treePrinter.print(avl)
//
//    val avl2: Tree<String, Int> = AvlTree()
//    avl2.insert("one", 1)
//    avl2.insert("two", 2)
//    avl2.insert("three", 3)
//    avl2.insert("four", 4)
//    avl2.insert("five", 5)
//    avl2.insert("six", 6)
//    avl2.insert("seven", 7)
//    avl2.insert("eight", 8)
//    avl2.insert("nine", 9)
//    avl2.insert("ten", 10)

}
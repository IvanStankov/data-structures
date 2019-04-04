package com.ivan.datastructures.tree.bst

import com.ivan.datastructures.tree.DigitsConsoleTreePrinter
import com.ivan.datastructures.tree.Tree
import com.ivan.datastructures.tree.TreePrinter

fun main(args: Array<String>) {

    val treePrinter: TreePrinter = DigitsConsoleTreePrinter()

    val bst: Tree<String, Int> = BinarySearchTree()
    bst.insert("3", 3)
    bst.insert("1", 1)
    bst.insert("4", 4)
    bst.insert("2", 2)
    bst.insert("7", 7)
    bst.insert("9", 9)
    bst.insert("5", 5)
    bst.insert("8", 8)
    bst.insert("99", 99)
    bst.insert("88", 88)

    treePrinter.print(bst)
//    bst.remove("7")
    bst.remove("3")
    treePrinter.print(bst)

    val bst2: Tree<String, Int> = BinarySearchTree()
    bst2.insert("one", 1)
    bst2.insert("two", 2)
    bst2.insert("three", 3)
    bst2.insert("four", 4)
    bst2.insert("five", 5)
    bst2.insert("six", 6)
    bst2.insert("seven", 7)
    bst2.insert("eight", 8)
    bst2.insert("nine", 9)
    bst2.insert("ten", 10)
}
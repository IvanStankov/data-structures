package com.ivan.datastructures.tree.bst

import com.ivan.datastructures.tree.Tree
import kotlin.test.Test
import kotlin.test.assertEquals

internal class BinarySearchTree_Find_Test {

    val bst: Tree<Int, String> = BinarySearchTree()

    //      6
    //     / \
    //    /   \
    //   4     8
    //  / \   / \
    // 2   5 7   9
    init {
        bst.insert(6, "six")
        bst.insert(4, "four")
        bst.insert(8, "eight")
        bst.insert(2, "two")
        bst.insert(5, "five")
        bst.insert(7, "seven")
        bst.insert(9, "nine")
    }

    @Test
    fun find_SearchRoot_ShouldReturnRoot() {
        assertEquals("six", bst.find(6))
    }

    @Test
    fun find_SearchRootLeftChild_ShouldReturnRootLeftChild() {
        assertEquals("eight", bst.find(8))
    }

    @Test
    fun find_SearchRootRightChild_ShouldReturnRootRightChild() {
        assertEquals("four", bst.find(4))
    }

    @Test
    fun find_SearchLeftChildOfLeftChild_ShouldReturnLeftChildOfLeftChild() {
        assertEquals("two", bst.find(2))
    }

    @Test
    fun find_SearchRightChildOfLeftChild_ShouldReturnRightChildOfLeftChild() {
        assertEquals("five", bst.find(5))
    }

    @Test
    fun find_SearchLeftChildOfRightChild_ShouldReturnLeftChildOfRightChild() {
        assertEquals("seven", bst.find(7))
    }

    @Test
    fun find_SearchRightChildOfRightChild_ShouldReturnRightChildOfRightChild() {
        assertEquals("nine", bst.find(9))
    }

}
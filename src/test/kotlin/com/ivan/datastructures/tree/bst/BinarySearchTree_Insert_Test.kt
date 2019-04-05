package com.ivan.datastructures.tree.bst

import com.ivan.datastructures.tree.Entry
import com.ivan.datastructures.tree.Tree
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

internal class BinarySearchTree_Insert_Test {

    @Test
    fun insert_WhenTreeDoesNotHaveRoot_ShouldInsertNewRoot() {
        // Given
        val bst: Tree<Int, String> = BinarySearchTree()

        // When
        bst.insert(1, "one")

        // then
        assertNode(bst.root(), 1, "one")
    }

    // 2
    //  \
    //   3
    @Test
    fun insert_WhenOnlyRootAndInsertBiggerElem_ShouldInsertNewRightNode() {
        // Given
        val bst: Tree<Int, String> = BinarySearchTree()
        bst.insert(2, "two")

        // When
        bst.insert(3, "three")

        // then
        val root = bst.root()
        assertNode(root, 2, "two", parent = null, rightKey = 3, rightValue = "three")
        assertNode(root?.right, 3, "three", parent = root)
    }

    //   2
    //  /
    // 1
    @Test
    fun insert_WhenOnlyRootAndInsertSmallerElem_ShouldInsertNewLeftNode() {
        // Given
        val bst: Tree<Int, String> = BinarySearchTree()
        bst.insert(2, "two")

        // When
        bst.insert(1, "one")

        // then
        val root = bst.root()
        assertNode(root, 2, "two", parent = null, leftKey = 1, leftValue = "one")
        assertNode(root?.left, 1, "one", parent = root)
    }

    // 2          2
    //  \   ->   / \
    //   4      1   4
    @Test
    fun insert_WhenRootAndRightElemInsertSmallerElem_ShouldInsertNewLeftNodeForRoot() {
        // Given
        val bst: Tree<Int, String> = BinarySearchTree()
        bst.insert(2, "two")
        bst.insert(4, "four")

        // When
        bst.insert(1, "one")

        // then
        val root = bst.root()
        assertNode(root, 2, "two", null, 1, "one", 4, "four")
        assertNode(root?.left, 1, "one", parent = root)
        assertNode(root?.right, 4, "four", parent = root)
    }

    //   2        2
    //  /   ->   / \
    // 1        1   3
    @Test
    fun insert_WhenRootAndLeftElemInsertBiggerElem_ShouldInsertNewRightNodeForRoot() {
        // Given
        val bst: Tree<Int, String> = BinarySearchTree()
        bst.insert(2, "two")
        bst.insert(1, "one")

        // When
        bst.insert(3, "three")

        // then
        val root = bst.root()
        assertNode(root, 2, "two", null, 1, "one", 3, "three")
        assertNode(root?.left, 1, "one", parent = root)
        assertNode(root?.right, 3, "three", parent = root)
    }

    //   2        2
    //  / \  ->  / \
    // 1   4    1   4
    //             /
    //            3
    @Test
    fun insert_WhenRootWithBothChildrenAndInsertBiggerElemLessThenRightChildren_ShouldInsertLeftNodeForRightChild() {
        // Given
        val bst: Tree<Int, String> = BinarySearchTree()
        bst.insert(2, "two")
        bst.insert(1, "one")
        bst.insert(4, "four")

        // When
        bst.insert(3, "three")

        // then
        val root = bst.root()
        assertNode(root, 2, "two", null, 1, "one", 4, "four")
        assertNode(root?.left, 1, "one", parent = root)
        assertNode(root?.right, 4, "four", parent = root, leftKey = 3, leftValue = "three")
        assertNode(root?.right?.left, 3, "three", parent = root?.right)
    }

    //   2        2
    //  / \  ->  / \
    // 1   4    1   4
    //               \
    //                5
    @Test
    fun insert_WhenRootWithBothChildrenAndInsertBiggerElemBiggerThenRightChildren_ShouldInsertRightNodeForRightChild() {
        // Given
        val bst: Tree<Int, String> = BinarySearchTree()
        bst.insert(2, "two")
        bst.insert(1, "one")
        bst.insert(4, "four")

        // When
        bst.insert(5, "five")

        // then
        val root = bst.root()
        assertNode(root, 2, "two", null, 1, "one", 4, "four")
        assertNode(root?.left, 1, "one", parent = root)
        assertNode(root?.right, 4, "four", parent = root, rightKey = 5, rightValue = "five")
        assertNode(root?.right?.right, 5, "five", parent = root?.right)
    }

    //   6        6
    //  / \  ->  / \
    // 4   8    4   8
    //           \
    //            5
    @Test
    fun insert_WhenRootWithBothChildrenAndInsertSmallerElemBiggerThenLeftChildren_ShouldInsertRightNodeForLeftChild() {
        // Given
        val bst: Tree<Int, String> = BinarySearchTree()
        bst.insert(6, "six")
        bst.insert(4, "four")
        bst.insert(8, "eight")

        // When
        bst.insert(5, "five")

        // then
        val root = bst.root()
        assertNode(root, 6, "six", null, 4, "four", 8, "eight")
        assertNode(root?.left, 4, "four", parent = root, rightKey = 5, rightValue = "five")
        assertNode(root?.left?.right, 5, "five", parent = root?.left)
        assertNode(root?.right, 8, "eight", parent = root)
    }

    //   6        6
    //  / \  ->  / \
    // 4   8    4   8
    //         /
    //        2
    @Test
    fun insert_WhenRootWithBothChildrenAndInsertSmallerElemSmallerThenLeftChildren_ShouldInsertLeftNodeForLeftChild() {
        // Given
        val bst: Tree<Int, String> = BinarySearchTree()
        bst.insert(6, "six")
        bst.insert(4, "four")
        bst.insert(8, "eight")

        // When
        bst.insert(2, "two")

        // then
        val root = bst.root()
        assertNode(root, 6, "six", null, 4, "four", 8, "eight")
        assertNode(root?.left, 4, "four", parent = root, leftKey = 2, leftValue = "two")
        assertNode(root?.left?.left, 2, "two", parent = root?.left)
        assertNode(root?.right, 8, "eight", parent = root)
    }

    private fun assertNode(node: Entry<Int, String>?, key: Int, value: String, parent: Entry<Int, String>? = null,
                          leftKey: Int? = null, leftValue: String? = null, rightKey: Int? = null, rightValue: String? = null,
                           checkChild: Boolean = true) {
        assertNotNull(node)
        assertEquals(key, node.key)
        assertEquals(value, node.value)
        assertEquals(parent, node.parent)

        if (checkChild) {
            if (leftKey == null) {
                assertNull(node.left)
            } else {
                assertNode(node.left, leftKey, leftValue!!, node, checkChild = false)
            }

            if (rightKey == null) {
                assertNull(node.right)
            } else {
                assertNode(node.right, rightKey, rightValue!!, node, checkChild = false)
            }
        }

    }

}
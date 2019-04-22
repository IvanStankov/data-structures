package com.ivan.datastructures.tree.avl

import com.ivan.datastructures.tree.BaseBinaryTree
import com.ivan.datastructures.tree.Entry
import kotlin.math.absoluteValue

class AvlTree<K : Comparable<K>, V> : BaseBinaryTree<K, V>() {

    override fun createEntry(key: K, value: V): Entry<K, V> {
        return AvlEntry(key, value)
    }

    override fun insert(key: K, value: V) {
        val newNode = insertNode(key, value)

        // find first node which is out of balance
        var nodeToRotate: AvlEntry<K, V> = newNode as AvlEntry // node which is out of balance
        var balanceFactor = 0
        while (nodeToRotate.parent != null && balanceFactor.absoluteValue <= 1) {
            val parent = nodeToRotate.parent as AvlEntry
            when (nodeToRotate) {
                parent.left -> {
                    parent.leftSubtreeHeight++
                }
                parent.right -> {
                    parent.rightSubtreeHeight++
                }
            }
            balanceFactor = calcBalanceFactor(parent)
            nodeToRotate = parent
        }

        if (balanceFactor.absoluteValue <= 1) {
            return
        }

        when {
            balanceFactor > 1 -> {
                val right = nodeToRotate.right
                if (right != null && right.key > newNode.key) {
                    // contains kink
                    rotateRight(nodeToRotate.right as AvlEntry)
                }
                rotateLeft(nodeToRotate)
            }
            balanceFactor < -1 -> {
                val left = nodeToRotate.left
                if (left != null && left.key < newNode.key) {
                    // contains kink
                    rotateLeft(nodeToRotate.left as AvlEntry)
                }
                rotateRight(nodeToRotate)
            }
        }
        // TODO Ivan Stankov: update balances of nodes above nodeToRotate
    }

    override fun remove(key: K): V? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun calcBalanceFactor(node: AvlEntry<K, V>): Int {
        return node.rightSubtreeHeight - node.leftSubtreeHeight
    }

    private fun rotateLeft(nodeToRotate: AvlEntry<K, V>): AvlEntry<K, V> {
        val newParent = nodeToRotate.right as AvlEntry
        val oldLeftNodeOfNewParent = newParent.left as AvlEntry?
        val grandParent = nodeToRotate.parent

        if (grandParent != null) {
            if (grandParent.right == nodeToRotate) {
                grandParent.right = newParent
            } else {
                grandParent.left = newParent
            }
            newParent.parent = grandParent
        } else {
            // rotation on root
            root = newParent
            newParent.parent = null
        }

        nodeToRotate.right = oldLeftNodeOfNewParent
        oldLeftNodeOfNewParent?.parent = nodeToRotate

        newParent.left = nodeToRotate
        nodeToRotate.parent = newParent

        updateHeights(oldLeftNodeOfNewParent)
        updateHeights(nodeToRotate)
        updateHeights(newParent, 1)


        return newParent
    }

    private fun rotateRight(node: AvlEntry<K, V>): AvlEntry<K, V> {
        val a = node.left as AvlEntry<K, V>
        val y = a.right
        val parent = node.parent

        if (parent != null) {
            if (parent.right == node) {
                parent.right = a
            } else {
                parent.left = a
            }
            a.parent = parent
        } else {
            // rotation on root
            root = a
            a.parent = null
        }

        node.left = y
        y?.parent = node

        a.right = node
        node.parent = a

        return a
    }

    private fun updateHeights(node: AvlEntry<K, V>?, increment: Int = 0) {
        if (node == null) {
            return
        }
        node.leftSubtreeHeight = maxOf((node.left as AvlEntry?)?.leftSubtreeHeight ?: 0, (node.left as AvlEntry?)?.rightSubtreeHeight ?: 0) + increment
        node.rightSubtreeHeight = maxOf((node.right as AvlEntry?)?.leftSubtreeHeight ?: 0, (node.right as AvlEntry?)?.rightSubtreeHeight ?: 0) + increment
    }
}

class AvlEntry<K : Comparable<K>, V>(
    key: K,
    value: V,
    parent: AvlEntry<K, V>? = null,
    left: AvlEntry<K, V>? = null,
    right: AvlEntry<K, V>? = null,
    var leftSubtreeHeight: Int = 0,
    var rightSubtreeHeight: Int = 0
) : Entry<K, V>(
    key, value, parent, left, right
)
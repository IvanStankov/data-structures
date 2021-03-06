package com.ivan.datastructures.tree.avl

import com.ivan.datastructures.tree.*
import kotlin.math.absoluteValue

class AvlTree<K : Comparable<K>, V> : BaseBinaryTree<K, V>() {

    override fun createEntry(key: K, value: V): Entry<K, V> {
        return AvlEntry(key, value)
    }

    override fun insert(key: K, value: V) {
        val newNode = insertNode(key, value)
        balanceTree(newNode as AvlEntry<K, V>)
    }

    override fun remove(key: K): V? {
        val removedNode = removeNode(key) ?: return null
        // it checks for kinks, but we do not need it for deletion
        balanceTree(removedNode as AvlEntry<K, V>, false)
        return removedNode.value
    }

    private fun balanceTree(node: AvlEntry<K, V>, checkKinks: Boolean = true) {
        // find first node which is out of balance
        var nodeToRotate: AvlEntry<K, V> = node // node which is out of balance
        var balanceFactor = 0
        while (nodeToRotate.parent != null && balanceFactor.absoluteValue <= 1) {
            val parent = nodeToRotate.parent as AvlEntry
            updateHeights(parent)
            balanceFactor = calcBalanceFactor(parent)
            nodeToRotate = parent
        }

        if (balanceFactor.absoluteValue <= 1) return

        when {
            balanceFactor > 1 -> {
                val right = nodeToRotate.right
                if (checkKinks && right != null && right.key > node.key) {
                    // contains kink
                    rotateRight(nodeToRotate.right as AvlEntry)
                }
                rotateLeft(nodeToRotate)
            }
            balanceFactor < -1 -> {
                val left = nodeToRotate.left
                if (checkKinks && left != null && left.key < node.key) {
                    // contains kink
                    rotateLeft(nodeToRotate.left as AvlEntry)
                }
                rotateRight(nodeToRotate)
            }
        }
    }

    private fun calcBalanceFactor(node: AvlEntry<K, V>): Int {
        return node.rightSubtreeHeight - node.leftSubtreeHeight
    }

    private fun rotateLeft(nodeToRotate: AvlEntry<K, V>) {
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
            // rotation on the root
            root = newParent
            newParent.parent = null
        }

        nodeToRotate.right = oldLeftNodeOfNewParent
        oldLeftNodeOfNewParent?.parent = nodeToRotate

        newParent.left = nodeToRotate
        nodeToRotate.parent = newParent

        updateHeights(oldLeftNodeOfNewParent)
        updateHeights(nodeToRotate)
        updateHeights(newParent)
    }

    private fun rotateRight(nodeToRotate: AvlEntry<K, V>) {
        val newParent = nodeToRotate.left as AvlEntry<K, V>
        val oldRightNodeOfNewParent = newParent.right as AvlEntry?
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

        nodeToRotate.left = oldRightNodeOfNewParent
        oldRightNodeOfNewParent?.parent = nodeToRotate

        newParent.right = nodeToRotate
        nodeToRotate.parent = newParent

        updateHeights(oldRightNodeOfNewParent)
        updateHeights(nodeToRotate)
        updateHeights(newParent)
    }

    private fun updateHeights(node: AvlEntry<K, V>?) {
        if (node == null) return

        val leftIncrement = if (node.left != null) 1 else 0
        val rightIncrement = if (node.right != null) 1 else 0

        val leftNode = node.left as AvlEntry?
        val rightNode = node.right as AvlEntry?

        node.leftSubtreeHeight = maxOf(leftNode?.leftSubtreeHeight ?: 0, leftNode?.rightSubtreeHeight ?: 0) + leftIncrement
        node.rightSubtreeHeight = maxOf(rightNode?.leftSubtreeHeight ?: 0, rightNode?.rightSubtreeHeight ?: 0) + rightIncrement
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
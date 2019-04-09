package com.ivan.datastructures.tree.avl

import com.ivan.datastructures.tree.BaseBinaryTree
import com.ivan.datastructures.tree.Entry

class AvlTree<K : Comparable<K>, V> : BaseBinaryTree<K, V>() {

    override fun insert(key: K, value: V) {
        val newNode = insertNode(key, value)

        var node = newNode
        while (node.parent != null) {
            val parent = node.parent
            when (node) {
                parent?.left -> {

                }
                parent?.right -> {

                }
            }
        }
    }

    override fun remove(key: K): V? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class AvlEntry<K : Comparable<K>, V>(
    key: K,
    value: V,
    parent: AvlEntry<K, V>? = null,
    left: AvlEntry<K, V>? = null,
    right: AvlEntry<K, V>? = null,
    val leftSubtreeHeight: Int = 0,
    val rightSubtreeHeight: Int = 0
) : Entry<K, V>(
    key, value, parent, left, right
)
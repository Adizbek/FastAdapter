package com.mikepenz.fastadapter.expandable.items

import android.support.v7.widget.RecyclerView
import com.mikepenz.fastadapter.IExpandable
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.IParentItem
import com.mikepenz.fastadapter.ISubItem
import com.mikepenz.fastadapter.items.AbstractItem

abstract class AbstractExpandableItem<VH : RecyclerView.ViewHolder> :
        AbstractItem<VH>(),
        IItem<VH>,
        IExpandable<VH> {
    override var isExpanded: Boolean = false
    override var parent: IParentItem<*>? = null
    private var _subItems: MutableList<out ISubItem<*>>? = null
    override var subItems: MutableList<out ISubItem<*>>?
        set(value) {
            _subItems = value
            _subItems?.let { subItems ->
                for (item in subItems) {
                    item.parent = this
                }
            }
        }
        get() {
            return _subItems
        }
    override val isAutoExpanding: Boolean = true
    override var isSelectable: Boolean
        get() = _subItems.isNullOrEmpty()
        set(value) {}
}

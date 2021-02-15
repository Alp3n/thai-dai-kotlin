package com.example.thaidai.backend.domain.order

import android.content.Context
import io.paperdb.Paper

class OrderListDAO {

    companion object {
        fun addItem(orderLine: OrderLine) {
            val orderList = OrderListDAO.getOrder()

            val targetItem = orderList.singleOrNull {it.item.id == orderLine.item.id}
            if(targetItem == null) {
                orderLine.quantity++
                orderList.add(orderLine)
            } else {
                targetItem.quantity++
            }
            OrderListDAO.saveOrder(orderList)
        }


        fun removeItem(orderLine: OrderLine, context: Context) {
            val orderList = OrderListDAO.getOrder()

            val targetItem = orderList.singleOrNull {it.item.id == orderLine.item.id}
                if(targetItem != null) {
                    if (targetItem.quantity > 0) {
                        targetItem.quantity--
                    } else {
                        orderList.remove(targetItem)
                    }
                }
        }

        fun saveOrder(order: MutableList<OrderLine>) {
            Paper.book().write("order", order)
        }

        fun getOrder(): MutableList<OrderLine> {
            return Paper.book().read("order", mutableListOf())
        }

        fun clearOrder() {
            Paper.book().delete("order")
        }

        fun getShoppingCartSize(): Int {
            var cartSize = 0
            OrderListDAO.getOrder().forEach{
                cartSize += it.quantity
            }
            return cartSize
        }
    }
}
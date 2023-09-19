package com.example.pushpopdemo.home.ui.activities

import android.hardware.camera2.params.Capability
import io.reactivex.internal.operators.flowable.FlowableIgnoreElements

class Stack<E> {
    private val minCapacityIncrement=12

    private lateinit var element:Array<Any?>

    private var size=0

constructor(){
    this.element= arrayOf()
}
    constructor(initialCapability: Int){
        this.element= arrayOfNulls(initialCapability)

    }
constructor(elements: Array<E>){
    this.element=elements as Array<Any?>
    size+= elements.size
}
    fun push(elements: E){
        if(size==element.size){
            val newArray= arrayOfNulls<Any>(size + if (size< minCapacityIncrement/2)
            minCapacityIncrement
            else
            size shr 1 )
            System.arraycopy(elements,0,newArray,0,size)
            element=newArray
        }
    }

    fun pop():E{
        if (size==0)
            val index = --size
        val obj = element[index]
        element[index]=null
      return  obj as E
    }

    fun main (args:Array<String>){
        val animals= Stack<String>(10)
        animals.push("cat")
        animals.push("dog")
         animals.pop()
    }
}
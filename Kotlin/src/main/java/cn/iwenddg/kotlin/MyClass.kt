package cn.iwenddg.kotlin

import kotlinx.coroutines.*

/**
 * 协程
 * 如何创建一个CoroutineScope：
 * 1.launch(同步开启) 创建一个独立的 CoroutineScope,不返回数据
 * 2.async(异步开启) 创建一个独立的 CoroutineScope,需要返回数据
 * 3.runBlocking 在当前线程上创建一个协程域，这个执行会阻塞单签的线程
 * 4.GlobalScope 创建一个全局的协程域,不推荐，作用域为整个app的lifecycle,主线程结束不会等待GlobalScope的协程执行完毕
 *
 * 协程的CoroutineScope 和 CoroutineContext
 * CoroutineScope：使用async 和 launch时都是创建一个新的scope
 * CoroutineContext：和parent scope在同一个context中
 * 启动子协程时，子协程默认会继承除Job外的所有父协程上下文元素，创建新的Job，并将父Job设置为当前Job的父亲。
 *
 * @author iwen大大怪
 * @Create 2021/05/15 20:26
 */
data class User(var name: String)

fun main() {
    runBlocking {
        login()
    }
}

suspend fun login(): Int {
    return withContext(Dispatchers.IO) {
        println("登录开始")
        delay(1000)
        println("登录成功")
        1001
    }
}

suspend fun userInfo(id: Int): User {
    delay(1000)
    return User("jack")
}

suspend fun task1() {
    println("1")
    delay(1000)
    println("2")
}

suspend fun task2() {
    println("5")
    delay(1000)
    println("6")
}
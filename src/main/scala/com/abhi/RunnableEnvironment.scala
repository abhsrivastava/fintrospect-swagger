package com.abhi

object RunnableEnvironment extends App {
  new HelloServer().start()
  Thread.currentThread.join()
}
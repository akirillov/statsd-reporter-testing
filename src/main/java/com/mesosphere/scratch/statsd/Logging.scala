package com.mesosphere.scratch.statsd

trait Logging {
  def logWarning(msg: String, exception: Throwable): Unit = {
    log(s"[WARN] $msg", exception)
  }

  def logDebug(msg: String, exception: Throwable): Unit = {
    log(s"[DEBUG] $msg", exception)
  }


  def log(msg: String, exception: Throwable): Unit = {
    println(msg)
    if(exception != null) {
      exception.printStackTrace()
    }
  }
}

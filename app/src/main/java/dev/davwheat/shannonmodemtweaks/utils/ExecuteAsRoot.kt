package dev.davwheat.shannonmodemtweaks.utils

import timber.log.Timber
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader

object ExecuteAsRoot {
  fun hasRoot(): Boolean {
    val suProcess: Process?
    var retval: Boolean

    try {
      suProcess = Runtime.getRuntime().exec("su")
      val outStream = DataOutputStream(suProcess.outputStream)
      val inputStream = BufferedReader(InputStreamReader(suProcess.inputStream))

      // Getting the id of the current user to check if this is root
      outStream.writeBytes("id\n")
      outStream.flush()
      val currUid = inputStream.readLine()
      val exitSu: Boolean

      if (null == currUid) {
        retval = false
        exitSu = false
        Timber.d("Can't get root access or denied by user")
      } else if (currUid.contains("uid=0")) {
        retval = true
        exitSu = true
        Timber.d("Root access granted")
      } else {
        retval = false
        exitSu = true
        Timber.d("Root access rejected: $currUid")
      }

      if (exitSu) {
        outStream.writeBytes("exit\n")
        outStream.flush()
      }
    } catch (e: Exception) {
      // Can't get root!
      // Probably broken pipe exception on trying to write to output stream (os) after su failed,
      // meaning that the device is not rooted
      retval = false
      Timber.d("Root access rejected [" + e.javaClass.getName() + "] : " + e.message)
    }

    return retval
  }

  fun executeAsRoot(commands: List<String>): List<Pair<Int, String>?>? {
    val results = mutableListOf<Pair<Int, String>?>()

    if (!hasRoot()) {
      return null
    }

    for (currCommand in commands) {
      try {
        val suProcess = Runtime.getRuntime().exec("su")
        val os = DataOutputStream(suProcess.outputStream)
        val inputStream = BufferedReader(InputStreamReader(suProcess.inputStream))

        // Execute commands that require root access
        os.writeBytes(currCommand + "\n")
        os.flush()

        os.writeBytes("exit\n")
        os.flush()
        try {
          val exitVal = suProcess.waitFor()
          val data = inputStream.readText()
          results.add(Pair(exitVal, data))
        } catch (ex: java.lang.Exception) {
          Timber.e(ex, "Error executing root action")
        }
      } catch (ex: IOException) {
        Timber.w(ex, "Can't get root access")
      } catch (ex: SecurityException) {
        Timber.w(ex, "Can't get root access")
      } catch (ex: Exception) {
        Timber.w(ex, "Error executing internal operation")
      }
    }

    return results
  }
}

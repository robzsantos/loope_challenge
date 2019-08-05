package com.br.loopechallenge.extensions

import java.io.IOException
import java.io.Reader
import java.io.Writer

/**
 * Created by Robz on 25/03/2018.
 */
private const val BUFFER_SIZE: Int = 8192

@Throws(IOException::class)
fun Reader.copy(writer: Writer) {
    use {
        writer.use {
            var read: Int

            val buffer = CharArray(BUFFER_SIZE)

            read = read(buffer)

            while (read != -1) {
                writer.write(buffer, 0, read)

                read = read(buffer)
            }

            writer.flush()
        }
    }
}
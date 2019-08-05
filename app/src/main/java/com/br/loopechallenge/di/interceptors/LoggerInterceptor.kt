package com.br.loopechallenge.di.interceptors

import android.util.Log
import com.br.loopechallenge.BuildConfig
import com.br.loopechallenge.extensions.copy
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.util.zip.GZIPInputStream

/**
 * Created by Robz on 25/03/2018.
 */
class LoggerInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!BuildConfig.DEBUG) {
            return chain.proceed(chain.request())
        }
        try {
            val request = chain.request()

            val sb = StringBuilder()

            sb.append("Sending ")
                .append(request.method())
                .append(" Request: ")
                .append(request.url())
                .append("\n")

            sb.append(request.headers())

            val body = request.body()

            if (body != null) {
                val copy = request.newBuilder().build()

                val buffer = Buffer()

                copy.body().writeTo(buffer)

                sb.append(buffer.readUtf8())
            }

            Log.d(TAG, sb.toString())

            val t1 = System.currentTimeMillis()

            val response = chain.proceed(request)

            val t2 = System.currentTimeMillis()

            sb.setLength(0)

            Log.d(TAG, sb.toString())

            sb.setLength(0)

            sb.append("Received response for ")
                .append(response.request().url())
                .append("\n")
                .append("Status: ")
                .append(response.code())
                .append("\n")
                .append("Delay: ")
                .append(t2 - t1)
                .append(" ms")
                .append("\n")

            sb.append(response.headers())

            Log.d(TAG, sb.toString())

            val contentType = response.header("Content-Type")

            if (contentType != null && (contentType.contains("json") || contentType.contains("text"))) {
                val responseBody = response.body()

                responseBody.use { responseBody ->
                    val writer = StringWriter()

                    val contentEncoding = response.header("Content-Encoding")

                    val reader: Reader

                    reader = if (contentEncoding != null && with(contentEncoding) { trim { it <= ' ' }.isNotEmpty() } &&
                        contentEncoding.contains("gzip")
                    ) {
                        gzipReader(responseBody)
                    } else {
                        responseBody.charStream()
                    }

                    reader.copy(writer)

                    val content = writer.toString()

                    Log.d(TAG, format(content))

                    return response.newBuilder()
                        .removeHeader("Content-Encoding")
                        .body(ResponseBody.create(responseBody.contentType(), content))
                        .build()
                }
            }

            return response
        } catch (ex: IOException) {
            ex.printStackTrace()

            throw ex
        }

    }

    //As a network interceptor, we need to handle response compression
    @Throws(IOException::class)
    private fun gzipReader(responseBody: ResponseBody): Reader {
        val input = responseBody.byteStream()

        val gzipInput = GZIPInputStream(input)

        return InputStreamReader(gzipInput, "UTF-8")
    }

    private fun format(content: String): String {
        return try {
            if (content.startsWith("[")) JSONArray(content).toString(2)
            else JSONObject(content).toString(2) //Heavy memory usage
        } catch (ex: Exception) {
            content
        }

    }

    companion object {

        private val TAG = LoggerInterceptor::class.java.simpleName
    }
}
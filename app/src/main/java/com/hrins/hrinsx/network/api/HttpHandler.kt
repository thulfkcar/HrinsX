package com.hrins.hrinsx.network.api

import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.hrins.hrinsx.App
import com.hrins.hrinsx.R
import org.json.JSONException
import java.io.UnsupportedEncodingException
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets
import java.util.*


class HttpHandler(val app: App?) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(app)
    fun addRequest(
        dest: String,
        jsonBody: String?,
        type: Type?,
        requestType: Int,
        tag: String?,
        onCompleteListener: OnCompleteListener<*>
    ) {
        val url = EndPoint.BASE_URL + dest
        try {
            val retryPolicy: RetryPolicy =
                DefaultRetryPolicy(TIME_OUT, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            val resp = arrayOfNulls<String>(1)
            val stringRequest: StringRequest = object : StringRequest(
                requestType,
                url, Response.Listener { s: String ->
                    resp[0] = s
                    try {
                        println("HttpHandler download[]: $s")
                        onCompleteListener.onComplete(
                            GsonBuilder()
                                .serializeSpecialFloatingPointValues()
                                .create()
                                .fromJson(s, type)
                        )
                    } catch (e: JsonSyntaxException) {
                        onCompleteListener.onError(url + "\t" + e.toString())
                    } catch (e: JSONException) {
                        onCompleteListener.onError(url + "\t" + e.toString())
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error: VolleyError ->
                    parseVolleyError(error)
                    if (error.toString().trim().uppercase()
                            .contains("TimeOut".uppercase())
                    ) {
                        onCompleteListener.onError(app!!.getString(R.string.noConnection))

                    }
                    if (resp[0] != null)
                        onCompleteListener.onComplete(
                            GsonBuilder()
                                .serializeSpecialFloatingPointValues()
                                .serializeNulls()
                                .create()
                                .fromJson(resp[0], type)
                        )
                }) {
                override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }

                override fun parseNetworkError(volleyError: VolleyError): VolleyError {
                    return super.parseNetworkError(volleyError)
                }

                override fun getBody(): ByteArray {

                    return jsonBody?.toByteArray(StandardCharsets.UTF_8) ?: "".toByteArray()
                }

                override fun getHeaders(): Map<String, String> {
                    return object : HashMap<String, String>() {
                        init {
                            put("Content-Type", "application/json")
                            put("APP-KEY", EndPoint.App_Key)
                        }
                    }
                }

                override fun parseNetworkResponse(response: NetworkResponse): Response<String> {
                    var parsed: String
                    try {
                        parsed =
                            String(
                                response.data,
                                charset(HttpHeaderParser.parseCharset(response.headers))
                            )
                    } catch (e: UnsupportedEncodingException) {
                        parsed = String(response.data)
                        onCompleteListener.onError(parsed)
                    }
                    return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response))
                }


            }
            stringRequest.retryPolicy = retryPolicy
            stringRequest.tag = tag
            requestQueue.add(stringRequest)
        } catch (e: Exception) {
            onCompleteListener.onError(url + "\t" + e.toString())
        }
    }





    fun parseVolleyError(error: VolleyError) {
        var responseBody = ""
        try {
            if (error.networkResponse != null) responseBody =
                String(error.networkResponse.data, StandardCharsets.UTF_8)
            println(responseBody)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun removeRequest(tag: String?) {
        requestQueue.cancelAll(tag)
    }

    companion object {
        var TIME_OUT = 10000
    }


}
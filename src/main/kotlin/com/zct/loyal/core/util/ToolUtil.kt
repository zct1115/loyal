package com.zct.loyal.core.util

import java.io.IOException
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.reflect.Array
import java.math.BigDecimal
import java.net.URISyntaxException
import java.util.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder



object ToolUtil {

    init {

    }
    /**
     * 获取随机位数的字符串
     *
     */
    fun getRandomString(length: Int): String {
        val base = "abcdefghijklmnopqrstuvwxyz0123456789"
        val random = Random()
        val sb = StringBuffer()
        for (i in 0 until length) {
            val number = random.nextInt(base.length)
            sb.append(base[number])
        }
        return sb.toString()
    }


    /**
     * 获取异常的具体信息
     *
     */
    fun getExceptionMsg(e: Exception): String {
        val sw = StringWriter()
        try {
            e.printStackTrace(PrintWriter(sw))
        } finally {
            try {
                sw.close()
            } catch (e1: IOException) {
                e1.printStackTrace()
            }

        }
        return sw.buffer.toString().replace("\\$".toRegex(), "T")
    }

    /**
     * 比较两个对象是否相等。<br></br>
     * 相同的条件有两个，满足其一即可：<br></br>
     * 1. obj1 == null && obj2 == null; 2. obj1.equals(obj2)
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @return 是否相等
     */
    fun equals(obj1: Any?, obj2: Any?): Boolean {
        return if (obj1 != null) obj1 == obj2 else obj2 == null
    }

    /**
     * 计算对象长度，如果是字符串调用其length函数，集合类调用其size函数，数组调用其length属性，其他可遍历对象遍历计算长度
     *
     * @param obj 被计算长度的对象
     * @return 长度
     */
    fun length(obj: Any?): Int {
        if (obj == null) {
            return 0
        }
        if (obj is CharSequence) {
            return obj.length
        }
        if (obj is Collection<*>) {
            return obj.size
        }
        if (obj is Map<*, *>) {
            return obj.size
        }

        var count: Int
        if (obj is Iterator<*>) {
            val iter = obj as Iterator<*>?
            count = 0
            while (iter!!.hasNext()) {
                count++
                iter.next()
            }
            return count
        }
        if (obj is Enumeration<*>) {
            val enumeration = obj as Enumeration<*>?
            count = 0
            while (enumeration!!.hasMoreElements()) {
                count++
                enumeration.nextElement()
            }
            return count
        }
        return if (obj.javaClass.isArray == true) {
            Array.getLength(obj)
        } else -1
    }

    /**
     * 对象中是否包含元素
     *
     * @param obj     对象
     * @param element 元素
     * @return 是否包含
     */
    fun contains(obj: Any?, element: Any?): Boolean {
        if (obj == null) {
            return false
        }
        if (obj is String) {
            return if (element == null) {
                false
            } else obj.contains(element.toString())
        }
        if (obj is Collection<*>) {
            return obj.contains(element)
        }
        if (obj is Map<*, *>) {
            return obj.values.contains(element)
        }

        if (obj is Iterator<*>) {
            val iter = obj as Iterator<*>?
            while (iter!!.hasNext()) {
                val o = iter.next()
                if (equals(o, element)) {
                    return true
                }
            }
            return false
        }
        if (obj is Enumeration<*>) {
            val enumeration = obj as Enumeration<*>?
            while (enumeration!!.hasMoreElements()) {
                val o = enumeration.nextElement()
                if (equals(o, element)) {
                    return true
                }
            }
            return false
        }
        if (obj.javaClass.isArray == true) {
            val len = Array.getLength(obj)
            for (i in 0 until len) {
                val o = Array.get(obj, i)
                if (equals(o, element)) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 对象是否不为空(新增)
     *
     * @param o String,List,Map,Object[],int[],long[]
     * @return
     */
    fun isNotEmpty(o: Objects): Boolean {
        return !isEmpty(o)
    }

    /**
     * 符串是否不为空(新增)
     *
     * @param o String,List,Map,Object[],int[],long[]
     * @return
     */
    fun isNotEmpty(o: String?): Boolean {
        return !isEmpty(o)
    }

    /**
     * 对象是否为空
     *
     * @param o String,List,Map,Object[],int[],long[]
     * @return
     */
    fun isEmpty(o: Any?): Boolean {
        if (o == null) {
            return true
        }
        if (o is String) {
            if (o.toString().trim { it <= ' ' } == "") {
                return true
            }
        } else if (o is List<*>) {
            if (o.size == 0) {
                return true
            }
        } else if (o is Map<*, *>) {
            if (o.size == 0) {
                return true
            }
        } else if (o is Set<*>) {
            if (o.size == 0) {
                return true
            }
        } else if (o is IntArray) {
            if (o.size == 0) {
                return true
            }
        } else if (o is LongArray) {
            if (o.size == 0) {
                return true
            }
        }
        return false
    }

    /**
     * 对象组中是否存在 Empty Object
     *
     * @param os 对象组
     * @return
     */
    fun isOneEmpty(vararg os: Any): Boolean {
        for (o in os) {
            if (isEmpty(o)) {
                return true
            }
        }
        return false
    }

    /**
     * 对象组中是否全是 Empty Object
     *
     * @param os
     * @return
     */
    fun isAllEmpty(vararg os: Any): Boolean {
        for (o in os) {
            if (!isEmpty(o)) {
                return false
            }
        }
        return true
    }

    /**
     * 是否为数字
     *
     * @param obj
     * @return
     */
    fun isNum(obj: Any): Boolean {
        try {
            Integer.parseInt(obj.toString())
        } catch (e: Exception) {
            return false
        }

        return true
    }

    /**
     * 如果为空, 则调用默认值
     *
     * @param str
     * @return
     */
    operator fun getValue(str: Any, defaultValue: Any): Any {
        return if (isEmpty(str)) {
            defaultValue
        } else str
    }


    /**
     * 强转->string,并去掉多余空格
     *
     * @param str
     * @return
     */
    fun toStr(str: Any): String {
        return toStr(str, "")
    }

    /**
     * 强转->string,并去掉多余空格
     *
     * @param str
     * @param defaultValue
     * @return
     */
    fun toStr(str: Any?, defaultValue: String): String {
        return str?.toString()?.trim { it <= ' ' } ?: defaultValue
    }

    /**
     * 获取map中第一个数据值
     *
     * @param <K> Key的类型
     * @param <V> Value的类型
     * @param map 数据源
     * @return 返回的值
    </V></K> */
    fun <K, V> getFirstOrNull(map: Map<K, V>): V? {
        var obj: V? = null
        for ((_, value) in map) {
            obj = value
            if (obj != null) {
                break
            }
        }
        return obj
    }

    /**
     * 创建StringBuilder对象
     *
     * @return StringBuilder对象
     */
    fun builder(vararg strs: String): StringBuilder {
        val sb = StringBuilder()
        for (str in strs) {
            sb.append(str)
        }
        return sb
    }

    /**
     * 创建StringBuilder对象
     *
     * @return StringBuilder对象
     */
    fun builder(sb: StringBuilder, vararg strs: String) {
        for (str in strs) {
            sb.append(str)
        }
    }

    /**
     * 去掉指定后缀
     *
     * @param str    字符串
     * @param suffix 后缀
     * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
     */
    fun removeSuffix(str: String, suffix: String): String? {
        if (isEmpty(str) || isEmpty(suffix)) {
            return str
        }

        return if (str.endsWith(suffix)) {
            str.substring(0, str.length - suffix.length)
        } else str
    }


    /**
     * 判断是否是windows操作系统
     */
    fun isWinOs(): Boolean {
        val os = System.getProperty("os.name")
        return if (os.toLowerCase().startsWith("win")) {
            true
        } else {
            false
        }
    }

    /**
     * 获取临时目录
     */
    fun getTempPath(): String {
        return System.getProperty("java.io.tmpdir")
    }

    /**
     * 把一个数转化为int
     *
     */
    fun toInt(`val`: Any): Int? {
        if (`val` is Double) {
            val bigDecimal = BigDecimal(`val`)
            return bigDecimal.toInt()
        } else {
            return Integer.valueOf(`val`.toString())
        }

    }


}


package com.zct.loyal.config.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File


@Component
class LoyalProperties{
    @Value("\${loyal.file-upload-path}")
    private var fileUploadPath: String? = null

    private var haveCreatePath: Boolean? = false

    fun getFileUploadPath(): String? {
        //如果没有写文件上传路径,保存到临时目录
        if (fileUploadPath==null) {
            return System.getProperty("java.io.tmpdir")
        } else {
            //判断有没有结尾符,没有得加上
            if (!fileUploadPath!!.endsWith(File.separator)) {
                fileUploadPath = fileUploadPath + File.separator
            }
            //判断目录存不存在,不存在得加上
            if (!haveCreatePath!!) {
                val file = File(fileUploadPath)
                file.mkdirs()
                haveCreatePath = true
            }
            return fileUploadPath
        }
    }
}
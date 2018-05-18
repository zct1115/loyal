package com.zct.loyal.config.properties

import com.zct.loyal.core.util.ToolUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

import java.util.Properties

/**
 * beetl配置(如果需要配置别的配置可参照这个形式自己添加)
 */
@Configuration
@ConfigurationProperties(prefix = "beetl")
class BeetlProperties {

    var delimiterStatementStart: String? = null

    var delimiterStatementEnd: String? = null

    var resourceTagroot: String? = null

    var resourceTagsuffix: String? = null

    var resourceAutoCheck: String? = null

    @Value("\${spring.mvc.view.prefix}")
    val prefix: String? = null

    val properties: Properties
        get() {
            val properties = Properties()
            if (ToolUtil.isNotEmpty(delimiterStatementStart)) {
                if (delimiterStatementStart!!.startsWith("\\")) {
                    delimiterStatementStart = delimiterStatementStart!!.substring(1)
                }
                properties.setProperty("DELIMITER_STATEMENT_START", delimiterStatementStart)
            }
            if (ToolUtil.isNotEmpty(delimiterStatementEnd)) {
                properties.setProperty("DELIMITER_STATEMENT_END", delimiterStatementEnd)
            } else {
                properties.setProperty("DELIMITER_STATEMENT_END", "null")
            }
            if (ToolUtil.isNotEmpty(resourceTagroot)) {
                properties.setProperty("RESOURCE.tagRoot", resourceTagroot)
            }
            if (ToolUtil.isNotEmpty(resourceTagsuffix)) {
                properties.setProperty("RESOURCE.tagSuffix", resourceTagsuffix)
            }
            if (ToolUtil.isNotEmpty(resourceAutoCheck)) {
                properties.setProperty("RESOURCE.autoCheck", resourceAutoCheck)
            }
            return properties
        }

    companion object {

    }
}

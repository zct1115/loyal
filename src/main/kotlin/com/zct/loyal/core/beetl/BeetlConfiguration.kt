package com.zct.loyal.core.beetl

import com.zct.loyal.core.util.ToolUtil
import org.beetl.ext.spring.BeetlGroupUtilConfiguration

class BeetlConfiguration : BeetlGroupUtilConfiguration() {

    override fun initOther() {
        groupTemplate.registerFunctionPackage("tool", ToolUtil)
    }
}
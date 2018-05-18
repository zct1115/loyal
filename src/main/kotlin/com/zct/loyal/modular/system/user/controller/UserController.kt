package com.zct.loyal.modular.system.user.controller

import com.zct.loyal.config.properties.LoyalProperties
import com.zct.loyal.core.error.BizExceptionEnum
import com.zct.loyal.core.error.LoyalException
import com.zct.loyal.modular.system.user.model.Avatar
import com.zct.loyal.modular.system.user.model.User
import com.zct.loyal.modular.system.user.service.IUserService
import org.apache.commons.fileupload.FileItemFactory
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import org.apache.commons.fileupload.servlet.ServletFileUpload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import java.util.UUID
import java.io.FileOutputStream
import java.io.BufferedOutputStream
import java.io.BufferedInputStream
import jdk.nashorn.internal.codegen.ObjectClassGenerator.getFieldName
import org.apache.commons.fileupload.util.Streams
import java.util.Random
import java.text.SimpleDateFormat





/**
 * 管理员模块
 */
@Controller
class UserController {

    val PATH:String="system/user/"

    @Autowired
    val userService: IUserService? = null

    @Autowired
    val loyalProperties:LoyalProperties?=null

    @RequestMapping("/login")
    fun login(model: Model,requst:HttpServletRequest):String{
        if(requst.getAttribute("message")!=null){
            model.addAttribute("error",requst.getAttribute("message"))
        }else{
            model.addAttribute("error","")
        }
        return "login.html"
    }

    @RequestMapping("/")
    fun index(model: Model,httpSession: HttpSession):String{
        model.addAttribute("user",httpSession.getAttribute("user"))
        return "index.html"
    }

    @ResponseBody
    @GetMapping("/loginout")
    fun log(username:String):User{
        return userService!!.getByAccount(username)
    }

    @GetMapping("/avatar")
    fun avatar():String{
        return PATH+"avatar.html"
    }


    @GetMapping("/email")
    fun email():String{
        return PATH+"email.html"
    }

    @GetMapping("/upload")
    @ResponseBody
    fun upload(request: HttpServletRequest):Avatar{
        //设置图片为唯一的uuid

        var result=Avatar()
        val pictureName = UUID.randomUUID().toString() + ".jpg"
        if(request.contextPath.indexOf("multipart/form-data")>0){
            val factory = DiskFileItemFactory()
            val upload = ServletFileUpload(factory)
            val files = upload.getItemIterator(request)


            //定义一个变量用以储存当前头像的序号
            var avatarNumber = 1
            //取服务器时间+8位随机码作为部分文件名，确保文件名无重复。
            val simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmssS")
            var fileName = simpleDateFormat.format(Date())
            val random = Random()
            var randomCode = ""
            for (i in 0..7) {
                randomCode += Integer.toString(random.nextInt(36), 36)
            }
            fileName = fileName + randomCode
            //遍历所有文件域
            while (files.hasNext()) {
                val file = files.next()
                if (!file.isFormField) {
                    val fieldName = file.fieldName
                    var virtualPath = loyalProperties!!.getFileUploadPath() + avatarNumber + "_" + fileName + ".jpg"
                    //原始图片(file 域的名称：__source，如果客户端定义可以上传的话，可在此处理）。
                    if (fieldName == "__source") {
                        virtualPath = loyalProperties!!.getFileUploadPath()+"jsp_source_$fileName.jpg"
                        result.sourceUrl = virtualPath
                    } else {
                        result.avatarUrls!!.add(virtualPath)
                        avatarNumber++
                    }//头像图片(file 域的名称：__avatar1,2,3...)。
                    val inputStream = BufferedInputStream(file.openStream())
                    val outputStream = BufferedOutputStream(FileOutputStream(File(request.getRealPath(virtualPath))))
                    Streams.copy(inputStream, outputStream, true)
                    inputStream.close()
                    outputStream.close()
                }
            }
            result.success = true
            result.msg = "Success!"
        }

        return result
    }





}
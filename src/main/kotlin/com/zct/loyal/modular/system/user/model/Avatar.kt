package com.zct.loyal.modular.system.user.model

data class Avatar(var success:Boolean=false,var msg:String="failture",var sourceUrl:String?="",var avatarUrls:ArrayList<String>?=null)
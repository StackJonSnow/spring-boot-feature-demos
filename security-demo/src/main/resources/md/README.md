###概述
####认证流程
AuthenticationFilter->AuthenticationManager->AuthenticationProvider

####认证票据
使用Authentication在各个认证环节间流转

####自定义实现
Custom开头类，默认使用UserNamePasswordAuthenticationFilter做登录认证

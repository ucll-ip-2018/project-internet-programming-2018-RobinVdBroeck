package me.robinvdb.runetracker.web.config

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class WebInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {
    override fun getRootConfigClasses(): Array<Class<*>> {
        return arrayOf(ApplicationConfig::class.java, SecurityWebConfig::class.java)
    }

    override fun getServletConfigClasses(): Array<Class<*>> {
        return arrayOf(DispatcherServletConfig::class.java)
    }

    override fun getServletMappings(): Array<String> {
        return arrayOf("/")
    }
}

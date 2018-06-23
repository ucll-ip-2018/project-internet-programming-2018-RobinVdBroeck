package me.robinvdb.runetracker.web.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.*
import org.springframework.web.servlet.i18n.CookieLocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.resource.GzipResourceResolver
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver

import java.util.Locale

@Configuration
@EnableWebMvc
@ComponentScan("me.robinvdb.runetracker.web.controller")
class DispatcherServletConfig(@Autowired private val applicationContext: ApplicationContext) : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/").setViewName("index")
        registry.addViewController("index.html").setViewName("index")
        registry.addViewController("/admin").setViewName("admin")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/WEB-INF/css/")

        registry.addResourceHandler("js/**")
                .addResourceLocations("/WEB-INF/js/")
    }

    @Bean
    fun localeResolver(): LocaleResolver {
        val resolver = CookieLocaleResolver()
        resolver.cookieName = "runetracker_local"
        resolver.setDefaultLocale(Locale.ENGLISH)
        return resolver
    }

    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val localeChangeInterceptor = LocaleChangeInterceptor()
        localeChangeInterceptor.paramName = "lang"
        return localeChangeInterceptor
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }

    @Bean
    fun messageSource(): MessageSource {
        val source = ReloadableResourceBundleMessageSource()
        source.setBasename("classpath:messages")
        return source
    }

    @Bean
    fun templateResolver(): SpringResourceTemplateResolver {
        val templateResolver = SpringResourceTemplateResolver()
        templateResolver.setApplicationContext(applicationContext)
        templateResolver.prefix = "/WEB-INF/views/"
        templateResolver.suffix = ".html"
        return templateResolver
    }

    @Bean
    fun templateEngine(): SpringTemplateEngine {
        val templateEngine = SpringTemplateEngine()
        templateEngine.setTemplateResolver(templateResolver())
        templateEngine.enableSpringELCompiler = true
        return templateEngine
    }

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        val resolver = ThymeleafViewResolver()
        resolver.templateEngine = templateEngine()
        registry.viewResolver(resolver)
    }
}

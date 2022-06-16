package com.dunedin.sensorx.questionario.config;

import com.dunedin.sensorx.questionario.service.AlternativaServiceImpl;
import com.dunedin.sensorx.questionario.service.ControleServiceImpl;
import com.dunedin.sensorx.questionario.service.OpcaoServiceImpl;
import com.dunedin.sensorx.questionario.service.PerguntaServiceImpl;
import com.dunedin.sensorx.questionario.service.RegraPrologServiceImpl;
import com.dunedin.sensorx.questionario.service.RespostaServiceImpl;
import com.dunedin.sensorx.questionario.service.UsuarioServiceImpl;
import com.dunedin.sensorx.questionario.service.ScripteServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages= "com.dunedin.sensorx.questionario")
public class AppConfig implements WebMvcConfigurer {

	private static ApplicationContext applicationContext;

	@Bean
	public CommonsMultipartResolver multipartResolver() throws IOException {
	    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	    resolver.setMaxUploadSize(10000000);
	    return resolver;
	}

	@Bean
	public AlternativaServiceImpl alternativaService() throws IOException {
	    AlternativaServiceImpl service = new AlternativaServiceImpl();
	    return service;
	}

	@Bean
	public PerguntaServiceImpl perguntaService() throws IOException {
	    PerguntaServiceImpl service = new PerguntaServiceImpl();
	    return service;
	}

	@Bean
	public RespostaServiceImpl respostaService() throws IOException {
	    RespostaServiceImpl service = new RespostaServiceImpl();
	    return service;
	}

	@Bean
	public UsuarioServiceImpl usuarioService() throws IOException {
	    UsuarioServiceImpl service = new UsuarioServiceImpl();
	    return service;
	}
        
	@Bean
	public ControleServiceImpl controleService() throws IOException {
	    ControleServiceImpl service = new ControleServiceImpl();
	    return service;
	}

	@Bean
	public OpcaoServiceImpl opcaoService() throws IOException {
	    OpcaoServiceImpl service = new OpcaoServiceImpl();
	    return service;
	}

	@Bean
	public RegraPrologServiceImpl regraPrologService() throws IOException {
	    RegraPrologServiceImpl service = new RegraPrologServiceImpl();
	    return service;
	}

	@Bean
	public ScripteServiceImpl scripteService() throws IOException {
	    ScripteServiceImpl service = new ScripteServiceImpl();
	    return service;
	}
        
	@Bean
	public DataSource dataSource() throws IOException {
		
		/*
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://mysql.kinghost.com.br:3306/dragon04");
		ds.setUsername("dragon0404_add1");
		ds.setPassword("dragon04");
		*/

	    DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://207.244.248.55:3306/Questionario?useUnicode=yes&characterEncoding=UTF-8");
		ds.setUsername("dunedin");
		ds.setPassword("dunedin94");

		/*
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:~/test");
		ds.setUsername("sa");
		ds.setPassword("");
		*/

		return ds;

	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException{

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[]{"com.dunedin.sensorx.questionario.model"});

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}	

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

    Properties additionalProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		
		/*
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	    */
	    
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		
		properties.setProperty("hibernate.connection.CharSet", "UTF8");
		properties.setProperty("hibernate.connection.useUnicode", "true");
		properties.setProperty("hibernate.connection.characterEncoding", "UTF8");

		return properties;
	}

    /*----------------------------------------------------------------------
	METODOS HERDADOS, POUCOS IMPLEMENTADOS
    ------------------------------------------------------------------------*/
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){}

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	/*
        registry.addMapping("/**")
        		.allowedOrigins("*")
        		.allowedMethods("GET, POST, PUT, DELETE, OPTIONS, HEAD")
        		.allowedHeaders("origin, content-type, accept, authorization")
        		.allowCredentials(true);								  
    	*/
    }

    @Override
	public void addFormatters(FormatterRegistry registry){}

    @Override
	public void addInterceptors(InterceptorRegistry registry) {}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fotoscv/**")
          .addResourceLocations("/fotoscv/").setCachePeriod(0);
          //.resourceChain(true).addResolver(new PathResourceResolver());
    }

	@Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers){}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {}

	@Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){}

	@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer){}

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers){}

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){}

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){}

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){}

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers){}

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){}

    @Override
    public MessageCodesResolver getMessageCodesResolver(){
    	return null;
    }

    @Override
    public Validator getValidator(){
    	return null;
    }


    
	public static void main(String[] args) {

        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
 
    }
	
}

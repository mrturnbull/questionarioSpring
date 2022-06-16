package com.dunedin.sensorx.questionario.config;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;
 
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
 
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
    private int maxUploadSizeInMb = 1024 * 1024; // 1 MB
     
    private File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));
 
    @Override
    protected Class[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }
 
    @Override
    protected Class[] getServletConfigClasses() {
        return null;
    }
 
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] { characterEncodingFilter};
    }
     
    private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement = new 
            MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
        return multipartConfigElement;
    }
     
    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }
     
}
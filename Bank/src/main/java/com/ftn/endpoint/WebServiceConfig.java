package com.ftn.endpoint;

import com.ftn.exception.DetailSoapFaultDefinitionExceptionResolver;
import com.ftn.exception.ServiceFaultException;
import com.ftn.validation.ValidationInterceptor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Jasmina on 23/06/2017.
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public SoapFaultMappingExceptionResolver exceptionResolver(){

        final SoapFaultMappingExceptionResolver exceptionResolver = new DetailSoapFaultDefinitionExceptionResolver();

        final SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        exceptionResolver.setDefaultFault(faultDefinition);

        final Properties errorMappings = new Properties();
        errorMappings.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
        errorMappings.setProperty(ServiceFaultException.class.getName(), SoapFaultDefinition.SERVER.toString());
        exceptionResolver.setExceptionMappings(errorMappings);
        exceptionResolver.setOrder(1);
        return exceptionResolver;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        final ValidationInterceptor validationInterceptor = new ValidationInterceptor();
        final Map<String, XsdSchema> schemas = new HashMap<>();
        schemas.put(Mt102Endpoint.NAMESPACE_URI, mt102Schema());
        schemas.put(Mt103Endpoint.NAMESPACE_URI, mt103Schema());
        schemas.put(Mt900Endpoint.NAMESPACE_URI, mt900Schema());
        schemas.put(Mt910Endpoint.NAMESPACE_URI, mt910Schema());
        schemas.put(WarrantEndpoint.NAMESPACE_URI, warrantSchema());
        schemas.put(SectionEndpoint.NAMESPACE_URI, sectionSchema());
        schemas.put(InquiryEndpoint.NAMESPACE_URI, inquirySchema());
        validationInterceptor.setSchemas(schemas);
        interceptors.add(validationInterceptor);
    }

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }


    @Bean(name = "mt102")
    public DefaultWsdl11Definition Mt102SchemaDefinition(XsdSchema mt102Schema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("Mt102Port");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.ftn.com/mt102");
        wsdl11Definition.setSchema(mt102Schema);
        return wsdl11Definition;
    }


    @Bean
    public XsdSchema mt102Schema() {
        return new SimpleXsdSchema(new ClassPathResource("mt102.xsd"));
    }

    @Bean(name = "mt103")
    public DefaultWsdl11Definition Mt103SchemaDefinition(XsdSchema mt103Schema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("Mt103Port");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.ftn.com/mt103");
        wsdl11Definition.setSchema(mt103Schema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema mt103Schema() {
        return new SimpleXsdSchema(new ClassPathResource("mt103.xsd"));
    }

    @Bean(name = "mt900")
    public DefaultWsdl11Definition Mt900SchemaDefinition(XsdSchema mt900Schema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("Mt900Port");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.ftn.com/mt900");
        wsdl11Definition.setSchema(mt900Schema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema mt900Schema() {
        return new SimpleXsdSchema(new ClassPathResource("mt900.xsd"));
    }

    @Bean(name = "mt910")
    public DefaultWsdl11Definition Mt910SchemaDefinition(XsdSchema mt910Schema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("Mt910Port");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.ftn.com/mt910");
        wsdl11Definition.setSchema(mt910Schema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema mt910Schema() {
        return new SimpleXsdSchema(new ClassPathResource("mt910.xsd"));
    }

    @Bean(name = "warrant")
    public DefaultWsdl11Definition WarrantSchemaDefinition(XsdSchema warrantSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("WarrantPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.ftn.com/warrant");
        wsdl11Definition.setSchema(warrantSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema warrantSchema() {
        return new SimpleXsdSchema(new ClassPathResource("warrant.xsd"));
    }

    @Bean(name = "section")
    public DefaultWsdl11Definition SectionSchemaDefinition(XsdSchema sectionSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("SectionPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.ftn.com/section");
        wsdl11Definition.setSchema(sectionSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema sectionSchema() {
        return new SimpleXsdSchema(new ClassPathResource("section.xsd"));
    }

    @Bean(name = "statementAccountInquiry")
    public DefaultWsdl11Definition InquirySchemaDefinition(XsdSchema inquirySchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("InquiryPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://www.ftn.com/statementAccountInquiry");
        wsdl11Definition.setSchema(inquirySchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema inquirySchema() {
        return new SimpleXsdSchema(new ClassPathResource("statement_account_inquiry.xsd"));
    }


}

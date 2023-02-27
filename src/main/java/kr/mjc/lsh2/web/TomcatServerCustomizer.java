package kr.mjc.lsh2.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * 톰캣 커스터마이저.
 * HTML과 JSP의 기본 인코딩을 설정하고 welcome file을 등록한다.
 */
@Component
@Slf4j
public class TomcatServerCustomizer
        implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        Collection<TomcatContextCustomizer> col = new ArrayList<>();
        col.add((context) -> {
            // jspPropertyGroup
            JspPropertyGroup jspPropertyGroup = new JspPropertyGroup();
            jspPropertyGroup.addUrlPattern("*.jsp");
            jspPropertyGroup.setPageEncoding("UTF-8");

            // jspPropertyGroupDescriptors
            Collection<JspPropertyGroupDescriptor> jspPropertyGroupDescriptors =
                    new HashSet<>();
            jspPropertyGroupDescriptors.add(
                    new JspPropertyGroupDescriptorImpl(jspPropertyGroup));

            // jspConfigDescriptor
            context.setJspConfigDescriptor(
                    new JspConfigDescriptorImpl(jspPropertyGroupDescriptors,
                            new HashSet<>()));

            // add welcome file list
            context.addWelcomeFile("/index.html");
            context.addWelcomeFile("/index.jsp");
        });

        factory.setTomcatContextCustomizers(col);
        log.info("Tomcat customized");
    }
}
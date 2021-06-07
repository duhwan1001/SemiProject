package kr.or.ddit.listener;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kr.or.ddit.context.ApplicationContext;

public class ApplicationContextInitListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent ctxEvent) {
		ServletContext ctx = ctxEvent.getServletContext();

		String beanConfigXml = ctx.getInitParameter("contextConfigLocation");

		if (beanConfigXml == null)
			return;

		beanConfigXml = ctx.getRealPath("/")
				+ beanConfigXml.replace("classpath:", "WEB-INF/classes/").replace("/", File.separator);

		System.out.println(beanConfigXml);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(beanConfigXml);
			Element root = document.getDocumentElement();

			NodeList beans = root.getElementsByTagName("bean");
			Map<String, Object> applicationContext = ApplicationContext.getApplicationContext();

			for (int i = 0; i < beans.getLength(); i++) {
				System.out.println(beans.item(i).getNodeName());

				Node bean = beans.item(i);
				if (bean.getNodeType() == Node.ELEMENT_NODE) {
					Element ele = (Element) bean;
					String id = ele.getAttribute("id");
					String classType = ele.getAttribute("class");

					System.out.printf("id : %s, class = %s\n", id, classType);

					// map instance put
					Class<?> cls = Class.forName(classType);
					Object targetObj = cls.newInstance(); // single tone

					applicationContext.put(id, targetObj);

					// System.out.printf("id : " + id + "class : " + classType);

				}

			}

			for (int i = 0; i < beans.getLength(); i++) {
				Node bean = beans.item(i);
				if (bean.getNodeType() == Node.ELEMENT_NODE) {
					Element eleBean = (Element) bean;

					NodeList properties = bean.getChildNodes();
					for (int j = 0; j < properties.getLength(); j++) {
						Node property = properties.item(j);
						if (property.getNodeType() == Node.ELEMENT_NODE) {
							Element ele = (Element) property;
							String name = ele.getAttribute("name");
							String ref = ele.getAttribute("ref-value");

							System.out.printf("name = %s, ref-value = %s\n", name, ref);

							String setMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

							String className = eleBean.getAttribute("class");
							Class<?> classType = Class.forName(className);

							Method[] methods = classType.getMethods();

							for (Method method : methods) {
								// 의존성 여부 확인
								if (method.getName().equals(setMethodName)) {
									method.invoke(applicationContext.get(eleBean.getAttribute("id")),
											applicationContext.get(ref));

									System.out.println("[invoke]" + applicationContext.get(eleBean.getAttribute("id"))
											+ " : " + applicationContext.get(ref));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}
}

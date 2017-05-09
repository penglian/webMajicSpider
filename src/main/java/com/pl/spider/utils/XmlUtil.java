package com.pl.spider.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * xml工具
 * @author sean
 */
public final class XmlUtil
{
	/**
	 * 读取xml
	 * @param classpath
	 * @return
	 */
	public static Document readXml(String classpath) throws DocumentException, IOException
	{
		InputStream input = null;
		try
		{
			input = XmlUtil.class.getResourceAsStream(classpath);
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(input);
			return doc;
		}
		finally
		{
			if (input != null)
			{
				input.close();
			}
		}
	}

	/**
	 * 读取节点的数据
	 * @param doc
	 * @param xpath
	 * @param def
	 * @return
	 */
	public static String getStringValue(Document doc, String xpath, String def)
	{
		Element node = (Element) doc.selectSingleNode(xpath);
		if (node == null)
		{
			return def;
		}
		return node.getTextTrim();
	}

	public static int getIntValue(Document doc, String xpath, int def)
	{
		String val = getStringValue(doc, xpath, def + "");
		return Integer.parseInt(val);
	}

	public static boolean getBooleanValue(Document doc, String xpath, boolean def)
	{
		String val = getStringValue(doc, xpath, def + "");
		return Boolean.parseBoolean(val);
	}

	/**
	 * 读取节点属性
	 * @param doc
	 * @param xpath
	 * @param attr
	 * @param def
	 * @return
	 */
	public static String getStringAttr(Document doc, String xpath, String attr, String def)
	{
		Element node = (Element) doc.selectSingleNode(xpath);
		if (node == null || node.attributeValue(attr) == null)
		{
			return def;
		}
		return node.attributeValue(attr);
	}

	public static int getIntAttr(Document doc, String xpath, String attr, int def)
	{
		String val = getStringAttr(doc, xpath, attr, def + "");
		return Integer.parseInt(val);
	}

	public static boolean getBooleanAttr(Document doc, String xpath, String attr, boolean def)
	{
		String val = getStringAttr(doc, xpath, attr, def + "");
		return Boolean.parseBoolean(val);
	}

	/**
	 * 读取节点
	 * @param doc
	 * @param xpath
	 * @return
	 */
	public static Element getNode(Document doc, String xpath)
	{
		Element node = (Element) doc.selectSingleNode(xpath);
		return node;
	}

	/**
	 * 读取节点列表
	 * @param doc
	 * @param xpath
	 * @return
	 */
	public static List<Element> getNodes(Document doc, String xpath)
	{
		@SuppressWarnings("unchecked")
		List<Element> nodes = (List<Element>) doc.selectNodes(xpath);
		return nodes;
	}
}

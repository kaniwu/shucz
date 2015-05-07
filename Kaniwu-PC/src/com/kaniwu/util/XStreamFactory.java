package com.kaniwu.util;

import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;

public class XStreamFactory {
	protected static String PREFIX_CDATA = "<![CDATA[";
	protected static String SUFFIX_CDATA = "]]>";

	/**
	 * ��ʼ��XStream ��֧��ĳһ�ֶο��Լ���CDATA��ǩ �����Ҫĳһ�ֶ�ʹ��ԭ��
	 * ����Ҫ��String���͵�text��ͷ����"<![CDATA["�ͽ�β������"]]>"��ǩ�� �Թ�XStream���ʱ����ʶ��
	 * 
	 * @param isAddCDATA
	 *            �Ƿ�֧��CDATA��ǩ
	 * @return
	 */
	public static XStream init(boolean isAddCDATA) {
		XStream xstream = null;
		if (isAddCDATA) {
			xstream = new XStream(new DomDriver() {
				public HierarchicalStreamWriter createWriter(Writer out) {
					return new PrettyPrintWriter(out) {
						protected void writeText(QuickWriter writer, String text) {
							if (!text.startsWith(PREFIX_CDATA)) {
								text = PREFIX_CDATA + text + SUFFIX_CDATA;
							}
							writer.write(text);
						}
					};
				};
			});
		} else {
			xstream = new XStream(new DomDriver());
		}
		return xstream;
	}
}

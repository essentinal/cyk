package cyk.model.interfaces;

import org.jdom.Element;

public interface IXML {
	public Element toXml();

	public void parse(Element element);
}
